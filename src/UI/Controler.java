package UI;

        import Domain.Invoice;
        import Service.InvoiceService;

        import javafx.application.Platform;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.cell.TextFieldTableCell;
        import javafx.stage.Modality;
        import javafx.stage.Stage;
        import javafx.util.converter.DoubleStringConverter;

        import java.io.IOException;
        import java.util.logging.Level;
        import java.util.logging.Logger;


public class Controller {

    public TableView tableViewInvoice;
    public TableColumn tableColumnId;
    public TableColumn tableColumnInvoiceNumber;
    public TableColumn tableColumnDateofRegistration;
    @FXML
    public TableColumn tableColumnDescriere;
    public TableColumn tableColumnAmmount;
    public Button btnInvoiceDelete;
    public Button btnInvoiceAdd;

    private InvoiceService invoiceService;

    private ObservableList<Invoice> cakes = FXCollections.observableArrayList();

    public void setServices(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;

    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            tableColumnAmmount.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            invoice.addAll(invoiceService.getAll());
            tableViewInvoice.setItems(invoices);
        });
    }

    public void editDescriere(TableColumn.CellEditEvent cellEditEvent) {
        Descriere editedCake = (Descriere) cellEditEvent.getRowValue();
        try {
            String newName = (String)cellEditEvent.getNewValue();
            cakeService.addOrUpdate(editedCake.getId(), newName, editedCake.getIngredients(), editedCake.getCalories(), editedCake.getPrice(), editedCake.isSugarFree());
            editedCake.setName(newName);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
        tableViewCakes.refresh();
    }

    public void editCakeCalories(TableColumn.CellEditEvent cellEditEvent) {
        Cake editedCake = (Cake)cellEditEvent.getRowValue();
        try {
            double newCalories = (double)cellEditEvent.getNewValue();
            cakeService.addOrUpdate(editedCake.getId(), editedCake.getName(), editedCake.getIngredients(), newCalories, editedCake.getPrice(), editedCake.isSugarFree());
            editedCake.setCalories(newCalories);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
        tableViewCakes.refresh();
    }

    public void btnCakeAddClick(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("cakeAdd.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 200);
            Stage stage = new Stage();
            stage.setTitle("Cake add");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            CakeAddController controller =  fxmlLoader.getController();
            controller.setService(cakeService);
            stage.showAndWait();
            cakes.clear();
            cakes.addAll(cakeService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Cake add.", e);
        }
    }

    public void btnCakeDeleteClick(ActionEvent actionEvent) {
        Cake selected = (Cake)tableViewCakes.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                cakeService.remove(selected.getId());
                cakes.clear();
                cakes.addAll(cakeService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }
}
