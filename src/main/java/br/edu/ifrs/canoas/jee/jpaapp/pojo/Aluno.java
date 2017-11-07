package br.edu.ifrs.canoas.jee.jpaapp.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity

public class Aluno extends Pessoa implements Serializable {
	@ManyToMany 
	private List<Turma> turmas = new ArrayList<>();
	
	public List<Turma> getTurma() {
		return turmas;
	}

	public void setTurma(List<Turma> turma) {
		this.turmas = turma;
	}

	private static final long serialVersionUID = 1L;

	private double indiceAproveitamentoCurso;
	
	public Aluno() {
		super();
	}

	public double getIndiceAproveitamentoCurso() {
		return indiceAproveitamentoCurso;
	}

	public void setIndiceAproveitamentoCurso(double indiceAproveitamentoCurso) {
		this.indiceAproveitamentoCurso = indiceAproveitamentoCurso;
	}
   
}
