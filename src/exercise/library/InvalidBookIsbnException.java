package exercise.library;

public class InvalidBookIsbnException extends Exception
{

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 4237524941638091108L;
	
	/**
	 * Default constructor
	 */
	public InvalidBookIsbnException() {}
	
	/**
	 * Constructor with a user friendly message
	 * @param message Message string
	 */
	public InvalidBookIsbnException(String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * @param message
	 * @param t
	 */
	public InvalidBookIsbnException(String message, Throwable t) {
		super(message, t);
	}
}
