/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Controlador.Dao.CuentaDao;
import Controlador.Dao.PersonaDao;
import Modelo.Cuenta;
import Modelo.Enum.Genero;
import Modelo.Enum.ProvinciaEcuador;
import Modelo.Persona;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Stali
 */
public class PersonaController implements Initializable {
    PersonaDao personadao = new PersonaDao();
    CuentaDao cuentadao= new CuentaDao();
    private int ordenSeleccionado = -1;

    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private DatePicker txtCalendario;
    @FXML
    private ComboBox<ProvinciaEcuador> cbxCiudad;
    @FXML
    private ComboBox<String> cbxCanton;
    @FXML
    private TextField txtCorreo;
    @FXML
    private ComboBox<Genero> cbxGenero;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnSeleccionar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<Persona> jtablePersona;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCombo();
        try {
            cargarTabla();
        } catch (VacioException ex) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PosicionException ex) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private void cargarCombo(){
        cbxCiudad.getItems().addAll(ProvinciaEcuador.values());      
        cbxGenero.getItems().addAll(Genero.values());
    }
    private void cargarCantones() {
        ProvinciaEcuador provinciaSeleccionada = cbxCiudad.getValue();
        if (provinciaSeleccionada != null) {
            cbxCanton.getItems().clear();
            cbxCanton.getItems().addAll(provinciaSeleccionada.getCantones());
            cbxCanton.setValue(provinciaSeleccionada.getCantones()[0]);
        }
    }

    @FXML
    private void Cargar(MouseEvent event) {
        cbxCiudad.setOnAction(e -> cargarCantones());
    }

    @FXML
    private void ActionAgregar(ActionEvent event) throws VacioException, PosicionException {
        Persona persona = new Persona();
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(txtCedula.getText());
        cuenta.setCorreo(txtCorreo.getText());
        cuenta.setClave("123");
        cuenta.setIdRol(1);
        cuentadao.insertObject(cuenta);
        persona.setCedula(txtCedula.getText());
        persona.setNombre(txtNombres.getText());
        persona.setApellido(txtApellidos.getText());
        java.sql.Date fecha = java.sql.Date.valueOf(txtCalendario.getValue());
        persona.setFechaNacimiento(fecha);
        persona.setCiudad(cbxCiudad.getValue().name());
        persona.setCanton(cbxCanton.getValue());
        Genero generoSeleccionado = cbxGenero.getValue();
        persona.setGenero(generoSeleccionado);
        persona.setEstadovoto((byte) 0);
        persona.setIdCuenta(cuentadao.BuscarporID(cuenta.getUsuario()).getIdCuenta());        
        personadao.insertObject(persona);
        cargarTabla();
    }

    @FXML
    private void ActionSeleccionar(ActionEvent event) throws VacioException, PosicionException {
        Persona personaseleccionada = personadao.listar().get(jtablePersona.getSelectionModel().getSelectedIndex());
        
    }

    @FXML
    private void ActionModificar(ActionEvent event) {
    }

    @FXML
    private void ActionEliminar(ActionEvent event) {
    }
    public void cargarTabla() throws VacioException, PosicionException{        
        jtablePersona.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        jtablePersona.setPrefHeight(TableView.USE_COMPUTED_SIZE);
        jtablePersona.setPrefWidth(TableView.USE_COMPUTED_SIZE);        
        TableColumn<Persona, String> columnaCedula = new TableColumn<>("Cedula");
        columnaCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        TableColumn<Persona, String> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Persona, String> columnaApellido = new TableColumn<>("Apellido");
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        TableColumn<Persona, String> columnaCiudad = new TableColumn<>("Ciudad");
        columnaCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        TableColumn<Persona, String> columnaCanton = new TableColumn<>("Canton");
        columnaCanton.setCellValueFactory(new PropertyValueFactory<>("canton"));
        TableColumn<Persona, Genero> columnaGenero = new TableColumn<>("Genero");
        columnaGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        jtablePersona.getColumns().addAll(columnaCedula,columnaNombre,columnaApellido,columnaCiudad,columnaCanton,columnaGenero);
        jtablePersona.setItems(pasarLista());
    }
    public ObservableList pasarLista() throws VacioException, PosicionException{
        ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();
        for (int i = 0; i < personadao.listar().getSize(); i++) {            
            listaPersonas.add(personadao.listar().get(i));
        }
        return listaPersonas;
    }
}
