package exercise.library;

public class BookNotFoundException extends Exception
{

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 4237524941638091108L;
	
	/**
	 * Default constructor
	 */
	public BookNotFoundException() {}
	
	/**
	 * Constructor with a user friendly message
	 * @param message Message string
	 */
	public BookNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * @param message
	 * @param t
	 */
	public BookNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
