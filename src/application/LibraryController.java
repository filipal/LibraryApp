package application;

import java.util.HashMap;
import java.util.Map;

public class LibraryController {
    
    private Book[] books = new Book[5];
    private int currentBooks = 0;
    private Map<String, Integer> borrowedBooks = new HashMap<>(); // Tracking borrowed books
  
    // Methods for managing books
    public String addBook(String title, String author, int quantity) {
        if (currentBooks < books.length) {
            books[currentBooks++] = new Book(title, author, quantity);
            return "You have entered " + quantity + " copies of the book '" + title + "'.";
        } else {
            return "The library is full. Cannot add more books.";
        }
    }

    public String borrowBook(String title, int quantity) {
        for (int i = 0; i < currentBooks; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                if (books[i].getQuantity() >= quantity) {
                    books[i].setQuantity(books[i].getQuantity() - quantity);
                    borrowedBooks.put(title, borrowedBooks.getOrDefault(title, 0) + quantity);
                    return "You have successfully borrowed " + quantity + " copies of '" + title + "'.";
                } else {
                    return "Insufficient copies available. Only " + books[i].getQuantity() + " copies available.";
                }
            }
        }
        return "Book not available or not found.";
    }

    public String returnBook(String title, int numberOfBooks) {
        for (int i = 0; i < currentBooks; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                if ((books[i].getQuantity() + numberOfBooks) <= books[i].getInitialQuantity()) {
                    books[i].setQuantity(books[i].getQuantity() + numberOfBooks);
                    // Updating the map of borrowed books
                    int currentBorrowed = borrowedBooks.getOrDefault(title, 0) - numberOfBooks;
                    if (currentBorrowed > 0) {
                        borrowedBooks.put(title, currentBorrowed);
                    } else {
                        borrowedBooks.remove(title); // If there are no more borrowed copies, remove the book from the map
                    }
                    // Returning a message about successful return plus printing currently borrowed
                    return "You have returned " + numberOfBooks + " copies of '" + title + "'.\n\n" + listBorrowedBooks();
                } else {
                    return "Cannot return " + numberOfBooks + " copies of '" + title + "'. It exceeds the total number of copies.";
                }
            }
        }
        return "This book does not belong to our library.";
    }

    // Method for listing currently borrowed books
    public String listBorrowedBooks() {
        StringBuilder sb = new StringBuilder("You currently have the following books on loan:\n");
        for (Map.Entry<String, Integer> entry : borrowedBooks.entrySet()) {
            sb.append("- ").append(entry.getKey()).append(", ").append(entry.getValue()).append(" copies\n");
        }
        return sb.toString();
    }   
}
