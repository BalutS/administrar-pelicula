package org.unimag.recurso.utilidad;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.unimag.recurso.constante.Persistencia;

public class Icono {

    public static ImageView obtenerIcono(
            String nombreIcono, int alto
    ) {
        String rutaIcono
                = Persistencia.NOMBRE_CARPETA_IMAGENES_INTERNAS + nombreIcono;
        Image iconito = new Image(rutaIcono);
        ImageView vistaIconito = new ImageView(iconito);

        if (alto > 0) {
            vistaIconito.setFitHeight(alto);
        }
        vistaIconito.setPreserveRatio(true);
        vistaIconito.setSmooth(true);
        return vistaIconito;
    }

}
