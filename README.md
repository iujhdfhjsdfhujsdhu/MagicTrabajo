





  Daniel Pais Pombar
	




Documentación
Índice
Introducción
Descripción del Proyecto
Requisitos
Configuración del Entorno
Estructura del Proyecto
Instalación
Uso
Pruebas
Contacto



















Introducción
Este proyecto es una aplicación de Android que simula la gestión y visualización de cartas coleccionables del juego Magic: The Gathering. La aplicación permite explorar una lista de cartas, expandir sus detalles, alternar entre un modo día/noche y eliminar cartas seleccionadas. Está diseñada como una herramienta práctica para los fanáticos del juego, mejorando la organización de sus colecciones.

Descripción del Proyecto
El objetivo principal del proyecto es ofrecer una experiencia interactiva para gestionar cartas de Magic: The Gathering. Entre las funcionalidades más destacadas están:
Visualizar una lista de cartas con nombre, descripción y una imagen representativa.
Alternar entre modos visuales día y noche.
Expandir o contraer detalles de cada carta.
Seleccionar y eliminar cartas de manera dinámica.
El alcance de la aplicación es presentar un sistema funcional para la visualización y administración de cartas, con posibilidad de futuras extensiones, como la integración de bases de datos, servicios en la nube y un buscador.

Requisitos
Software necesario
Android Studio (versión 2020.3 o superior).
SDK de Android (versión 33 o superior).
JDK 11+.
Conexión a Internet (opcional para futuras actualizaciones).
Dispositivo de prueba
Versión de Android mínima: API 21 (Lollipop).
Recomendado: API 30 (Android 11) o superior.

Configuración del Entorno
Clona el repositorio del proyecto:
 git clone https://github.com/iujhdfhjsdfhujsdhu/MagicTrabajo
Abre el proyecto en Android Studio:
Inicia Android Studio y selecciona Open.
Navega a la carpeta donde clonaste el repositorio.
Configura el SDK de Android:
Ve a File > Project Structure > SDK Location y verifica que el SDK de Android esté configurado correctamente.
Sincroniza las dependencias:
En el archivo build.gradle, selecciona Sync Now para asegurarte de que las dependencias estén actualizadas.

Estructura del Proyecto
/nombre-del-proyecto
│
├── app/                   # Código fuente de la aplicación
├── build/                 # Archivos generados de la construcción
├── gradle/                # Archivos de configuración de Gradle
├── res/                   # Recursos (imágenes, layouts, strings)
│   ├── drawable/          # Imágenes de las cartas y fondos
│   ├── layout/            # Archivos de diseño XML
│   ├── values/            # Colores e idiomas castellano y gallego
├── src/                   # Código fuente
│   ├── main/
│   │   ├── java/          # Código Java 3 paquetes
│   │   ├── res/           # Recursos XML
│   └── test/              # Pruebas unitarias
└── README.md              # Descripción general del proyecto


Clase Carta
	Esta clase Carta se utiliza para representar una carta en un juego o aplicación, guardando información relevante como su nombre, descripción, imagen y su estado de expansión. Permite acceder y modificar estos valores a través de sus métodos getter y setter. Además, el constructor inicializa la carta con los datos proporcionados y establece el estado inicial de expansión como “false”.
Clase MainActivity
onCreate(Bundle savedInstanceState):
Este es el método principal que se ejecuta al crear la actividad. Aquí:
Se inicializa el RecyclerView y se asigna un LinearLayoutManager, que es un administrador de diseño que organiza los elementos de la lista en una sola columna.
Se crea una lista de cartas, que son objetos de la clase Carta, cada una con un nombre, una descipción y una imagen asociada.
Se establece un adaptador (RecyclerViewAdapter) para el RecyclerView, que es el encargado de mostrar la lista de cartas.
Se configura un Switch (un interruptor) para alternar entre el modo día y noche. Dependiendo de la posición del Switch, cambia el fondo de la actividad y se actualiza el modo en el adaptador.
Se configura un botón para eliminar las cartas seleccionadas. Al hacer clic en el botón, se eliminan las cartas seleccionadas del adaptador y se muestra un Toast que indica cuántas cartas fueron eliminadas.
setOnCheckedChangedListener(...) (dentro del Switch):
Este método se ejecuta cuando el usuario cambia el estado del Switch. Cambia el modo entre "día" y "noche". Si el Switch está activado (modo noche), el fondo de la actividad cambia y el adaptador recibe la actualización para cambiar la apariencia de los elementos de la lista.
setOnClickListener(...) (para el botón de eliminar):
Este método se ejecuta cuando el usuario presiona el botón para eliminar las cartas seleccionadas. Si hay cartas seleccionadas, se eliminan y se muestra un Toast con el número de cartas eliminadas. Si no hay cartas seleccionadas, se muestra un mensaje indicando que no se seleccionaron cartas.

