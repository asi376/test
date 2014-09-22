/**
 * @author Andrew Si
 * @version 1.0
 * Last Modified: 2014-09-14
 */
import java.util.*;

public class Book
{
    private String title;
    private String author; 
    private ArrayList<List> lists;
    /*
     * constructor
     */
    public Book(String title, String author)
    {
        this.title = title.trim();
        this.author = author.trim();
        this.lists = new ArrayList<List>();
    }

    /*
     * sets the book title
     * @param title the books name
     */
    public void setTitle( String title )
    {
        this.title = title.trim();
    }

    /*
     * sets the author of the book
     * @param author the authors name
     */
    public void setAuthor(String author)
    {
        this.author = author.trim();
    }

    /*
     * gets the title of the book
     * @return book title
     */
    public String getTitle()
    {
        return title;
    }

    /*
     * gets the author of the book
     * @return book author
     */
    public String getAuthor()
    {
        return author;
    }

    /*
     * adds a list for the book
     * @param alist the list to be added
     */
    public void addList(List aList)
    {
        lists.add(aList);
    }

    /*
     * removes a list for the book
     * @param alist the list to be removed
     */
    public void removeList(List aList)
    {
        lists.remove(aList);
    }

    /*
     * gets the number of lists the book is in
     * @return list size
     */
    public int listSize()
    {
        return lists.size();
    }

    /*
     * gets a list from the book
     * @param num the position of the list
     * @return returns the list at the postiion
     */

    public List getList( int num)
    {
        return lists.get(num);
    }

    /*
     * prints the lists a book is in
     */
    public String printList()
    {
        String print = "";
        for(int iterator = 0; iterator < lists.size(); iterator++)
        {
            print = print + lists.get(iterator).getName() + "\n" ;
        }
        return print;
    }
}