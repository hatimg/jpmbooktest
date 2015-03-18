package exercise.library;

import static exercise.library.BookRepositoryImpl.ISBN_PREFIX;;

/**
 * Implementation of the BookService interface. This class validates the ISBN for its format
 * and provides methods to retrieve the book information/summary.
 * 
 * @author hatimg
 *
 */
public class BookServiceImpl implements BookService {

	private BookRepository repository;
	
	/**
	 * Constructor
	 */
	public BookServiceImpl() {
		repository = BookRepositoryImpl.getInstance();
	}

	@Override
	public Book retrieveBook(String isbn) throws BookNotFoundException, InvalidBookIsbnException {
		// TODO Auto-generated method stub
		Book book = validateAndReturnBook(isbn);
		return book;
	}

	@Override
	public String getBookSummary(String isbn) throws BookNotFoundException, InvalidBookIsbnException {
		validateIsbn(isbn);
		Book book = validateAndReturnBook(isbn);
		return book.toString();
	}

	/**
	 * Method to validate the ISBN and then return the book if the ISBN exists in the repository.
	 * 
	 * @param isbn The ISBN
	 * @return The book corresponding to the ISBN
	 * @throws InvalidBookIsbnException exception thrown if the book ISBN is invalid
	 * @throws BookNotFoundException exception thrown if the book is not found
	 */
	private Book validateAndReturnBook(String isbn)	throws InvalidBookIsbnException, BookNotFoundException {
		// validate the ISBN
		validateIsbn(isbn);
		Book book = repository.retrieveBook(isbn);
		
		// Throw exception if the book is not found
		if (book == null) {
			throw new BookNotFoundException("No book found for the ISBN : " + isbn);
		}
		return book;
	}

	/**
	 * Validate the ISBN to check if it begins with the prefix "ISBN-".
	 * 
	 * @param isbn The ISBN
	 * @throws InvalidBookIsbnException Exception thrown if the ISBN is null or empty 
	 * 									or does not begin with "ISBN-"
	 */
	private void validateIsbn(String isbn) throws InvalidBookIsbnException {
		if (isbn == null || isbn.isEmpty())
			throw new InvalidBookIsbnException("Invalid ISBN provided [Empty or Null]");
		
		if (!isbn.startsWith(ISBN_PREFIX))
			throw new InvalidBookIsbnException("ISBN should start with the prefix " + ISBN_PREFIX);
	}

}
