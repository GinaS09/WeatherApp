package ro.mta.se.lab;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ApiWeatherTest {

    private  ApiWeather test1;
    private  ApiWeather test2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        System.out.println("setUp");
        test1 = new ApiWeather("Zalau");
        test2 = new ApiWeather("Cluj");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getJson() {
        JSONObject jsonTes1 =new JSONObject();
        jsonTes1=test1.getJson();
        String city=jsonTes1.get("name").toString();
        assertEquals(city,"Zalău");
        String coord1=jsonTes1.get("coord").toString();
        assertEquals(coord1,"{\"lon\":23.05,\"lat\":47.2}");

        JSONObject jsonTes2 =new JSONObject();
        jsonTes2=test2.getJson();
        String city2=jsonTes2.get("name").toString();
        assertEquals(city2,"Cluj");
        String coord2=jsonTes2.get("coord").toString();
        assertEquals(coord2,"{\"lon\":23.8184,\"lat\":46.6494}");
}

    @Test
    void connectHttp() throws IOException {
        System.out.println("connectHttp");

        String respons1= new String(String.valueOf(((test1.connectHttp("Zalau")).getResponseCode())));
        assertEquals(respons1,"200");

        String respons2= new String(String.valueOf(((test1.connectHttp("Cluj")).getResponseCode())));
        assertEquals(respons2,"200");


    }

}