package AlgoritmosDeBúsqueda;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del algoritmo Knuth-Morris-Pratt (AlgoritmosDeBúsqueda.KMP).
 * Este algoritmo utiliza un preprocesamiento del patrón para optimizar la búsqueda,
 * evitando comparaciones redundantes.
 */
public class KMP extends AlgoritmoDeBúsqueda {

    /**
     * Busca todas las apariciones del patrón en el texto usando el algoritmo AlgoritmosDeBúsqueda.KMP.
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

        // Preprocesar el patrón para construir el arreglo LPS
        int[] lps = computeLPSArray(patrón);

        int i = 0; // Índice del texto
        int j = 0; // Índice del patrón

        // Realizar la búsqueda del patrón en el texto
        while (i < n) {
            // Comparar caracteres del patrón y el texto
            if (patrón.charAt(j) == texto.charAt(i)) {
                incrementarComparaciones(); // Incrementar el contador de comparaciones
                incrementarCoincidencias(); // Incrementar el contador de coincidencias
                i++;
                j++;
            }

            // Si se encontró una coincidencia completa
            if (j == m) {
                incrementarApariciones(); // Incrementar el contador de apariciones
                posiciones.add(i - j); // Agregar la posición de inicio a la lista
                j = lps[j - 1]; // Ajustar índice del patrón utilizando el arreglo LPS
            } else if (i < n && patrón.charAt(j) != texto.charAt(i)) {
                // Si hay un desajuste entre el patrón y el texto
                incrementarComparaciones(); // Incrementar el contador de comparaciones (fallida)
                incrementarFallos(); // Incrementar el contador de fallos
                incrementarDesplazamientos(); // Incrementar el contador de desplazamientos

                // Ajustar índice del patrón utilizando el arreglo LPS
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        double tiempoFin = (System.nanoTime() - tiempoInicio) / 1e6; // Tiempo de fin de la ejecución
        imprimirResultados(tiempoFin, posiciones); // Imprimir resultados
    }

    /**
     * Calcula el arreglo LPS (Longest Prefix Suffix) para el patrón.
     * Este arreglo permite evitar comparaciones redundantes durante la búsqueda.
     *
     * @param patrón El patrón para el cual se calculará el arreglo LPS.
     * @return Un arreglo LPS donde cada elemento indica el prefijo más largo que también es sufijo.
     */
    private int[] computeLPSArray(String patrón) {
        int m = patrón.length();
        int[] lps = new int[m]; // Inicializar el arreglo LPS
        int len = 0; // Longitud del prefijo más largo que también es sufijo
        int i = 1;

        // Calcular el arreglo LPS
        while (i < m) {
            if (patrón.charAt(i) == patrón.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // Ajustar longitud utilizando valores previos en LPS
                } else {
                    lps[i] = 0; // No hay prefijo que también sea sufijo
                    i++;
                }
            }
        }

        return lps; // Retornar el arreglo LPS calculado
    }
}
