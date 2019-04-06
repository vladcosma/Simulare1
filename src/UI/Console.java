package UI;

        import Domain.Invoice;
        import Service.InvoiceService;

        import java.util.Scanner;

public class Console {

    private InvoiceService invoiceService;

    private Scanner scanner;

    public Console(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;

        this.scanner = new Scanner(System.in);
    }

    private void showMenu() {
        System.out.println("1. Invoice CRUD");

        System.out.println("x. Exit");
    }

    public void run() {
        while (true) {
            showMenu();

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    runInvoiceCrud();
                    break;

                case "x":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void runInvoiceCrud() {
        while (true) {
            System.out.println("1. Add an invoice");
            System.out.println("2. Remove an invoice");
            System.out.println("3. View all invoices");
            System.out.println("4. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateTransaction();
                    break;
                case "2":
                    handleRemoveTransaction();
                    break;
                case "3":
                    handleViewTransactions();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewTransactions() {
        for (Invoice transaction : invoiceService.getAll()) {
            System.out.println(transaction);
        }
    }

    private void handleRemoveTransaction() {
        try {
            System.out.print("Enter the id to remove:");
            String id = scanner.nextLine();
            invoiceService.remove(id);

            System.out.println("Invoice removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateTransaction() {
        try {
            System.out.print("Enter id: ");
            String id = scanner.nextLine();
            System.out.print("Enter invoice id (empty to not change for update): ");
            String idInvoice = scanner.nextLine();
            System.out.print("Enter description of invoice (empty to not change for update): ");
            String idDescriere = scanner.nextLine();
            System.out.print("Enter ammount of invoice (0 to not change for update): ");
            int ammount = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter date (empty to not change for update): ");
            double date = Double.parseDouble(scanner.nextLine());

            Invoice invoice = invoiceService.addOrUpdate(id, idInvoice, idDescriere, ammount, date);
            System.out.println(String.format("Added invoice id=%s, ammount=%f, dateofregistration=%s", invoice.getInvoicenumber(), invoice.getAmmount(), invoice.getDateOfRegistration());
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }



    private void handleViewInvoice() {
        for (Invoice invoice : invoiceService.getAll()) {
            System.out.println(invoice);
        }
    }

    private void handleRemoveInvoice() {
        try {
            System.out.print("Enter the id to remove:");
            String id = scanner.nextLine();
            invoiceService.remove(id);

            System.out.println("Invoice removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddInvoice() {
        try {
            System.out.print("Enter id: ");
            String id = scanner.nextLine();
            System.out.print("Enter invoice number (empty to not change for update): ");
            String invoiceNumber = scanner.nextLine();
            System.out.print(" Enter description (empty to not change for update): ");
            String descriere = scanner.nextLine();
            System.out.print("Enter ammount(empty to not change for update): ");
            int ammount = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter date of registration (empty to not change for update): ");
            double dateOfRegistration = Double.parseDouble(scanner.nextLine());

            invoiceService.addOrUpdate(id, invoiceNumber, descriere, ammount, dateOfRegistration);

            System.out.println("Invoice added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }


