/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Controlador.Dao.PartidoPoliticoDao;
import Controlador.Dao.PersonaDao;
import Controlador.Lista.ListaEnlazada;
import Modelo.Candidato;
import Modelo.PartidoPolitico;
import Modelo.Persona;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Sergio Jumbo
 */
public class DetalleCandidatosController implements Initializable {
    PersonaDao personadao = new PersonaDao();
    PartidoPoliticoDao partidopdao = new PartidoPoliticoDao();
    Candidato candidatoL;
    private PapeletaController pepeleta;
    @FXML
    private Label txtPartidoPolitico;
    @FXML
    private ImageView imagenP;
    @FXML
    private CheckBox chekSelec;
    @FXML
    private ImageView imagenC;
    @FXML
    private Label txtNombreC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void cargar(Candidato candidato,PapeletaController p) throws SQLException{
        Persona persona = personadao.objeto(candidato.getIdPersona());
        PartidoPolitico partido = partidopdao.objeto(candidato.getIdPartidoPolitico());
        txtPartidoPolitico.setText(partido.getNombre());
        txtNombreC.setText(persona.getNombre()+" "+persona.getApellido());
        imagenP.setImage(sacarImagen(partido.getLogo()));
        imagenC.setImage(sacarImagen(candidato.getImagen()));     
        candidatoL = candidato;
        pepeleta = p;
    }
    public Image sacarImagen(Blob blob) throws SQLException {
        Image image = null;
        InputStream in = blob.getBinaryStream();
        image = new Image(in, 200, 200, true, true);
        return image;
    }    

    @FXML
    private void CandidatoSeleccion(MouseEvent event) {
        if (chekSelec.isSelected()) {
            pepeleta.agregarVoto(candidatoL);
        }else{
            pepeleta.removerVoto(candidatoL);
        }
    }
    public boolean isSeleccionado(){
        return chekSelec.isSelected();
    }
    public void candidatoSelection(ListaEnlazada<Candidato> candidato){
        if (isSeleccionado()) {
            candidato.insertarNodo(candidatoL);
        }else{
            
        }
    }   
}
