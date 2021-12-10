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
			
			for (int i = 0; i < 100; i++) {
				Member member = new Member();
				member.setUsername("member" + i);
				member.setAge(i);
				em.persist(member);
			}
			
			em.flush();
			em.clear();
						
			List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class) //꼭 order by
				.setFirstResult(1) //(0) : order by member0_.age desc limit ?
				//order by member0_.age desc limit ? offset ? : H2Dialect 방언. oracle 방언 -> rownum
				.setMaxResults(10)
				.getResultList();
			
			System.out.println("result.size = " + result.size());
			for (Member member1 : result) {
				System.out.println("member1 = " + member1);
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