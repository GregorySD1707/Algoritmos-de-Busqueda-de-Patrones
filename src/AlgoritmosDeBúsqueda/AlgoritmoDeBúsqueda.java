package AlgoritmosDeBúsqueda;

import java.util.List;

/**
 * Clase abstracta que define la estructura básica para los algoritmos de búsqueda de patrones.
 * Proporciona funcionalidad común para el seguimiento de métricas (desplazamientos, comparaciones, etc.)
 * y delega la implementación específica de la búsqueda a las clases hijas.
 */
public abstract class AlgoritmoDeBúsqueda {
    // Variables privadas para rastrear estadísticas de la ejecución del algoritmo
    private int desplazamientos = 0;  // Número de veces que el patrón se desplaza en el texto
    private int comparaciones = 0;    // Número total de comparaciones realizadas
    private int coincidencias = 0;    // Número total de coincidencias encontradas
    private int fallos = 0;           // Número total de fallos al intentar coincidir el patrón
    private int apariciones = 0;      // Número total de veces que el patrón aparece en el texto

    /**
     * Función abstracta para buscar un patrón en un texto.
     * Esta función debe ser implementado por las clases concretas que extiendan esta clase.
     *
     * @param texto  El texto donde se realizará la búsqueda.
     * @param patrón El patrón que se desea buscar.
     */
    public abstract void buscarPatrónDeTexto(String texto, String patrón);

    /**
     * Imprime los resultados de la búsqueda, incluidas las estadísticas de ejecución y las posiciones
     * donde el patrón fue encontrado.
     *
     * @param tiempoDeEjecución El tiempo total de ejecución del algoritmo, en milisegundos.
     * @param posiciones        Lista de posiciones donde se encontró el patrón en el texto.
     */
    public void imprimirResultados(double tiempoDeEjecución, List<Integer> posiciones) {
        System.out.println("Tiempo computacional: " + tiempoDeEjecución + " ms");
        System.out.println("Desplazamientos: " + desplazamientos);
        System.out.println("Comparaciones: " + comparaciones);
        System.out.println("Coincidencias: " + coincidencias);
        System.out.println("Fallos: " + fallos);

        // Imprime información adicional dependiendo de si se encontraron coincidencias
        if (!posiciones.isEmpty()) {
            System.out.println("El patrón aparece " + apariciones + " vez/veces en la cadena.");
            System.out.println("El patrón aparece en las posiciones: " + posiciones);
        } else {
            System.out.println("No se encontró el patrón en el texto.");
        }
    }

    /**
     * Incrementa en uno el contador de desplazamientos del patrón.
     * Se utiliza cuando el patrón se mueve a una nueva posición en el texto.
     */
    public void incrementarDesplazamientos() {
        desplazamientos++;
    }

    /**
     * Incrementa en uno el contador de comparaciones realizadas.
     * Se utiliza cada vez que se compara un carácter del patrón con uno del texto.
     */
    public void incrementarComparaciones() {
        comparaciones++;
    }

    /**
     * Incrementa en uno el contador de coincidencias de caracteres.
     * Se utiliza cuando un carácter del patrón coincide con uno del texto.
     */
    public void incrementarCoincidencias() {
        coincidencias++;
    }

    /**
     * Incrementa en uno el contador de fallos.
     * Se utiliza cuando hay un desajuste entre el patrón y el texto.
     */
    public void incrementarFallos() {
        fallos++;
    }

    /**
     * Incrementa en uno el contador de apariciones del patrón en el texto.
     * Se utiliza cada vez que se encuentra una coincidencia completa del patrón.
     */
    public void incrementarApariciones() {
        apariciones++;
    }
}
