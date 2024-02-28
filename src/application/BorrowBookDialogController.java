package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BorrowBookDialogController {

    @FXML
    private TextField bookTitleTextField;
    
    @FXML
    private TextField numberOfBooksTextField;
    
    @FXML
    private Label errorMessageLabel;

    @FXML
    private Button borrowButton;    

    @FXML
    private Button exitButton;
    
    private LibraryController libraryController;

    // Method for setting the LibraryController instance
    public void setLibraryController(LibraryController libraryController) {
        this.libraryController = libraryController;
    }

    // Method for borrowing a book
    @FXML
    private void borrowBook() {
        String bookTitle = bookTitleTextField.getText();
        String quantityStr = numberOfBooksTextField.getText();
        int quantity;

        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        if (bookTitle.isEmpty() || quantityStr.isEmpty()) {
            errorMessageLabel.setText("You must enter both the book title and the quantity.");
            errorMessageLabel.setVisible(true);
            return;
        }

        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                errorMessageLabel.setText("Quantity must be a positive integer.");
                errorMessageLabel.setVisible(true);
                return;
            }
            // Calling the method for borrowing a book
            String result = libraryController.borrowBook(bookTitle, quantity);
            errorMessageLabel.setText(result);
            errorMessageLabel.setVisible(true); // Ensuring that the message is displayed
            clearFields();
        } catch (NumberFormatException e) {
            errorMessageLabel.setText("Quantity must be a valid number.");
            errorMessageLabel.setVisible(true);
        }
    }

    private void clearFields() {
        bookTitleTextField.setText("");
        numberOfBooksTextField.setText("");
    }
    
    // Method for closing the dialog window
    @FXML
    private void exitDialog() {
        closeDialog();
    }

    private void closeDialog() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
