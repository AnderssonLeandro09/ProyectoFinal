/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Stali
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnDatosGenerales;
    @FXML
    private Button btnPersona;
    @FXML
    private Button btnCandidato;
    @FXML
    private Button btnPartido;
    @FXML
    private VBox panel;
    @FXML
    private Button btnBusquedaD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarFXMLEnVBox("/Vista/Estadistica.fxml");
    }    

    @FXML
    private void datosGenerales(ActionEvent event) {
        cargarFXMLEnVBox("/Vista/Estadistica.fxml");
    }

    @FXML
    private void actionPersona(ActionEvent event) {
        cargarFXMLEnVBox("/Vista/Persona.fxml");
    }

    @FXML
    private void actionCandidato(ActionEvent event) {
        cargarFXMLEnVBox("/Vista/Candidatos.fxml");
    }

    @FXML
    private void ActionPartidoP(ActionEvent event) {
        cargarFXMLEnVBox("/Vista/PartidoPolitico.fxml");
    }
    private void cargarFXMLEnVBox(String archivoFXML) {
        try {
            // Cargar el archivo FXML usando FXMLLoader
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(archivoFXML));
            Parent root = fxmlLoader.load();
            panel.getChildren().setAll(root);
            VBox.setVgrow(root, Priority.ALWAYS);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar errores si ocurre algún problema al cargar el archivo FXML
        }
    }

    @FXML
    private void ActionBusqueda(ActionEvent event) {
        cargarFXMLEnVBox("/Vista/BusquedaD.fxml");
    }
}
