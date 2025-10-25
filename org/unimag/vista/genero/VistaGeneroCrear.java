package org.unimag.vista.genero;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.unimag.controlador.genero.GeneroControladorGrabar;
import org.unimag.dto.GeneroDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaGeneroCrear extends StackPane {
    

    private static final int H_GAP = 10; 
    private static final int V_GAP = 20;
    private static final int ALTO_FILA = 40;
    private static final int ALTO_CAJA = 35;
    private static final int TAMANIO_FUENTE = 20;
    private static final double ANCHO = 0.8;
    
    private static final double AJUSTE_TITULO = 0.1;

    private final GridPane miGrilla;
    private final Rectangle miMarco;

    private TextField txtNombreGenero;
    private ComboBox<String> cbmEstadoGenero;
    

    public VistaGeneroCrear(Stage esce, double ancho, double alto) {
                
        setAlignment(Pos.CENTER); 
        
        miGrilla = new GridPane();

        miMarco = Marco.crear(esce,Configuracion.MARCO_ALTO_PORCENTAJE, 
                Configuracion.MARCO_ANCHO_PORCENTAJE, 
                Configuracion.DEGRADE_ARREGLO_GENERO, 
                Configuracion.DEGRADE_BORDE);
        

        getChildren().add(miMarco);
        
        configurarMiGrilla(ancho, alto);
        crearTitulo();
        crearFormulario();
        colocarFrmElegante();
        getChildren().add(miGrilla);
    }

    private void configurarMiGrilla(double ancho, double alto) {
        
        double miAnchoGrilla = ancho*Configuracion.GRILLA_ANCHO_PORCENTAJE;
        
        miGrilla.setHgap(H_GAP);
        miGrilla.setVgap(V_GAP);
        miGrilla.setPrefSize(miAnchoGrilla, alto);
        miGrilla.setMinSize(miAnchoGrilla, alto);
        
        miGrilla.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        ColumnConstraints col0 = new ColumnConstraints();
        ColumnConstraints col1 = new ColumnConstraints();

        col0.setPrefWidth(200);
        col1.setPrefWidth(400);
        
        col1.setHgrow(Priority.ALWAYS);
 
        miGrilla.getColumnConstraints().addAll(col0, col1); 

        for (int i = 0; i < 7; i++) {
            RowConstraints fila = new RowConstraints();
            fila.setMinHeight(ALTO_FILA);
            fila.setMaxHeight(ALTO_FILA);
            miGrilla.getRowConstraints().add(fila);
        }
    }


    private void crearTitulo() {
        Text miTitulo = new Text("FORMULARIO - CREAR GÉNERO");      

        miTitulo.setFill(Color.web(Configuracion.MORADO_OSCURO));

        miTitulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));
        
        GridPane.setHalignment(miTitulo, HPos.CENTER);
 
        GridPane.setMargin(miTitulo, new Insets(30, 0, 0, 0));
        
        miGrilla.add(miTitulo, 0, 0, 2, 1);
        
        
    }

    private void crearFormulario() {
     
        Label lblNombre = new Label("Nombre del Género");
        lblNombre.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblNombre, 0, 2);
        
        txtNombreGenero = new TextField();
        txtNombreGenero.setPromptText("Digita el género");
        GridPane.setHgrow(txtNombreGenero, Priority.ALWAYS);        
        txtNombreGenero.setPrefHeight(ALTO_CAJA);        
        miGrilla.add(txtNombreGenero, 1, 2);
        
        Label lblEstado = new Label("Estado del Género");
        lblEstado.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblEstado, 0, 3);
       
        cbmEstadoGenero = new ComboBox<>();
        cbmEstadoGenero.setMaxWidth(Double.MAX_VALUE);
        cbmEstadoGenero.setPrefWidth(ALTO_CAJA);
        cbmEstadoGenero.getItems().addAll("Selecciona un género", "Activo", "Inactivo");
        cbmEstadoGenero.getSelectionModel().select(0);
        miGrilla.add(cbmEstadoGenero, 1, 3);
        
        Button btnGrabar = new Button("Grabar Género");
        btnGrabar.setTextFill(Color.web(Configuracion.MORADO_OSCURO));
        btnGrabar.setMaxWidth(Double.MAX_VALUE);
        btnGrabar.setFont(Font.font("Times New Roman", TAMANIO_FUENTE));
        btnGrabar.setOnAction((e)->{
            guardarGenero();
        });
        miGrilla.add(btnGrabar, 1, 5);
   }
    
    private Boolean obtenerEstado(){
        String miTexto = cbmEstadoGenero.getValue();
        
        if ("Activo".equals(miTexto)) {
            return true;
        }
        
        if ("Inactivo".equals(miTexto)) {
            return false;
        }
        
        return null;
    }
    
    private void limpiarFormulario(){
        txtNombreGenero.setText("");
        cbmEstadoGenero.getSelectionModel().select(0);
        txtNombreGenero.requestFocus();
    }
    
    private Boolean formularioCompleto(){
        
        if (txtNombreGenero.getText().isBlank()) {
            
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(), "Alerta", "Agrega un nombre");
            txtNombreGenero.requestFocus();
            return false;
        }
        
        if (cbmEstadoGenero.getSelectionModel().getSelectedIndex() == 0) {
            
            Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Escoge un estado");
            cbmEstadoGenero.requestFocus();            
            return false;
        }
        
        return true;
    }
    
    private void guardarGenero(){
        
        if (formularioCompleto()) {
            
            GeneroDto dto = new GeneroDto();
            dto.setNombreGenero(txtNombreGenero.getText());
            dto.setEstadoGenero(obtenerEstado());
          
            if (GeneroControladorGrabar.crearGenero(dto)) {
                Mensaje.mostrar(Alert.AlertType.INFORMATION, null, "Exito", "La información ha sido guardada exitosamente");
                txtNombreGenero.requestFocus();
                limpiarFormulario();
            }else {
                Mensaje.mostrar(Alert.AlertType.ERROR, null, "Exito", "La información no ha podido ser guardada");
                txtNombreGenero.requestFocus();
            }
        }
    }


    private void colocarFrmElegante() {
        Runnable calcular = ()->{
            
            double alturaMarco = miMarco.getHeight();
            
            if (alturaMarco > 0) {
                
                double desplazamiento = alturaMarco * AJUSTE_TITULO;
                miGrilla.setTranslateY(-alturaMarco/2 + desplazamiento);
            }
        };
        
        calcular.run();
        
        miMarco.heightProperty().addListener((obs, antes, despues)->{
            calcular.run();
        });

    }
}
