import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

public class BruteForce {
    // Método para buscar un patrón en un texto usando brute force
    public static void bruteforce(String texto, String patron) {
        int n = texto.length();
        int m = patron.length();

        // Variables para contar desplazamientos, comparaciones, coincidencias y fallos
        int desplazamientos = -1;
        int comparaciones = 0;
        int coincidencias = 0;
        int fallos = 0;
        int apariciones = 0;


        // Lista para almacenar las posiciones de coincidencias
        List<Integer> posiciones = new ArrayList<>();

        // Registrar el tiempo inicial
        long tiempoInicio = System.nanoTime();

        // Recorrer el texto
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            // Comprobar si el patrón coincide con la subcadena del texto
            while (j < m && texto.charAt(i + j) == patron.charAt(j)) {
                comparaciones++;  // Contar cada comparación entre los caracteres
                coincidencias++;
                j++;
            }

            // Si se llegó al final del patrón, se encontró una coincidencia
            if (j == m) {
                apariciones++;
                posiciones.add(i);  // Agregar la posición a la lista
            } else {
                comparaciones++;  // Contar cada comparación entre los caracteres
                fallos++;  // Contar un fallo si no coincidió
            }
            desplazamientos++;  // Siempre se realiza un desplazamiento
        }

        // Registrar el tiempo final
        long tiempoFin = System.nanoTime();

        // Calcular el tiempo total en milisegundos
        double tiempoTotal = (tiempoFin - tiempoInicio) / 1e6;

        // Imprimir los resultados al final de la ejecución
        System.out.println("Tiempo computacional: " + tiempoTotal + " ms");
        System.out.println("Desplazamientos: " + desplazamientos);
        System.out.println("Comparaciones: " + comparaciones);
        System.out.println("Coincidencias: " + coincidencias);
        System.out.println("Fallos: " + fallos);

        // Imprimir las posiciones donde se encontró el patrón
        if (!posiciones.isEmpty()) {
            System.out.println("El patrón aparece " + apariciones + " vez/veces en la cadena.");
            System.out.println("El patrón aparece en las posiciones: " + posiciones);
        } else {
            System.out.println("No se encontró el patrón en el texto.");
        }
    }

    public static void main(String[] args) {
        // Crear un objeto Scanner para recibir entrada desde el teclado
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario que ingrese el texto y el patrón
        System.out.print("Introduce el texto: ");
        String texto = scanner.nextLine();

        System.out.print("Introduce el patrón a buscar: ");
        String patron = scanner.nextLine();

        // Llamar al método bruteforce con los datos ingresados
        bruteforce(texto, patron);

        // Cerrar el scanner
        scanner.close();
    }
}
