package exercise.library;

import java.util.HashMap;
import java.util.Map;

public class BookRepositoryImpl implements BookRepository {
	/**
	 * Singleton instance
	 */
	private static final BookRepository _repository = new BookRepositoryImpl();
	
	public static final String ISBN_PREFIX = "ISBN-";

    public static final String ISBN_DEATHLY_HALLOWS = ISBN_PREFIX + "001";
    public static final String ISBN_PLAYER_OF_GAMES = ISBN_PREFIX + "002";
    public static final String ISBN_GENIUS = ISBN_PREFIX + "003";

    private static final Map<String, Book> books;
    
    /**
     * Converted the object into a singleton class. Private constructor.
     */
    private BookRepositoryImpl() {}

    static {
        books = new HashMap<String, Book>();
        books.put(ISBN_DEATHLY_HALLOWS, new Book(
                ISBN_DEATHLY_HALLOWS,
                "Harry Potter and the Deathly Hallows",
                "Sorcery and Magic."));
        books.put(ISBN_PLAYER_OF_GAMES, new Book(
                ISBN_PLAYER_OF_GAMES,
                "The Player of Games",
                "Jernau Morat Gurgeh. The Player of Games. Master of every board, computer and strategy."));
        books.put(ISBN_GENIUS, new Book(
                ISBN_GENIUS,
                "Genius: Richard Feynman and Modern Physics",
                "A brilliant interweaving of Richard Feynman's colourful life and a detailed and accessible account of his theories and experiments."));
    }

    public Book retrieveBook(String isbn) {
        return books.get(isbn);
    }
    
    /**
     * Method to return the singleton instance.
     * 
     * @return An instance of the book repository.
     */
    public static BookRepository getInstance() {
    	return _repository;
    }
}
