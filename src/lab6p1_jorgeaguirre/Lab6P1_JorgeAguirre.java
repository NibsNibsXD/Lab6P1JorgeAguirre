/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab6p1_jorgeaguirre;

import java.util.Random;
import java.util.Scanner;
public class Lab6P1_JorgeAguirre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Scanner lector = new Scanner(System.in);
        System.out.print("Ingrese el tamaño de la lista: ");
        int tamano = lector.nextInt();
        
        // Generar y mostrar la lista
        int[] ListaDNumeros = GeneradorDLista(tamano);
        ImprimirLista(ListaDNumeros);
        
        // Encontrar y mostrar el índice de balance
        int[] resultado = encontrarIndiceDeBalance(ListaDNumeros);
        System.out.println("El punto de equilibrio está en el índice: " + resultado[2] + "(#" + ListaDNumeros[resultado[2]] + ")");
        System.out.println("Porque la suma a la izquierda es " + resultado[0] + " y en la derecha es " + resultado[1] + ".");
    }

    public static int[] GeneradorDLista(int tamano) {
        int[] ListaDNumeros = new int[tamano];
        Random random = new Random();
        for (int i = 0; i < tamano; i++) {
            ListaDNumeros[i] = random.nextInt(100); // Números entre 0 y 99
        }
        return ListaDNumeros;
    }

    public static void ImprimirLista(int[] ListaDNumeros) {
        System.out.print("Lista generada: [");
        for (int i = 0; i < ListaDNumeros.length; i++) {
            System.out.print(ListaDNumeros[i]);
            if (i < ListaDNumeros.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static int[] encontrarIndiceDeBalance(int[] ListaDNumeros) {
        int sumaTotal = 0;
        for (int num : ListaDNumeros) sumaTotal += num;

        int sumaIzquierda = 0;
        int sumaDerecha = sumaTotal;
        int indiceMasCercano = -1;
        int diferenciaMasPequena = Integer.MAX_VALUE;

        for (int i = 0; i < ListaDNumeros.length; i++) {
            if (i > 0) sumaIzquierda += ListaDNumeros[i - 1];
            sumaDerecha -= ListaDNumeros[i];

            int diferenciaActual = MiPropioSacaAbsoluto(sumaIzquierda - sumaDerecha);
            if (diferenciaActual < diferenciaMasPequena) {
                indiceMasCercano = i;
                diferenciaMasPequena = diferenciaActual;
            }
        }

        return new int[]{sumaIzquierda, sumaDerecha + ListaDNumeros[indiceMasCercano], indiceMasCercano}; // Ajuste para incluir el elemento en la suma derecha
    }
        public static int MiPropioSacaAbsoluto(int numero) {
        // Si el número es negativo, lo multiplica por -1
        if (numero < 0) {
            return numero * -1;
        } else {
            // Si el número es positivo o cero, lo devuelve sin cambios
            return numero;
        }
    }
    
    
}


/*




Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese cadena (formato número-número): ");
        String input = scanner.nextLine();
        String[] partes = input.split("-");
        int numeroDeCadenas = Integer.parseInt(partes[0]);
        int longitudDeCadenas = Integer.parseInt(partes[1]);

        if (longitudDeCadenas < 2 || longitudDeCadenas > 6) {
            System.out.println("El segundo número debe ser entre 2 y 6.");
            return;
        }

        // Generar el arreglo de cadenas
        String[] arreglo = new String[numeroDeCadenas];
        Random random = new Random();
        boolean[] letraInicialUsada = new boolean[26]; // Para A-Z

        for (int i = 0; i < numeroDeCadenas; i++) {
            char[] cadena = new char[longitudDeCadenas];
            int primeraLetra;

            do {
                primeraLetra = random.nextInt(26) + 65; // Generar una letra mayúscula (A-Z)
            } while (letraInicialUsada[primeraLetra - 65]); // Asegurar que la primera letra sea única

            letraInicialUsada[primeraLetra - 65] = true;
            cadena[0] = (char) primeraLetra;

            for (int j = 1; j < longitudDeCadenas; j++) {
                cadena[j] = (char) (random.nextInt(26) + 65); // Generar letra mayúscula aleatoria
            }


*/
