/**
 * @author Andrew Si
 * @version 1.0
 * Last Modified: 2014-09-14
 */
import java.util.*;
public class BookLibrary
{
    private ArrayList<Book> libraryBook;
    /*
     * constructor
     */
    public BookLibrary()
    {
        libraryBook = new ArrayList<Book>();
    }

    /*
     * gives the number of books
     * @returns the number of books
     */
    public int size()
    {
        return libraryBook.size();
    }

    /*
     * gets a book from the BookLibrary
     * @param num the position of the book
     * @return the book in that position
     */
    public Book getBook(int num)
    {
        return libraryBook.get(num);
    }

    /*
     * adds a book to the library
     * @param aBook passes a book for the BookLibrary to store
     */
    public void addBook(Book aBook)
    {
        libraryBook.add(aBook);
    }

    /*
     * removes a book
     * @param selects the book to remove
     */
    public void removeBook(int num)
    {
        libraryBook.remove(num);
    }
}
