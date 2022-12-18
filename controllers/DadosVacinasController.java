package controllers;

import Repository.Repository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DadosVacinasController {

    @FXML
    private Button top1;

    @FXML
    private Button top2;

    @FXML
    private Button top3;

    @FXML
    private Button top4;

    @FXML
    private Button top5;

    @FXML
    private Button voltarV;

    @FXML
    private Label mostrar;

    @FXML
    void bTop1(ActionEvent event) {
        mostrar.setText(Repository.repository.getQuantPersonsFullVaccinated() + " pessoas terminaram o ciclo de vacinação"); 
    }

    @FXML
    void bTop2(ActionEvent event) {
        double[] percents = Repository.repository.percentPerPriorityGroupWithoutVaccination();
        mostrar.setText("Percentual de pessoas que não iniciaram ciclo de vacinação\n" + "Profissionais da Saude: " + percents[0] + 
        "% \nPessoas mais velhas: " + percents[1] + "% \nGrupos indigenas: " + percents[2] + "% \nGrupos com comorbidades: " + percents[3] +
        "% \nFuncionarios de presidios: " + percents[4] + "% \nGrupo de segurança publica: " + percents[5] + "% \nProfissionais da educaçao: " + percents[6] + "%");
    }

    @FXML
    void bTop3(ActionEvent event) {
        mostrar.setText("Percentual de pessoas totalmente vacinadas: "+ Repository.repository.percentOfCompleteVaccination() + "%");
    }

    @FXML
    void bTop4(ActionEvent event) {
        double[] percents = Repository.repository.getPercentOfVaccinationByPriorityGroup();
        mostrar.setText("Percentual de cobertura vacinal\n" + "Profissionais da Saude: " + percents[0] + 
        "% \nPessoas mais velhas: " + percents[1] + "% \nGrupos indigenas: " + percents[2] + "% \nGrupos com comorbidades: " + percents[3] +
        "% \nFuncionarios de presidios: " + percents[4] + "% \nGrupo de segurança publica: " + percents[5] + "% \nProfissionais da educaçao: " + percents[6] + "%");
    }

    @FXML
    void bTop5(ActionEvent event) {
        int[] quantities = Repository.repository.getQuantApplicationsPerManufacture();
        mostrar.setText("Percentual de cobertura vacinal\n" + "Sinovac: " + quantities[0] + 
        "\nAstrazeneca: " + quantities[1] + "\nPfizer: " + quantities[2] + "\njanssen: " + quantities[3]);
    }
    
    @FXML
    void voltar(ActionEvent event) {
        Stage stage = (Stage) voltarV.getScene().getWindow();
        stage.close();
    }

}

