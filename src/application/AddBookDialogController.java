package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddBookDialogController {

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button exitButton;
    
    private LibraryController libraryController;
    
    // Method for setting the LibraryController instance
    public void setLibraryController(LibraryController libraryController) {
        this.libraryController = libraryController;
    }    

    // This method is called when the user clicks the "Add Book" button
    @FXML
    private void addBook() {
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String quantityStr = quantityTextField.getText();
        int quantity;

        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);

        if (title.isEmpty() || author.isEmpty() || quantityStr.isEmpty()) {
            errorMessageLabel.setText("All fields must be filled.");
            errorMessageLabel.setVisible(true);
            return;
        }

        // Check if the author's input contains at least one letter
        if (!author.matches(".*[a-zA-Z]+.*")) {
            errorMessageLabel.setText("The author's name must contain at least one letter.");
            errorMessageLabel.setVisible(true);
            return;
        }

        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                errorMessageLabel.setText("Quantity must be greater than 0.");
                errorMessageLabel.setVisible(true);
                return;
            }

            // Use libraryController to add the book
            String result = libraryController.addBook(title, author, quantity);
            errorMessageLabel.setText(result);
            errorMessageLabel.setVisible(true);

            // Clearing fields after successful addition
            clearFields();
        } catch (NumberFormatException e) {
            errorMessageLabel.setText("Quantity must be a positive number.");
            errorMessageLabel.setVisible(true);
        }
    }

    // Method for clearing the input fields
    private void clearFields() {
        titleTextField.setText("");
        authorTextField.setText("");
        quantityTextField.setText("");
    }

    // Method for closing the dialog window
    @FXML
    private void exitDialog() {
        closeDialog();
    }

    // Centralized method for closing the dialog window
    private void closeDialog() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
