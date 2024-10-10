// import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class LibrarySys {
    private BookDatabase bookDatabase;

    // Constructor
    public LibrarySys() {
        this.bookDatabase = new BookDatabase();
    }

    // Method to import books from a file
    public int importBooks(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(", ");

                int bookId = Integer.parseInt(data[0]);
                int bookType = getBookType(data[1]); // Assuming the second element is the book type
                String title = data[2];
                int numPages = Integer.parseInt(data[3]);
                boolean isBCE = Boolean.parseBoolean(data[4]);
                String borrower = data[5];
                String dueDate = data[6];

                Book newBook = null;
                switch (bookType) {
                    case 0: // History
                        newBook = new History(bookId, title, numPages, isBCE, "History Genre");
                        break;
                    case 1: // NonFiction
                        newBook = new NonFiction(bookId, title, numPages, "Some Genre", 1);
                        break;
                    case 2: // Novel
                        newBook = new Novel(bookId, title, numPages, 3);
                        break;
                    default:
                        continue; // Skip invalid book types
                }

                if (newBook != null) {
                    newBook.setBorrower(borrower);
                    newBook.setDueDate(java.sql.Date.valueOf(dueDate));
                    bookDatabase.addBook(newBook);
                }
            }

            scanner.close();
            return BookDatabase.ADD_BOOK_SUCCESS; // Success
        } catch (Exception e) {
            e.printStackTrace();
            return BookDatabase.ADD_BOOK_FAIL; // Failure
        }
    }

    private int getBookType(String type) {
        switch (type.toLowerCase()) {
            case "history":
                return 0;
            case "nonfiction":
                return 1;
            case "novel":
                return 2;
            default:
                return -1; // Invalid type
        }
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
                book.setDueDate(java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(numDays)));
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