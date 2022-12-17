package controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.VaccinationRecord;

public class PesquisarPersonControler {
    
    @FXML
    private ListView<VaccinationRecord> ListVRCPF;

    @FXML
    private Button pequisarButton;

    @FXML
    private Button voltarV;
    
    @FXML
    private TextField seaechCPF;
    private ObservableList<VaccinationRecord> obsVR;
    private LinkedList<VaccinationRecord> VR = Repository.repository.getVaccinationList();
    private LinkedList<VaccinationRecord> viewVR = new LinkedList<VaccinationRecord>() ;

    @FXML
    void pesquisa(ActionEvent event) {
        ListVRCPF.getItems().clear();;
        String cpfSearch = seaechCPF.getText();
        Iterator<VaccinationRecord> vaccinationIterator = this.VR.iterator();
        boolean isEmpty = true;

        while(vaccinationIterator.hasNext()){
            VaccinationRecord vaccinationTemp = vaccinationIterator.next();
            
            if(vaccinationTemp.getPerson().getCpf().equals(cpfSearch)){
                viewVR.add(vaccinationTemp);
                isEmpty = false;
            }
        }
        if(isEmpty){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("CPF não encontrado");
            alert.setHeaderText("Não há nenhum registro para o CPF pesquisado ou o CPF foi informado incorretamente!");
            alert.showAndWait();
        }
        obsVR = FXCollections.observableList(viewVR);
        ListVRCPF.setItems(obsVR);
    }
    @FXML
    void voltar(ActionEvent event) throws IOException {
        Stage stage = (Stage) voltarV.getScene().getWindow();
        stage.close();
    }

}
