/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Controlador.Dao.PartidoPoliticoDao;
import Modelo.PartidoPolitico;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.sql.Date;
import javafx.stage.FileChooser;
import java.sql.Blob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Stali
 */
public class PartidoPoliticoController implements Initializable {
    PartidoPoliticoDao partidopdao = new PartidoPoliticoDao();
    Blob blob;     
    @FXML
    private ImageView imagen;
    @FXML
    private Button btnImportarLogo;
    @FXML
    private TextField txtnombrepartido;
    @FXML
    private DatePicker fechaFundacion;
    @FXML
    private TableView<PartidoPolitico> jtable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarTabla();
        } catch (VacioException ex) {
            Logger.getLogger(PartidoPoliticoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PosicionException ex) {
            Logger.getLogger(PartidoPoliticoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void importarLogo(ActionEvent event) throws IOException, SQLException {
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
    private void Agregar(ActionEvent event) throws VacioException, PosicionException {
        PartidoPolitico partido = new PartidoPolitico();
        partido.setNombre(txtnombrepartido.getText());
        java.sql.Date fecha = java.sql.Date.valueOf(fechaFundacion.getValue());
        partido.setFundacion(fecha);
        partido.setLogo(blob);
        if (partidopdao.insertObject(partido)) {
            cargarTabla();
        }
    }

    @FXML
    private void seleccionar(ActionEvent event) {
    }

    @FXML
    private void Eliminar(ActionEvent event) {
    }

    @FXML
    private void Modificar(ActionEvent event) {
    }
    public void cargarTabla() throws VacioException, PosicionException{
        jtable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        jtable.setPrefWidth(TableView.USE_COMPUTED_SIZE);
        jtable.setPrefHeight(TableView.USE_COMPUTED_SIZE);        
        TableColumn<PartidoPolitico, Integer> columnaid = new TableColumn<>("idPartidoPolitico");
        columnaid.setCellValueFactory(new PropertyValueFactory<>("idPartidoPolitico"));
        TableColumn<PartidoPolitico, String> columnaCedula = new TableColumn<>("Nombre");
        columnaCedula.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<PartidoPolitico, Date> columnaNombre = new TableColumn<>("Fundación");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("fundacion"));        
        jtable.getColumns().addAll(columnaCedula,columnaNombre,columnaid);
        jtable.setItems(pasarLista());
    }
    public ObservableList pasarLista() throws VacioException, PosicionException{
        ObservableList<PartidoPolitico> listaPersonas = FXCollections.observableArrayList();
        for (int i = 0; i < partidopdao.listar().getSize(); i++) {            
            listaPersonas.add(partidopdao.listar().get(i));
        }
        return listaPersonas;
    }
    
}
