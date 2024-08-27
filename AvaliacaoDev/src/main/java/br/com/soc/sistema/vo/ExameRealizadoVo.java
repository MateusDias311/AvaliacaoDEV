package br.com.soc.sistema.vo;

public class ExameRealizadoVo {
	private String rowid;
	private String nome;	
	private String codigoExame; 
    private String nomeExame; 
    private String codFuncionario;  
    private String nomeFuncionario; 
    private String dataResultado;
	
	public ExameRealizadoVo() {}
		
	public ExameRealizadoVo(String rowid, String nome, String codigoExame, String nomeExame, String codFuncionario, String nomeFuncionario) {
		this.rowid = rowid;
		this.nome = nome;
		this.codigoExame = codigoExame;
        this.nomeExame = nomeExame;
        this.codFuncionario = codFuncionario;
        this.nomeFuncionario = nomeFuncionario;
	}
	
	public String getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(String codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
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
	
	public String getCodigoExame() {
        return codigoExame;
    }

    public void setCodigoExame(String codigoExame) {
        this.codigoExame = codigoExame;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

	public String getDataResultado() {
		return dataResultado;
	}

	public void setDataResultado(String dataResultado) {
		this.dataResultado = dataResultado;
	}
	
}
