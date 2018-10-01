package br.com.sistemaescolar.model;

public class Disciplina {
	Integer id;
	private String disciplina;
	private int codDisciplina;
	
	public Disciplina(Integer id,int codDisciplina,String disciplina) {
		this.id = id;
		this.disciplina = disciplina;
		this.codDisciplina = codDisciplina;
	}
	
	public Disciplina() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	
	public int getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(int codDisciplina) {
		this.codDisciplina = codDisciplina;
	}
}
