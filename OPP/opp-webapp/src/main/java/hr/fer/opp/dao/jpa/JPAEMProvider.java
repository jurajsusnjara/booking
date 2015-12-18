package hr.fer.opp.dao.jpa;

import hr.fer.opp.dao.DAOException;

import javax.persistence.EntityManager;

/**
 * Class for storing JPA entity manager's in <code>ThreadLocal</code> object.
 * <code>ThreadLocal</code> is map which key is thread id.
 * 
 * @author Juraj
 *
 */

public class JPAEMProvider {

	/**
	 * Map which key is thread id and value is entity manager.
	 */
	private static ThreadLocal<LocalData> locals = new ThreadLocal<>();

	/**
	 * Gets entity manager from locals map, initiates it to entity manager
	 * fetched from JPA enitity manager factory. After that entity manager's
	 * transaction begins and that entity manager is stored in locals map and
	 * returned.
	 * 
	 * @return entity manager
	 */

	public static EntityManager getEntityManager() {
		LocalData ldata = locals.get();
		if (ldata == null) {
			ldata = new LocalData();
			ldata.em = JPAEMFProvider.getEmf().createEntityManager();
			ldata.em.getTransaction().begin();
			locals.set(ldata);
		}
		return ldata.em;
	}

	/**
	 * Gets entity manager from locals map, commits its transaction, closes it
	 * and finally removes it form locals map.
	 * 
	 * @throws DAOException
	 *             if error occurs while commiting transaction or closing entity
	 *             manager
	 */

	public static void close() throws DAOException {
		LocalData ldata = locals.get();
		if (ldata == null) {
			return;
		}
		DAOException dex = null;
		try {
			ldata.em.getTransaction().commit();
		} catch (Exception ex) {
			dex = new DAOException("Unable to commit transaction.", ex);
		}
		try {
			ldata.em.close();
		} catch (Exception ex) {
			if (dex != null) {
				dex = new DAOException("Unable to close entity manager.", ex);
			}
		}
		locals.remove();
		if (dex != null)
			throw dex;
	}

	/**
	 * Class that wraps entity manager
	 * 
	 * @author Juraj
	 *
	 */

	private static class LocalData {
		EntityManager em;
	}

}
