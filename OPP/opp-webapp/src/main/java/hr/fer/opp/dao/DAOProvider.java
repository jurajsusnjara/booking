package hr.fer.opp.dao;

import hr.fer.opp.dao.DAO;
import hr.fer.opp.dao.jpa.JPADAOImpl;

public class DAOProvider {

	/**
	 * Service provider for system for data persistency.
	 */
	private static DAO dao = new JPADAOImpl();

	/**
	 * Gets service provider.
	 * 
	 * @return service provider
	 */

	public static DAO getDAO() {
		return dao;
	}

}
