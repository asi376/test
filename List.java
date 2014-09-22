/**
 * @author Andrew Si
 * @version 1.0
 * Last Modified: 2014-09-14
 */
import java.util.*;

public class List
{
    private String name;
    private String description;
    private ArrayList<Book> bookList;
    /*
     * constructor
     */
    public List(String name, String description)
    {
        this.name = name.trim();
        this.description = description.trim();
        bookList = new ArrayList<Book>();
    }

    /*
     * sets the name for the list
     * @param name list name
     */
    public void setName(String name)
    {
        this.name = name.trim();
    }

    /*
     * sets the description for the list
     * @param description sets info for list
     */
    public void setDescription(String description)
    {
        this.description = description.trim();
    }

    /*
     * gets the name for the list
     * @return list name
     */
    public String getName()
    {
        return name;
    }

    /*
     * gets the description for the list
     * @return list descripition
     */
    public String getDescription()
    {
        return description;
    }

    /*
     * adds a book to the list
     * @param title book name
     */
    public void addBook(Book title)
    {
        bookList.add(title);
    }

    /*
     * deletes a book from the list
     * @param title book name
     */
    public void deleteBook(String title)
    {
        for(int iterator = 0; iterator < bookList.size() ; iterator++)
        {
            if(bookList.get(iterator).getTitle().equals(title))
            {
                bookList.remove(iterator);   
            }
        }
    }

    /*
     * deletes the book from the list. just a different parameter
     * @param num the book number in the list
     */
    public void deleteBook(int num)
    {       
        bookList.remove(num);           
    }

    /*
     *gets the number of books
     * @returns the size of the List
     */
    public int size()
    {
        return bookList.size();
    }

    /*
     * prints the books in the list
     */
    public void print()
    {
        System.out.println(name + "\n");
        for(int iterator = 0; iterator < bookList.size() ; iterator++)
        {
            System.out.println((iterator+1) +". " + bookList.get(iterator).getTitle()+" by " +bookList.get(iterator).getAuthor());   
        }
    }
}
