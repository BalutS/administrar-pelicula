package org.unimag.vista.gestor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.unimag.controlador.genero.GeneroVistasControlador;
import org.unimag.controlador.pelicula.PeliculaVistasControlador;
import org.unimag.recurso.constante.Configuracion;

public class VistaCabecera extends HBox {

    private final int menuAncho = 150;
    private final int menuAlto = 35;
    private final int menuEspacio = 10;

    private final Stage miEscenario;
    private final BorderPane miPanelPrincipal;

    public VistaCabecera(
            Stage esce,
            BorderPane bpan,
            double altoCabecera
    ) {

        miEscenario = esce;
        miPanelPrincipal = bpan;
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(menuEspacio);
        setPadding(new Insets(0, 20, 0, 20));
        setPrefHeight(altoCabecera);
        setStyle(Configuracion.CABECERA_ESTILO_FONDO);

        crearMenus();
    }

    private void agregarBotonMenu(MenuButton miMenu) {
        miMenu.setCursor(Cursor.HAND);
        miMenu.setPrefWidth(menuAncho);
        miMenu.setPrefHeight(menuAlto);
        getChildren().add(miMenu);
    }

    private void crearMenus() {
        menuGenero();
        menuPelicula();
        menuSalas();
        menuBoletas();
    }

    private void menuGenero() {
        MenuItem opcion1 = new MenuItem("Crear Género");
        MenuItem opcion2 = new MenuItem("Listar Géneros");
        MenuItem opcion3 = new MenuItem("Administrar Géneros");
        MenuItem opcion4 = new MenuItem("Carrusel");

        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    GeneroVistasControlador.crearGen(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );

        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    GeneroVistasControlador.ListarGenero(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    GeneroVistasControlador.administrarGenero(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir formulario Carrusel");
        });

        MenuButton miBoton = new MenuButton("Géneros");

        miBoton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarBotonMenu(miBoton);
    }

    private void menuPelicula() {
        MenuItem opcion1 = new MenuItem("Crear Película");
        MenuItem opcion2 = new MenuItem("Listar Película");
        MenuItem opcion3 = new MenuItem("Administrar Película");
        MenuItem opcion4 = new MenuItem("Carrusel");

        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    PeliculaVistasControlador.crearPelicula(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    PeliculaVistasControlador.ListarPelicula(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            System.out.println("Abrir formulario Administrar Película");
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir formulario Película");
        });

        MenuButton miBoton = new MenuButton("Películas");

        miBoton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarBotonMenu(miBoton);
    }

    private void menuSalas() {
        MenuItem opcion1 = new MenuItem("Crear Sala");
        MenuItem opcion2 = new MenuItem("Listar Sala");
        MenuItem opcion3 = new MenuItem("Administrar Sala");
        MenuItem opcion4 = new MenuItem("Carrusel");

        opcion1.setOnAction((e) -> {
            System.out.println("Abrir formulario Crear Sala");
        });

        opcion2.setOnAction((e) -> {
            System.out.println("Abrir formulario Listar Sala");
        });

        opcion3.setOnAction((e) -> {
            System.out.println("Abrir formulario Administrar Sala");
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir formulario Carrusel");
        });

        MenuButton miBoton = new MenuButton("Salas");

        miBoton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarBotonMenu(miBoton);
    }

    private void menuBoletas() {
        MenuItem opcion1 = new MenuItem("Crear Boleta");
        MenuItem opcion2 = new MenuItem("Listar Boleta");
        MenuItem opcion3 = new MenuItem("Administrar Boleta");
        MenuItem opcion4 = new MenuItem("Carrusel");

        opcion1.setOnAction((e) -> {
            System.out.println("Abrir formulario Crear Boleta");
        });

        opcion2.setOnAction((e) -> {
            System.out.println("Abrir formulario Listar Boleta");
        });

        opcion3.setOnAction((e) -> {
            System.out.println("Abrir formulario Administrar Boleta");
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir formulario Carrusel");
        });

        MenuButton miBoton = new MenuButton("Boletas");

        miBoton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarBotonMenu(miBoton);
    }

}
