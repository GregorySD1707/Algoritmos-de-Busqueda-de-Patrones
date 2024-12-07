package AlgoritmosDeBúsqueda;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del algoritmo de búsqueda Fuerza Bruta (Brute Force).
 * Este algoritmo verifica todas las posibles posiciones donde el patrón
 * puede coincidir en el texto, comparando carácter por carácter.
 */
public class BruteForce extends AlgoritmoDeBúsqueda {

    /**
     * Busca todas las apariciones del patrón en el texto usando el método de fuerza bruta.
     *
     * @param texto  El texto donde se buscará el patrón.
     * @param patrón El patrón que se desea buscar.
     */
    @Override
    public void buscarPatrónDeTexto(String texto, String patrón) {
        long tiempoInicio = System.nanoTime(); // Tiempo de inicio de la ejecución
        int n = texto.length(); // Longitud del texto
        int m = patrón.length(); // Longitud del patrón
        List<Integer> posiciones = new ArrayList<>(); // Lista para almacenar las posiciones de coincidencia

        // Recorrer el texto en busca de coincidencias
        for (int i = 0; i <= n - m; i++) {
            int j = 0;

            // Comparar carácter por carácter
            while (j < m && texto.charAt(i + j) == patrón.charAt(j)) {
                incrementarComparaciones(); // Incrementar el contador de comparaciones
                incrementarCoincidencias(); // Incrementar el contador de coincidencias
                j++;
            }

            // Si se encontró una coincidencia completa
            if (j == m) {
                incrementarApariciones(); // Incrementar el contador de apariciones
                posiciones.add(i); // Agregar la posición de inicio a la lista
            } else {
                // Si no se encontró coincidencia, se registran métricas adicionales
                incrementarDesplazamientos(); // Incrementar el contador de desplazamientos
                incrementarComparaciones(); // Incrementar el contador de comparaciones (fallida)
                incrementarFallos(); // Incrementar el contador de fallos
            }
        }

        double tiempoFin = (System.nanoTime() - tiempoInicio) / 1e6; // Tiempo de fin de la ejecución
        imprimirResultados(tiempoFin, posiciones); // Imprimir resultados
    }
}
