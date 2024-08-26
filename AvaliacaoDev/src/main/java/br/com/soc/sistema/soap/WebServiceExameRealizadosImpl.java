package br.com.soc.sistema.soap;

import javax.jws.WebService;

import br.com.soc.sistema.business.ExameRealizadoBusiness;

@WebService(endpointInterface = "br.com.soc.sistema.soap.WebServiceExameRealizados" )
public class WebServiceExameRealizadosImpl implements WebServiceExameRealizados {

	private ExameRealizadoBusiness business;
	
	public WebServiceExameRealizadosImpl() {
		this.business = new ExameRealizadoBusiness();
	}
	
	@Override
	public String buscarExameRealizado(String codigo) {		
		return business.buscarExameRealizadoPor(codigo).toString();
	}
}
