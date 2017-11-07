package br.edu.ifrs.canoas.jee.jpaapp.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity

public class Professor extends Pessoa implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@OneToMany 
	private List<Turma> t1 = new ArrayList<>();
	
	@OneToMany (mappedBy = "professor")
	private List<Turma> turma = new ArrayList<>();
	
	private Integer indiceTitutlacao;
	
	public Professor() {
		super();
	}

	public Integer getIndiceTitutlacao() {
		return indiceTitutlacao;
	}

	public void setIndiceTitutlacao(Integer indiceTitutlacao) {
		this.indiceTitutlacao = indiceTitutlacao;
	}
	
   
}
