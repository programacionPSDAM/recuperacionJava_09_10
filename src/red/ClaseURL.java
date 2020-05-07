package red;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ClaseURL {
    public static void main(String[] args) {
        Scanner in = null;
        try {
            URL url = new URL("http://www.iesvirgendelcarmen.com:80");
            System.out.printf("HOST: %s, PORT: %d, PROTOCOL: %s%n",
                    url.getHost(), url.getPort(), url.getProtocol());
            in = new Scanner(url.openStream());
            while (in.hasNextLine())
                System.out.println(in.nextLine());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in !=null)
                in.close();
        }
    }
}
