package controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import Repository.Repository;
import Utils.RegisterUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
            boolean cpfSearchError = true;
            while(cpfSearchError){
                cpfOfPerson = CPFregistro.getText();
                person = RegisterUtils.searchPerson(Repository.repository.getPeopleList() , cpfOfPerson);
                if(person != null){
                    cpfSearchError = false;
                    System.out.printf("Pessoa selecionada: %s \n", person.getName());
                }else{
                    throw new Exception();
                }

            }
            if(RegisterUtils.isFullVaccinated(person, Repository.repository.vaccinationRecords)){
                
                System.out.println("Pessoa ja está totalmente vacinada");
            }else{
                manufacturer = RegisterUtils.haveOneDose(person, Repository.repository.vaccinationRecords);
                if(manufacturer == null){
                    manufacturer = Manufacturer.selectManufacture(opcaoVacina.getValue());
                    dose = RegisterUtils.qtDose(opcaoVacina.getValue());
                    System.out.println("Primeira dose aplicada");
                    VaccinationRecord vacinationRecord = new VaccinationRecord(person, date, manufacturer, dose);
                    Repository.repository.vaccinationRecords.add(vacinationRecord);
                    
                }else{
                    dose = Dose.second;
                    System.out.println("Segunda dose aplicada");

                    VaccinationRecord vacinationRecord = new VaccinationRecord(person, date, manufacturer, dose);
                    Repository.repository.vaccinationRecords.add(vacinationRecord);
                }
                    
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("cpf não encontrado");
            alert.setHeaderText("Coloque os dados corretamente");
            alert.showAndWait();
        }
        Repository.repository.showAllRecords();
        Repository.repository.writeBd();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        opcaoVacina.getItems().addAll(Manufacturer.getNamesOfManofacturer());
        
    }

}