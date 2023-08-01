/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Enum.ProvinciaEcuador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Stali
 */
public class BusquedaDController implements Initializable {

    @FXML
    private ComboBox<ProvinciaEcuador> cbxCiudad;
    @FXML
    private ComboBox<String> cbxCanton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCombo();
    }    
    private void cargarCombo(){
        cbxCiudad.getItems().addAll(ProvinciaEcuador.values());        
    }
    private void cargarCantones() {
        ProvinciaEcuador provinciaSeleccionada = cbxCiudad.getValue();
        if (provinciaSeleccionada != null) {
            cbxCanton.getItems().clear();
            cbxCanton.getItems().addAll(provinciaSeleccionada.getCantones());
            cbxCanton.setValue(provinciaSeleccionada.getCantones()[0]);
        }
    }

    private void cargar(ActionEvent event) {
        
    }

    @FXML
    private void Cargar(MouseEvent event) {
        cbxCiudad.setOnAction(e -> cargarCantones());
    }
}
