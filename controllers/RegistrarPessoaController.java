package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Repository.Repository;
import Utils.UtilPerson;
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
        try {
            String name;
            String cpf = null;
            Sexo sexo = null;
            PriorityGroup priorityGroup = null;

            name = insrtnome.getText();
            cpf = insertCPF.getText();
            UtilPerson.cpfValidator(cpf);
            if(!UtilPerson.cpfVerify(cpf, Repository.repository.getPeopleList())){
                throw new IllegalAccessException();
            }else{
                sexo = Sexo.selectedSexo(selectSex.getValue());
                priorityGroup = PriorityGroup.selectedPriorityGroup(selectedGP.getValue());
                if(priorityGroup == null || sexo == null || name == ""){
                    throw new Exception();
                }
                if (!UtilPerson.cpfValidator(cpf)){
                    throw new IllegalArgumentException();
                }
                Person newPerson = new Person(name, cpf, sexo, priorityGroup);
                Repository.repository.getPeopleList().add(newPerson);    
                Repository.repository.writeBd();
                Alert alerti = new Alert(AlertType.INFORMATION);
                alerti.setHeaderText("Cadastro realizado com sucesso");
                alerti.showAndWait();
                insrtnome.setText(null);
                insertCPF.setText(null);
                selectSex.setValue(null);
                selectedGP.setValue(null);
            }
        }catch(IllegalAccessException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("CPF já cadastrado!");
            alert.showAndWait();
        }catch (IllegalArgumentException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("CPF digitado nos padroes incorretos!\nColoque no formato XXX-XXX-XXX-XX");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Há um ou mais campos vazios");
            alert.showAndWait();
        }
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