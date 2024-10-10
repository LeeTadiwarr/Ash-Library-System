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
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Display Books by Type");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Import Books from File (Not Implemented)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
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
                    librarySys.addBook(bookType, title, numPages, extraInfo);
                    break;
                case 2:
                    System.out.println("All Books:");
                    librarySys.displayAllBooks();
                    break;
                case 3:
                    System.out.print("Enter book type to display (0: History, 1: NonFiction, 2: Novel): ");
                    int typeToDisplay = scanner.nextInt();
                    librarySys.displayAllBooks(typeToDisplay);
                    break;
                case 4:
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
                    System.out.println("Importing books from file is not implemented yet.");
                    break;
                case 0:
                    System.out.println("Exiting the library system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}