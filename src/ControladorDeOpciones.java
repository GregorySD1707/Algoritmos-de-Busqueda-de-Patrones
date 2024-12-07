import AlgoritmosDeBúsqueda.BruteForce;
import AlgoritmosDeBúsqueda.KMP;

import java.util.Scanner;

/**
 * Clase que controla las opciones del usuario para seleccionar texto y patrón,
 * así como el algoritmo de búsqueda que desea ejecutar.
 */
public class ControladorDeOpciones {

    /**
     * Función principal que guía al usuario a través de las opciones para seleccionar texto, patrón y
     * función de búsqueda. Se ejecuta en un bucle hasta que el usuario decida finalizar.
     */
    public void controlarOpciones() {
        String texto = "";  // Variable para almacenar el texto seleccionado
        String patrón = ""; // Variable para almacenar el patrón seleccionado

        Scanner sc = new Scanner(System.in); // Objeto Scanner para capturar entradas del usuario
        int opción; // Variable para capturar la elección del usuario

        do {
            // Mostrar las opciones de texto y patrón disponibles
            System.out.println("""
                    Escoja el texto y patrón a utilizar en la búsqueda:
                    1.  T = babcbabcabcaabcabcabcacabc
                        P = abcabcacab
                    2.  T = CTAAAGATGATCTTTAGTCCCGGTTCGAA
                        P = GAT
                    3.  T = TTATAGATCTCGTATTCTTTTATAGATCTCCTATTCTT
                        P = TCCTATTCTT
                    4.  T = AUG CUA GCA UCG (molécula ARNm)
                        P = GCA
                    """);

            // Validar que el usuario ingrese una opción entre 1 y 4
            opción = obtenerOpciónValida(sc, 1, 4);

            // Asignar texto y patrón según la opción seleccionada
            switch (opción) {
                case 1 -> {
                    texto = "babcbabcabcaabcabcabcacabc";
                    patrón = "abcabcacab";
                }
                case 2 -> {
                    texto = "CTAAAGATGATCTTTAGTCCCGGTTCGAA";
                    patrón = "GAT";
                }
                case 3 -> {
                    texto = "TTATAGATCTCGTATTCTTTTATAGATCTCCTATTCTT";
                    patrón = "TCCTATTCTT";
                }
                case 4 -> {
                    texto = "AUG CUA GCA UCG";
                    patrón = "GCA";
                }
                default ->
                        System.out.println("Opción no válida, por favor inténtelo de nuevo."); // Este caso no debería ocurrir
            }

            // Mostrar las opciones de métodos de búsqueda
            System.out.println("""
                    Escoja un método de búsqueda de patrones:
                    1. Brute Force (BF)
                    2. Knuth-Morris-Pratt (KMP)
                    3. Boyer-Moore (BM)
                    """);

            // Validar que el usuario seleccione una función entre 1 y 3
            opción = obtenerOpciónValida(sc, 1, 3);

            // Ejecutar el algoritmo de búsqueda según la opción seleccionada
            switch (opción) {
                case 1 -> {
                    BruteForce bruteForce = new BruteForce();
                    bruteForce.buscarPatrónDeTexto(texto, patrón);
                }
                case 2 -> {
                    KMP kmp = new KMP();
                    kmp.buscarPatrónDeTexto(texto, patrón);
                }
                case 3 ->
                        System.out.println("TODAVÍA NO EXISTE"); // Placeholder para una futura función
            }

            // Preguntar al usuario si desea continuar o salir
            System.out.println("\nIngrese 1 para continuar con la ejecución del código, o 0 para salir:");
            opción = obtenerOpciónValida(sc, 0, 1);

        } while (opción == 1); // Continuar mientras el usuario desee continuar

        System.out.println("Ejecución finalizada."); // Mensaje al terminar la ejecución
    }

    /**
     * Función auxiliar para obtener una opción válida dentro de un rango.
     * <p>
     * Esta función asegura que el usuario ingrese un valor entero dentro del rango permitido.
     *
     * @param sc  Objeto Scanner para leer la entrada del usuario.
     * @param min Valor mínimo permitido.
     * @param max Valor máximo permitido.
     * @return Opción válida ingresada por el usuario.
     */
    private static int obtenerOpciónValida(Scanner sc, int min, int max) {
        int opción; // Variable para almacenar la opción ingresada por el usuario
        while (true) { // Bucle infinito hasta que el usuario ingrese un valor válido
            try {
                opción = sc.nextInt(); // Leer la entrada del usuario
                // Verificar si la opción está dentro del rango permitido
                if (opción >= min && opción <= max) {
                    return opción; // Retornar la opción si es válida
                } else {
                    // Informar al usuario que debe ingresar un valor dentro del rango
                    System.out.printf("Por favor, ingrese un valor entre %d y %d.\n", min, max);
                }
            } catch (Exception e) {
                // Manejar entradas no válidas (por ejemplo, caracteres en lugar de números)
                System.out.println("Entrada no válida. Intente de nuevo.");
                sc.next(); // Limpiar la entrada no válida del buffer del Scanner
            }
        }
    }
}
