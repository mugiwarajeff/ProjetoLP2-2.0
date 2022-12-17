package controllers;

import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;

import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Person;
import models.VaccinationRecord;

public class ListPersonController implements Initializable {

    @FXML
    private ListView<Person> ListPerson;

    @FXML
    private Button voltarV;


    private ObservableList<Person> obsPerson;
    private LinkedList<Person> people = Repository.repository.getPeopleList();
    private LinkedList<VaccinationRecord> VR = Repository.repository.getVaccinationList();
    private LinkedList<Person> Viewpeople = new LinkedList<Person>();
    
    private void loadperson(){
        Iterator<Person> peopleIterator = this.people.iterator();

        while(peopleIterator.hasNext()){
            Person personTemp = peopleIterator.next();
            if(!checkIfPersonHaveRegister(personTemp)){
                Viewpeople.add(personTemp);
            }
        }
        obsPerson = FXCollections.observableList(Viewpeople);
        ListPerson.setItems(obsPerson);
    }
    private boolean checkIfPersonHaveRegister(Person person){
        Iterator<VaccinationRecord> vaccinationIterator = this.VR.iterator();
            
            while(vaccinationIterator.hasNext()){
                VaccinationRecord vaccinationTemp = vaccinationIterator.next();
                if(vaccinationTemp.getPerson().getCpf().equals(person.getCpf())){
                    return true;
                }

            }
        return false;
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadperson();
        
    }
    @FXML
    void voltar(ActionEvent event) {
        Stage stage = (Stage) voltarV.getScene().getWindow();
        stage.close();
    }

}