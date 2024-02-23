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
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("¿Qué parte de mi código te gustaría correr?");
            System.out.println("1.Indice de balance");
            System.out.println("2.Alternados ");
            System.out.println("3.Torneos de pelea ");
            System.out.println("4. Salir");

            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
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
    
                    break;
                case 2:
                ejercicio2();
                    break;
                case 3:
                 ejercicio3();   
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        } while (opcion != 4);
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
    
    
        
        public static void ejercicio2(){
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

        String[] arreglo = new String[numeroDeCadenas];
        Random random = new Random();
        boolean[] letraInicialUsada = new boolean[26];

        for (int i = 0; i < numeroDeCadenas; i++) {
            char[] cadena = new char[longitudDeCadenas];
            int primeraLetra;

            do {
                primeraLetra = random.nextInt(26) + 65;
            } while (letraInicialUsada[primeraLetra - 65]);

            letraInicialUsada[primeraLetra - 65] = true;
            cadena[0] = (char) primeraLetra;

            for (int j = 1; j < longitudDeCadenas; j++) {
                cadena[j] = (char) (random.nextInt(26) + 65);
            }

            arreglo[i] = new String(cadena);
        }

        System.out.println("Su arreglo generado es:");
        for (String s : arreglo) {
            System.out.print(s + ", ");
        }
        System.out.println();

        System.out.println("Ingrese forma de ordenamiento (-+ o +-): ");
        String ordenamiento = scanner.nextLine();

        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - i - 1; j++) {
                boolean condicion = ordenamiento.equals("-+") ? esMayor(arreglo[j], arreglo[j + 1]) :
                                    esMenor(arreglo[j], arreglo[j + 1]);
                if ((j % 2 == 0) == condicion) {
                    String temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;  
                }
            }
        }

        for (String s : arreglo) {
            System.out.print(s + ", ");
        }
        System.out.println();
        
        }
        
        
        
        private static boolean esMayor(String a, String b) {
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) > b.charAt(i)) return true;
            else if (a.charAt(i) < b.charAt(i)) return false;
        }
        return a.length() > b.length();
    }

    private static boolean esMenor(String a, String b) {
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) < b.charAt(i)) return true;
            else if (a.charAt(i) > b.charAt(i)) return false;
        }
        return a.length() < b.length();
    }
    public static void ejercicio3(){
        String[] competidores = {"Diego", "Darian", "Ana", "Beatriz", "Carlos", "David", "Elena", "Fernando"};
        int[] vidas = new int[competidores.length];
        for (int i = 0; i < competidores.length; i++) {
            vidas[i] = competidores[i].length();
        }

        boolean[] activos = new boolean[competidores.length];
        for (int i = 0; i < activos.length; i++) {
            activos[i] = true; // Inicialmente todos los competidores están activos
        }

        int ronda = 1;
        while (!hayUnSoloGanador(activos)) {
            System.out.println("Ronda " + ronda + " del torneo");
            for (int i = 0; i < competidores.length - 1; i++) {
                if (activos[i]) {
                    for (int j = i + 1; j < competidores.length; j++) {
                        if (activos[j]) {
                            int ganador = enfrentamiento(i, j, competidores, vidas);
                            activos[i] = ganador == i;
                            activos[j] = ganador == j;
                            if (ganador == i) {
                                System.out.println(competidores[i] + " gana a " + competidores[j]);
                            } else {
                                System.out.println(competidores[j] + " gana a " + competidores[i]);
                            }
                            System.out.println("---------------------------------");
                            break; // Salir del bucle una vez que se ha encontrado un competidor para enfrentarse
                        }
                    }
                }
            }
            ronda++;
        }

        // Mostrar al ganador final
        for (int i = 0; i < activos.length; i++) {
            if (activos[i]) {
                System.out.println("El ganador es: " + competidores[i]);
                break;
            }
        }
    }

    private static boolean hayUnSoloGanador(boolean[] activos) {
        int contador = 0;
        for (boolean activo : activos) {
            if (activo) {
                contador++;
                if (contador > 1) {
                    return false; // Hay más de un competidor activo
                }
            }
        }
        return contador == 1; // Solo hay un competidor activo
    }

    public static int calcularAtaque(String nombre) {
        int[] contadorLetras = new int[256];
        for (char c : nombre.toCharArray()) {
            contadorLetras[c]++;
        }
        int maxRep = 0;
        for (int i : contadorLetras) {
            if (i > maxRep) {
                maxRep = i;
            }
        }
        return maxRep;
    }

    public static int enfrentamiento(int index1, int index2, String[] competidores, int[] vidas) {
        String competidor1 = competidores[index1];
        String competidor2 = competidores[index2];
        int vida1 = vidas[index1];
        int vida2 = vidas[index2];
        int ataque1 = calcularAtaque(competidor1);
        int ataque2 = calcularAtaque(competidor2);

        vida2 -= ataque1;
        vida1 -= ataque2;

        imprimirResultado(competidor1, competidor2, vida1, vida2, ataque1, ataque2);

        vidas[index1] = vida1;
        vidas[index2] = vida2;

        if (vida1 > vida2) {
            return index1;
        } else if (vida2 > vida1) {
            return index2;
        } else {
            int asciiMax1 = competidor1.chars().max().orElse(0);
            int asciiMax2 = competidor2.chars().max().orElse(0);
            if (asciiMax1 > asciiMax2) {
                return index1;
            } else if (asciiMax2 > asciiMax1) {
                return index2;
            } else {
                return new Random().nextInt(2) == 0 ? index1 : index2;
            }
        }
    }

    public static void imprimirResultado(String competidor1, String competidor2, int vidaOriginal1, int vidaOriginal2, int ataque1, int ataque2) {
        System.out.println(competidor1 + " (vida: " + vidaOriginal1 + ") VS " + competidor2 + " (vida: " + vidaOriginal2 + ")");
        System.out.println(competidor1 + " quitó " + ataque1 + " de vida a " + competidor2);
        System.out.println(competidor2 + " quitó " + ataque2 + " de vida a " + competidor1);
        System.out.println(competidor1 + " ahora tiene " + (vidaOriginal1 - ataque2) + " puntos de vida");
        System.out.println(competidor2 + " ahora tiene " + (vidaOriginal2 - ataque1) + " puntos de vida");
    }        
        



}


    
    
    
