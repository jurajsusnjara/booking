package hr.fer.opp.dao;

/**
 * Exception that is thrown when some error occures during fetching data from
 * database.
 * 
 * @author Juraj
 *
 */

public class DAOException extends RuntimeException {

	/**
	 * Serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs exception from given message and cause.
	 * 
	 * @param message
	 *            given message
	 * @param cause
	 *            given cause
	 */

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs exception from given message.
	 * 
	 * @param message
	 *            given message
	 */

	public DAOException(String message) {
		super(message);
	}
}
