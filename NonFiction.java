public class NonFiction extends Book {
    // Additional attributes for the NonFiction class
    private String genre;
    private int level;

    // Constructor
    public NonFiction(int bookId, String title, int numPages, String genre, int level) {
        super(bookId, title, numPages); // Call to the superclass constructor
        this.genre = genre;
        this.level = level;
    }

    // Getter for genre
    public String getGenre() {
        return genre;
    }

    // Getter for level
    public int getLevel() {
        return level;
    }

    // Implementing the abstract method from Book class
    @Override
    public String extraInfo() {
        return "Genre: " + genre + ", Level: " + level;
    }
}