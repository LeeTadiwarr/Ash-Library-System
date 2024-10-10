public class History extends Book {
    // Additional attributes for the History class
    private boolean isBCE;
    private String genre;

    // Constructor
    public History(int bookId, String title, int numPages, boolean isBCE, String genre) {
        super(bookId, title, numPages); // Call to the superclass constructor
        this.isBCE = isBCE;
        this.genre = genre;
    }

    // Getter for isBCE
    public boolean isBCE() {
        return isBCE;
    }

    // Getter for genre
    public String getGenre() {
        return genre;
    }

    // Implementing the abstract method from Book class
    @Override
    public String extraInfo() {
        return "Genre: " + genre + ", Is BCE: " + (isBCE ? "Yes" : "No");
    }
}