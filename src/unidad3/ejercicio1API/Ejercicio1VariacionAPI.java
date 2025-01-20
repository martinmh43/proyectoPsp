package unidad3.ejercicio1API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

public class Ejercicio1VariacionAPI {


    public static void main(String[] args) throws URISyntaxException, IOException {

        URL url = new URL("https://dog-api.kinduff.com/api/facts?number=1");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader((con.getInputStream())));
            String linea;
            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
            }
            con.disconnect();
        } else {
            System.out.println(responseCode);
        }
    }
}
