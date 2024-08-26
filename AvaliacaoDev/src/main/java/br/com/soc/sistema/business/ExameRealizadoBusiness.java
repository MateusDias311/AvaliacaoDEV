package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.exameRealizados.ExameRealizadoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameRealizadoFilter;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExameRealizadoDao dao;
	
	
	public ExameRealizadoBusiness() {
		this.dao = new ExameRealizadoDao();
	}
	
	public List<ExameRealizadoVo> trazerTodosOsExameRealizados(){
		return dao.findAllExameRealizados();
	}	
	
	
	public List<ExameRealizadoVo> filtrarExameRealizados(ExameRealizadoFilter filter){
		List<ExameRealizadoVo> exameRealizados = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					exameRealizados.add(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case NOME:
				exameRealizados.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}
		
		return exameRealizados;
	}
	
	public ExameRealizadoVo buscarExameRealizadoPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	
	
	//**********EXCLUSÃO DE EXAMES REALIZADOS**********
	
	public boolean excluirExameRealizado(String rowid) {
	    if (rowid == null || rowid.isEmpty()) {
	        throw new IllegalArgumentException();
	    }

	    try {
	        int cod = Integer.parseInt(rowid);
	        return dao.deleteExameRealizado(cod);
	    } catch (NumberFormatException e) {
	        throw new BusinessException(e.getMessage());
	    }
	}
	
	
	//**********EDIÇÃO / INCLUSÃO DE EXAMES REALIZADOS**********

	
	public void salvarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
	    try {
	        if(exameRealizadoVo.getNome().isEmpty())
	            throw new IllegalArgumentException("Nome não pode ser em branco");
	        
	        if (exameRealizadoVo.getRowid() == null || exameRealizadoVo.getRowid().isEmpty()) {
	            dao.insertExameRealizado(exameRealizadoVo);
	        } else {
	            dao.updateExameRealizado(exameRealizadoVo); 
	        }
	    } catch (Exception e) {
	        throw new BusinessException("Não foi possível realizar a inclusão do registro");
	    }
	}
	
	public void atualizarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
	    if (exameRealizadoVo == null || exameRealizadoVo.getRowid() == null) {
	        throw new BusinessException("Exame Realizado inválido para atualização.");
	    }
	    dao.updateExameRealizado(exameRealizadoVo);
	}
	
	public ExameRealizadoVo editar(String rowid) {
        try {
            Integer cod = Integer.parseInt(rowid);
            return dao.findByCodigo(cod);
        } catch (NumberFormatException e) {
            throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
        }
    }
	
	

}