public class Book {
    String title;
    String author;
    int publicationYear;

    public Book() {
        this.title = "Harry Potter";
        this.author = "George R. R. Martin";
    }

    // Parameterized Constructor
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Overloaded 
    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public void displayDetails() {
        System.out.println("Title: " + title + ", Author: " + author + ", Year: " + publicationYear);
    }

    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 = new Book("1984", "George Orwell");
        Book book3 = new Book("To Kill a Mockingbird", "Harper Lee", 1960);

        book1.displayDetails();
        book2.displayDetails();
        book3.displayDetails();
    }
}
