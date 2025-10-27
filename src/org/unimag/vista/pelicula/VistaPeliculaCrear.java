package org.unimag.vista.pelicula;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import org.unimag.controlador.genero.GeneroControladorListar;
import org.unimag.controlador.pelicula.PeliculaControladorGrabar;
import org.unimag.dto.GeneroDto;
import org.unimag.dto.PeliculaDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaPeliculaCrear extends StackPane {

    private static final int H_GAP = 10;
    private static final int V_GAP = 20;
    private static final int ALTO_FILA = 40;
    private static final int ALTO_CAJA = 35;
    private static final int TAMANIO_FUENTE = 20;
    private static final double ANCHO = 0.8;

    private static final double AJUSTE_TITULO = 0.1;

    private final GridPane miGrilla;
    private final Rectangle miMarco;

    private TextField txtNombrePelicula;
    private TextField txtNombreProtagonista;
    private ComboBox<GeneroDto> cbmGeneroPelicula;
    private RadioButton rbtRestriccion1;
    private RadioButton rbtRestriccion2;
    private TextField txtPresupuesto;
    private ToggleGroup grupoRadioBoton;

    public VistaPeliculaCrear(Stage esce, double ancho, double alto) {
        
        setAlignment(Pos.CENTER);

        miGrilla = new GridPane();

        miMarco = Marco.crear(esce, Configuracion.MARCO_ALTO_PORCENTAJE,
                Configuracion.MARCO_ANCHO_PORCENTAJE,
                Configuracion.DEGRADE_ARREGLO_PELICULA,
                Configuracion.DEGRADE_BORDE);

        getChildren().add(miMarco);

        configurarMiGrilla(ancho, alto);
        crearTitulo();
        crearFormulario();
        colocarFrmElegante();
        getChildren().add(miGrilla);
    }

    private void configurarMiGrilla(double ancho, double alto) {

        double miAnchoGrilla = ancho * Configuracion.GRILLA_ANCHO_PORCENTAJE;

        miGrilla.setHgap(H_GAP);
        miGrilla.setVgap(V_GAP);
        miGrilla.setPrefSize(miAnchoGrilla, alto);
        miGrilla.setMinSize(miAnchoGrilla, alto);

        miGrilla.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        ColumnConstraints col0 = new ColumnConstraints();
        ColumnConstraints col1 = new ColumnConstraints();

        col0.setPrefWidth(250);
        col1.setPrefWidth(400);
        col1.setHgrow(Priority.ALWAYS);

        miGrilla.getColumnConstraints().addAll(col0, col1);

        for (int i = 0; i < 9; i++) {
            RowConstraints fila = new RowConstraints();
            fila.setMinHeight(ALTO_FILA);
            fila.setMaxHeight(ALTO_FILA);
            miGrilla.getRowConstraints().add(fila);
        }
    }

    private void crearTitulo() {
        Text miTitulo = new Text("FORMULARIO - CREAR PELÍCULA");

        miTitulo.setFill(Color.web("#ffffff"));

        miTitulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));

        GridPane.setHalignment(miTitulo, HPos.CENTER);

        GridPane.setMargin(miTitulo, new Insets(30, 0, 0, 0));

        miGrilla.add(miTitulo, 0, 0, 2, 1);
    }

    private void crearFormulario() {

        Label lblNombre = new Label("Nombre");
        lblNombre.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblNombre, 0, 2);

        txtNombrePelicula = new TextField();
        txtNombrePelicula.setPromptText("Digita la pelicula");
        GridPane.setHgrow(txtNombrePelicula, Priority.ALWAYS);
        txtNombrePelicula.setPrefHeight(ALTO_CAJA);
        miGrilla.add(txtNombrePelicula, 1, 2);

        
        Label lblProtagonista = new Label("Nombre del Protagonista");
        lblProtagonista.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblProtagonista, 0, 3);

        txtNombreProtagonista = new TextField();
        txtNombreProtagonista.setPromptText("Digita el protagonista");
        GridPane.setHgrow(txtNombreProtagonista, Priority.ALWAYS);
        txtNombreProtagonista.setPrefHeight(ALTO_CAJA);
        miGrilla.add(txtNombreProtagonista, 1, 3);

        
        Label lblEstado = new Label("Género");
        lblEstado.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblEstado, 0, 4);

        //ACÁ VIENE LO DEL COMBO
        List<GeneroDto> arrGeneros = GeneroControladorListar.obtenerGenerosActivos();
        //que el código inicie en 0
        GeneroDto opcionInicial = new GeneroDto(0, "Seleccione genero", true, (short)0);
        arrGeneros.add(0, opcionInicial);
        
        cbmGeneroPelicula = new ComboBox<>();
        cbmGeneroPelicula.setMaxWidth(Double.MAX_VALUE);
        cbmGeneroPelicula.setPrefHeight(ALTO_CAJA);

        //Listas que usa javafx para manipular la imformación
        ObservableList<GeneroDto> items = FXCollections.observableArrayList(arrGeneros);
        
        cbmGeneroPelicula.setItems(items);

        cbmGeneroPelicula.getSelectionModel().select(0);
        miGrilla.add(cbmGeneroPelicula, 1, 4);

        
        Label lblPresupuesto = new Label("Presupuesto");
        lblPresupuesto.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblPresupuesto, 0, 5);

        txtPresupuesto = new TextField();
        txtPresupuesto.setPromptText("Digita el presupuesto");
        GridPane.setHgrow(txtPresupuesto, Priority.ALWAYS);
        txtPresupuesto.setPrefHeight(ALTO_CAJA);
        miGrilla.add(txtPresupuesto, 1, 5);
        
        
        Label lblRestriccion = new Label("Restriccion de Edad");
        lblRestriccion.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblRestriccion, 0, 6);
        
        
        rbtRestriccion1 = new RadioButton("Infantil");
        rbtRestriccion2 = new RadioButton("Mayor de 12 años");
        grupoRadioBoton = new ToggleGroup();
        rbtRestriccion1.setMaxWidth(Double.MAX_VALUE);
        rbtRestriccion2.setMaxWidth(Double.MAX_VALUE);
        rbtRestriccion1.setToggleGroup(grupoRadioBoton);
        rbtRestriccion2.setToggleGroup(grupoRadioBoton);
        
        miGrilla.add(rbtRestriccion1, 1, 6);
        miGrilla.add(rbtRestriccion2, 1, 7);
        
        Button btnGrabar = new Button("Grabar Pelicula");
        btnGrabar.setTextFill(Color.web(Configuracion.MORADO_OSCURO));
        btnGrabar.setMaxWidth(Double.MAX_VALUE);
        btnGrabar.setFont(Font.font("Times New Roman", TAMANIO_FUENTE));
        btnGrabar.setOnAction((e) -> {
            guardarPelicula();
        });
        miGrilla.add(btnGrabar, 1, 8);
    }

    private GeneroDto obtenerGenero(){
        GeneroDto seleccionado = cbmGeneroPelicula.getSelectionModel().getSelectedItem();
        
        if (seleccionado != null && seleccionado.getIdGenero() != 0) {
            
            return seleccionado;
        }
              
        return null;
    }
    
    
    private Boolean obtenerRestriccion() {
        if (rbtRestriccion1.isSelected()) {
            return false;
        }
        if (rbtRestriccion2.isSelected()) {
            return true;
        }
        return null;
    }
    
    private void limpiarFormulario() {
        txtNombrePelicula.setText("");
        cbmGeneroPelicula.getSelectionModel().select(0);
        txtNombreProtagonista.setText("");
        txtPresupuesto.setText("");
        rbtRestriccion1.setSelected(false);
        rbtRestriccion2.setSelected(false);
    }

    private Boolean formularioCompleto() {

        if (txtNombrePelicula.getText().isBlank()) {

            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(), "Alerta", "Agrega un nombre");
            txtNombrePelicula.requestFocus();
            return false;
        }

        if (txtNombreProtagonista.getText().isBlank()) {

            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(), "Alerta", "Agrega un protagonista");
            txtNombreProtagonista.requestFocus();
            return false;
        }

        if (cbmGeneroPelicula.getSelectionModel().getSelectedIndex() == 0) {

            Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Escoge un genero");
            cbmGeneroPelicula.requestFocus();
            return false;
        }
        
        if (txtPresupuesto.getText().isBlank()) {

            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(), "Alerta", "Agrega un presupuesto");
            txtNombreProtagonista.requestFocus();
            return false;
        }

        if (!txtPresupuesto.getText().matches("\\d+(\\.\\d+)?")) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(), "Alerta", "Sólo puedes ingresar números en el presupuesto (Usa ´.´ para decimal) ");
            txtPresupuesto.requestFocus();
            return false;
        }
        
        if (obtenerRestriccion()== null) {

            Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Escoge una restricción");
            cbmGeneroPelicula.requestFocus();
            return false;
        }

        return true;
    }
    
    private void guardarPelicula() {

        if (formularioCompleto()) {

            PeliculaDto dto = new PeliculaDto();
            
            dto.setNombrePelicula(txtNombrePelicula.getText());
            dto.setProtagonistaPelicula(txtNombreProtagonista.getText());
            dto.setGeneroPelicula(obtenerGenero());
            dto.setRestriccionEdadPelicula(obtenerRestriccion());
            dto.setPresupuestoPelicula(Double.valueOf(txtPresupuesto.getText()));
   
            if (PeliculaControladorGrabar.crearPelicula(dto)) {
                Mensaje.mostrar(Alert.AlertType.INFORMATION, null, "Exito", "La información ha sido guardada exitosamente");
                txtNombrePelicula.requestFocus();
                txtNombreProtagonista.requestFocus();
                txtPresupuesto.requestFocus();
                limpiarFormulario();
            } else {
                Mensaje.mostrar(Alert.AlertType.ERROR, null, "Exito", "La información no ha podido ser guardada");
                txtNombrePelicula.requestFocus();
                txtNombreProtagonista.requestFocus();
                txtPresupuesto.requestFocus();
            }  
        }
    }

    private void colocarFrmElegante() {
        Runnable calcular = () -> {

            double alturaMarco = miMarco.getHeight();

            if (alturaMarco > 0) {

                double desplazamiento = alturaMarco * AJUSTE_TITULO;
                miGrilla.setTranslateY(-alturaMarco / 2 + desplazamiento);
            }
        };

        calcular.run();

        miMarco.heightProperty().addListener((obs, antes, despues) -> {
            calcular.run();
        });

    }
}
