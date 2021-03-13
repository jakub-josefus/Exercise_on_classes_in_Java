package knihovna;

import java.util.ArrayList;
import java.util.List;

public class Knihovna {

	public static void main(String[] args) {
        System.out.println("LIBRARY \n");
        Library Knihovna = new Library();
        Author JKRowling = new Author("J.K", "Rowling", 1965, 7, 31);
        Author JRRTolkien = new Author("J.R.R.", "Tolkien", 1892, 1, 3);
        Book HarryPotter1 = new Book(JKRowling, "Harry Potter a Kámen mudrcù", 336, 268,Knihovna.libBooks);
        Book HarryPotter2 = new Book(JKRowling, "Harry Potter a Tajemná komnata", 272, 449,Knihovna.libBooks);
        Book LordOfTheRing1 = new Book(JRRTolkien, "Spoleèenstvo prstenu", 436, 356,Knihovna.libBooks);
        Book LordOfTheRing2 = new Book(JRRTolkien, "Dvì vìže", 366, 311,Knihovna.libBooks);
        Book LordOfTheRing3 = new Book(JRRTolkien, "Návrat krále", 366, 311);
        JKRowling.listBooks();
        JRRTolkien.listBooks();
        System.out.println(JKRowling.ToString());
        System.out.println(JRRTolkien.ToString());
        System.out.println();
        Reader JJ = new Reader("Jakub", "Josefus", 1992, 3, 25);
        System.out.println();
        JJ.ListBorrowed();
        System.out.println();
        JJ.ReturnBook(HarryPotter1);
        System.out.println();
        JJ.BorrowBook(HarryPotter1);
        JJ.ListBorrowed();

        Knihovna.addBook(HarryPotter1);
        Knihovna.addBook(LordOfTheRing3);
        Knihovna.listAvailable();
        Knihovna.listUnavailable();
        Knihovna.listAll();

	}

}

class Book
{
    Author author;
    String title;
    int pages;
    double price;
    boolean available = true;
    
    public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}  
    
    
    
    public Book(Author author, String title, int pages, double price, List<Book> libBooks)
    {
        this.author = author;
        this.title = title;
        this.pages = pages;
        this.price = price;
        libBooks.add(this);
        author.getBooks().add(this);
    }

    public Book(Author author, String title, int pages, double price)
    {
        this.author = author;
        this.title = title;
        this.pages = pages;
        this.price = price;
        author.getBooks().add(this);
    }

    public String ToString()
    {
        String print;
        print = "Dílo " + title + " stránky "+pages+ " cena "+ price+" Autor "+ author.getFirstName()+" "+ author.getLastName();
        return print;
    }
}
class Date
{
	int year;
    int month;
    int day;
    public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

	

    public Date(int year, int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }

}
class Author
{
	String lastName;
    private List<Book> books;
    Date dateOfBirth;
    String firstName;
    public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	
    
    
    public Author(String firstName, String lastName, int year, int month, int day)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = new ArrayList<Book>();
        dateOfBirth = new Date(year, month, day);


    }

    public void listBooks()
    {
        System.out.println("List of "+ firstName+" "+ lastName+ " books");
        for (Book book : books)
        {
            System.out.println(book.ToString());
        }
        System.out.println("End of list \n");
    }
    public String ToString()
    {
        String print;
        print = "Výpis autora \nJméno "+firstName+" "+ lastName+ "Narozen(a) Rok/Mìsíc/Den " +dateOfBirth.getYear()+"/"+dateOfBirth.getMonth()+"/"+dateOfBirth.getDay()+"\n";
        return print;
    }
}
class Reader
{
    String firstName;
    String lastName;
    List<Book> myBooks;
    Date dateOfBirth;

    public Reader(String firstName, String lastName, int year, int month, int day)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = new Date(year, month, day);
        this.myBooks = new ArrayList<Book>();

    }
    public void BorrowBook(Book book)
    {
        if (book.isAvailable())
        {
            myBooks.add(book);
            book.available = false;
        }
        else System.out.println("Kniha je zabraná");
    }
    public void ReturnBook(Book book)
    {
        if (book.available == false && myBooks.contains(book))
        {
            myBooks.remove(book);
            book.available = true;
        }
        else System.out.println("Knihu nemáte pùjèenou. Chyba!");
    }
    public void ListBorrowed()
    {
        System.out.println("List pùjèených knih");
        if (myBooks.size() == 0)
        {
            System.out.println("Nula vypùjèených knih");
        }
        else
        {
            for (Book book : myBooks)
            {
                System.out.println(book.ToString());
            }
        }

        System.out.println("Konec listu");
    }

    public String ToString()
    {
        String print;
        print = "Výpis ètenáøe \nJméno "+ firstName+" "+ lastName+" Narozen(a) Rok/Mìsíc/Den "+dateOfBirth.getYear()+"/"+dateOfBirth.getMonth()+"/"+dateOfBirth.getDay()+"\n";
        return print;
    }


}
class Library
{
    List<Book> libBooks;
    public Library()
    {
        this.libBooks = new ArrayList<Book>();
    }

    public void addBook(Book book)
    {
        if (libBooks.contains(book))
        {
            System.out.println("Nelze pøidat znova");
        }
        else
        {
            libBooks.add(book);
            System.out.println("Kniha "+book.getTitle() + "pøidána");
        }

    }
    public void listAvailable()
    {
        System.out.println("List dostupné knihy");
        for (Book book : libBooks)
        {
            if (book.available)
            {
                System.out.println("Kniha"+book.getTitle()+" Dostupnost: "+book.available);
            }
        }
        System.out.println("Konec listu dostupných knih \n");
    }

    public void listUnavailable()
    {
        System.out.println("List nedostupné knihy");
        for (Book book : libBooks)
        {
            if (book.available == false)
            {
                System.out.println("Kniha {book.Title} Dostupnost: {book.Available}");
            }
        }
        System.out.println("Konec listu nedostupných knih \n");
    }

    public void listAll()
    {
        System.out.println("List všech knih");
        for (Book book : libBooks)
        {
            System.out.println("Kniha "+book.getTitle()+" Autor "+book.author.getFirstName()+" "+ book.author.getLastName()+" Dostupnost: "+book.available);
        }
        System.out.println("Konec všech knih \n");
    }


}
