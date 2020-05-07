package red.ejercicio3;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DescargaFichero {
    public static void main(String[] args) throws IOException {
        String fileURL = "https://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar"; //url donde descargar
        URL url = new URL(fileURL);  //creamos el objeto URL
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection(); //creamos el objeto HttpURLConnection
        int responseCode = httpConn.getResponseCode(); //obtenemos la respuesta
        //si es un 200 ok que corresponde a la constante HTTP_OK la procesamos, sino terminamos
        if (!(responseCode == HttpURLConnection.HTTP_OK) ){
            System.out.println("No se puede conectar");
            return;  //terminamos
        }
        //si no hemos terminado con el return significa que la conexión es correcta
        String contentType = httpConn.getContentType();  //obtenemos el tipo de fichero
        int contentLength = httpConn.getContentLength(); //obtenemos el tamaño del fichero
        //obtenemos el nombre del fichero, hacemos un split por / de la url
        //http://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar
        //el último es el nombre del fichero: postgresql-9.2-1002.jdbc4.jar
        String[] tokens = fileURL.split("/");
        String fileName = tokens[ tokens.length -1 ];
        System.out.println("Content-Type = " + contentType);
        System.out.println("Content-Length = " + contentLength);
        System.out.println("fileName = " + fileName);
        try (InputStream in = httpConn.getInputStream();
             FileOutputStream out = new FileOutputStream(new File("ficheros/" + fileName))) {
            int c;
            while ((c = in.read()) != -1 ){
                out.write(c);
                out.flush();
            }
        }
        System.out.printf("Descargado fichero %s de %d bytes%n", fileName, contentLength);


    }
}
