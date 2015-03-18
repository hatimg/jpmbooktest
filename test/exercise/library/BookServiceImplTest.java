package exercise.library;

import static exercise.library.BookRepositoryImpl.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.expectPrivate;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

import org.easymock.EasyMock;

/**
 * Test class for BookServiceImpl
 * 
 * @author hatimg
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({BookRepositoryImpl.class})
public class BookServiceImplTest {

	private BookService bookService;
	
	@Mock
	private BookRepository repository;
	
	/**
	 * Setup method
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bookService = new BookServiceImpl();
	}

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#BookServiceImpl()}.
	 * 
	 * Test to check if a non null object of the BookService is created
	 */
	@Test
	public void testBookServiceImpl_Constructor_NotNullObject() {
		assertNotNull(bookService);
	}

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#retrieveBook(java.lang.String)}.
	 * 
	 * Test the retrieveBook method for an empty isbn.
	 * 
	 * @throws Exception 
	 */
	@Test (expected = InvalidBookIsbnException.class)
	public void testRetrieveBook_By_EmptyString_Isbn() throws Exception {
		bookService.retrieveBook("");
	}

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#retrieveBook(java.lang.String)}.
	 * 
	 * Test the retrieveBook method for a null isbn
	 * @throws Exception 
	 */
	@Test (expected = InvalidBookIsbnException.class)
	public void testRetrieveBook_By_NullString_Isbn() throws Exception {
		bookService.retrieveBook(null);
	}

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#retrieveBook(java.lang.String)}.
	 * 
	 * Test the retrieveBook method for an invalid isbn (INVALID-TEXT)
	 * @throws Exception 
	 */
	@Test (expected = InvalidBookIsbnException.class)
	public void testRetrieveBook_By_Isbn_WithoutPrefix() throws Exception {
		bookService.retrieveBook("INVALID-TEXT");
	}

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#retrieveBook(java.lang.String)}.
	 * 
	 * Test the retrieveBook method for a valid isbn
	 * @throws Exception 
	 */
	@Test
	public void testRetrieveBook_By_Valid_Isbn() throws Exception {
		Book returnBook = new Book(ISBN_DEATHLY_HALLOWS, "Harry Potter and the Deathly Hallows",
                "Sorcery and Magic.");
        mockStatic(BookRepositoryImpl.class);
        BookRepository repository = createMock(BookRepositoryImpl.class);
        
        expectPrivate(repository, "getInstance").andReturn(repository).anyTimes();
        expectPrivate(repository, "retrieveBook", EasyMock.anyString()).andReturn(returnBook);
        
        replay(BookRepositoryImpl.class);
        replay(repository);
		

		Book book = bookService.retrieveBook(ISBN_DEATHLY_HALLOWS);
		assertEquals("Harry Potter and the Deathly Hallows", book.getTitle());
	}

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#retrieveBook(java.lang.String)}.
	 * 
	 * Test the retrieveBook method for a non existing valid isbn
	 * @throws Exception 
	 */
	@Test  (expected = BookNotFoundException.class)
	public void testRetrieveBook_By_Invalid_Isbn() throws Exception {
        mockStatic(BookRepositoryImpl.class);
        BookRepository repository = createMock(BookRepositoryImpl.class);
        
        expectPrivate(repository, "getInstance").andReturn(repository).anyTimes();
        expectPrivate(repository, "retrieveBook", EasyMock.anyString()).andReturn(null);
        
        replay(BookRepositoryImpl.class);
        replay(repository);
		

		bookService.retrieveBook("ISBN-777");
	}
	

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#getBookSummary(java.lang.String)}.
	 * 
	 * Test the getBookSummary method for an empty isbn
	 * @throws Exception 
	 */
	@Test (expected = InvalidBookIsbnException.class)
	public void testGetBookSummary_By_EmptyString_Isbn() throws Exception {

		bookService.getBookSummary("");
	}

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#getBookSummary(java.lang.String)}.
	 * 
	 * Test the getBookSummary method for a null isbn
	 * @throws Exception 
	 */
	@Test (expected = InvalidBookIsbnException.class)
	public void testGetBookSummary_By_NullString_Isbn() throws Exception {

		bookService.getBookSummary(null);
	}

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#getBookSummary(java.lang.String)}.
	 * 
	 * Test the getBookSummary method for an invalid isbn without the prefix (INVALID-TEXT)
	 * @throws Exception 
	 */
	@Test (expected = InvalidBookIsbnException.class)
	public void testGetBookSummary_By_Isbn_WithoutPrefix() throws Exception {

		bookService.getBookSummary("INVALID-TEXT");
	}

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#getBookSummary(java.lang.String)}.
	 * 
	 * Test the getBookSummary method for a valid isbn
	 * @throws Exception 
	 */
	@Test
	public void testGetBookSummary_By_Valid_Isbn_001() throws Exception {
		Book returnBook = new Book(ISBN_DEATHLY_HALLOWS, "Harry Potter and the Deathly Hallows",
                "Sorcery and Magic.");
        mockStatic(BookRepositoryImpl.class);
        BookRepository repository = createMock(BookRepositoryImpl.class);
        
        expectPrivate(repository, "getInstance").andReturn(repository).anyTimes();
        expectPrivate(repository, "retrieveBook", EasyMock.anyString()).andReturn(returnBook);
        
        replay(BookRepositoryImpl.class);
        replay(repository);
		

		String bookSummary = bookService.getBookSummary(ISBN_DEATHLY_HALLOWS);
		assertEquals("[ISBN-001] Harry Potter and the Deathly Hallows - Sorcery and Magic.", bookSummary);
	}

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#getBookSummary(java.lang.String)}.
	 * 
	 * Test the getBookSummary method for a valid isbn (ISBN-002)
	 * @throws Exception 
	 */
	@Test
	public void testGetBookSummary_By_Valid_Isbn_002() throws Exception {
		Book returnBook = new Book(ISBN_PLAYER_OF_GAMES, "The Player of Games",
				"Jernau Morat Gurgeh. The Player of Games. Master of every board, computer and strategy.");
        mockStatic(BookRepositoryImpl.class);
        BookRepository repository = createMock(BookRepositoryImpl.class);
        
        expectPrivate(repository, "getInstance").andReturn(repository).anyTimes();
        expectPrivate(repository, "retrieveBook", EasyMock.anyString()).andReturn(returnBook);
        
        replay(BookRepositoryImpl.class);
        replay(repository);
		

		String bookSummary = bookService.getBookSummary(ISBN_PLAYER_OF_GAMES);
		assertEquals("[ISBN-002] The Player of Games - Jernau Morat Gurgeh. The Player of Games. Master of every board, computer and strategy.", bookSummary);
	}

	/**
	 * Test method for {@link exercise.library.BookServiceImpl#getBookSummary(java.lang.String)}.
	 * 
	 * Test the getBookSummary method for an invalid isbn.
	 * @throws Exception 
	 */
	@Test  (expected = BookNotFoundException.class)
	public void testGetBookSummary_By_Invalid_Isbn() throws Exception {
        mockStatic(BookRepositoryImpl.class);
        BookRepository repository = createMock(BookRepositoryImpl.class);
        
        expectPrivate(repository, "getInstance").andReturn(repository).anyTimes();
        expectPrivate(repository, "retrieveBook", EasyMock.anyString()).andReturn(null);
        
        replay(BookRepositoryImpl.class);
        replay(repository);
		

		bookService.getBookSummary("ISBN-777");
	}

}
