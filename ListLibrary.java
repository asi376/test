/**
 * @author Andrew Si
 * @version 1.0
 * Last Modified: 2014-09-14
 */
import java.util.*;
public class ListLibrary
{
    private ArrayList<List> libraryList;
/*
 * listLibrary constructor
 */
    public ListLibrary()
    {
        libraryList = new ArrayList<List>();
    }
/*
 * returns one of the lists within
 * @param num selects the list to look at
 * @return returns the selected list
 */
    public List getList(int num)
    {
        return libraryList.get(num);
    }
/*
 * adds a list to the ListLibrary
 * @param name passes a name for the list
 * @param description passes a description for the list
 */
    public void addList(String name,String description)
    {
        libraryList.add(new List(name, description));
    }
/*
 * removes a list from the listLibrary
 * param num selects the list to be removed
 */
    public void removeList(int num)
    {
        libraryList.remove(num);
    }
/*
 * gets the size of the Library list
 * @return the size of the librarylist itself
 */
    public int size()
    {
        return libraryList.size();
    }
/*
 * adds a book to the reading list
 * @param num which list to add it to
 * @param newBook the book in which to add
 */
    public void addBookToReadingList(int num, Book newBook)
    {
        libraryList.get(num).addBook(newBook);
    }

}
