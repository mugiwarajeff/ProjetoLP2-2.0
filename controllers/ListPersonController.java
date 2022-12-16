package controllers;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import models.Person;

public class ListPersonController implements Initializable {

    @FXML
    private ListView<Person> ListPerson;

    private ObservableList<Person> obsPerson;
    private LinkedList<Person> people = Repository.repository.getPeopleList();

    private void loadperson(){
        obsPerson = FXCollections.observableList(people);
        ListPerson.setItems(obsPerson);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadperson();
        
    }

}