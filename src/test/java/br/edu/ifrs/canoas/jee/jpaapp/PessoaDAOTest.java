package br.edu.ifrs.canoas.jee.jpaapp;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.ifrs.jee.jpaapp.DAO.PessoaDAO;
import br.edu.ifrs.canoas.jee.jpaapp.pojo.Professor;
import br.edu.ifrs.canoas.jee.jpaapp.util.EntityManagerUtil;
import static org.assertj.core.api.Assertions.*;

public class PessoaDAOTest {
 
	PessoaDAO pDAO = new PessoaDAO();
	
	@Before
	public void setup(){
		EntityManager em;
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.createQuery("delete from Pessoa").executeUpdate();
		em.getTransaction().commit();
		em.close();
		
	}
	
	@Test
	public void listaTest(){
		
		Professor p1 = criaPessoa();
		pDAO.salva(p1);
		assertThat(p1.getId()).isNotNull();
		
		Professor p2 = criaPessoa();
		p2.setMatricula("2244");
		pDAO.salva(p2);
		assertThat(p2.getId()).isNotNull();
		
		Professor p3 = criaPessoa();
		p3.setMatricula("5644");
		pDAO.salva(p3);
		assertThat(p3.getId()).isNotNull();
		
		assertThat(pDAO.lista().get(1).getMatricula()).as("2244");
		assertThat(pDAO.lista()).hasSize(3);

	}
	
	@Test
	public void buscarPorNomeTest(){
		
		Professor p1 = criaPessoa();
		pDAO.salva(p1);	
			assertThat(pDAO.buscarPorNome("Melissa").get(0).getNome()).isEqualTo("Melissa");
		
	}
	
	@Test(expected = NullPointerException.class)
	public void buscarNomeNullTest(){

		try {
			
			pDAO.buscarPorNome(null);
		}catch(NullPointerException e) {
			
			assertThat(e.getMessage()).as("O nome não pode ser nulo");
			throw e;	
			
		}		
	}
	@Test(expected = IllegalArgumentException.class)
	public void buscarNomeVazioTest(){
		
		try {
			
			pDAO.buscarPorNome("");
		}catch(IllegalArgumentException e) {
			
			assertThat(e.getMessage()).as("O nome precisa ser preenchido");
			throw e;	
			
		}
		
	}
	@Test
	public void buscaPorMatriculaTest(){
		
		Professor p1 = criaPessoa();
		pDAO.salva(p1);

		assertThat(pDAO.buscarPorMatricula("2254").get(0).getNome()).as("Melissa");
	}
	
	@Test(expected = NullPointerException.class)
	public void buscaPorMatriculaNullTest(){
		
		try {
			
			pDAO.buscarPorMatricula(null);
		}catch(NullPointerException e) {
			
			assertThat(e.getMessage()).as("Não pode ser nulo.");
			throw e;
			}
			
		}	
		
		@Test(expected = IllegalArgumentException.class)
		public void buscarPorMatriculaEmBrancoTest(){	

			try{
				
				pDAO.buscarPorMatricula("");
				
			}catch (IllegalArgumentException e){
				
				assertThat (e.getMessage()).as("Não pode ficar em branco.");
				throw e;
			}
	}

	@Test (expected = NullPointerException.class)
	public void editaPessoaNullTest() {
		
		try {
		pDAO.edita(null);
		} catch( NullPointerException e){
			
			assertEquals("Não pode ser nulo.", e.getMessage());
			throw e;
		}		
	}
	
	@Test
	public void editaPessoaTest(){
		Professor p1 = criaPessoa();
		pDAO.salva(p1);
		p1.setNome("Felipe");
		pDAO.edita(p1);
		
		assertEquals("Felipe", pDAO.lista().get(0).getNome());
		
	}
	
	@Test (expected = NullPointerException.class)
	public void excluiPessoaNullTest() {
		
		try {
		pDAO.exclui(null);
		} catch( NullPointerException e){
			
			assertEquals("Não pode excluir objeto nulo.", e.getMessage());
			throw e;
		}		
	}
	
	@Test
	public void excluiTest(){

		Professor p1 = criaPessoa();
        pDAO.salva(p1);
		assertThat(p1.getId()).isNotNull();
		pDAO.exclui(p1.getId());
		assertThat(pDAO.lista()).isEmpty();
		
	}
	@Test (expected = NullPointerException.class)
	public void salvaPessoaNullTest() {
		
		try {
		pDAO.salva(null);
		} catch( NullPointerException e){
			
			assertEquals("Não pode ser nulo.", e.getMessage());
			throw e;
		}		
	}
	
	@Test
	public void SalvaTest(){
		
		Professor prof1= criaPessoa();
		prof1.setIndiceTitutlacao(2);
		
		pDAO.salva(prof1);
		
		assertThat (prof1.getId()).isNotNull();
	}
	
	public Professor criaPessoa(){
		
		Professor p1 = new Professor();
		p1.setMatricula("2254");
		p1.setNome("Melissa");
		p1.setIndiceTitutlacao(2);
		
		return p1;
	}
	
	
}
