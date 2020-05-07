package procesos;

import java.io.IOException;
import java.util.Scanner;

public class ExamenB {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce interfaz:");
        String interfaz = sc.next(); //solicitamos el host a resolver
        String[] comandos = {"ifconfig", interfaz}; //creamos el comando nslookup host
        Runtime runtime = Runtime.getRuntime();  //obtenemos el objeto Runtime

            try {
                Process process =runtime.exec(comandos); //se ejecuta el proceso
                process.waitFor(); //esperamos la ejecución del proceso
            if (process.exitValue() != 0)
                System.out.printf("error al requerir información de %s%n", interfaz);
            else {
                sc = new Scanner(process.getInputStream()); //usamos la referencia del Scanner para leer la entrada
                while (sc.hasNextLine())
                    System.out.println(sc.nextLine());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            sc.close();  //cerramos flujos
        }

    }
}

