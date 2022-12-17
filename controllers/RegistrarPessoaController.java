package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Repository.Repository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.Person;
import models.SignaturesObjects.PriorityGroup;
import models.SignaturesObjects.Sexo;

public class RegistrarPessoaController implements Initializable {

    @FXML
    private Button cadastroPessoa;

    @FXML
    private TextField insertCPF;

    @FXML
    private TextField insrtnome;

    @FXML
    private ChoiceBox<String> selectSex;

    @FXML
    private ChoiceBox<String> selectedGP;

    @FXML
    private Button voltarV;

    
    @FXML
    void cadastrarPessoa() throws Exception{
        String nome = insrtnome.getText();
        String cpf = insertCPF.getText();
        Sexo sexo = Sexo.selectedSexo(selectSex.getValue());
        PriorityGroup priorityGroup = PriorityGroup.selectedPriorityGroup(selectedGP.getValue());

        Person person = new Person(nome, cpf, sexo, priorityGroup);
        
        Repository.repository.getPeopleList().add(person);
        Repository.repository.showPeoplewithoutVaccine();
        Repository.repository.writeBd();
        Alert alerti = new Alert(AlertType.INFORMATION);
        alerti.setHeaderText("Cadastro realizado com sucesso");
        alerti.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectSex.getItems().addAll(Sexo.getNamesOfSexo());
        selectedGP.getItems().addAll(PriorityGroup.getNamesOfPriorityGroup());
    }
    @FXML
    void voltar(ActionEvent event) throws IOException {
        Stage stage = (Stage) voltarV.getScene().getWindow();
        stage.close();
    }
}