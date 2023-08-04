/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Controlador.Dao.CandidatoDao;
import Controlador.Dao.PartidoPoliticoDao;
import Controlador.Dao.PersonaDao;
import Modelo.Candidato;
import Modelo.Enum.Cargo;
import Modelo.Persona;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import java.sql.SQLException;
import java.sql.Blob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Sergio Jumbo
 */
public class CandidatosController implements Initializable {
    Blob blob;
    PartidoPoliticoDao partidopdao = new PartidoPoliticoDao();
    PersonaDao personadao = new  PersonaDao();
    CandidatoDao candidatodao = new CandidatoDao();
    Persona persona;
    byte estado;
    @FXML
    private ImageView imagen;
    @FXML
    private Button btnImport;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private TableView<String> jtable;
    @FXML
    private ComboBox<String> cbxPartidoP;
    @FXML
    private CheckBox chkVisible;
    @FXML
    private ComboBox<Cargo> cbxCargo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarcombo();
        } catch (VacioException ex) {
            Logger.getLogger(CandidatosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PosicionException ex) {
            Logger.getLogger(CandidatosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ImportarImagen(ActionEvent event) throws IOException, SQLException {
        selectImage(event);
    }
    void selectImage(ActionEvent event) throws IOException, SQLException {
        FileChooser fileChooser = new FileChooser();

        // Filtrar por archivos de imagen
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el explorador de archivos
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Leer el archivo seleccionado y guardar en un objeto byte[]
            byte[] imageData = readBytesFromFile(selectedFile);
            blob = new javax.sql.rowset.serial.SerialBlob(imageData);

            // Convertir el objeto byte[] a un objeto Image
            Image image = new Image(new FileInputStream(selectedFile));

            // Cargar la imagen en el objeto ImageView
            imagen.setImage(image);
        }
    }
    private byte[] readBytesFromFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            byteOutputStream.write(buffer, 0, bytesRead);
        }
        fileInputStream.close();
        return byteOutputStream.toByteArray();
    }

    @FXML
    private void ActionAgregar(ActionEvent event) {
        Candidato candidato = new Candidato();
        candidato.setCargo(cbxCargo.getValue());
        candidato.setEstado(estado);
        candidato.setImagen(blob);
        candidato.setIdPersona(persona.getIdPersona());
        candidato.setIdPartidoPolitico(cbxPartidoP.getSelectionModel().getSelectedIndex()+1);
        candidatodao.insertObject(candidato);
    }

    @FXML
    private void ActionBuscar(ActionEvent event) throws VacioException, PosicionException {
        persona = personadao.BuscarporCedula(txtBusqueda.getText());
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Estás seguro de haber seleccionado a? "+persona.getNombre()+" "+persona.getApellido());
        alert.setContentText("Presiona OK para confirmar o Cancel para cancelar.");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
    }
    public void cargarcombo() throws VacioException, PosicionException{
        cbxCargo.getItems().addAll(Cargo.values());
        cbxPartidoP.getItems().addAll(pasarLista());           
    }
    public ObservableList pasarLista() throws VacioException, PosicionException{
        ObservableList<String> listaPersonas = FXCollections.observableArrayList();
        for (int i = 0; i < partidopdao.listar().getSize(); i++) {            
            listaPersonas.add(partidopdao.listar().get(i).getNombre());
        }
        return listaPersonas;
    }
    public void estadoV(){
        estado = (byte) (chkVisible.isSelected() ? 1 : 0);
    }

    @FXML
    private void activarVisible(MouseEvent event) {
        estadoV();
    }
    
}
