/**
 * @author Andrew Si
 * @version 1.0
 * Last Modified: 2014-09-14
 * 
 * Most methods do not really have exception handling. some do, but they're very basic.
 * Reading files does not account for random strings. it does sort of catch ints and other data types, but it doesn't do anything
 * When saving a file, I can't get rid of some of the commas for list that books are in. This will probably cause issues reloading text files.
 * it cannot stop duplicates from being entered
 * There's probably a lot more things that I can't remember at the moment. Think of it as a horrible easter egg hunt!
 */
import java.util.*;
import java.io.*;
import userCommunication.*;

public class RLMS
{
    private UserInteraction comm;
    private Menu aMenu;
    private MenuOption choice;
    private BookLibrary aBookLibrary;
    private ListLibrary aListLibrary;
    /*
     * the constructor
     */
    public RLMS()
    {
        aBookLibrary = new BookLibrary();
        aListLibrary = new ListLibrary();
        comm = new UserInteraction();
        aMenu = new Menu(comm);

    }

    /*
     * this method runs the program
     */
    public void run()
    {
        boolean end = false;
        String fileName;

        fileName = comm.getInput_String("Please enter a file name: ");

        loadFile(fileName);
        setUpMenu();
        do{

            end = getChoice( getUserString() ); 
            aMenu.clearWhenMenuIsPrinted(true);

        }while(!end);

        printToFile(fileName);
        System.out.println("Have a good day!");

    }

    /*
     * this method sets up the menu
     */
    public void setUpMenu()
    {

        aMenu.addMenuOption("A","Add Book");
        aMenu.addMenuOption("R","Remove Book");
        aMenu.addMenuOption("AL","Add Reading List");
        aMenu.addMenuOption("RL","Remove Reading List");
        aMenu.addMenuOption("L","List All Books in a Reading List");
        aMenu.addMenuOption("AR","Add a Book to a Reading List");
        aMenu.addMenuOption("RR","Remove a Book to a Reading List");
        aMenu.addMenuOption("PL","Print Lists");
        aMenu.addMenuOption("PB","Print Books"); 
        aMenu.addMenuOption("Q","Quit");   
    }

    /*
     * this method gets the user input
     * @return returns the user choice
     */
    public String getUserString()
    {
        return aMenu.getUserChoice().getOptionLowerCase();
    }

    /*
     * determines which method to use based on user input
     * @param choice passes the user choice into the switch
     * @return returns the booleon end if user ends program
     */
    public boolean getChoice(String choice)
    {
        boolean end = false;
        switch(choice){
            case "a": addBook(); break;
            case "r": removeBook(); break;
            case "al": addReadingList(); break;
            case "rl": removeReadingList(); break;
            case "l": listBookInReadingList(); break;
            case "ar": addBookToReadingList();break;
            case "rr": removeBookFromReadingList(); break;
            case "pl": printLists();comm.pause(); break;
            case "pb": printBooks();comm.pause(); break;
            case "q": end = true; break;
            default:
        }
        return end;
    }

    /*
     * loads the file into program
     * param@ fileName passes the filename into the method.
     * p.s. UGLY CODE
     */
    public void loadFile(String fileName)
    {
        String tempLine;
        String[] tempReadingList = null;
        String[] tempBookInput = null;
        String[] tempBookList = null;
        String[] tempBook = null;
        String[] tempList = null;
        String delimiter = ",";
        Scanner inputFile = null;
        boolean check = false;
        int iterator = 0;
        int iterator2 = 0;
        List aList;
        Book aBook;
        int bookTracker = 0;
        //checks to see if file works
        try
        {
            inputFile = new Scanner(new File(fileName));
        }   
        catch(Exception ex)
        {
            System.out.println("\nSomethin ain't workin. New File will be created. \n");
        }

        if (inputFile != null) 
        {
            try
            {                

                //converts lists for program
                while(inputFile.hasNext() && check == false)
                {
                    tempLine = inputFile.nextLine();
                    if(!tempLine.equals("####"))
                    {
                        tempReadingList = tempLine.split(delimiter);
                        aListLibrary.addList(tempReadingList[0].toString(), tempReadingList[1].toString());
                    }
                    else
                    {
                        check = true;
                    }
                }

                //converts books for program
                while(inputFile.hasNext())
                {
                    delimiter = "#";

                    tempLine = inputFile.nextLine();
                    tempBookInput = tempLine.split(delimiter);

                    delimiter = ",";
                    tempBook = tempBookInput[0].split(delimiter);

                    aBookLibrary.addBook(new Book( tempBook[0].trim(), tempBook[1].trim()));
                    tempList = tempBookInput[1].trim().split(delimiter);

                    for( iterator = 0 ; iterator < tempList.length ; iterator++  )
                    {
                        for( iterator2 = 0 ; iterator2 < aListLibrary.size() ; iterator2++ )
                        {
                            if(aListLibrary.getList(iterator2).getName().equals(tempList[iterator]))
                            {
                                aBookLibrary.getBook(bookTracker).addList(aListLibrary.getList(iterator2));
                                aListLibrary.getList(iterator2).addBook(aBookLibrary.getBook(bookTracker));

                            }
                        }

                    }
                    bookTracker++;

                }
            }
            catch(Exception ex) {}
        }

    }

    /*
     * adds a book into the program
     */
    public void addBook()
    {
        String title = comm.getInput_String("What is the title?: ");
        String author = comm.getInput_String("Who is the author?: ");
        aBookLibrary.addBook(new Book(title, author));
    }

