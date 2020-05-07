package procesos;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamenA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el host a resolver:");
        String host = sc.next(); //solicitamos el host a resolver
        String[] comandos = {"nslookup", host}; //creamos el comando nslookup host
        ProcessBuilder processBuilder = new ProcessBuilder(comandos); //creamos el objeto proceso
        try {
            Process process = processBuilder.start(); //se ejecuta el proceso
            process.waitFor(); //esperamos la ejecución del proceso
            if (process.exitValue() != 0)
                System.out.printf("error al resolver %s%n", host);
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
