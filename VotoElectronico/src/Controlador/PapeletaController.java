/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Controlador.Dao.CandidatoDao;
import Controlador.Lista.ListaEnlazada;
import Modelo.Candidato;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.sql.Blob;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Sergio Jumbo
 */
public class PapeletaController implements Initializable {

    CandidatoDao candidatos = new CandidatoDao();
    private List<Candidato> votos = new ArrayList<>();
    @FXML
    private HBox panel;
    @FXML
    private VBox vboxPrincipal;
    @FXML
    private Button btnVotar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            for (int i = 0; i < sacarCandidatosVisibles(candidatos.listar()).getSize(); i++) {
                Candidato candidato = null;
                try {
                    candidato = sacarCandidatosVisibles(candidatos.listar()).get(i);
                } catch (VacioException ex) {
                    Logger.getLogger(PapeletaController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PosicionException ex) {
                    Logger.getLogger(PapeletaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/detalleCandidatos.fxml"));
                    VBox candidateDetails = loader.load();
                    DetalleCandidatosController detalleController = loader.getController();
                    detalleController.cargar(candidato,this); // Pasa los detalles del candidato al controlador
                    panel.getChildren().add(candidateDetails);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(PapeletaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (VacioException ex) {
            Logger.getLogger(PapeletaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PosicionException ex) {
            Logger.getLogger(PapeletaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    public ListaEnlazada<Candidato> sacarCandidatosVisibles(ListaEnlazada<Candidato> listaC) throws VacioException, PosicionException{
        ListaEnlazada<Candidato> nuevalista = new ListaEnlazada<>();
        for (int i = 0; i < listaC.getSize(); i++) {
            Candidato can = listaC.get(i);
            if (can.getEstado()==1) {
                nuevalista.insertarNodo(can);
            }
        }
        return nuevalista;
    }   
    public boolean tieneVoto(Candidato candidato) {
        return votos.contains(candidato);
    }

    public void agregarVoto(Candidato candidato) {
        votos.add(candidato);
    }

    public void removerVoto(Candidato candidato) {
        votos.remove(candidato);
    }
    public Blob capturarYGuardarCaptura() {
        // Capturar una imagen de la vista de la papeleta
        WritableImage image = vboxPrincipal.snapshot(null, null);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        // Convertir la imagen en un arreglo de bytes (BLOB)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            byte[] bytes = baos.toByteArray();
            baos.close();
            return new javax.sql.rowset.serial.SerialBlob(bytes);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    private void ActionVotar(ActionEvent event) {
        tipoVoto();
    }
    public void tipoVoto(){
        if (votos.size()==0) {
            System.out.println("En blanco");
        }else if(votos.size()>1){
            System.out.println("Nulo");
        }else{
            System.out.println("Valido");
        }        
    }
}
