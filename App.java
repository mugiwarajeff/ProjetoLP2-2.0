/*
 * This Java source file was generated by the Gradle 'init' task.
 */


import java.util.Scanner;

import Repository.Repository;
import Utils.DataUtils;
import Utils.RegisterUtils;
import Utils.UtilPerson;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class App extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("views/MenuPrincipal.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
       
        
        
        boolean whileController = true;
        Scanner input = new Scanner(System.in);

        while(whileController){
            int opcao = 0;
            System.out.println("=======Menu de opções========");  
            System.out.println("Digite 1 para Registrar uma nova Pessoa");
            System.out.println("Digite 2 para realizar um novo registro de vacinaçao");
            System.out.println("Digite 3 para pesquisar pessoa cadastradas");
            System.out.println("Digite 4 para pesquisar pessoa que não foram vacinadas");
            System.out.println("Digite 5 para imprimir registros de vacinaçao");
            System.out.println("Digite 6 para Dados das vacinas");
            System.out.println("Digite 0 para Sair do Programa");
            opcao = input.nextInt();

            switch(opcao){
                case 1: 
                    input.nextLine();
                    UtilPerson.personRegister(Repository.repository.getPeopleList(), input);
                    System.out.println("Pessoa cadastrada com sucesso!");
                    break;
                case 2: 
                    input.nextLine();
                    RegisterUtils.recordRegister(Repository.repository.getPeopleList(), Repository.repository.getVaccinationList(), input);
                    break;
                case 3:
                    Repository.repository.searchRegisterByCPF(input);
                    break;
                case 4: 
                    input.nextLine();
                    Repository.repository.showPeoplewithoutVaccine();
                    break;
                case 5:
                Repository.repository.showAllRecords();
                    break;
                case 6:
                    DataUtils.showOption();
                    DataUtils.selectedOption(Repository.repository, input);
                    break;
                case 0:
                    whileController = false;
                    break;
                default:
                    System.out.println("Tentativa invalida, tente novamente...");
            }
        }

    }

    
}

/*
 * 
 */