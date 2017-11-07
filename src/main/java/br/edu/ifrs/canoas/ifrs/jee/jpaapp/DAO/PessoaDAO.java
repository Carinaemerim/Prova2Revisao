package br.edu.ifrs.canoas.ifrs.jee.jpaapp.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.jpaapp.pojo.Pessoa;
import br.edu.ifrs.canoas.jee.jpaapp.util.EntityManagerUtil;

public class PessoaDAO {

	EntityManager em;
	
	public List<Pessoa> lista(){
		
		em = EntityManagerUtil.getEM();
		List<Pessoa> p1 = new ArrayList<Pessoa>();
		
		TypedQuery<Pessoa> busca = em.createQuery("select p from Pessoa p", Pessoa.class);
		p1 = busca.getResultList();
		em.close();
		
		return p1;
	}
	
	public List<Pessoa> buscarPorNome(String nome){
		
		em = EntityManagerUtil.getEM();
		List<Pessoa> p1 = new ArrayList<Pessoa>();
        if(nome == null){
			
			throw new NullPointerException("O nome não pode ser nulo");
		}
		
		if(nome.isEmpty()){
			
			throw new IllegalArgumentException("O nome precisa ser preenchido");
		}
		TypedQuery<Pessoa> busca1 = em.createQuery("select p from Pessoa p where p.nome = :nome", Pessoa.class);
		busca1.setParameter("nome", nome);
		p1 = busca1.getResultList();
		em.close();
		
		return p1;
	}
	
	public List<Pessoa> buscarPorMatricula(String matricula){
		
		if (matricula == null){
			
			throw new NullPointerException ("Não pode ser nulo.");
			
		}
		
		if (matricula.isEmpty()){
			
			throw new IllegalArgumentException ("Não pode ficar em branco.");
			
		}	
		
		em = EntityManagerUtil.getEM();
		List<Pessoa> p1 = new ArrayList<Pessoa>();
		
		TypedQuery<Pessoa> busca2 = em.createQuery("select p from Pessoa p where p.matricula = :matricula", Pessoa.class);
		busca2.setParameter("matricula", matricula);
		p1 = busca2.getResultList();
		em.close();
		
		return p1;
	}
	
	public void salva(Pessoa pessoa){
		
		if (pessoa == null){
			
			throw new NullPointerException ("Não pode ser nulo.");
			
		}
		
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(pessoa);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public boolean exclui(Long id){
        if (id == null){
			
			throw new NullPointerException ("Não pode excluir objeto nulo.");
			
		}
		
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.remove(em.find(Pessoa.class, id));
		em.getTransaction().commit();
		em.close();
		
		return true;
	}
	
	public boolean edita(Pessoa pessoa){
		
        if (pessoa == null){
			
			throw new NullPointerException ("Não pode ser nulo.");
			
		}
		
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.merge(pessoa);
		em.getTransaction().commit();
		em.close();
		
		return true;
		
	}
	
	
}
