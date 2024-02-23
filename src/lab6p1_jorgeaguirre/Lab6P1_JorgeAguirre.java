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

            int diferenciaActual = Math.abs(sumaIzquierda - sumaDerecha);
            if (diferenciaActual < diferenciaMasPequena) {
                indiceMasCercano = i;
                diferenciaMasPequena = diferenciaActual;
            }
        }

        return new int[]{sumaIzquierda, sumaDerecha + ListaDNumeros[indiceMasCercano], indiceMasCercano}; // Ajuste para incluir el elemento en la suma derecha
    }
}
