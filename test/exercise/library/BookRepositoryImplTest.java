/**
 * 
 */
package exercise.library;

import static exercise.library.BookRepositoryImpl.ISBN_DEATHLY_HALLOWS;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Owner
 *
 */
public class BookRepositoryImplTest {

	private BookRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		repository = BookRepositoryImpl.getInstance();
	}
	
	/**
	 * Check for the singleton nature of the class
	 */
	@Test
	public void testGetInstanceShouldGiveOnlyOneInstance() {
		BookRepository repository2 = BookRepositoryImpl.getInstance();
		BookRepository repository3 = BookRepositoryImpl.getInstance();
		
		assertSame(repository, repository2);
		assertSame(repository, repository3);
		assertSame(repository2, repository3);
	}


	/**
	 * Test method for {@link exercise.library.BookRepositoryImpl#retrieveBook(java.lang.String)}.
	 */
	@Test
	public void testRetrieveBook_By_Valid_ISBN() {
		Book localBook = new Book(ISBN_DEATHLY_HALLOWS, "Harry Potter and the Deathly Hallows",
                "Sorcery and Magic.");
		Book book = repository.retrieveBook(ISBN_DEATHLY_HALLOWS);
		assertEquals(localBook, book);
	}

	@Test
	public void testRetrieveBook_By_Invalid_ISBN() {
		Book book = repository.retrieveBook("1234");
		assertEquals(null, book);
	}
}
