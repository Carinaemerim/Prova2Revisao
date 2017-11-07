package br.edu.ifrs.canoas.jee.jpaapp.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TecnicoAdministrativo
 *
 */
@Entity

public class TecnicoAdministrativo extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public TecnicoAdministrativo() {
		super();
	}
   
}
