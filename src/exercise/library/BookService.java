package exercise.library;

public interface BookService
{
    public Book retrieveBook(String isbn) throws BookNotFoundException, InvalidBookIsbnException;
    public String getBookSummary(String isbn) throws BookNotFoundException, InvalidBookIsbnException;
}
