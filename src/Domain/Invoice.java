package Domain;

import java.util.Objects;

public class Invoice extends Entity {

    private String invoicenumber, dateOfRegistration, descriere, ammount;

    public Invoice(String invoicenumber, String id,  String dateOfRegistration, String descriere, String ammount) {
        super(id);
        this.invoicenumber = invoicenumber;
        this.dateOfRegistration = dateOfRegistration;
        this.descriere = descriere;
        this.ammount = ammount;
            }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + getId() + '\'' +
                "invoice number='" + getInvoicenumber() + '\'' +
                ", date of registration='" + dateOfRegistration + '\'' +
                ", amount='" + ammount + '\'' +
                ", descriere='" + descriere + '\'';}


    @Override
    public int hashCode() {
        return Objects.hash(getId(), invoicenumber, dateOfRegistration, descriere, ammount);
    }

    public String getInvoicenumber() {
        return invoicenumber;
    }


    public String getAmmount() {
        return ammount;
    }


    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
}
