package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {

    private LibraryController libraryController = new LibraryController();
    
    @FXML
    private Button addBookButton;
    @FXML
    private Button borrowBookButton;
    @FXML
    private Button returnBookButton;
    @FXML
    private Button exitButton;

    @FXML
    private void openAddBookDialog(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBookDialog.fxml"));
            Parent root = loader.load();

            AddBookDialogController controller = loader.getController();
            controller.setLibraryController(libraryController);

            Stage stage = new Stage();
            stage.setTitle("Add Book");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openBorrowBookDialog(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BorrowBookDialog.fxml"));
            Parent root = loader.load();

            BorrowBookDialogController controller = loader.getController();
            controller.setLibraryController(libraryController);

            Stage stage = new Stage();
            stage.setTitle("Borrow Book");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openReturnBookDialog(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReturnBookDialog.fxml"));
            Parent root = loader.load();

            ReturnBookDialogController controller = loader.getController();
            controller.setLibraryController(libraryController);

            Stage stage = new Stage();
            stage.setTitle("Return Book");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exitApplication(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
