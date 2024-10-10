import java.util.Arrays;

public class BookDatabase {
    // Constants for array positions
    public static final int ARRAY_POS_BOOK_TYPE = 0;
    public static final int ARRAY_POS_BOOK_NAME = 1;
    public static final int ARRAY_POS_NUM_PAGE = 2;
    public static final int ARRAY_POS_INFO = 3;
    public static final int ADD_BOOK_SUCCESS = 1;
    public static final int ADD_BOOK_FAIL = -1;

    // Array to hold all books
    private Book[] allBooks;
    private int bookCount;

    // Constructor
    public BookDatabase() {
        this.allBooks = new Book[10]; // Initial size of the array
        this.bookCount = 0;
    }

    // Method to grow the book array if needed
    private void growBookArray() {
        allBooks = Arrays.copyOf(allBooks, allBooks.length * 2);
    }

    // Method to generate a new book ID
    private int randid() {
        return bookCount + 1; // Simple ID generation, could be improved
    }

    public int genNewBookId() {
        return randid();
    }

    // Method to add a book from a file (stub; implementation can vary)
    public int addBook(String filename) {
        // Implementation for adding book from a file would go here
        return ADD_BOOK_FAIL; // Placeholder return value
    }

    // Method to add a new book object
    public int addBook(Book newBook) {
        if (bookCount >= allBooks.length) {
            growBookArray(); // Ensure there is room to add a new book
        }
        allBooks[bookCount] = newBook;
        bookCount++;
        return ADD_BOOK_SUCCESS;
    }

    // Method to get the number of books
    public int numBooks() {
        return bookCount;
    }

    // Method to delete a book at a specified position
    public boolean deleteBook(int pos) {
        if (pos < 0 || pos >= bookCount) {
            return false; // Invalid position
        }
        // Shift books to the left
        for (int i = pos; i < bookCount - 1; i++) {
            allBooks[i] = allBooks[i + 1];
        }
        allBooks[bookCount - 1] = null; // Remove the last book reference
        bookCount--;
        return true;
    }

    // Method to get a book at a specific position
    public Book getBookAt(int pos) {
        if (pos < 0 || pos >= bookCount) {
            return null; // Invalid position
        }
        return allBooks[pos];
    }
}