import java.util.ArrayList;

public class LibrarySys {
    private BookDatabase bookDatabase;

    // Constructor
    public LibrarySys() {
        this.bookDatabase = new BookDatabase();
    }

    // Method to import books from a file (stub; implementation can vary)
    public int importBooks(String filename) {
        // Implementation for importing books from a file would go here
        return BookDatabase.ADD_BOOK_FAIL; // Placeholder return value
    }

    // Method to add a book
    public int addBook(int bookType, String title, int numPages, String extraInfo) {
        Book newBook = null;

        switch (bookType) {
            case BookDatabase.ARRAY_POS_BOOK_TYPE:
                newBook = new History(bookDatabase.genNewBookId(), title, numPages, extraInfo.equalsIgnoreCase("true"), extraInfo);
                break;
            case BookDatabase.ARRAY_POS_NUM_PAGE:
                newBook = new NonFiction(bookDatabase.genNewBookId(), title, numPages, extraInfo, 1); // Assuming level is set to 1 for simplicity
                break;
            case BookDatabase.ARRAY_POS_BOOK_NAME:
                newBook = new Novel(bookDatabase.genNewBookId(), title, numPages, Integer.parseInt(extraInfo)); // Assuming extraInfo as level
                break;
            default:
                return BookDatabase.ADD_BOOK_FAIL; // Invalid book type
        }

        return bookDatabase.addBook(newBook);
    }

    // Method to display all books
    public void displayAllBooks() {
        for (int i = 0; i < bookDatabase.numBooks(); i++) {
            Book book = bookDatabase.getBookAt(i);
            System.out.println("ID: " + book.getBookId() + ", Title: " + book.getTitle() + ", Pages: " + book.getNumPages() + ", Extra Info: " + book.extraInfo());
        }
    }

    // Method to display all books of a specific type
    public void displayAllBooks(int bookType) {
        for (int i = 0; i < bookDatabase.numBooks(); i++) {
            Book book = bookDatabase.getBookAt(i);
            if ((bookType == BookDatabase.ARRAY_POS_BOOK_TYPE && book instanceof History) ||
                (bookType == BookDatabase.ARRAY_POS_NUM_PAGE && book instanceof NonFiction) ||
                (bookType == BookDatabase.ARRAY_POS_BOOK_NAME && book instanceof Novel)) {
                System.out.println("ID: " + book.getBookId() + ", Title: " + book.getTitle() + ", Pages: " + book.getNumPages() + ", Extra Info: " + book.extraInfo());
            }
        }
    }

    // Method to borrow a book
    public int borrowBook(int bookId, String borrower, int numDays) {
        for (int i = 0; i < bookDatabase.numBooks(); i++) {
            Book book = bookDatabase.getBookAt(i);
            if (book.getBookId() == bookId && !book.isBorrowed()) {
                book.borrow(borrower);
                // Optionally set the due date based on numDays
                return 1; // Success
            }
        }
        return -1; // Book not found or already borrowed
    }

    // Method to return a book
    public int returnBook(int bookId) {
        for (int i = 0; i < bookDatabase.numBooks(); i++) {
            Book book = bookDatabase.getBookAt(i);
            if (book.getBookId() == bookId && book.isBorrowed()) {
                book.returnBook();
                return 1; // Success
            }
        }
        return -1; // Book not found or not borrowed
    }
}