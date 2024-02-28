package application;

public class Book {
    private String title;
    private String author;
    private int quantity;
    private int initialQuantity; // Added attribute for tracking initial quantity

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.initialQuantity = quantity; // Setting initial quantity
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInitialQuantity() { // Getter for initialQuantity
        return initialQuantity;
    }

    public void setInitialQuantity(int initialQuantity) { // Setter for initialQuantity
        this.initialQuantity = initialQuantity;
    }
}
