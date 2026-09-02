# PAE - Eventos en JavaFX (Ejercicios en Pareja)

Repositorio que reúne la solución a tres retos prácticos centrados en la programación de aplicaciones de escritorio con JavaFX, arquitectura MVC, implementación del patrón DAO y manejo de navegación modular.

---
## Integrantes del Equipo

* **Carlos Eduardo Rodríguez Flores**
* **Steven David Cuadra Palacios**

* **Institución:** Universidad Americana (UAM)
* **Asignatura:** Programación de Aplicaciones de Escritorio

---

## Descripción del Proyecto

El sistema proporciona un Menú Principal centralizado desde el cual es posible navegar de forma interactiva e independiente hacia cada uno de los tres retos desarrollados:

### Reto 1: Inventario de Pulpería
* **Contexto:** Registro e inventariado de productos para la consulta rápida de existencias en una pulpería.
* **Funcionalidades Clave:**
    * Formulario de captura: Código, Nombre, Precio y Cantidad.
    * Manejo de ActionEvent al hacer clic en guardar.
    * Búsqueda dinámica por código mediante KeyEvent escuchando la tecla ENTER.
    * Validación estricta de campos vacíos, tipos de datos numéricos y prevención de valores negativos.
    * Visualización en tabla mediante TableView.

### Reto 2: Recepción de Café (Cooperativa Agro - Registro de Lotes)
* **Contexto:** Registro y control de entregas de lotes de café aportados por distintos productores a una cooperativa.
* **Funcionalidades Clave:**
    * Tabla central TableView vinculada a un ObservableList.
    * MouseEvent: Doble clic sobre un lote registrado para desplegar una ventana emergente (Alert) con la información detallada del lote.
    * ContextMenu (clic derecho) sobre la tabla con opciones para Modificar Lote y Eliminar Registro.
    * Confirmación de seguridad mediante cuadro de diálogo antes de efectuar la eliminación de un registro.
    * Animación de entrada fluida empleando FadeTransition.

### Reto 3: Tienda de Artesanías Nicaragüenses
* **Contexto:** Organización del catálogo de productos artesanales y gestión de búsquedas.
* **Funcionalidades Clave:**
    * Menús superiores (Catálogo, Ventas y Ayuda).
    * Barra de herramientas (ToolBar) con acciones directas para Nuevo, Guardar y Buscar.
    * Renderizado de imágenes locales en las celdas de la tabla mediante ImageView.
    * Diálogos emergentes de entrada (TextInputDialog) para la búsqueda específica por código.

---

## Arquitectura y Estructura del Proyecto

El proyecto está diseñado bajo el patrón MVC (Modelo-Vista-Controlador) junto al patrón DAO (Data Access Object):

```text
src/main/java/ni/edu/uam/pae_eventos_javafx_equipo/
├── Launcher.java                       
├── Navegador.java                      
├── application/                       
│   ├── InventarioApplication.java
│   ├── LoteApplication.java
│   ├── MenuApplication.java
│   └── TiendaArtesaniaApplication.java
├── controller/                        
│   ├── ArtesaniaController.java
│   ├── InventarioController.java
│   ├── LoteController.java
│   └── MenuController.java
├── dao/                                
│   ├── ArtesaniaDAO.java
│   ├── InventarioDAO.java
│   └── LoteDAO.java
├── interfaces/                         
│   └── Crud.java
└── models/                             
    ├── Artesania.java
    ├── Inventario.java
    ├── Lote.java
    └── Recepcion.java
```
---

## Instrucciones de Ejecución

El proyecto cuenta con la clase Launcher como punto de inicio centralizado que levanta la interfaz del Menú Principal.

### Opción 1: Desde el IDE (IntelliJ IDEA)

1. Clona con git bash o desde Intellij IDEA

    `git clone https://github.com/cerf007/-PAE_Eventos_JavaFX_Equipo.git`
2. Abre el proyecto en el IDE.
3. Permite que el IDE descargue e importe las dependencias (javafx-controls, javafx-fxml, lombok).
4. Navega a la clase de entrada:
   src/main/java/ni/edu/uam/pae_eventos_javafx_equipo/Launcher.java
5. Ejecuta la clase haciendo clic derecho y seleccionando Run 'Launcher.main()'.
---
