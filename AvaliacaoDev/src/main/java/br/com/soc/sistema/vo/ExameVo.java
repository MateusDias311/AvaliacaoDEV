package br.com.soc.sistema.vo;

public class ExameVo {
	private String rowid;
	private String nome;	
	private Long codigoExame;
	
	public ExameVo() {}
		
	public ExameVo(String rowid, String nome, String codigoExame) {
		this.rowid = rowid;
		this.nome = nome;
		this.codigoExame = Long.parseLong(codigoExame);;
	}

	public String getRowid() {
		return rowid;
	}
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;		
	}

	public Long getCodigoExame() { 
		return codigoExame;
	}
	public void setCodigoExame(Long codigoExame) { 
		this.codigoExame = codigoExame;
	}
	
	public String getCodigoExameAsString() {
	    return String.valueOf(codigoExame);
	}

	public void setCodigoExameFromString(String codigoExame) {
	    this.codigoExame = Long.parseLong(codigoExame);
	}
	@Override
	public String toString() {
		return "ExameVo [rowid=" + rowid + ", nome=" + nome + "]";
	}
}
