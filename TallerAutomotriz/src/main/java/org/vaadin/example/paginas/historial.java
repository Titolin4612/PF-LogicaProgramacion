package org.vaadin.example.paginas;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@PageTitle("Historial") // TITULO DE LA PAGINA
@Route("paginas/historial") // RUTA DE LA PAGINA
public class historial extends VerticalLayout {

    public historial() {
        setClassName("HISTORIAL_PRINCIPAL"); // AÑADIR PARA PODER MODIFICAR CSS
        setPadding(false);
        setAlignItems(Alignment.CENTER); // ALINEACION AL CENTRO
        setHeight("100%");


        setHeight("100%"); // USAMOS 100% PARA QUE OCUPE TODA LA ALTURA DE LA PANTALLA
        setWidth("100%"); // PARA QUE OCUPE TODO
        setAlignItems(Alignment.CENTER); // ALINEACION AL CENTRO EN X
        setJustifyContentMode(JustifyContentMode.CENTER); // ALINEACION AL CENTRO EN Y

        // CREACION DE BOTONES BARRA DE NAVEGACION

        // BOTON PARA REDIRECCION VOLVER AL INICIO
        Button volverAlInicio = new Button("Volver al Inicio");
        volverAlInicio.setClassName("boton_volverAlInicio");
        //-------------------------------------------------------------------

        // ACCION PARA QUE EL BOTON VOLVER AL INICIO NOS LLEVE A RUTA RAIZ ("") (PAGINA PRINCIPAL)
        volverAlInicio.addClickListener(clickEvent -> {
            volverAlInicio.getUI().ifPresent(ui -> {
                ui.navigate("");
            });
        });
        //-------------------------------------------------------------------


        // CREACION BOTON PARA VOLVER A LA PESTAÑA SERVICIOS
        Button volverAlCotizar = new Button("Volver a Cotizar");
        volverAlCotizar.setClassName("boton_volverAlCotizar");
        //-------------------------------------------------------------------

        // ACCION DEL BOTON VOLVER A SERVICIOS RUTA
        volverAlCotizar.addClickListener(buttonClickEvent -> {

            UI.getCurrent().navigate("SERVICIOS");

        });
        //-------------------------------------------------------------------

        // CREACION BARRA DE NAVEGACION Y UBICACION DE CONTENEDOR Y BOTONES
        HorizontalLayout barraSuperior = new HorizontalLayout(volverAlInicio, volverAlCotizar); // CREACION CONTENEDOR CON BOTONES
        barraSuperior.setClassName("barraSuperior");
        barraSuperior.setWidth("50%"); // El ancho debe ser 100%
        barraSuperior.setPadding(true);
        barraSuperior.setSpacing(true);
        barraSuperior.setAlignItems(Alignment.CENTER); // Centra los botones verticalmente
        barraSuperior.setJustifyContentMode(JustifyContentMode.CENTER); // Centra los botones horizontalmente

        // AGREGAR LA BARRA A LA PAGINA
                add(barraSuperior);
        //-------------------------------------------------------------------

        // RUTA PARA LA LECTURA Y ESCRITURA DEL ARCHIVO HISTORIAL
        String ruta = "src/main/java/org/vaadin/example/historial.txt";
        //-------------------------------------------------------------------

        // VARIABLES PARA RECORRER EL ARCHIVO
        String linea_actual = "";
        String[] info = {};
        String info_user = "";
        String info_carro = "";
        //-------------------------------------------------------------------

        // CREACION CONTENEDOR MAYOR (EL QUE CONTIENE CADA HISTORIAL)
        VerticalLayout contenedor_mayor = new VerticalLayout(); // Cambiado a VerticalLayout
        contenedor_mayor.setClassName("contenedor_mayor_historial");
        contenedor_mayor.setWidth("50%"); // Ajusta el ancho del contenedor mayor
        contenedor_mayor.setAlignItems(Alignment.CENTER); // Centrar los elementos dentro del contenedor mayor
        //-------------------------------------------------------------------


        // OPERACION PARA LECTURA DEL ARCHIVO
        try {

            FileReader filereader = new FileReader(ruta);
            BufferedReader lector = new BufferedReader(filereader);

            linea_actual = lector.readLine();
            while ((linea_actual = lector.readLine()) != null) {
                info = linea_actual.split(";");

                // Verifica que el array tenga al menos dos elementos
                if (info.length >= 2) {
                    info_user = info[0];
                    info_carro = info[1];
                } else {
                    // Maneja el caso en que la línea no tenga datos de usuario y carro completos
                    info_user = info[0];
                    info_carro = "Información de carro no disponible";
                }

                // CREACION TEXTO PARA EL HISTORIAL
                Paragraph p = new Paragraph("Datos Usuario: " + info_user + "\n Datos Carro: " + info_carro);

                // CREACION DEL CONTENEDOR QUE ALMACENA CADA HISTORIAL
                VerticalLayout contendor_info = new VerticalLayout(p);
                contendor_info.setClassName("contenedores_info_historial");
                contendor_info.setWidth("100%"); // Ajusta el ancho de cada contenedor de información
                contenedor_mayor.add(contendor_info); // AÑADIR EL CONTENEDOR A LA PAGINA HISTORIAL
                // -------------------------------------------------------------------
            }

            lector.close(); // CERRAMOS EL LECTOR

            // CONTROL DE ERRORES DE ARCHIVOS
        } catch (IOException e) {
            e.printStackTrace();
        }
            //-------------------------------------------------------------------


        add(contenedor_mayor); // AÑADIR CONTENEDOR A LA PAGINA
    }
}
