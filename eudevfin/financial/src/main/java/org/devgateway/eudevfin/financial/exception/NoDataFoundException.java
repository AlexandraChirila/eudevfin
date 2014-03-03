/**
 * 
 */
package org.devgateway.eudevfin.financial.exception;

/**
 * 
 * This will be the exception thrown in case a DAO object doesn't find any data in the database.
 * Returning NULL via spring integration could make the calling thread to wait forever (or for the timeout).
 * @author Alex
 * 
 * 
 */
public class NoDataFoundException extends RuntimeException {

	/**
	 * 
	 */
	public NoDataFoundException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public NoDataFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public NoDataFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NoDataFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NoDataFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
