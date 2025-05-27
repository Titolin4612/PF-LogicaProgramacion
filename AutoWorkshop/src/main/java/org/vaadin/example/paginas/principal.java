package org.vaadin.example.paginas;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Taller Automotriz") // TITULO DE PAGINA
@Route("")                      // RUTE RAIZ POR ESO VACIA ("")
public class principal extends VerticalLayout {


    public principal() {
        setSpacing(false);
        setPadding(false);

        // STRING ENCARGADO DE ALMACENAR LA RUTA PARA EL EMBED DE GOOGLE MAPS
        String src_mapa = "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d964.1090823392029!2d-75.586744418489!3d6.223160267260505!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8e4429c608b3818f%3A0xc28e18a55cae0f12!2sServicio%20Automotriz%20Luiscar!5e1!3m2!1ses!2sco!4v1731645580975!5m2!1ses!2sco";
        //-------------------------------------------------------------------

        //COMPONENTES -------------------------------------------------------

        // TITULOS ----------------------------------------------------------
        H1 titulo_presentacion = new H1("TALLER AUTOMOTRIZ"); // TITULO PRINCIPAL (TALLER AUTOMORIZ)
        titulo_presentacion.setClassName("h1-h2-generico-titulo-principal");

        H2 subtitulo = new H2("Especializados en darte el mejor servicio"); // SUBTITULO PRINCIPAL (ACOMPAÑA EL TITULO PRINCIPAL)
        subtitulo.setClassName("h1-h2-generico");

        H1 titulo_info_taller = new H1("Informacion"); // TITULO PARA EL CONTENEDOR DE INFORMACION
        titulo_info_taller.setClassName("h1-h2-generico-negro");

        // CONTENIDO DEL CONTENEDOR DE INFORMACION (SE COMPLEMETO EL TEXTO CON AYUDA DE LA IA)
        H3 texto_info_segundo = new H3("Somos un taller automotriz especializado, fundado con el propósito de ofrecer a nuestros clientes una experiencia inigualable en el cuidado y mantenimiento de sus vehículos. Desde nuestra fundación en 2014, en Medellín, hemos crecido y evolucionado, posicionándonos como el taller de referencia en el área metropolitana. Nos destacamos por nuestra dedicación, profesionalismo y el compromiso con la satisfacción total de nuestros clientes. \n" +
                                        "Horarios de atención: Estamos disponibles de lunes a domingo, de 7:00 a.m. a 6:00 p.m. para agendar tu cita. Si necesitas otro horario, contáctanos y te ayudaremos a coordinar la mejor opción. Contáctanos: Llama al +57 313-22845438 y con gusto programaremos tu cita.");
        texto_info_segundo.setClassName("h3-h4-generico-negro");

        // CREACION BOTONES PANEL SUPERIOR ---------------------------------

        Button boton1_panelsup = new Button("Info"); // BOTON INFORMACION (SCROLL)
        boton1_panelsup.setClassName("boton_panesup_generico");

        Button boton2_historial= new Button("Historial"); // BOTON REDIRECCION A HISTORIAL
        boton2_historial.setClassName("boton_panesup_generico");

        Icon icono = new Icon(VaadinIcon.USER); // ICONO PROPIO LIBRERIA VAADIN PARA BOTON COTIZAR
        icono.setClassName("icono");

        Button cotizar_principal = new Button("Cotizar", icono); // CREACION BOTON COTIZAR (REDIRECCION A PESTAÑA SERVICIOS)
        cotizar_principal.setClassName("boton_cotizar_principal");

        //-------------------------------------------------------------------

        IFrame mapa = new IFrame(src_mapa); // INTEGRACION PARA GOOGLE MAPS (EMBED)
        mapa.setClassName("mapa_googlemaps");


        // CONTENEDORES --------------------------------------------------

        VerticalLayout subcontenedor_segundoconte = new VerticalLayout(titulo_info_taller, texto_info_segundo); // CONTENEDOR INFORMACION TALLER PARTE INFERIOR PAGINA PRINCIPAL
        subcontenedor_segundoconte.setWidthFull();
        subcontenedor_segundoconte.setClassName("subcontenedor_segundo");


        HorizontalLayout segundo_contenedor = new HorizontalLayout(mapa,subcontenedor_segundoconte); // CONTENEDOR INTEGRACION CON GOOGLE MAPS
        segundo_contenedor.setWidthFull();
        segundo_contenedor.setHeight("500px");
        segundo_contenedor.setAlignItems(Alignment.CENTER);
        segundo_contenedor.setClassName("segundo_contenedor");


        HorizontalLayout panel_superior = new HorizontalLayout(boton1_panelsup, boton2_historial); // CONTENEDOR DE BOTONES PARTE SUPERIOR DE LA PAGINA PRINCIPAL

        panel_superior.setPadding(false);
        panel_superior.setSpacing(false);
        panel_superior.setWidthFull();
        panel_superior.setHeight("60px");
        panel_superior.setClassName("panel_superior");




        VerticalLayout titulos_botones = new VerticalLayout(titulo_presentacion, subtitulo, cotizar_principal); // CONTENEDOR PRINCIPAL DEL CENTRO
        titulos_botones.setAlignItems(Alignment.CENTER);
        titulos_botones.setSpacing(false);
        titulos_botones.setClassName("contenedor-titulo-y-cotizar");
        titulos_botones.setWidth("50%");



        VerticalLayout contenedor_titulo_presentacion = new VerticalLayout(titulos_botones);    // CONTENEDOR BACKGROUND IMAGE
        contenedor_titulo_presentacion.setWidthFull();
        contenedor_titulo_presentacion.setHeight("950px");
        contenedor_titulo_presentacion.setAlignItems(Alignment.CENTER);
        contenedor_titulo_presentacion.setClassName("contenedor-principal-presentacion");

        //-------------------------------------------------------------------


        //ACCION BOTONES-----------------------------------------------

        // BOTTON SCROLL INFORMACION
        boton1_panelsup.addClickListener(buttonClickEvent -> {
            // Usamos scrollIntoView() para desplazar la vista hasta el contenedor de "Informacion"
            subcontenedor_segundoconte.getElement().scrollIntoView();  // DESPLAZA HACIA EL CONTENEDOR DE INFORMACION
        });
        //-------------------------------------------------------------------

        // BOTON REDIRECCION PARA LA PESTAÑA SERVICIOS
        cotizar_principal.addClickListener(buttonClickEvent -> {


            UI.getCurrent().navigate("SERVICIOS"); // REDIRECCION A SERVICIOS (RUTA)

        });

        //-------------------------------------------------------------------

        // BOTTON REDIRECCION PARA LA PESTAÑA DE HISTORIAL
        boton2_historial.addClickListener(ButtonClickEvent -> {


            UI.getCurrent().navigate("paginas/historial"); // REDIRECCION A HISTORIAL (RUTA)

        });

        //-------------------------------------------------------------------



        //---------------------------------------------
        add(panel_superior,contenedor_titulo_presentacion,segundo_contenedor); // AÑADIR TODOS LOS CONTENEDORES A LA PAGINA


    }
}