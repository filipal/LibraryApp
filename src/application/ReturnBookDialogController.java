package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReturnBookDialogController {

    @FXML
    private TextField bookTitleTextField;
    
    @FXML
    private TextField numberOfBooksTextField;
    
    @FXML
    private Label errorMessageLabel;

    @FXML
    private Button returnButton;

    @FXML
    private Button exitButton;
    
    private LibraryController libraryController;

    // Method for setting the LibraryController instance
    public void setLibraryController(LibraryController libraryController) {
        this.libraryController = libraryController;
    }

    // Method for returning a book
    @FXML
    private void returnBook() {
        String bookTitle = bookTitleTextField.getText();
        String numberOfBooksStr = numberOfBooksTextField.getText();
        int numberOfBooks;

        // Resetting the error message
        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        if(bookTitle.isEmpty() || numberOfBooksStr.isEmpty()) {
            errorMessageLabel.setText("You must enter the book title and number of books.");
            errorMessageLabel.setVisible(true);
            return;
        }

        try {
            numberOfBooks = Integer.parseInt(numberOfBooksStr);
            if (numberOfBooks <= 0) {
            	errorMessageLabel.setText("The number of books must be greater than 0.");
            	errorMessageLabel.setVisible(true);
            	return;
            }
            
            // Calling libraryController to return the book
            String result = libraryController.returnBook(bookTitle, numberOfBooks);
            
            errorMessageLabel.setText(result);
            errorMessageLabel.setVisible(true);
            clearFields();
            // Close the dialog window if the return is successful
            if (result.startsWith("You have returned the book")) {
                closeDialog();
            }
            
        } catch (NumberFormatException e) {
            errorMessageLabel.setText("The number of books must be a valid number.");
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
