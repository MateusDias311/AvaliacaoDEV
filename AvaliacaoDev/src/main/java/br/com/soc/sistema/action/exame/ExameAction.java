package br.com.soc.sistema.action.exame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExames;
import br.com.soc.sistema.vo.ExameVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

	
public class ExameAction extends Action {
	private List<ExameVo> exames = new ArrayList<>();
	private ExameBusiness business = new ExameBusiness();
	private ExameFilter filtrar = new ExameFilter();
	private ExameVo exameVo = new ExameVo();
	
	public String todosExames() {
		exames.addAll(business.trazerTodosOsExames());	

		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		exames = business.filtrarExames(filtrar);
		
		return SUCCESS;
	}
	
	public String novo() {
		if(exameVo.getNome() == null)
			return INPUT;
		
		business.salvarExame(exameVo);
		
		return REDIRECT;
	}	
	
	
//**********EXCLUSÃO DE EXAMES**********
	

	public String excluir() {
	    if (exameVo.getRowid() == null) {
	        return REDIRECT;
	    }
	    
	    try {
	        business.excluirExame(exameVo.getRowid());
	        exames.removeIf(exame -> exame.getRowid().equals(exameVo.getRowid()));
	        exames = business.trazerTodosOsExames(); 
	        
	        return SUCCESS;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ERROR;
	    }
	}
	    
	
	//**********EDIÇÃO DE EXAMES**********
	
	public String editarExame() {
		if(exameVo.getRowid() == null)
			return INPUT;
		
		exameVo = business.buscarExamePor(exameVo.getRowid());
		
		return INPUT;
	}
	    
	
	//**********FIM**********

	        
	public List<OpcoesComboBuscarExames> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarExames.values());
	}
	
	public List<ExameVo> getExames() {
		return exames;
	}

	public void setExames(List<ExameVo> exames) {
		this.exames = exames;
	}

	public ExameFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExameVo getExameVo() {
		return exameVo;
	}

	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
	}
}
