package br.edu.ifrs.canoas.ifrs.jee.jpaapp.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.edu.ifrs.canoas.jee.jpaapp.pojo.Turma;
import br.edu.ifrs.canoas.jee.jpaapp.util.EntityManagerUtil;

public class TurmaDAO {

	EntityManager em;

	public List<Turma> lista(){
		
		List<Turma> t1 = new ArrayList<>();
		em = EntityManagerUtil.getEM();
		TypedQuery<Turma> query = em.createQuery("select t from Turma t", Turma.class);
		t1 = query.getResultList();
		
		return t1;
	}
	
	 public List<Turma> buscaPorDisciplina (Long idDisciplina){

		 List<Turma> t1 = new ArrayList<>();
		 em = EntityManagerUtil.getEM();	
			
		 if(idDisciplina == null){
				
				throw new NullPointerException("Não pode ser nulo");
		}			 
		
		TypedQuery<Turma> busca1 = em.createQuery("select t from Turma t where t.disciplina.id = :disciplina", Turma.class);
		busca1.setParameter("disciplina", idDisciplina);
		t1 = busca1.getResultList();
		em.close();
			
			return t1;
		}
		 
	 
	 public boolean edita (Turma turma){
		
		 if (turma == null){
				
				throw new NullPointerException ("Não pode ser nulo.");
				
			}
		    em = EntityManagerUtil.getEM();
			em.getTransaction().begin();
			em.merge(turma);
			em.getTransaction().commit();
			em.close();
			
			return true;
			
	}
		  
	 public boolean exclui (Turma turma){
		 
		 if (turma == null){
				
				throw new NullPointerException ("Não pode excluir objeto nulo.");
				
			}
			
			em = EntityManagerUtil.getEM();
			em.getTransaction().begin();
			em.remove(em.find(Turma.class, turma.getId()));
			em.getTransaction().commit();
			em.close();
			
			return true;
		}
	 
	 public void salva(Turma turma){
			
			if (turma == null){
				
				throw new NullPointerException ("Não pode ser nulo.");	
			}
			em = EntityManagerUtil.getEM();
			em.getTransaction().begin();
			em.persist(turma);
			em.getTransaction().commit();
			em.close();
			
		}	 
}
	
