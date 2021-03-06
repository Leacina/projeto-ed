package br.com.sistemaescolar.model;

public class Usuario implements Model {
	public static String ADMINISTRADOR = "Administrador";
	public static String CONVIDADO = "Convidado";
	
	private Integer id;
	private String senha;
	private String login;
	private String perfil;
	private boolean ultimoAdm = false;
	
	public Usuario() {
	}
	
	public Usuario(Integer id, String login, String senha, String perfil) {
		this.id = id;
		this.senha = senha;
		this.login = login;
		this.perfil = perfil;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public Boolean possuiPerfilConvidado() {
		return CONVIDADO.equals(getPerfil());
	}
	
	public Boolean possuiPerfilAdministrador() {
		return ADMINISTRADOR.equals(getPerfil());
	}

	public boolean getUltimoAdm() {
		return ultimoAdm;
	}

	public void setUltimoAdm(boolean ultimoAdm) {
		this.ultimoAdm = ultimoAdm;
	}
	
	
}
