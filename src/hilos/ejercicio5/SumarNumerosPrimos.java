package hilos.ejercicio5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumarNumerosPrimos {
    public static void main(String[] args) throws InterruptedException {
        List<Long> listaNumero = new ArrayList<>(); //lista para almacenar los  números
        long suma = 0;  //almacenamos resultado de la suma de los primos
       // long sumaTodos = 0; //para comprobar que no suma el último
        try (
                Scanner in = new Scanner(new File("ficheros/numeros_primos.txt")))

        { //leemos el fichero
            while (in.hasNextLong()) {
                long numero = in.nextLong();
                listaNumero.add(numero);  //añadimos los long a la lista
         //       sumaTodos += numero;  comprobación
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AveriguarSiPrimo[] hilos = new AveriguarSiPrimo[listaNumero.size()]; //creamos un array con tanto hilos como números tiene la lista
        //lanzamos un hilo por cada elemento
        for (int i = 0; i < hilos.length ; i++) {
            hilos[i] = new AveriguarSiPrimo(listaNumero.get(i));  //creamos el hilo para que haga el cálculo
            hilos[i].start(); //arrancamos el hilo
            hilos[i].join(); //esperamos la finalización de los hilos con join
        }
        for (int i = 0; i < hilos.length ; i++) {
            if  (hilos[i].isResultado())
                suma += listaNumero.get(i);
        }
      //  System.out.println(sumaTodos); comprobación
        System.out.printf("La suma de los números que son primos vales %d%n", suma);
       // System.out.println(sumaTodos - suma ); comprobación
    }

}

class AveriguarSiPrimo extends  Thread {
    private long numero;
    private boolean resultado = false;  //resultado de la operación

    public AveriguarSiPrimo(long numero){
        this.numero = numero;
    }

    public boolean isResultado() {  //lo llamamaos en la clase anterior
        return resultado;
    }

    @Override
    public void run() {
        resultado = isPrime(numero);
    }
    private boolean isPrime ( long n) {
        if ( n <= 3 ) {
            return n > 1;
        } else if ( n % 2 == 0 || n % 3 == 0 ) {
            return false;
        } else {
            for ( long i = 5 ; i * i <= n ; i += 6 ) {
                if ( n % i == 0 || n % ( i + 2 ) == 0 ) {
                    return false ;
                }
            }
            return true ;
        }
    }
}