Clase RecyclerViewAdapter
RecyclerViewAdapter(List<Carta> cartas, Context context, boolean isNightMode):
Constructor del adaptador que recibe la lista de cartas, el contexto de la actividad y el estado del modo noche. Inicializa los atributos correspondientes.
setNightMode(boolean isNightMode):
Este método permite actualizar el estado del modo noche en el adaptador. Al cambiar el estado, se refresca la vista para aplicar el cambio de apariencia en todos los elementos del RecyclerView.
onCreateViewHolder(ViewGroup parent, int viewType):
Este método infla la vista de cada elemento de la lista. Aquí se crea un objeto ViewHolder, que será usado para manejar las vistas dentro de cada ítem del RecyclerView.
onBindViewHolder(ViewHolder holder, int position):
Este método se encarga de enlazar los datos de la carta con las vistas de cada item del RecyclerView. Además:
Cambia los colores de los textos dependiendo del estado del modo noche/día.
Muestra u oculta el texto de la carta dependiendo de si está expandido o no (esto se gestiona mediante un botón de flecha).
Cambia el fondo de un elemento si ha sido seleccionado para eliminar.
Maneja los clics en las vistas de los ítems y en el botón de flecha para expandir o contraer la descripción de la carta.
getItemCount():
Este método devuelve el número total de elementos en la lista de cartas. Es necesario para el RecyclerView para determinar cuántos elementos tiene que mostrar.
removeSelectedCards():
Este método elimina las cartas seleccionadas. Primero, ordena las posiciones seleccionadas en orden inverso (para evitar problemas al eliminar elementos de la lista), luego elimina las cartas correspondientes y refresca la vista.
getSelectedPositions():
Retorna una lista con las posiciones de las cartas seleccionadas. Esta lista se utiliza para saber qué cartas han sido marcadas para ser eliminadas.
Clase interna ViewHolder:
Esta clase es un ViewHolder estándar para el RecyclerView. Se encarga de almacenar las referencias a las vistas de cada elemento (nombre, texto, imagen y el botón de flecha) para evitar llamar repetidamente a findViewById() y mejorar el rendimiento.
En el constructor se inicializan las vistas del itemView que representan un elemento individual de la lista.



Archivos principales:
Carta.java: Modelo de datos para las cartas.
MainActivity.java: Lógica principal de la aplicación.
RecyclerViewAdapter.java: Adaptador para la lista de cartas.
Layouts XML:
activity_main.xml: Diseño principal.
item.xml: Diseño de los elementos de la lista.

Instalación
Ejecuta la aplicación en Android Studio:
Ve a Run > Run app.
Conecta un dispositivo Android:
Usa un cable USB para conectar tu dispositivo o inicia un emulador.
Compilación e instalación:
Espera a que el proyecto se compile e instale automáticamente en el dispositivo/emulador.

Uso
Abrir la aplicación:
Una vez instalada, ejecuta la aplicación en tu dispositivo.
Alternar modo día/noche:
Usa el interruptor en la pantalla principal para cambiar el modo visual.
Explorar cartas:
Desplázate por la lista de cartas y pulsa la flecha para expandir o contraer los detalles.
Eliminar cartas:
Selecciona una carta o más pulsándola y presiona el botón Eliminar.
Flecha para mostrar la descripción de la carta:
Selecciona la flecha a la derecha de la carta para mostrar o ocultar la descripción de la misma





Pruebas
Métodos de prueba
Pruebas manuales:
Verificación de la funcionalidad de expansión y contracción.
Alternancia entre modos día/noche y su impacto visual.
Correcta eliminación de cartas seleccionadas.

Eliminación de todas las cartas (cadena vacía) no causa error

Eliminación de una imagen lo cual causa error public ViewHolder(@NonNull View itemView) {

Dispositivos de prueba:
Samsung Galaxy S10 (API 30).
Medium Phone (API 35).
Nota: No se han implementado pruebas automatizadas en este proyecto.

Contacto
Autor: Daniel Pais Pombar
Email: danipaispombar@gmail.com
GitHub: https://github.com/iujhdfhjsdfhujsdhu


