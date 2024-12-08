package AlgoritmosDeBúsqueda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP4Casos extends AlgoritmoDeBúsqueda{
    List<Integer> posiciones = new ArrayList<>(); // Lista para almacenar las posiciones de coincidencia

    public int[] determinarTablaSiguiente(String patrón) {
        // Inicialización de la tabla y variables auxiliares
        int[] tablaSiguiente = new int[patrón.length()];
        tablaSiguiente[0] = -1;
        int posiciónActual = 1;

        while (posiciónActual < patrón.length()) {
            boolean subpatrónEncontrado = false;
            String patrónMásLargo = "";

            // Buscar sub-patrones que coincidan (prefijos y sufijos)
            for (int posiciónDelSufijo = posiciónActual - 1; posiciónDelSufijo > 0; posiciónDelSufijo--) {
                String subPatrónPrefijo = patrón.substring(0, posiciónDelSufijo);
                String subPatrónSufijo = patrón.substring(posiciónActual - posiciónDelSufijo, posiciónActual);

                if (subPatrónPrefijo.equals(subPatrónSufijo)) {
                    subpatrónEncontrado = true;
                    if (subPatrónPrefijo.length() > patrónMásLargo.length()) {
                        patrónMásLargo = subPatrónPrefijo;
                    }
                }
            }

            // Determinar el caso del carácter de fallo
            if (!subpatrónEncontrado) {
                // Caso 1 y 2
                tablaSiguiente[posiciónActual] = (patrón.charAt(0) == patrón.charAt(posiciónActual)) ? -1 : 0;
            } else {
                // Caso 3 y 4
                if (patrón.charAt(patrónMásLargo.length()) != patrón.charAt(posiciónActual)) {
                    tablaSiguiente[posiciónActual] = patrónMásLargo.length();
                } else {
                    tablaSiguiente[posiciónActual] = tablaSiguiente[patrónMásLargo.length()];
                }
            }

            // Avanzar y reiniciar variables
            posiciónActual++;
        }
        System.out.println("Tabla siguiente: "+Arrays.toString(tablaSiguiente));
        return tablaSiguiente;
    }

    @Override
    public void buscarPatrónDeTexto(String texto, String patrón) {
        long tiempoInicio = System.nanoTime(); // Tiempo de inicio de la ejecución

        int[] tablaSiguiente = determinarTablaSiguiente(patrón); // Determinar tabla siguiente

        int posiciónEnElTexto = 0; // Variable entera para poder avanzar en el texto
        int posiciónEnElPatrón = 0; // Variable entera para poder avanzar y retroceder en el patrón

        // Se recorre el texto en busca de coincidencias con el patrón
        while (posiciónEnElTexto < texto.length()) {

            // Si un carácter del patrón coincide con el del texto, se avanzan ambas posiciones en para buscar más coincidencias
            if (patrón.charAt(posiciónEnElPatrón) == texto.charAt(posiciónEnElTexto)) {
                incrementarCoincidencias();
                incrementarComparaciones();
                posiciónEnElPatrón++;
                posiciónEnElTexto++;

                // Cuando existe una coincidencia completa, se indica la posición donde inicia el patrón.
                if (posiciónEnElPatrón == patrón.length()) {
                    incrementarApariciones();
                    posiciones.add(posiciónEnElTexto-posiciónEnElPatrón); // Agregar la posición de inicio a la lista
                    posiciónEnElPatrón = 0;
                }

            } else {
                incrementarComparaciones();
                incrementarFallos();
                incrementarDesplazamientos();
                // Si no son iguales las letras, se usa la tabla siguiente para determinar cómo avanzar (O no) en el
                // texto y cómo retroceder o avanzar en el patrón.
                switch (tablaSiguiente[posiciónEnElPatrón]) {
                    case -1:
                        // Se vuelve a iniciar la comparación desde el inicio del patrón y una posición más adelante
                        // de la del carácter de fallo para evitar de nuevo comparar con el texto letras distintas.
                        posiciónEnElPatrón = 0;
                        posiciónEnElTexto++;
                        break;
                    case 0:
                        // Se vuelve a iniciar la comparación desde el inicio del patrón y en la misma posición
                        // del carácter de fallo porque el carácter inicial es diferente y ahora sí puede coincidir
                        posiciónEnElPatrón = 0;
                        break;
                    default:
                        // Se tiene el tamaño 'n' del patrón anterior al carácter de fallo, por lo que se asigna ese valor
                        // al apuntador del patrón para que así esé a 'n' posiciones a la izquierda donde hubo el fallo
                        posiciónEnElPatrón = tablaSiguiente[posiciónEnElPatrón];
                        break;
                }
            }

        }
        double tiempoFin = (System.nanoTime() - tiempoInicio) / 1e6; // Tiempo de fin de la ejecución
        imprimirResultados(tiempoFin, posiciones); // Imprimir resultados
    }
}
