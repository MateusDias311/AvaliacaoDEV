package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.exames.ExameDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.vo.ExameVo;

public class ExameBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExameDao dao;
	
	
	public ExameBusiness() {
		this.dao = new ExameDao();
	}
	
	public List<ExameVo> trazerTodosOsExames(){
		return dao.findAllExames();
	}	
	
	
	//FUNÇÃO ORIGINAL PARA SALVAR EXAMES
	/*
	public void salvarExame(ExameVo exameVo) {
		try {
			if(exameVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			dao.insertExame(exameVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}*/
	
	public List<ExameVo> filtrarExames(ExameFilter filter){
		List<ExameVo> exames = new ArrayList<>();
		
		 String valorBusca = filter.getValorBusca();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				if (valorBusca != null && valorBusca.matches("\\d+")) { 
	                try {
	                	Long codigo = Long.parseLong(valorBusca);
	                    exames.add(dao.findByCodigo(codigo));
	                } catch (NumberFormatException e) {
	                    throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
	                }
	            } else {
	                throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
	            }
				break;
				
			case NOME:
	            exames.addAll(dao.findAllByNome(valorBusca));
			break;
			
			default:
	            throw new BusinessException("Filtro inválido");
		}
		
		return exames;
	}
	
	public ExameVo buscarExamePor(String codigo) {
		try {
			Long cod = Long.parseLong(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	public ExameVo buscarExamePorId(String id) {
	    try {
	    	Long cod = Long.parseLong(id);
	        return dao.findByCodigo(cod);  
	    } catch (NumberFormatException e) {
	        throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO); 
	    }
	}
		
	
	//**********EXCLUSÃO DE EXAMES**********
	
	public boolean excluirExame(String rowid) {
	    if (rowid == null || rowid.isEmpty()) {
	        throw new IllegalArgumentException("O ID para exclusão não pode ser nulo ou vazio.");
	    }

	    try {
	        int cod = Integer.parseInt(rowid);
	        return dao.deleteExame(cod);
	    } catch (NumberFormatException e) {
	        throw new BusinessException("O ID fornecido para exclusão não é um número válido.");
	    }
	}
	
	
	//**********EDIÇÃO / INCLUSÃO DE EXAMES**********

	
	public void salvarExame(ExameVo exameVo) {
	    try {
	        if(exameVo.getNome().isEmpty())
	            throw new IllegalArgumentException("Nome não pode ser em branco");
	        
	        if (exameVo.getRowid() == null || exameVo.getRowid().isEmpty()) {
	            dao.insertExame(exameVo);
	        } else {
	            dao.updateExame(exameVo); 
	        }
	    } catch (Exception e) {
	        throw new BusinessException("Não foi possível realizar a inclusão do registro");
	    }
	}
	
	public void atualizarExame(ExameVo exameVo) {
	    if (exameVo == null || exameVo.getRowid() == null) {
	        throw new BusinessException("Exame inválido para atualização.");
	    }
	    dao.updateExame(exameVo);
	}
	

}
