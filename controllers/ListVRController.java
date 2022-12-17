package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.VaccinationRecord;

public class ListVRController implements Initializable {

    @FXML
    private Label LabelRV;
    
    @FXML
    private Button voltarV;

    @FXML
    private ListView<VaccinationRecord> ListVR;

    private ObservableList<VaccinationRecord> obsVR;
    private LinkedList<VaccinationRecord> VR = Repository.repository.getVaccinationList();

    private void loadVR(){
        obsVR = FXCollections.observableList(VR);
        ListVR.setItems(obsVR);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadVR();
        
    }
    @FXML
    void voltar(ActionEvent event) throws IOException {
        Stage stage = (Stage) voltarV.getScene().getWindow();
        stage.close();
    }

}
