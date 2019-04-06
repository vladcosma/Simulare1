
import Domain.*;
        import Repository.*;
        import Service.InvoiceServiceService;

        import Service.TransactionService;
        import UI.Controller;
        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();

        Controller controller =  fxmlLoader.getController();

        IValidator<Invoice> cakeValidator = new InvoiceValidator()Validator();
        IValidator<Client> clientValidator = new ClientValidator();
        IValidator<Transaction> transactionValidator = new TransactionValidator();

//        IRepository<Cake> cakeRepository = new SerializableFileRepository<>(cakeValidator, "cakes.ser"); //new InMemoryRepository<>(cakeValidator);
        IRepository<Cake> cakeRepository = new JsonFileRepository<>(cakeValidator, "cakes.json", Cake.class); //new InMemoryRepository<>(cakeValidator);
        IRepository<Client> clientRepository = new InMemoryRepository<>(clientValidator);
        IRepository<Transaction> transactionRepository = new InMemoryRepository<>(transactionValidator);

        CakeService cakeService = new CakeService(cakeRepository);
        cakeService.addOrUpdate("1", "Tiramisu", "1 2 3", 200, 100, false);
        cakeService.addOrUpdate("2", "Eclair", "e e e", 250, 300, false);
        cakeService.addOrUpdate("3", "IceCream", "c c c", 150, 200, false);

        ClientService clientService = new ClientService(clientRepository);
        TransactionService transactionService = new TransactionService(transactionRepository, cakeRepository);
        controller.setServices(cakeService, clientService, transactionService);

        primaryStage.setTitle("Cake manager");
        primaryStage.setScene(new Scene(root, 650, 500));

        primaryStage.show();


        // exemplu adapter:
        IRepository<Cake> ourRepository = new InMemoryRepository<>(new CakeValidator());
        IOtherTeamRepository<Cake> ourAdapter = new OtherTeamRepositoryAdapter<>(ourRepository);
        OtherTeamCakeService otherTeamCakeService = new OtherTeamCakeService(ourAdapter);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
