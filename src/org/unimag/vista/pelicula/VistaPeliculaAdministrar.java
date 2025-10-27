package org.unimag.vista.pelicula;

import java.text.DecimalFormat;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
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
import org.unimag.controlador.pelicula.PeliculaControladorEliminar;
import org.unimag.controlador.pelicula.PeliculaControladorListar;
import org.unimag.dto.PeliculaDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaPeliculaAdministrar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<PeliculaDto> miTabla;

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_DERECHA = "-fx-alignment: CENTER-RIGHT;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";
    private static final String ESTILO_ROJO = "-fx-text-fill: red;" + ESTILO_CENTRAR;
    private static final String ESTILO_VERDE = "-fx-text-fill: green;" + ESTILO_CENTRAR;

    private Text titulo;
    private HBox cajaBotones;

    public VistaPeliculaAdministrar(Stage ventanaPadre, double ancho, double alto) {
        setAlignment(Pos.CENTER);
        miEscenario = ventanaPadre;
        marco = Marco.crear(miEscenario,
                Configuracion.MARCO_ALTO_PORCENTAJE,
                Configuracion.MARCO_ANCHO_PORCENTAJE,
                Configuracion.DEGRADE_ARREGLO_PELICULA,
                Configuracion.DEGRADE_BORDE
        );

        miTabla = new TableView<>();
        cajaVertical = new VBox(20);
        getChildren().add(marco);

        configurarCajaVertical();
        crearTitulo();
        crearTabla();
        losIconosAdmin();
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

        int cant = PeliculaControladorListar.obtenerCantidadPeliculas();
        titulo = new Text("Administrar Películas (" + cant + ")");
        titulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        titulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<PeliculaDto, Integer> crearColumnaId() {
        TableColumn<PeliculaDto, Integer> columna = new TableColumn<>("ID");
        columna.setCellValueFactory(new PropertyValueFactory<>("idPelicula"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.05));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<PeliculaDto, String> crearColumnaNombre() {
        TableColumn<PeliculaDto, String> columna = new TableColumn<>("Nombre");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombrePelicula"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }
    
    private TableColumn<PeliculaDto, String> crearColumnaProtagonista() {
        TableColumn<PeliculaDto, String> columna = new TableColumn<>("Protagonista");
        columna.setCellValueFactory(new PropertyValueFactory<>("protagonistaPelicula"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<PeliculaDto, String> crearColumnaGenero() {
        TableColumn<PeliculaDto, String> columna = new TableColumn<>("Género");
        columna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGeneroPelicula().getNombreGenero()));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.15));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<PeliculaDto, Double> crearColumnaPresupuesto() {
        TableColumn<PeliculaDto, Double> columna = new TableColumn<>("Presupuesto");
        columna.setCellValueFactory(new PropertyValueFactory<>("presupuestoPelicula"));
        columna.setCellFactory(tc -> new TableCell<PeliculaDto, Double>() {
            private final DecimalFormat format = new DecimalFormat("#.#############");

            @Override
            protected void updateItem(Double presupuesto, boolean empty) {
                super.updateItem(presupuesto, empty);
                if (empty || presupuesto == null) {
                    setText(null);
                } else {
                    setText(format.format(presupuesto));
                }
            }
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_DERECHA);
        return columna;
    }

    private TableColumn<PeliculaDto, String> crearColumnaRestriccionEdad() {
        TableColumn<PeliculaDto, String> columna = new TableColumn<>("Restricción Edad");
        columna.setCellValueFactory(obj -> {
            String estado = obj.getValue().getRestriccionEdadPelicula() ? "Sí" : "No";
            return new SimpleStringProperty(estado);
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
                    setStyle("Sí".equals(item) ? ESTILO_ROJO : ESTILO_VERDE);
                }
            }
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.15));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaId(),
                        crearColumnaNombre(),
                        crearColumnaProtagonista(),
                        crearColumnaGenero(),
                        crearColumnaPresupuesto(),
                        crearColumnaRestriccionEdad()
                ));
    }

    private void crearTabla() {
        configurarColumnas();

        List<PeliculaDto> arrPeliculas = PeliculaControladorListar.obtenerPelicula();
        ObservableList<PeliculaDto> datosTabla = FXCollections.observableArrayList(arrPeliculas);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay películas registradas"));

        miTabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        miTabla.maxWidthProperty().bind(miEscenario.widthProperty().multiply(0.80));
        miTabla.maxHeightProperty().bind(miEscenario.heightProperty().multiply(0.50));

        VBox.setVgrow(miTabla, Priority.ALWAYS);

        cajaVertical.getChildren().add(miTabla);
        getChildren().add(cajaVertical);
    }

    private void losIconosAdmin() {
        int ancho = 40;
        int tamanioIconito = 16;
        
        Button btnEliminar = new Button();
        btnEliminar.setPrefWidth(ancho);
        btnEliminar.setCursor(Cursor.HAND);
        btnEliminar.setGraphic(Icono.obtenerIcono(
                Configuracion.ICONO_BORRAR, tamanioIconito));
        btnEliminar.setOnAction((e) -> {
            if (miTabla.getSelectionModel().getSelectedItem() == null) {
                Mensaje.mostrar(Alert.AlertType.WARNING,
                        miEscenario, "Atención", "Debe seleccionar una película.");
            } else {
                PeliculaDto objPeliculita = miTabla.getSelectionModel().getSelectedItem();
                
                String mensaje1 = "¿Está seguro de eliminar la película?";
                String mensaje2 = "\nID: " + objPeliculita.getIdPelicula();
                String mensaje3 = "\nNombre: " + objPeliculita.getNombrePelicula();
                String mensaje4 = "\nEsta acción no se puede deshacer.";

                Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
                msg.setTitle("Confirmar Eliminación");
                msg.setHeaderText(null);
                msg.setContentText(mensaje1 + mensaje2 + mensaje3 + mensaje4);
                msg.initOwner(miEscenario);

                if (msg.showAndWait().get() == ButtonType.OK) {
                    int posi = miTabla.getSelectionModel().getSelectedIndex();
                    if (PeliculaControladorEliminar.borrar(posi)) {
                        
                        List<PeliculaDto> arrPeliculas = PeliculaControladorListar.obtenerPelicula();
                        ObservableList<PeliculaDto> datosTabla = FXCollections.observableArrayList(arrPeliculas);
                        miTabla.setItems(datosTabla);
                        miTabla.refresh();
                        
                        int cant = PeliculaControladorListar.obtenerCantidadPeliculas();
                        titulo.setText("Administrar Películas (" + cant + ")");

                        Mensaje.mostrar(Alert.AlertType.INFORMATION,
                                miEscenario, "Éxito", "La película ha sido eliminada.");
                    } else {
                        Mensaje.mostrar(Alert.AlertType.ERROR,
                                miEscenario, "Error", "Ocurrió un error al eliminar la película.");
                    }
                }
                miTabla.getSelectionModel().clearSelection();
            }
        });

        Button btnActualizar = new Button();
        btnActualizar.setPrefWidth(ancho);
        btnActualizar.setCursor(Cursor.HAND);
        btnActualizar.setGraphic(Icono.obtenerIcono(
                Configuracion.ICONO_EDITAR, tamanioIconito));
        btnActualizar.setOnAction((e) -> {
            System.out.println("Actualizar");
        });

        Button btnCancelar = new Button();
        btnCancelar.setPrefWidth(ancho);
        btnCancelar.setCursor(Cursor.HAND);
        btnCancelar.setGraphic(Icono.obtenerIcono(
                Configuracion.ICONO_CANCELAR, tamanioIconito));
        btnCancelar.setOnAction((e) -> {
            System.out.println("Cancelar");
        });

        cajaBotones = new HBox(5);
        cajaBotones.setAlignment(Pos.CENTER);
        cajaBotones.getChildren().addAll(btnEliminar, btnActualizar, btnCancelar);
        cajaVertical.getChildren().add(cajaBotones);
    }
}
