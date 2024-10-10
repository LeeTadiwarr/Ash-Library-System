import java.util.Scanner;

public class LibraryMain {
    private LibrarySys librarySys;

    // Constructor
    public LibraryMain() {
        this.librarySys = new LibrarySys();
    }

    // Main method
    public static void main(String[] args) {
        LibraryMain mainApp = new LibraryMain();
        mainApp.run();
    }

    // Method to run the library system
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Add Books From File");
            System.out.println("2. Add Single Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add books from file
                    System.out.print("Enter the name of the file: ");
                    String filename = scanner.nextLine();

                    int result = librarySys.importBooks(filename);

                    if (result == BookDatabase.ADD_BOOK_SUCCESS) {
                        System.out.println("Books imported successfully.");
                    } else {
                        System.out.println("Failed to import books.");
                    }
                    break;

                case 2:
                    // Add a single book
                    System.out.print("Enter book type (0: History, 1: NonFiction, 2: Novel): ");
                    int bookType = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter number of pages: ");
                    int numPages = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter extra info (e.g., level or genre): ");
                    String extraInfo = scanner.nextLine();

                    int addResult = librarySys.addBook(bookType, title, numPages, extraInfo);
                    if (addResult == BookDatabase.ADD_BOOK_SUCCESS) {
                        System.out.println("Book added successfully.");
                    } else {
                        System.out.println("Failed to add book.");
                    }
                    break;

                case 3:
                    // Display all books
                    System.out.println("All Books:");
                    librarySys.displayAllBooks();
                    break;

                case 4:
                    // Borrow a book
                    System.out.print("Enter book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter borrower's name: ");
                    String borrower = scanner.nextLine();
                    System.out.print("Enter number of days to borrow: ");
                    int numDays = scanner.nextInt();

                    int borrowResult = librarySys.borrowBook(borrowId, borrower, numDays);
                    if (borrowResult == 1) {
                        System.out.println("Book borrowed successfully.");
                    } else {
                        System.out.println("Failed to borrow book.");
                    }
                    break;

                case 5:
                    // Return a book
                    System.out.print("Enter book ID to return: ");
                    int returnId = scanner.nextInt();

                    int returnResult = librarySys.returnBook(returnId);
                    if (returnResult == 1) {
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Failed to return book.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting the library system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6); // Exit when user selects 6

        scanner.close();
    }
}