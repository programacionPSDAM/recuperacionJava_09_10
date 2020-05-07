package hilos.ejercicio2;

import java.io.*;
import java.net.Socket;

public class Manejador  extends Thread{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Manejador(Socket socket) throws IOException {
        this.socket = socket;
        this.in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        while (true) {
            try {
                String recibido = in.readLine();
                System.out.println("Recibido desde el cliente: " + recibido);
                if (recibido.equalsIgnoreCase("fin")) {
                    System.out.println("Cerrando conexión");
                    socket.close();
                    System.out.println("Cerrada conexión");
                    break;
                }
                if (!recibido.matches("[0-9]+")) {
                    System.out.printf("%s no es un número%n", recibido);
                    out.printf("%s no es un número entero%n", recibido);
                    out.flush();
                    continue;
                }
                System.out.println("Procesando: " + recibido);
                out.printf("El máximo divisor de %s es %d%n", recibido, obtenerMD(recibido));
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private int obtenerMD(String sNumero) {
        int divisor = 0;
        int iNumero = Integer.parseInt(sNumero);
        if (iNumero == divisor)
            return divisor;
        for (int i = 1; i < iNumero / 2; i ++)
            if (iNumero % i == 0)
                divisor = i;
        return divisor;
    }
}