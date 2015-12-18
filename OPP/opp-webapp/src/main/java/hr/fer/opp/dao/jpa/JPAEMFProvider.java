package hr.fer.opp.dao.jpa;

import javax.persistence.EntityManagerFactory;

/**
 * JPA entity manager factory provider.
 * 
 * @author Juraj
 *
 */

public class JPAEMFProvider {

	/**
	 * Entity manager factory.
	 */
	public static EntityManagerFactory emf;

	/**
	 * Gets entity manager factory.
	 * 
	 * @return entity manager factory
	 */

	public static EntityManagerFactory getEmf() {
		return emf;
	}

	/**
	 * Sets given entity manager factory.
	 * 
	 * @param emf
	 *            given entity manager factory
	 */

	public static void setEmf(EntityManagerFactory emf) {
		JPAEMFProvider.emf = emf;
	}
}
