public class Novel extends Book {
    // Additional attribute for the Novel class
    private int level;

    // Constructor
    public Novel(int bookId, String title, int numPages, int level) {
        super(bookId, title, numPages); // Call to the superclass constructor
        this.level = level;
    }

    // Getter for level
    public int getLevel() {
        return level;
    }

    // Implementing the abstract method from Book class
    @Override
    public String extraInfo() {
        return "Level: " + level;
    }
}