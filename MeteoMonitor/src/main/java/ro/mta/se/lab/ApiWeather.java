package ro.mta.se.lab;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiWeather {

    private JSONObject jsonApi;

    public JSONObject getJson(){
        return  this.jsonApi;
    }

    public HttpURLConnection connectHttp(String citySelect) throws IOException {

        String key = "4bed2d1f2545e4224ffdcf4d88794fe4";
        URL url ;
        url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+citySelect+"&appid="+key+"&units=metric");

        HttpURLConnection conn;
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-length", "0");

        int status= conn.getResponseCode();
        System.out.println("Response Code:"
                + status);
        return conn;
    }

    public void obtJson(HttpURLConnection conn) throws IOException {
        //citire
        JSONObject myResponse= new JSONObject();
        BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        in.close();

        //print in String
        System.out.println(response.toString());
        //Read JSON response and print
        myResponse = new JSONObject(response.toString());
        this.jsonApi=myResponse;
        }

    public ApiWeather(String citySelect) throws IOException {
       HttpURLConnection connect = connectHttp(citySelect);
       obtJson(connect);
       connect.disconnect();
    }
}
