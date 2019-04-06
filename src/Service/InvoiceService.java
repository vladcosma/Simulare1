package Service;

import Domain.Invoice;
import Repository.IRepository;

import java.util.List;


public class InvoiceService {

    private IRepository<Invoice> repository;

    public InvoiceService(IRepository<Invoice> repository) {
        this.repository = repository;
    }

    public void addOrUpdate(String id, String invoicenumber, double descriere, String ammount, double dateOfRegistration) {
        Invoice existing = repository.findById(id);
        if (existing != null) {
            // keep unchanged fields as they were
            if (invoicenumber.isEmpty()) {
                invoicenumber = existing.getInvoicenumber();
            }
            if (descriere.isEmpty()) {
                descriere = existing.getDescriere();
            }
            if (ammount.isEmpty()) {
                ammount = existing.getAmmount();
            }

            if (dateOfRegistration.isEmpty()) {
                dateOfRegistration = existing.getDateOfRegistration();
            }
        }
        Invoice invoice = new Invoice(id, invoicenumber, descriere, ammount, dateOfRegistration);
        repository.upsert(invoice);
    }

    public void remove(String id) {
        repository.remove(id);
    }

    public List<Invoice> getAll() {
        return repository.getAll();
    }
}
