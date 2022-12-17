package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import Repository.Repository;
import Utils.RegisterUtils;
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
import models.VaccinationRecord;
import models.SignaturesObjects.Dose;
import models.SignaturesObjects.Manufacturer;

public class RegistrarVacinaController implements Initializable {

    @FXML
    private TextField CPFregistro;

    @FXML
    private Button cadastrarVacina;

    @FXML
    private ChoiceBox<String> opcaoVacina;

    @FXML
    private Button voltarV;

    public void cadastrarVacina() {
        try {
            String cpfOfPerson;
            Person person=null;
            Date date = new Date();
            Manufacturer manufacturer;
            Dose dose=null;
            cpfOfPerson = CPFregistro.getText();
            person = RegisterUtils.searchPerson(Repository.repository.getPeopleList() , cpfOfPerson);
            if(person == null){
                throw new Exception();
            }
            if(RegisterUtils.isFullVaccinated(person, Repository.repository.vaccinationRecords)){
                throw new IllegalArgumentException();
            }else{
                manufacturer = RegisterUtils.haveOneDose(person, Repository.repository.vaccinationRecords);
                if(manufacturer == null){
                    manufacturer = Manufacturer.selectManufacture(opcaoVacina.getValue());
                    dose = RegisterUtils.qtDose(opcaoVacina.getValue());
                    VaccinationRecord vacinationRecord = new VaccinationRecord(person, date, manufacturer, dose);
                    Repository.repository.vaccinationRecords.add(vacinationRecord);
                }else{
                    dose = Dose.second;
                    VaccinationRecord vacinationRecord = new VaccinationRecord(person, date, manufacturer, dose);
                    Repository.repository.vaccinationRecords.add(vacinationRecord);
                } 
            }
            Repository.repository.writeBd();
            Alert alerti = new Alert(AlertType.INFORMATION);
            alerti.setTitle("Registro realizado com sucesso.");
            if(dose == Dose.second){
                alerti.setHeaderText("Segunda dose aplicada!");
            }else{
                if(dose == Dose.firts){
                    alerti.setHeaderText("Primeira dose aplicada!");
                }else{
                    alerti.setHeaderText("Dose única aplicada!");
                }
            }
            alerti.showAndWait();
            CPFregistro.setText(null);
            opcaoVacina.setValue(null);
        } catch(IllegalArgumentException e){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Pessoa totalmente vacinada");
            alert.setHeaderText("A pessoa selecionada já está com o registro de vacinção completo!");
            alert.showAndWait();
        }catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("CPF não encontrado ou a vacina não foi selecionada!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        opcaoVacina.getItems().addAll(Manufacturer.getNamesOfManofacturer());
    }
    @FXML
    void voltar(ActionEvent event) throws IOException {
        Stage stage = (Stage) voltarV.getScene().getWindow();
        stage.close();
    }
}