package UI;

        import Service.InvoiceService;
        import javafx.event.ActionEvent;
        import javafx.scene.control.Button;
        import javafx.scene.control.CheckBox;
        import javafx.scene.control.Spinner;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

public class InvoiceAddControler {
    public TextField txtInvoiceNumber;
    public TextField txtAmmount;
    public TextField txtDateOfRegistration;
    public TextField txtDescriere;
    public Button btnAdd;
    public Button btnCancel;
    public Spinner spnId;

    private InvoiceService invoiceService;

    public void setService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnAddClick(ActionEvent actionEvent) {

        try {
            String id = String.valueOf(spnId.getValue());
            String invoicenumber = txtInvoiceNumber.getText();
            String descriere = txtDescriere.getText();
            double dateofregistration = Double.parseDouble(txtDateOfRegistration.getText());
            double ammount = Double.parseDouble(txtAmmount.getText());


            invoiceService.addOrUpdate(id, invoicenumber, ammount, descriere, dateofregistration);

            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }
}
