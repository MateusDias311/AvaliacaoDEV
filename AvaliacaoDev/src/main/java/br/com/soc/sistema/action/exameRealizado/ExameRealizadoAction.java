package br.com.soc.sistema.action.exameRealizado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExameRealizadoBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.business.ExameBusiness;  

import br.com.soc.sistema.filter.ExameRealizadoFilter;
import br.com.soc.sistema.filter.FuncionarioFilter;

import br.com.soc.sistema.infra.OpcoesComboBuscarExameRealizados;
import br.com.soc.sistema.infra.OpcoesComboBuscarFuncionarios;
import br.com.soc.sistema.infra.Action;

import br.com.soc.sistema.vo.ExameRealizadoVo;
import br.com.soc.sistema.vo.FuncionarioVo;
import br.com.soc.sistema.vo.ExameVo;

import br.com.soc.sistema.dao.exameRealizados.ExameRealizadoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

	
public class ExameRealizadoAction extends Action {
	private List<ExameRealizadoVo> exameRealizados = new ArrayList<>();
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	
	private List<ExameVo> exames;
	
	private FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
	
	private ExameBusiness exameBusiness = new ExameBusiness();
	
	private ExameRealizadoBusiness business = new ExameRealizadoBusiness();
	private ExameRealizadoFilter filtrar = new ExameRealizadoFilter();
	private ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();
	private ExameRealizadoDao exameRealizadoDao = new ExameRealizadoDao();
	
	private String codigoExame;
	private String codigoFuncionario;
	
	public String todosExameRealizados() {
		funcionarios = funcionarioBusiness.trazerTodosOsFuncionarios();	
		exames = exameBusiness.trazerTodosOsExames();
		
		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		exameRealizados = business.filtrarExameRealizados(filtrar);
		
		return SUCCESS;
	}
	
	public String novo() {
		
	    if (exameRealizadoVo.getNome() == null) {
	    
	    	exames = exameBusiness.trazerTodosOsExames();
	    	funcionarios = funcionarioBusiness.trazerTodosOsFuncionarios();
	    	return INPUT;
	    
	    } else {
	    
	    	ExameVo exameSelecionado = exameBusiness.buscarExamePor(exameRealizadoVo.getCodigoExame());

	    	if (exameSelecionado != null) {
	            exameRealizadoVo.setNomeExame(exameSelecionado.getNome());
	            exameRealizadoVo.setCodigoExame(exameSelecionado.getRowid());
	        }
	    	
	    	FuncionarioVo funcionarioSelecionado = funcionarioBusiness.buscarFuncionarioPor(exameRealizadoVo.getCodFuncionario());
            if (funcionarioSelecionado != null) {
                exameRealizadoVo.setNomeFuncionario(funcionarioSelecionado.getNome());
                exameRealizadoVo.setCodFuncionario(funcionarioSelecionado.getRowid());
            }
	    	
	    	business.salvarExameRealizado(exameRealizadoVo);
	    	return REDIRECT;
	    
	    }
	}	
	
	
	public String salvar() {
	    if (exameRealizadoVo.getCodigoExame() != null && exameRealizadoVo.getCodFuncionario() != null) {
	        ExameVo exameSelecionado = exameBusiness.buscarExamePor(exameRealizadoVo.getCodigoExame());
	        
	        if (exameSelecionado != null) {
	            exameRealizadoVo.setNomeExame(exameSelecionado.getNome());
	            exameRealizadoVo.setCodigoExame(exameSelecionado.getRowid());
	        }
	        
	        FuncionarioVo funcionarioSelecionado = funcionarioBusiness.buscarFuncionarioPor(exameRealizadoVo.getCodFuncionario());
	        
	        if (funcionarioSelecionado != null) {
	            exameRealizadoVo.setNomeFuncionario(funcionarioSelecionado.getNome());
	            exameRealizadoVo.setCodFuncionario(funcionarioSelecionado.getRowid());
	        }

	        business.salvarExameRealizado(exameRealizadoVo);
	        
	        return REDIRECT;
	    }

	    exames = exameBusiness.trazerTodosOsExames();
	    funcionarios = funcionarioBusiness.trazerTodosOsFuncionarios();
	    return INPUT;
	}


//    private void salvarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
//        try {
//            exameRealizadoDao.insertExameRealizado(exameRealizadoVo);
//            System.out.println("Exame realizado salvo com sucesso!");
//        } catch (Exception e) {
//            System.err.println("Erro ao salvar o exame realizado: " + e.getMessage());
//        }
//    }
	
	
	
	
//**********EXCLUSÃO DE EXAMES REALIZADOS**********
	

	public String excluir() {
	    if (exameRealizadoVo.getRowid() == null) {
	        return REDIRECT;
	    }
	    
	    try {
	        business.excluirExameRealizado(exameRealizadoVo.getRowid());
	        exameRealizados.removeIf(exameRealizado -> exameRealizado.getRowid().equals(exameRealizadoVo.getRowid()));
	        exameRealizados = business.trazerTodosOsExameRealizados(); 
	        
	        return SUCCESS;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ERROR;
	    }
	}
	    
	
	//**********EDIÇÃO DE EXAMES REALIZADOS**********
	
	public String editar() {
		funcionarios = funcionarioBusiness.trazerTodosOsFuncionarios();	
		if(exameRealizadoVo.getRowid() == null)
			return REDIRECT;
		
		exameRealizadoVo = business.buscarExameRealizadoPor(exameRealizadoVo.getRowid());
		exames = exameBusiness.trazerTodosOsExames();
		
		return INPUT;
	}
	    
	
	//**********FIM**********

	        
	public List<OpcoesComboBuscarExameRealizados> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarExameRealizados.values());
	}
	
	public List<FuncionarioVo> getFuncionarios() {
        return funcionarios;
    }
	
	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
        this.funcionarios = funcionarios;
    }
	
    public List<ExameVo> getExames() { 
        return exames;
    }

    public void setExames(List<ExameVo> exames) {  
        this.exames = exames;
    }

	public ExameRealizadoFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameRealizadoFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExameRealizadoVo getExameRealizadoVo() {
		return exameRealizadoVo;
	}

	public void setExameRealizadoVo(ExameRealizadoVo exameRealizadoVo) {
		this.exameRealizadoVo = exameRealizadoVo;
	}

	public String getCodigoExame() {
		return codigoExame;
	}

	public void setCodigoExame(String codigoExame) {
		this.codigoExame = codigoExame;
	}

	public String getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(String codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}
}

