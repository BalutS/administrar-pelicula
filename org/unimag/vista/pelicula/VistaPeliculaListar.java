package org.unimag.vista.pelicula;

import java.text.DecimalFormat;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.unimag.controlador.pelicula.PeliculaControladorListar;
import org.unimag.dto.GeneroDto;
import org.unimag.dto.PeliculaDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;

public class VistaPeliculaListar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<PeliculaDto> miTabla;

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_DERECHA = "-fx-alignment: CENTER-RIGHT;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";
    private static final String ESTILO_ROJO_IZQUIERDA = "-fx-text-fill: red;" + ESTILO_IZQUIERDA;
    private static final String ESTILO_VERDE_IZQUIERDA = "-fx-text-fill: green;" + ESTILO_IZQUIERDA;

    private static final String ESTILO_ROJO_CENTRADO = "-fx-text-fill: red;" + ESTILO_CENTRAR;
    private static final String ESTILO_VERDE_CENTRADO = "-fx-text-fill: green;" + ESTILO_CENTRAR;

    public VistaPeliculaListar(Stage ventanaPadre, double ancho, double alto) {
        setAlignment(Pos.CENTER);
        miEscenario = ventanaPadre;
        marco = Marco.crear(miEscenario,
                Configuracion.MARCO_ALTO_PORCENTAJE,
                Configuracion.MARCO_ANCHO_PORCENTAJE,
                Configuracion.DEGRADE_ARREGLO_GENERO,
                Configuracion.DEGRADE_BORDE
        );

        miTabla = new TableView<>();
        cajaVertical = new VBox(20);
        getChildren().add(marco);

        configurarCajaVertical();
        crearTitulo();
        crearTabla();

    }

    private void configurarCajaVertical() {
        cajaVertical.setAlignment(Pos.TOP_CENTER);
        cajaVertical.prefWidthProperty().bind(miEscenario.widthProperty());
        cajaVertical.prefHeightProperty().bind(miEscenario.heightProperty());
    }

    private void crearTitulo() {
        Region bloqueSeparador = new Region();
        bloqueSeparador.prefHeightProperty().bind(
                miEscenario.heightProperty().multiply(0.05));

        int cant = PeliculaControladorListar.obtenerCantidadPelicula();
        Text titulo = new Text("LISTA PELICULAS (" + cant + ")");
        titulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        titulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<PeliculaDto, Integer> crearColumnaCodigo() {
        TableColumn<PeliculaDto, Integer> columna = new TableColumn<>("Código");
        columna.setCellValueFactory(new PropertyValueFactory<>("idPelicula"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.15));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<PeliculaDto, String> crearColumnaNombre() {
        TableColumn<PeliculaDto, String> columna = new TableColumn<>("Nombre");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombrePelicula"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.25));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<PeliculaDto, String> crearColumnaProtagonista() {
        TableColumn<PeliculaDto, String> columna = new TableColumn<>("Protagonista");
        columna.setCellValueFactory(new PropertyValueFactory<>("protagonistaPelicula"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.25));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<PeliculaDto, String> crearColumnaGenero() {
        TableColumn<PeliculaDto, String> columna = new TableColumn<>("Género (estado)");

        columna.setCellValueFactory(cellData -> {
            GeneroDto genero = cellData.getValue().getGeneroPelicula();
            if (genero != null) {
                String nombre = genero.getNombreGenero();
                Boolean estado = genero.getEstadoGenero();
                Short cant = genero.getCantidadPeliculaGenero();
                String textoGenero = (estado != null && estado) ? "Activo" : "Inactivo";
                return new SimpleStringProperty(nombre + " (" + textoGenero + " - " + cant + ")");
            } else {
                return new SimpleStringProperty("-");
            }
        });

        columna.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    if (item.contains("Activo")) {
                        setStyle(ESTILO_VERDE_IZQUIERDA);
                    } else if (item.contains("Inactivo")) {
                        setStyle(ESTILO_ROJO_IZQUIERDA);
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.4));
        return columna;
    }

    private TableColumn<PeliculaDto, Double> crearColumnaPresupuesto() {
        TableColumn<PeliculaDto, Double> columna = new TableColumn<>("Presupuesto");
        columna.setCellValueFactory(new PropertyValueFactory<>("presupuestoPelicula"));

        columna.setCellFactory(col -> new TableCell<PeliculaDto, Double>() {
            private final DecimalFormat formato = new DecimalFormat("#.#############");

            @Override
            protected void updateItem(Double precio, boolean empty) {
                super.updateItem(precio, empty);
                if (empty || precio == null) {
                    setText(null);
                } else {
                    setText(formato.format(precio));
                }
            }
        });

        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.25));
        columna.setStyle(ESTILO_DERECHA);

        return columna;
    }

    private TableColumn<PeliculaDto, Boolean> crearColumnaRestriccion() {
        TableColumn<PeliculaDto, Boolean> columna = new TableColumn<>("Adultos");
        columna.setCellValueFactory(new PropertyValueFactory<>("restriccionEdadPelicula"));

        columna.setCellFactory(col -> new TableCell<PeliculaDto, Boolean>() {
            @Override
            protected void updateItem(Boolean valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(valor ? "Si" : "No");
                    setStyle(valor ? ESTILO_VERDE_CENTRADO : ESTILO_ROJO_CENTRADO);
                }
            }
        });

        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.25));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaCodigo(),
                        crearColumnaNombre(),
                        crearColumnaProtagonista(),
                        crearColumnaGenero(),
                        crearColumnaPresupuesto(),
                        crearColumnaRestriccion()
                ));
    }

    private void crearTabla() {
        configurarColumnas();

        List<PeliculaDto> arrPelicula = PeliculaControladorListar.obtenerPelicula();
        ObservableList<PeliculaDto> datosTabla = FXCollections.observableArrayList(arrPelicula);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay peliculas registradas"));

        miTabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        miTabla.maxWidthProperty().bind(miEscenario.widthProperty().multiply(0.60));
        miTabla.maxHeightProperty().bind(miEscenario.heightProperty().multiply(0.50));

        miEscenario.heightProperty().addListener((o, oldVal, newVal)
                -> miTabla.setPrefHeight(newVal.doubleValue()
                ));
        VBox.setVgrow(miTabla, Priority.ALWAYS);

        cajaVertical.getChildren().add(miTabla);
        getChildren().add(cajaVertical);
    }

}
