package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarExameRealizados;

public class ExameRealizadoFilter {
	private OpcoesComboBuscarExameRealizados opcoesCombo;
	private String valorBusca;

	public String getValorBusca() {
		return valorBusca;
	}

	public ExameRealizadoFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarExameRealizados getOpcoesCombo() {
		return opcoesCombo;
	}

	public ExameRealizadoFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarExameRealizados.buscarPor(codigo);
		return this;
	}	
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static ExameRealizadoFilter builder() {
		return new ExameRealizadoFilter();
	}
}