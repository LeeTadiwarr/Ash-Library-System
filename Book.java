import java.util.Date;

public abstract class Book {
    // Constants for book types
    public static final int HISTORY = 0;
    public static final int NON_FICTION = 1;
    public static final int NOVEL = 2;

    // Attributes
    private int bookId;
    private String title;
    private boolean isBorrowed;
    private int numPages;
    private String borrower;
    private Date dueDate;

    // Constructor
    public Book() {
        // Default constructor
    }

    public Book(int bookId, String title, int numPages) {
        this.bookId = bookId;
        this.title = title;
        this.numPages = numPages;
        this.isBorrowed = false; // Initially not borrowed
    }

    // Getters
    public boolean isBorrowed() {
        return isBorrowed;
    }

    public String getTitle() {
        return title;
    }

    public int getNumPages() {
        return numPages;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBorrower() {
        return borrower;
    }

    public Date getDueDate() {
        return dueDate;
    }

    // Setters
    public void setBorrower(String borrowerName) {
        this.borrower = borrowerName;
    }

    public void setDueDate(Date date) {
        this.dueDate = date;
    }

    // Borrow method
    public boolean borrow(String borrowerName) {
        if (!isBorrowed) {
            this.borrower = borrowerName;
            this.isBorrowed = true;
            return true;
        }
        return false; // Already borrowed
    }

    // Return method
    public void returnBook() {
        this.isBorrowed = false;
        this.borrower = null;
        this.dueDate = null;
    }

    // Extra information method
    public abstract String extraInfo(); // Abstract method to be implemented by subclasses
}