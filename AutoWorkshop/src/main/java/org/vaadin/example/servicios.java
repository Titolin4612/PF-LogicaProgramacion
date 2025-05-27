package org.vaadin.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;

import com.vaadin.flow.component.checkbox.CheckboxGroup;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route("SERVICIOS") // DEFINICION RUTA DE LA PAGINA SERVICIOS
@PageTitle("SERVICIOS") // DEFINICION TITULO DE PAGINA "SERVICIOS"
public class servicios extends VerticalLayout {


    // DECLARACION VARIABLES GLOBALES PARA FACILITAR SU USO
    public static int valor_total = 0;
    public static double valor_iva = 0;
    public static double valor_total_con_iva = 0;


    public servicios() {
        setClassName("PRINCIPAL_SERVICIOS"); // ASIGNACION NOMBRE PARA CSS
        setPadding(false);
        setAlignItems(Alignment.CENTER);
        setHeight("100%");


        // ARREGLOS NECESARIOS PARA LA PAGINA   -----------------------------------------------------------------------------------
        // ARRAY PARA LOS SERVICIOS DISPONIBLES A MOSTRAR EN LA CHECKBOX LIST
        String[] servicios_q_realizamos = {"Cambio de aceite", "Cambio de frenos", "Mantenemiento de cajas automaticas", "Cambio Filtros AC", "Cambio Alternador", "Cambio de Clutch"};
        // ARRAY PARA LA MULTICOMBOBOX DE LOS INSUMOS USADOS
        String[] insumos = { "Aceite de motor", "Filtro de aceite", "Pastillas de freno", "Líquido de frenos", "Aceite para transmisión", "Filtro de aire", "Gas refrigerante", "Alternador", "Disco de embrague", "Kit de embrague" };
        // ARRAY PARA LOS PREFIJOS SEGUN EL PAIS PARA EL NUMERO TELEFONICO
        String[] prefijos_numeros = {"+1", "+7", "+20", "+27", "+30", "+31", "+32", "+33", "+34", "+36", "+39", "+40", "+41", "+43", "+44", "+45", "+46", "+47", "+48", "+49", "+51", "+52", "+53", "+54", "+55", "+56", "+57", "+58", "+60", "+61", "+62", "+63", "+64", "+65", "+66", "+81", "+82", "+84", "+86", "+90", "+91", "+92", "+93", "+94", "+95", "+98", "+211", "+212", "+213", "+216", "+218", "+220", "+221", "+222", "+223", "+224", "+225", "+226", "+227", "+228", "+229", "+230", "+231", "+232", "+233", "+234", "+235", "+236", "+237", "+238", "+239", "+240", "+241", "+242", "+243", "+244", "+245", "+246", "+248", "+249", "+250", "+251", "+252", "+253", "+254", "+255", "+256", "+257", "+258", "+260", "+261", "+262", "+263", "+264", "+265", "+266", "+267", "+268", "+269", "+290", "+291", "+297", "+298", "+299", "+350", "+351", "+352", "+353", "+354", "+355", "+356", "+357", "+358", "+359", "+370", "+371", "+372", "+373", "+374", "+375", "+376", "+377", "+378", "+380", "+381", "+382", "+383", "+385", "+386", "+387", "+389", "+420", "+421", "+423", "+500", "+501", "+502", "+503", "+504", "+505", "+506", "+507", "+508", "+509", "+590", "+591", "+592", "+593", "+594", "+595", "+596", "+597", "+598", "+599", "+670", "+672", "+673", "+674", "+675", "+676", "+677", "+678", "+679", "+680", "+681", "+682", "+683", "+685", "+686", "+687", "+688", "+689", "+690", "+691", "+692", "+850", "+852", "+853", "+855", "+856", "+880", "+886", "+960", "+961", "+962", "+963", "+964", "+965", "+966", "+967", "+968", "+970", "+971", "+972", "+973", "+974", "+975", "+976", "+977", "+992", "+993", "+994", "+995", "+996", "+998"};
        // ARRAY PARA LAS MARCAS DE CARROS DISPONIBLES A SELECCIONAR
        String[] marca_carros = {"Ford", "Renault", "Chevrolet", "Kia", "BMW", "Toyota", "Mazda"};
        // ARRAY PARA EL TIPO DE VEHICULO DISPONIBLE
        String[] tipo_vehiculo_list = {"Bus", "Campero", "Camionetas", "Automoviles", "Camiones"};
        // ARRAY PARA LOS MODELOS DE LA MARCA CHEVROLET
        String[] chevrolet_mod = {"Spark", "Onix", "Aveo", "Malibu"};
        // ARRAY PARA LOS MODELOS DE LA MARCA FORD
        String[] ford_mod = {"Ford Mustang", "Ford GT", "Ford EcoSport", "Ford Escape", "Ford Bronco", "Ford Bronco Sport", "Ford Edge", "Ford Explorer", "Ford Expedition", "Ford Maverick", "Ford Ranger", "Ford F-150", "Ford F-150 Raptor", "Ford F-150 Lightning", "Ford Super Duty F-250", "Ford Super Duty F-350", "Ford Super Duty F-450", "Ford Mustang Mach-E", "Ford F-150 Lightning", "Ford E-Transit", "Ford Transit", "Ford Transit Connect"};
        // ARRAY PARA LOS MODELOS DE LA MARCA MAZDA
        String[] mazda_mod = {"Mazda2", "Mazda3", "Mazda6"};
        // ARRAY PARA LOS MODELOS DE LA RENAULT
        String[] renault_mod = {"Renault Kwid", "Renault Logan", "Renault Sandero", "Renault Duster", "Renault Captur", "Renault Koleos", "Renault Stepway", "Renault Alaskan", "Renault Kangoo", "Renault Trafic", "Renault Master", "Renault Zoe", "Renault Twizy"};
        // ARRAY PARA LOS MODELOS DE LA KIA
        String[] kia_mod = {"Kia Picanto", "Kia Rio", "Kia Soul", "Kia Seltos", "Kia Sportage", "Kia Sorento", "Kia Carnival", "Kia Telluride", "Kia Stinger", "Kia K5", "Kia Niro", "Kia EV6"};
        // ARRAY PARA LOS MODELOS DE LA BMW
        String[] bmw_mod = {"BMW Serie 1", "BMW Serie 2", "BMW Serie 3", "BMW Serie 4", "BMW Serie 5", "BMW Serie 6", "BMW Serie 7", "BMW Serie 8", "BMW X1", "BMW X2", "BMW X3", "BMW X4", "BMW X5", "BMW X6", "BMW X7", "BMW Z4", "BMW i3", "BMW i4", "BMW iX", "BMW iX3", "BMW M2", "BMW M3", "BMW M4", "BMW M5", "BMW M8"};
        // ARRAY PARA LOS MODELOS DE LA MARCA TOYOTA
        String[] toyota_mod = {"Toyota Yaris", "Toyota Corolla", "Toyota Camry", "Toyota Avalon", "Toyota Supra", "Toyota 86", "Toyota C-HR", "Toyota RAV4", "Toyota Highlander", "Toyota 4Runner", "Toyota Sequoia", "Toyota Land Cruiser", "Toyota Tacoma", "Toyota Tundra", "Toyota Sienna", "Toyota Prius", "Toyota Mirai", "Toyota GR86", "Toyota GR Supra"};
        //----------------------------------------------------------------------------------------------------------


        //CREACION DE BARRA SUPERIOR E INFORMACION SOBRE EL TALLER ----------------------------------------------------------------------------------
        Paragraph info_d_bar = new Paragraph("Lunes - Domingo 7:00AM - 6:00PM    Taller Mecánico en Medellín Servicio Automotriz Especializado    Programe su cita: +57 313-2284538");
        info_d_bar.setClassName("texto_info_arriba");
        //----------------------------------------------------------------------------------------------

        //CREACION BOTONES A USAR BOTONES  ------------------------------------------------------------------------------------------------------
        Button boton_cerrado = new Button(new Icon("lumo", "cross"));//BOTON PARA CERRADO DE NOTIFICACIONES DE CREACION DE FACTURA

        Button crear_orden = new Button("CREAR ORDEN"); // BOTON PARA CREAR NUEVA ORDEN
        crear_orden.setClassName("crear_orden");

        Button historial = new Button("HISTORIAL"); // BOTTON REDIRECCION A LA PESTAÑA HISTORIAL
        historial.setClassName("historial");


        historial.addClickListener(buttonClickEvent -> {            // ACCION DEL BOTON PARA REDIRECCION A PESTAÑA HISTORIAL
            UI.getCurrent().navigate("paginas/historial");  // RUTA A REDIRECCIONAR
        });

        Button inicio = new Button("INICIO"); // BOTON PARA REDIRECCIONAR Y VOLVER A INICIO (PAGINA PRINCIPAL)
        inicio.setClassName("inicio_servicios");
        inicio.addClickListener(clickEvent -> { // ACCION PARA REDIRIJIR A LA PAGINA PRINCIPAL
            inicio.getUI().ifPresent(ui -> {
                ui.navigate("");        // RUTA A SEGUIR PARA IR A PAGINA PRINCIPAL (RAIZ)
            });
        });
        //--------------------------------------------------------------------------------------------------------


        // CREACION TITULOS
        H1 titulo_principal = new H1("TALLER AUTOMOTRIZ");          // CREACION TITULO ENCABEZADO CONTENEDOR CENTRAL
        titulo_principal.setClassName("texto_encabezado_servicios");



        // DEFINICION IMAGEN LOGO --------------------------------------------------------------------------------------------------

        Image logo = new Image("https://i.imgur.com/wESKx6h.png", "Logo Principal"); // IMAGEN DEL LOGO HOSTEADA EN LA PLATAFORMA imgur.com PARA FACILITAR SU USO
        logo.setWidth("60px");
        logo.setHeight("60px");


        //----------------------------------------------------------------------------------------------------------

        // BARRA NAVEGACION---------------------------------------------------------------------------------------------
        HorizontalLayout barra_navegacion = new HorizontalLayout(inicio, historial); // CREACION DE CONTENEDOR BOTONES BARRA DE NAVEGACION

        barra_navegacion.setWidth("60%");
        barra_navegacion.setHeight("60px");
        barra_navegacion.setClassName("barra_navegacion");
        barra_navegacion.setJustifyContentMode(JustifyContentMode.CENTER);
        barra_navegacion.setDefaultVerticalComponentAlignment(Alignment.CENTER);


        HorizontalLayout barra_info = new HorizontalLayout(logo, info_d_bar, logo); // CREACION DE CONTENEDOR PARA ALMACENAR INFORMACION TALLER Y LOGO
        barra_info.setWidthFull();
        barra_info.setHeight("60px");
        barra_info.setClassName("barra_info");
        barra_info.setJustifyContentMode(JustifyContentMode.CENTER);
        barra_info.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        // FIN BARRA NAVEGACION--------------------------------------------------------------------------------------


        // PRIMERA FILA (DATOS USUARIOS) ----------------------------------------------------------------------------------------------

        Paragraph datosPersonales = new Paragraph("Datos personales:"); // LABEL PARA SEÑALAR CAMPOS DE INPUT DATOS USUARIOS
        datosPersonales.setClassName("datosPersonales");

        TextField nombre = new TextField(); // ENTRADA TIPO TEXTFIELD PARA EL NOMBRE
        nombre.setClassName("nombre");
        nombre.setPlaceholder("Nombre:");
        nombre.setPrefixComponent(VaadinIcon.USER.create());
        nombre.setAllowedCharPattern("[A-Za-z ]"); // CONTROL DE ERRORES PARA QUE SOLO ADMITA EL PATRON PREDEFINIDO EN ESTA LINEA


        TextField cedula = new TextField(); //ENTRADA TIPO TEXTFIELD PARA LA CEDULA
        cedula.setClassName("cedula");
        cedula.setPlaceholder("CC: 1020111918");
        cedula.setPrefixComponent(VaadinIcon.CLIPBOARD_USER.create());
        cedula.setMinLength(7);             // CONTROL DE ERRORES MIN 7 CARACTERES
        cedula.setMaxLength(10);            // CONTROL DE ERRORES MAXIMO 10 CARACTERES
        cedula.setAllowedCharPattern("[0-9]"); //LOS VALORES VALIDOS PERMITIDOS DENTRO DEL TEXTFIELD
        cedula.setI18n(new TextField.TextFieldI18n() //CONTROL DE ERROR MENSAJE PARA CEDULA
                .setMinLengthErrorMessage("Minimo 7 caracteres")
                .setMaxLengthErrorMessage("Maximo 10 caracteres")
                .setPatternErrorMessage("Solo numeros")
        );


        TextField numero = new TextField(); //ENTRADA TIPO TEXTFIELD PARA EL NUMERO TELEFONICO
        numero.setClassName("numero");
        numero.setPlaceholder("Numero:");
        numero.setPrefixComponent(VaadinIcon.PHONE.create());
        numero.setWidth("150px");
        numero.setMinLength(10);    // CONTROL DE ERRORES PARA QUE SOLO ADMITA MIN 10 CARACTERES
        numero.setMaxLength(10);    // CONTROL DE ERRORES PARA QUE SOLO ADMITA MAX 10 CARACTERES
        numero.setAllowedCharPattern("[0-9]"); // CONTROL DE ERRORES PARA QUE SOLO ADMITA EL PATRON PREDEFINIDO EN ESTA LINEA
        numero.setI18n(new TextField.TextFieldI18n() // CONTROL DE ERRORES PARA INVALIDEZ
                .setMinLengthErrorMessage("Minimo 10 caracteres")
                .setMaxLengthErrorMessage("Maximo 10 caracteres")
        );

        ComboBox<String> prefijo_num = new ComboBox<>(); //EL COMBOBOX PARA EL PREFIJO DEL NUM +57
        prefijo_num.setPlaceholder("Prefijo");
        prefijo_num.setClassName("prefijoNum");
        prefijo_num.setItems(prefijos_numeros); // SE LLAMA EL ARRAY QUE CONTIENE LOS PREFIJOS VALIDOS
        prefijo_num.setWidth("100px");


        HorizontalLayout primera_fila = new HorizontalLayout(datosPersonales,nombre, cedula, prefijo_num, numero); // CREACION CONTENEDOR "PRIMERA FILA" QUE ALMACENA LAS ENTRADAS DE LOS DATOS DE USUARIOS
        primera_fila.setAlignItems(Alignment.BASELINE); // IMPORTANTE PARA ALINEAR TODOS LOS ITEMS DEL LAYOUT

        //FIN PRIMERA FILA (DATOS USUARIOS) -------------------------------------------------------------------------------------------

        //SEPARADORES ---------------------------------------------------------------------------------------------------

        HorizontalLayout separador1 = new HorizontalLayout(); // SEPARADOR 1 PARA DISTANCIA LOS DATOS PERSONALES DE LOS DATOS VEHICULARES, MERAMENTE VISUAL
        separador1.setWidthFull();
        separador1.setHeight("10px");
        separador1.setClassName("separador");

        HorizontalLayout separador2 = new HorizontalLayout(); // SEPARADOR 2 PARA DISTANCIA LOS DATOS VEHICULARES DEL BOTON CREAR ORDEN, MERAMENTE VISUAL
        separador2.setWidthFull();
        separador2.setHeight("10px");
        separador2.setClassName("separador");

        //FIN SEPRADORES ------------------------------------------------------------------------------------------


        //SEGUNDA FILA (DATOS VEHICULARES) ------------------------------------------------------------------------------------------

        Paragraph label_datos_ve = new Paragraph("Datos Vehiculares:"); // LABEL PARA MARCAR LA ZONA DE ENTRADAS PARA LOS DATOS VEHICULARES
        label_datos_ve.setClassName("datosVehiculo");

        ComboBox<String> marca = new ComboBox<>(); // COMBOBOX PARA LA SELECCION DE LA MARCA DE LOS VEHICULO DISPONIBLES
        marca.setClassName("marca");
        marca.setPlaceholder("Marca");
        marca.setItems(marca_carros); // SE LLAMA AL ARRAY PARA SABER CUALES SON LAS MARCAS DISPONIBLES
        marca.setWidth("100px");


        ComboBox<String> modelo = new ComboBox<>(); // COMBOBOX PARA LA SELECCION DE LOS MODELOS O LINEAS DE LOS VEHICULO DISPONIBLES
        modelo.setPlaceholder("Modelo");
        modelo.setClassName("modelo");
        modelo.setPrefixComponent(VaadinIcon.CAR.create());
        modelo.setWidth("auto");

        marca.addValueChangeListener(event -> {    // LISTENER PARA SABER CUAL FUE LA MARCA SELECCIONADA Y TRABAJAR SOBRE ESO
            String valor_marca = marca.getValue();  // VARIABLE QUE GUARDA LO QUE PONGA EL USUARIO

            // VALIDACION PARA AÑADIR LA LISTA DE MODELOS DE AUTO SEGUN LA MARCA SELECCIONADA ANTERIORMENTE

            if(valor_marca.equals("Chevrolet")){

                modelo.setItems(chevrolet_mod);        // LLAMAMOS EL ARRAY DE CHEVROLET

            }else if(valor_marca.equals("Ford")){

                modelo.setItems(ford_mod);              // LLAMAMOS EL ARRAY DE FORD

            }else if(valor_marca.equals("Mazda")){

                modelo.setItems(mazda_mod);             // LLAMAMOS EL ARRAY DE MAZDA

            }else if(valor_marca.equals("Renault")) {

                modelo.setItems(renault_mod);           // LLAMAMOS EL ARRAY DE RENAULT

            }else if(valor_marca.equals("BMW")){

                modelo.setItems(bmw_mod);               // LLAMAMOS EL ARRAY DE BMW

            }else if(valor_marca.equals("Toyota")){

                modelo.setItems(toyota_mod);            // LLAMAMOS EL ARRAY DE TOYOTA

            }else if(valor_marca.equals("Kia")){

                modelo.setItems(kia_mod);               // LLAMAMOS EL ARRAY DE KIA
            }

        });

        //--------------------------------------------------------------------------------------------------------

        // COMBOBOX DE TIPOI DE VEHICULOS ----------------
        ComboBox<String> tipo_vehiculos = new ComboBox<>();   // CREAMOS LA COMBOBOX DE LA LISTA DE TIPOS DE VEHICULOS DISPONIBLES
        tipo_vehiculos.setPlaceholder("Tipo de vehiculo");
        tipo_vehiculos.setClassName("Tipo_de_vehiculo");
        tipo_vehiculos.setItems(tipo_vehiculo_list);           // SE LLAMA EL ARRAY QUE ALMACENA LOS TIPOS DE VEHICULO DISPONIBLES
        tipo_vehiculos.setPrefixComponent(VaadinIcon.CAR.create());
        tipo_vehiculos.setWidth("250");

        ComboBox<String> tipo_combus = new ComboBox<>(); // CREAMOS LA COMBOBOX DE LA LISTA DE TIPOS DE COMBUSTIBLE DISPONIBLES
        tipo_combus.setItems("Gasolina", "Diesel", "Electrico");        // DECLARAMOS DIRECTAMENTE LOS TIPOS DE COMBUSTIBLE DISPONIBLES
        tipo_combus.setClassName("tipoCombustible");
        tipo_combus.setPlaceholder("Tipo de combustible");
        tipo_combus.setWidth("300");


        TextField placa = new TextField();              // ENTRADA DE TEXTO PARA EL INGRESO DE LA PLACA
        placa.setPlaceholder("Placa: AAA123");
        placa.setClassName("placa");
        placa.setPrefixComponent(VaadinIcon.USER_CARD.create());
        placa.setMinLength(6);      // CONTROL DE ERRORES MIN 6
        placa.setMaxLength(6);      // CONTROL DE ERRORES MAX 6
        placa.setAllowedCharPattern("[0-9A-Za-z]"); // CONTROL DE ERRORES PARA QUE SOLO ADMITA NUMEROS DEL 0 AL 9 Y LETRAS DE LA A a LA Z MINUSCULA Y MAYUSCULA
        placa.setPattern("^[A-Za-z]{3}[0-9]{3}$"); //EL PATRON QUE TIENE QUE TENER O SEA [A-Z]{3} QUE LOS PRIMEROS 3 DIGITOS SON DE LA A a la Z Y ASI CON EL OTRO
        placa.setI18n(new TextField.TextFieldI18n()
                .setMinLengthErrorMessage("Minimo 6 caracteres")
                .setMaxLengthErrorMessage("Maximo 6 caracteres")
                .setPatternErrorMessage("PLACA INVALIDA")
        );

        DatePicker fecha = new DatePicker(); // CREACION DE CALENDARIO PARA LA SELECCION DE LA FECHA A USAR
        fecha.setClassName("fecha");
        fecha.setPlaceholder("Fecha");
        fecha.setMin(LocalDate.now()); // Establece la fecha mínima a hoy


        HorizontalLayout conten_datos_vehiculares = new HorizontalLayout(label_datos_ve,marca,modelo,tipo_vehiculos,tipo_combus,placa,fecha); // CREACION DE CONTENEDOR PARA LA ORGANIZACION DE TODOS LAS ENTRADAS DE DATOS VEHICULARES
        conten_datos_vehiculares.setAlignItems(Alignment.BASELINE);


        //FIN SEGUNDA FILA (DATOS VEHICULARES) -------------------------------

        // CONTENEDOR GRANDE ------------------------------------------------------------------------
        HorizontalLayout contenedor_botones = new HorizontalLayout(crear_orden); // CONTENEDOR "FINAL" PUES ES EL QUE ALMACENA EL BOTON DE CREAR ORDEN


        VerticalLayout contendor_total = new VerticalLayout(titulo_principal, primera_fila,separador1,conten_datos_vehiculares,separador2,contenedor_botones);  //CONTENEDOR QUE ORGANIZA T ODO EL PANEL DE ENTREADAS DE TEXTO
        contendor_total.setClassName("contenedor_final");
        contendor_total.setWidth("auto");


        Notification noti_success = new Notification();             // CREACION NOTIFICACION PARA CUANDO SE COTIZA O FACTURA CORRECTAMENTE (SUCCESS)
        noti_success.setPosition(Notification.Position.TOP_END);

        Notification noti_error = new Notification();               // CREACION NOTIFICACION PARA CUANDO SE PRESENT UN ERROR (CAMPOS VACIOS, CAMPOS NO SELECCIONADOS)
        noti_error.setPosition(Notification.Position.TOP_END);


        boton_cerrado.addClickListener(ClickEvent ->{ // ACCION USADA PARA EL BOTON QUE CIERRA LA NOTIFICACION SUCCESS

            noti_success.close();

        });

        // CREACION BOTON CREAR ORDEN -------------------
        crear_orden.addClickListener(event -> { //EL BOTON QUE CREA Y GUARDA DATOS EN EL ARCHVO .TXT---------------------------------



            //OBTENCION DATOS DEL USUARIO PA FACUTRA
            String numero_telf = prefijo_num.getValue() + " " + numero.getValue();
            String fecha_para_txt = "FECHA: " + fecha.getValue();
            String datos_user_guadar = "DATOS USUARIO: " + nombre.getValue() + " -- CC: " + cedula.getValue() + " -- TEL: " + numero_telf;
            String nombre_usuario = nombre.getValue();
            String datos_vehiculo =  "DATOS VEHICULARES: " +" MARCA: " + marca.getValue() + " -- MODELO: " + modelo.getValue() + " -- TIPO: " + tipo_vehiculos.getValue() + " -- COMBUSTIBLE: " + tipo_combus.getValue() + " -- PLACA: " + placa.getValue().toUpperCase();


            // VALIDACION POR SI HAY CAMPOS VACIOS QUE SALGA LA NOTIFICACION Y NO CONTINUE
            if (prefijo_num.getValue() == null || numero.getValue() == null || fecha.getValue() == null || cedula.getValue() == null || numero_telf == null || marca.getValue() == null || modelo.getValue() == null || tipo_vehiculos.getValue() == null || tipo_combus.getValue() == null ||placa.getValue() == null){
                if (noti_error.isOpened()) {
                    noti_error.close();
                }

                // MENSAJE GENERADO POR CONTROL DE ERRORES CAMPOS VACIOS NOTIFICACION
                Paragraph error_null = new Paragraph("Asegurece de rellenar todos los campos");
                noti_error.removeAll();                                                     // CONTROL DE ERRORES PARA EVITAR QUE SE ACUMULEN MUCHAS NOTIFICACIONES, SE LOGRÒ CON AYUDA DE LA IA
                noti_error.addThemeVariants(NotificationVariant.LUMO_ERROR);
                noti_error.add(error_null);
                noti_error.setClassName("error_null");
                noti_error.open();

                noti_error.setDuration(2000);                                               // LA IA ChatGPT 4.0 NOS AYUDO A HACER QUE LA NOTIFICACION SE CERRARA AUTOMATICAMENTE Y NO FUERA NECESARIO UN BOTON

                return;         // RETURN QUE ACTUA COMO BREAK; PARA EVITAR QUE CONTINUE
            }

            //--------------------------------------------------------------------------------------------------------

            // VARIABLES QUE AYUDAN CON EL FORMATO PARA AÑADIR A LA PESTAÑA DE HISTORIAL
            String datos_user_historial = nombre_usuario + " - CC: " + cedula.getValue() + " - TEL: " + numero_telf;  // VARIABLE DEDICADA A DATOS USUARIOS
            String datos_vehiculo_historial = " MARCA: " + marca.getValue() + "- MODELO: " + modelo.getValue() + " - TIPO:" + tipo_vehiculos.getValue() + " - COMBUSTIBLE:" + tipo_combus.getValue() + " - PLACA:" + placa.getValue().toUpperCase(); // VARIABLE DEDICADA A DATOS VEHICULO
            //--------------------------------------------------------------------------------------------------------


            // CONTROL DE ERRORES EN LA GENERACION DEL DIALOGO PARA LA COTIZACION Y GENERACION DE FACTURA

            try {


                Dialog dialogo_orden = new Dialog();  //CREACION DE EL MENU DE DIALOGO DE CREACION DE ORDEN, FACTURA Y COTIZACION
                dialogo_orden.setClassName("dialogoOrden");
                dialogo_orden.setHeaderTitle("NUEVA ORDEN");

                Button cerra_dialogo = new Button("Cerrar",e -> dialogo_orden.close() );  // ACCION QUE REALIZA EL BOTON PARA CERRA LA VENTANA DE DIALOGO
                Button enviar = new Button("Enviar");                                     // CREACION BOTON PARA ENVIAR LA FACTURA
                Button cotizar_dialogo = new Button("Cotizar");                           // CREACION PARA GENERAR LA COTIZACION

                CheckboxGroup servicios_check = new CheckboxGroup();                           // CHECKBOX GROUP PARA LA SELECCION DE LOS SERVICCIOS DISPONIBLES Y REALIZADOS
                servicios_check.setLabel("Servicios Realizados:");
                servicios_check.setItems(servicios_q_realizamos);                               // SE LLAMA EL ARRAY PARA TENER LOS SERVICIOS DISPONIBLES
                servicios_check.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);           // VARIANTE DE TEMA PA QUE SE VEA MÀS NITIDO



                    MultiSelectComboBox <String> insumosComb = new MultiSelectComboBox<>("Insumos Consumidos");    // CON AYUDA DE LA IA Chat GPT 4.o SE LOGRÓ IMPLEMENTAR LA MULTISELECTBOX PARA LOS INSUMOS,
                    insumosComb.setItems(insumos); // SE LLAMA EL ARRAY DE LOS INSUMOS                                  // DADO QUE SE DIFICULTÓ LA COMOPRENCION DE LA DOCUMENTACION VAADIN

                    TextArea insumos_consumidos = new TextArea("Insumos Consumidos");                              // PROPUESTA REALIZADA POR LA IA PARA EL TEXTO QUE MUESTRA LOS INSUMOS SELECCIONADOS HASTA EL MOMENTO
                    insumos_consumidos.setReadOnly(true);                                                                // SE HACE QUE SOLO SEA DE LECTURA PARA EVITAR MISSCLICKS

                    insumosComb.addValueChangeListener(e -> {                                                            // ACCION PARA QUE RECOLECTE LO SELECCIONADO
                        String insumosSeleccionados = e.getValue().stream().collect(Collectors.joining(", "));      // PROPUESTA GENERADA POR LA IA

                     insumos_consumidos.setValue(insumosSeleccionados);                                                 // QUE SE PONGAN LOS DATOS
                    });


                Details costos_segun = new Details("Costos:");                                                  // COMPONENTE DE VAADIN QUE NOS AYUDA A ABRIR Y CERRAR LA LISTA DESPLEGABLE


                cotizar_dialogo.addClickListener(ClickEvent ->{ // ACCION CUANDO SE LE DA AL BOTON DE COTIZAR

                    costos_segun.removeAll();                   // CONTROL DE ERRORES PARA QUE NO SE NOS ACUMULEN LAS COTIZACIONES Y CADA VEZ QUE SE DE CLICK SE LIMPIE Y VUELVA A GENERAR

                    int valor_total_cotizar = 0;                // REINICIAR VARIABLE PARA EVITAR ACUMULACION DE FATURAS Y DATOS ERRONEOS

                    String servicios_seleccionados = servicios_check.getValue().toString();     // SERVICIOS SELECCIONADOS POR EL USUARIO

                    if (servicios_check.getSelectedItems().isEmpty()){                          // CONTROL DE ERRORES POR SI NO SE HAN SELECCIONADO SERVICIOS (PROPUESTA GENERADA POR IA) (getSelectedItems().isEmpty())
                        if (noti_error.isOpened()) {
                            noti_error.close();
                        }


                        Paragraph error_null = new Paragraph("Asegurece de seleccionar al menos un servicio");   // TEXTO A APARECER EN LA NOTIFIACION PARA AVISAR QUE SE DEBE SELECCIONAR SERVICIOS
                        noti_error.removeAll();                                                                         // CONTROL DE ERRORES PARA EVITAR QUE SE ACUMULEN NOTIFICACIONES Y HACER QUE SOLO HAYA 1 A LA VEZ
                        noti_error.addThemeVariants(NotificationVariant.LUMO_ERROR);                                    // SE LE DA LA VARIANTE ROJA A LA NOTIFICACION
                        noti_error.add(error_null);
                        noti_error.setClassName("error_null");
                        noti_error.open();

                        noti_error.setDuration(2000);                                                                    // LA IA ChatGPT 4.0 NOS AYUDO A HACER QUE LA NOTIFICACION SE CERRARA AUTOMATICAMENTE Y NO FUERA NECESARIO UN BOTON

                        return;
                    }

                    VerticalLayout interior_del_detalles = new VerticalLayout();    // CREACION DE CONTENEDOR DE LOS DETALLES DE LA COTIZACION
                    interior_del_detalles.setPadding(false);
                    interior_del_detalles.setSpacing(false);


                    // SE VALIDA LAS SELECCIONES DEL USUARIO Y SEGUN SE VA AÑADIENDO EL VALOR PROPUESTO PARA DICHO SERVICIO -------------
                    if (servicios_seleccionados.contains("Cambio de aceite")){
                        Span cambio_aceite_label = new Span("Cambio de aceite: $120.000");          // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "CAMBIO ACEITE"
                        valor_total_cotizar += 120000;                                                     // SUMATORIA DE VALOR
                        interior_del_detalles.add(cambio_aceite_label);                                      // SE AÑADE AL CONTENEDOR DE DETALLES (COTIZACION)

                    }
                    if (servicios_seleccionados.contains("Cambio de frenos")){
                        Span cambio_frenos_label = new Span("Cambio de frenos: $140.000");          // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "CAMBIO FRENOS"
                        valor_total_cotizar += 140000;                                                      // SUMATORIA DE VALOR
                        interior_del_detalles.add(cambio_frenos_label);                                          // SE AÑADE AL CONTENEDOR DE DETALLES (COTIZACION)

                    }
                    if (servicios_seleccionados.contains("Mantenemiento de cajas automaticas")){
                        Span mantenimiento_cajas_auto = new Span("Mantenimiento Cajas Automaticas: $360.000");  // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "MANT. CAJA AUTO"
                        valor_total_cotizar += 360000;                                                          // SUMATORIA DE VALOR
                        interior_del_detalles.add(mantenimiento_cajas_auto);                                         // SE AÑADE AL CONTENEDOR DE DETALLES (COTIZACION)
                    }
                    if (servicios_seleccionados.contains("Cambio Filtros AC")){
                        Span Cambio_Filtro_AC = new Span("Cambio Filtros AC: $200.000");        // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "CAMBIO FILTROS AC"
                        valor_total_cotizar += 200000;                                                         // SUMATORIA DE VALOR
                        interior_del_detalles.add(Cambio_Filtro_AC);                                     // SE AÑADE AL CONTENEDOR DE DETALLES (COTIZACION)
                    }
                    if (servicios_seleccionados.contains("Cambio Alternador")){
                        Span Cambio_Alternador = new Span("Cambio Alternador: $250.000");        // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "CAMBIO ALTERNADOR"
                        valor_total_cotizar += 250000;                                                      // SUMATORIA DE VALOR
                        interior_del_detalles.add(Cambio_Alternador);                                    // SE AÑADE AL CONTENEDOR DE DETALLES (COTIZACION)
                    }
                    if (servicios_seleccionados.contains("Cambio de Clutch")){
                        Span Cambio_Clutch = new Span("Cambio de Clutch: $800.000");            // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "CAMBIO DE CLUTCH"
                        valor_total_cotizar += 800000;                                                       // SUMATORIA DE VALOR
                        interior_del_detalles.add(Cambio_Clutch);                                   // SE AÑADE AL CONTENEDOR DE DETALLES (COTIZACION)
                    }

                    Span costos_mostrar = new Span("El costo Total fue de: $" + valor_total_cotizar + "+ IVA");  // TEXTO PARA DEJAR EXPLICITO EL VALOR Y LA FALTA DE IVA HASTA EL MOMENTO (PRE FACTURA)
                    interior_del_detalles.add(costos_mostrar);                                                          // SE AÑADE AL CONTENEDOR DE DETALLES (COTIZACION)

                    costos_segun.add(interior_del_detalles);                                                            // SE AÑADE AL CONTENEDOR DE DIALOGO
                    costos_segun.setOpened(true);                                                                       // SE MANTIENE ABIERTO


                });

                //---------------------------------------------------------------------------------


                // ACCION PARA EL BOTON DE CREACION DE FACTURA Y GENERACION DE FACTURA (ENVIAR)
                enviar.addClickListener(ClickEvent ->{

                    valor_total = 0;                                    // SE REINICIA LA VARIABLE PARA EVITAR ACUMULACION Y DATOS ERRONEOS

                    String servicios_seleccionados = servicios_check.getValue().toString();         // VARIABLE PARA ALMACENAR LOS SERVICIOS SELECCIONADOS

                    if (servicios_seleccionados.contains("Cambio de aceite")){
                        Span cambio_aceite_label = new Span("Cambio de aceite: $120.000");                          // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "CAMBIO ACEITE"
                        valor_total += 120000;                                                                          // SUMATORIA DE VALOR
                    }
                    if (servicios_seleccionados.contains("Cambio de frenos")){
                        Span cambio_frenos_label = new Span("Cambio de frenos: $140.000");                          // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "CAMBIO FRENOS"
                        valor_total += 140000;                                                                          // SUMATORIA DE VALOR
                    }
                    if (servicios_seleccionados.contains("Mantenemiento de cajas automaticas")){
                        Span mantenimiento_cajas_auto = new Span("Mantenimiento Cajas Automaticas: $360.000");      // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "MANT. CAJA AUTO"
                        valor_total += 360000;                                                                           // SUMATORIA DE VALOR

                    }
                    if (servicios_seleccionados.contains("Cambio Filtros AC")){
                        Span Cambio_Filtro_AC = new Span("Cambio Filtros AC: $200.000");                            // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "CAMBIO FILTROS AC"
                        valor_total += 200000;                                                                          // SUMATORIA DE VALOR
                    }
                    if (servicios_seleccionados.contains("Cambio Alternador")){
                        Span Cambio_Alternador = new Span("Cambio Alternador: $250.000");                           // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "CAMBIO ALTERNADOR"
                        valor_total += 250000;                                                                          // SUMATORIA DE VALOR
                    }
                    if (servicios_seleccionados.contains("Cambio de Clutch")){
                        Span Cambio_Clutch = new Span("Cambio de Clutch: $800.000");                                // GENERACION DE TEXTO SPAN PARA EL APARTADO DE COTIZACION (PRE FACTURA) CASO "CAMBIO DE CLUTCH"
                        valor_total += 800000;                                                                          // SUMATORIA DE VALOR
                    }

                    if (servicios_check.getSelectedItems().isEmpty()){                                                   // CONTROL DE ERRORES POR SI NO SE HAN SELECCIONADO SERVICIOS (PROPUESTA GENERADA POR IA) (getSelectedItems().isEmpty())
                        if (noti_error.isOpened()) {
                            noti_error.close();
                        }


                        Paragraph error_null = new Paragraph("Asegurece de seleccionar al menos un servicio");   // TEXTO A APARECER EN LA NOTIFIACION PARA AVISAR QUE SE DEBE SELECCIONAR SERVICIOS
                        noti_error.removeAll();                                                                         // CONTROL DE ERRORES PARA EVITAR QUE SE ACUMULEN NOTIFICACIONES Y HACER QUE SOLO HAYA 1 A LA VEZ
                        noti_error.addThemeVariants(NotificationVariant.LUMO_ERROR);                                    // SE LE DA LA VARIANTE ROJA A LA NOTIFICACION
                        noti_error.add(error_null);
                        noti_error.setClassName("error_null");
                        noti_error.open();

                        noti_error.setDuration(2000);                                                                    // LA IA ChatGPT 4.0 NOS AYUDO A HACER QUE LA NOTIFICACION SE CERRARA AUTOMATICAMENTE Y NO FUERA NECESARIO UN BOTON

                        return;
                    }



                    String servicios_realizados = servicios_check.getValue().toString();                                                // VARIABLE PARA ALMACENAR LOS SERVICIOS SELECCIONADOS
                    String insumos_consum = insumos_consumidos.getValue().toString();                                                      // VARIABLE PARA ALMACENAR LOS INSUMOS CONSUMIDOS
                    String ruta = "src/main/java/org/vaadin/example/facturas/" + nombre_usuario + "_" + cedula.getValue() + ".txt";         // RUTA PARA LA CREACION DE LA FACTURA EN TEXTO PLANO .TXT EN LA CARPETA ESPECIALIZADA PARA FACTURAS
                    String ruta_historial = "src/main/java/org/vaadin/example/historial.txt";                                            // RUTA PARA ALMACENAR LOS DATOS EN EL HISTORIAL
                    valor_iva = valor_total * 0.19;                                                                                         // FORMULA Y OPERACION PARA CALCULAR EN IVA
                    valor_total_con_iva =  valor_total + valor_iva;                                                                         // FORMULA Y OPERACION PARA OBTENER EL TOTAL CON EL IVA YA SUMADO



                    // ORGANIZACION, FORMATO E IMPLEMENTACION DE SALIDA FACTURA A TEXTO PLANO
                    try {


                        BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta));
                        escritor.write("---------- FACTURA ----------\n" + "\n" + fecha_para_txt +              // FORMATO Y ESCRITURA
                                        "\n" +  "\n" + "----------         ----------" +
                                        "\n" + "\n" + datos_user_guadar +
                                        "\n" + "\n" + "----------         ----------" +
                                        "\n" + "\n" + datos_vehiculo +
                                        "\n" + "\n" + "----------         ----------");

                        escritor.newLine();
                        escritor.write("\n" + "SERVICIOS REALIZADOS: " + servicios_realizados +                 // FORMATO Y ESCRITURA
                                        "\n" + "\n" + "----------         ----------" +
                                        "\n" + "\n" + "INSUMOS CONSUMIDOS: " + insumos_consum +
                                        "\n" + "\n" + "----------         ----------" +
                                        "\n" + "\n" + "VALOR: $" + valor_total +
                                        "\n" + "\n" + "IVA: $" + valor_iva +
                                        "\n" + "\n" + "----------         ----------" +
                                        "\n" + "\n" + "VALOR TOTAL: $" + valor_total_con_iva);
                        escritor.close();

                        Paragraph texto_aviso = new Paragraph("FACTURA CREADA CORRECTAMENTE");                  // TEXTO DE NOTIFICACION PARA AVISAR FACTURA CREADA CORRECTAMENTE

                        HorizontalLayout contendio_noti_suceccess = new HorizontalLayout(texto_aviso,boton_cerrado);    // CONTENEDOR DE NOTIFICACION

                        noti_success.removeAll();                                                                       // CONTROL DE ERRORES PARA EVITAR QUE SE ACUMULEN LAS NOTIFICACIONES
                        noti_success.addThemeVariants(NotificationVariant.LUMO_SUCCESS);                                // VARIANTE VERDE PARA LA NOTIFICACION
                        noti_success.add(contendio_noti_suceccess);
                        noti_success.setClassName("noti_bien");
                        noti_success.open();
                        noti_success.setDuration(2000);                                                                 // LA IA ChatGPT 4.0 NOS AYUDO A HACER QUE LA NOTIFICACION SE CERRARA AUTOMATICAMENTE Y NO FUERA NECESARIO UN BOTON



                        dialogo_orden.close();

                    }catch (IOException e){                                                                             // CONTROL DE ERRORES ARCHIVO
                        e.printStackTrace();
                    }
                    try {
                        BufferedWriter escritor_historial = new BufferedWriter(new FileWriter(ruta_historial, true));
                        escritor_historial.newLine();
                        escritor_historial.write(datos_user_historial + ";" + datos_vehiculo_historial + ";");              // ESCRITURA
                        escritor_historial.close();
                    } catch (IOException e) {                                                                           // CONTROL DE ERRORES ARCHIVO
                        e.printStackTrace();
                    }

                });



                //CONTENEDORES DEL DIALOGO DE SERIVICIOS ----------------------------------

                VerticalLayout elementos_derecha_dialogo = new VerticalLayout(insumosComb, insumos_consumidos, costos_segun);    // CONTENEDOR DERECHA DEL DIALOGO (INSUMOS)
                HorizontalLayout contenedor_elementos_dialogo = new HorizontalLayout(servicios_check);                           // CONTENEDOR ELEMENTOS SERVICIOS (CHEKBOX GROUP IZQ)
                HorizontalLayout contenedor_botones_dialogo = new HorizontalLayout(cerra_dialogo,cotizar_dialogo,enviar);        // CONTENEDOR BOTONES (FOOTER)
                HorizontalLayout contenedor_principal_dialogo = new HorizontalLayout(servicios_check,elementos_derecha_dialogo); // CONTENEDOR PRINCIPAL DE LA VENTANA DE DIALOGO

                //-----------------------------------------------
                dialogo_orden.add(contenedor_principal_dialogo);                                                                // AGREGAR ELEMENTOS A LA PAGINA
                dialogo_orden.getFooter().add(contenedor_botones_dialogo);                                                      // (ia)
                dialogo_orden.open();
                //----------------------------------------------------------------------------------------------------





            } catch (Exception e) {                                         //EN CASO DE QUE HAYA ERROR AL GUARDAR LA INFORMACION--------------------------
                Paragraph texto_error = new Paragraph("ERROR");



            }

        });


        add(barra_info,barra_navegacion,contendor_total);              // AGREGAR ELEMENTOS PAGINA

    }


}
