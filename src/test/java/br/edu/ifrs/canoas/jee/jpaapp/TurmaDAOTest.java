package br.edu.ifrs.canoas.jee.jpaapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.ifrs.jee.jpaapp.DAO.TurmaDAO;
import br.edu.ifrs.canoas.jee.jpaapp.pojo.Turma;
import br.edu.ifrs.canoas.jee.jpaapp.util.EntityManagerUtil;

public class TurmaDAOTest {

	TurmaDAO tDAO = new TurmaDAO();
		
	@Before
	public void setup(){
		EntityManager em;
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.createQuery("delete from Turma").executeUpdate();
		em.getTransaction().commit();
		em.close();
		
	}
	 @Test
     public void listaTest(){
		
    	Turma t1 = criaTurma();
 		tDAO.salva(t1);
 		assertThat(t1.getId()).isNotNull();
 		
 		Turma t2 = criaTurma();
 		t2.setAno(2015);
 		tDAO.salva(t2);
 		assertThat(t2.getId()).isNotNull();
 		
 		Turma t3 = criaTurma();
 		tDAO.salva(t3);
 		assertThat(t3.getId()).isNotNull();
 		
 		assertThat(tDAO.lista()).hasSize(3);
	}
	 
	 @Test(expected = NullPointerException.class)
	 public void buscaPorDisciplinaNullTest(){
		 	
		 try {
				
		 tDAO.buscaPorDisciplina(null);
		 }catch(NullPointerException e) {
				
				assertThat(e.getMessage()).as("Não pode ser nulo");
				throw e;		
			}	
	 }
	 
	 @Test
	 public void buscaPorDisciplinaTest(){
	
			Turma t1 = criaTurma();
			tDAO.salva(t1);
			assertThat(t1.getId()).isNotNull();
			tDAO.buscaPorDisciplina(t1.getId());
					
			assertThat(tDAO.lista().get(0).getAno()).as("2016");
			}
	 
	   @Test (expected = NullPointerException.class)
	   public void excluiTurmaNullTest() {
		
			try {
				tDAO.exclui(null);
			} catch( NullPointerException e){
				
				assertEquals("Não pode excluir objeto nulo.", e.getMessage());
				throw e;
			}		
		}
	   
	   @Test
		public void excluiTest(){

			Turma t1 = criaTurma();
	        tDAO.salva(t1);
			assertThat(t1.getId()).isNotNull();
			tDAO.exclui(t1);
			assertThat(tDAO.lista()).hasSize(0);
			
		}
	   
	   @Test
	   public void editaTest(){
		   
		   Turma t1 = criaTurma();
		   tDAO.salva(t1);
		   t1.setAno(1991);
		   tDAO.edita(t1);
		   
		   assertThat(tDAO.lista().get(0).getAno()).isEqualTo(1991);
	   }
	   
	   @Test (expected = NullPointerException.class)
		public void editaTurmaNullTest() {
			
				try {
				tDAO.edita(null);
				} catch( NullPointerException e){
					
					assertEquals("Não pode ser nulo.", e.getMessage());
					throw e;
				}		
			}	
	   
	   public Turma criaTurma(){
		   Turma t1 = new Turma();
		   t1.setAno(2016);
		   
		   return t1;
	   }
		
	}
