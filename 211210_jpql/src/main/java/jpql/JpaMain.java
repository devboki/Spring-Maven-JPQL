package jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	
	public static void main(String[] args) {	
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try { 
			
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("Member1");
			member.setAge(10);
			member.changeTeam(team);
			em.persist(member);
			
			Member member2 = new Member();
			member2.setUsername("Member2");
			em.persist(member2);
			
			em.flush();
			em.clear();
			
			String query = "select locate('de','abcdefg') From Member m"; //'abcdefg'에서 'de'는 몇번째?
			
			List<Integer> result = em.createQuery(query, Integer.class).getResultList();
			
			for (Integer s : result) {
				System.out.println("s = " + s);
			}
							
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		emf.close();
	}
}