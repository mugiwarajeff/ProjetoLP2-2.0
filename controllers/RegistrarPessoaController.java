package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Repository.Repository;
import Utils.UtilPerson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
    private Button voltar;

    
    @FXML
    void cadastrarPessoa() throws Exception{
        String nome = insrtnome.getText();
        String cpf = insertCPF.getText();
        Sexo sexo = Sexo.selectedSexo(selectSex.getValue());
        PriorityGroup priorityGroup = PriorityGroup.selectedPriorityGroup(selectedGP.getValue());

        Person person = new Person(nome, cpf, sexo, priorityGroup);
        
        Repository.repository.getPeopleList().add(person);
        Repository.repository.showPeoplewithoutVaccine();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectSex.getItems().addAll(Sexo.getNamesOfSexo());
        selectedGP.getItems().addAll(PriorityGroup.getNamesOfPriorityGroup());
    }

}