    /*
     * removes a book from the program
     */
    public void removeBook()
    {
        printBooks();
        try{
            int rem = comm.getInput_Int("Which book do you want to remove?: ");
            String bookTitle = aBookLibrary.getBook(rem - 1).getTitle();
            aBookLibrary.removeBook(rem - 1);
            for(int iterator = 0; iterator < aListLibrary.size() ; iterator++)
            {
                aListLibrary.getList(iterator).deleteBook(bookTitle);
            }
            printBooks();
            comm.println("Successful!");
            comm.pause();
        }
        catch (Exception huh)
        {

            System.out.println("you did something wrong, but I don't know what....but you did");
            comm.pause();
        }

    }

    /*
     * adds a reading list
     */
    public void addReadingList()
    {
        try
        {
            String name = comm.getInput_String("What is the list name?: ");
            String description = comm.getInput_String("Is there a description?: ");
            aListLibrary.addList(name, description);
        }
        catch (Exception huh)
        {
            comm.pause();
            System.out.println("you did something wrong, but I don't know what....but you did");
        }
    }

    /*
     * removes a reading list
     */
    public void removeReadingList()
    {
        printLists();
        int rem = comm.getInput_Int("Which list do you want to remove?: ");
        aListLibrary.removeList(rem - 1);
        printLists();
        comm.println("Successful!");
        comm.pause();
    }

    /*
     * lists the lists that a book is in
     */
    public void listBookInReadingList()
    {
        printLists();
        int listNum = comm.getInput_Int("Which list do you want to see?: ");
        aListLibrary.getList(listNum - 1).print();
        comm.pause();
    }
    /*
     * allows user to add books into a list
     */
    public void addBookToReadingList()
    {
        printBooks();
        int bookNum = comm.getInput_Int("Which book do you want to add?(number): ");
        printLists();
        int listNum = comm.getInput_Int("Which list do you want to add the book to?(number): ");
        aBookLibrary.getBook(bookNum - 1).addList(aListLibrary.getList(listNum - 1));
        aListLibrary.getList(listNum - 1).addBook(aBookLibrary.getBook(bookNum - 1));
        comm.println("Successful!");
        printLists();
        printBooks();
        comm.pause();

    }

    /*
     * removes a book user indicats from a list
     */
    public void removeBookFromReadingList()
    {
        printLists();
        int listNum = comm.getInput_Int("Which list do you want examine?(number): ");
        aListLibrary.getList(listNum - 1).print();
        int bookNum = comm.getInput_Int("Which book do you want to remove?(number): ");
        aListLibrary.getList(listNum - 1).deleteBook(bookNum -1);
        printLists();
        printBooks();
        comm.println("Successful!");
        comm.pause();

    }

    /*
     * prints out a table of lists
     */
    public void printBooks()
    {
        System.out.printf("%-4s%-40s%-40s%-40s\n","#", "| Book Title ", "| Author", "| ( #) Reading Lists");
        String testing = "";
        System.out.printf( "-------------------------------------------------------------------------------------------------------------------------------\n");
        for(int iterator = 0; iterator < aBookLibrary.size(); iterator++)
        {
            System.out.printf("%4s%-40s%-40s%-5s",(iterator + 1), "| " + aBookLibrary.getBook(iterator).getTitle(), "| " + aBookLibrary.getBook(iterator).getAuthor(), "| " + "( " + aBookLibrary.getBook(iterator).listSize()+ ") ");
            for (int iterator2 = 0 ; iterator2 < aBookLibrary.getBook(iterator).listSize() ; iterator2++)
            {
                System.out.print(aBookLibrary.getBook(iterator).getList(iterator2).getName());
                if(iterator2 <= aBookLibrary.getBook(iterator).listSize())
                {
                    System.out.print(", ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /*
     * prints out a table of books
     */
    public void printLists()
    {
        System.out.printf("%-4s%-40s%-40s%-40s\n","#", "| Reading List ", "| # of books in list", "| Description");
        String testing = "";
        System.out.printf( "-------------------------------------------------------------------------------------------------------------------------------\n");
        for(int iterator = 0; iterator < aListLibrary.size(); iterator++)
        {
            System.out.printf("%4s%-40s%-40s%-40s\n",(iterator + 1), "| " + aListLibrary.getList(iterator).getName(), "| " + aListLibrary.getList(iterator).size(), "| " + aListLibrary.getList(iterator).getDescription());    
        }
        System.out.print("\n");
    }

    /*
     * outputs data to a text file
     * @param fileName passes a filename to output data to
     */
    public void printToFile(String fileName)
    {
        File file = new File(fileName);
        Writer output = null;
        BufferedWriter bufferedOutput = null;
        try
        {
            output = new FileWriter(file);
            bufferedOutput = new BufferedWriter(output);
            for(int iterator = 0; iterator < aListLibrary.size(); iterator++)
            {
                bufferedOutput.write(aListLibrary.getList(iterator).getName() + "," + aListLibrary.getList(iterator).getDescription());
                bufferedOutput.newLine();
            }
            bufferedOutput.write("#### \n");
            bufferedOutput.newLine();
            for(int iterator = 0; iterator < aBookLibrary.size(); iterator++)
            {
                bufferedOutput.write(aBookLibrary.getBook(iterator).getTitle() + "," + aBookLibrary.getBook(iterator).getAuthor() + "#");
                for(int iterator2 = 0; iterator2 < aBookLibrary.getBook(iterator).listSize(); iterator2++)
                {
                    bufferedOutput.write(aBookLibrary.getBook(iterator).getList(iterator2).getName() + ", \n");
                }
                bufferedOutput.newLine();
            }
        }
        catch(IOException e)
        {}
        if(bufferedOutput != null && output != null)
        {
            try
            {
                bufferedOutput.close();
                output.close();
            }
            catch( Exception e)
            {
            }
        }

    }
}
