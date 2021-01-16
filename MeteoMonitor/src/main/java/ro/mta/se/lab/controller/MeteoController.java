package ro.mta.se.lab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;
import ro.mta.se.lab.ApiWeather;
import ro.mta.se.lab.model.MeteoModel;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MeteoController {

    @FXML
    private ComboBox countryBox;
    @FXML
    private ComboBox cityBox;
    @FXML
    private Label countryNameLbl;
    @FXML
    private Label cityNameLbl;
    @FXML
    private Label descriptionLbl;
    @FXML
    private Label temperatureLbl;
    @FXML
    private Label pressureLbl;
    @FXML
    private Label humidityLbl;
    @FXML
    private Label windLbl;
    @FXML
    private Label iconLbl;
    @FXML
    private Label timeLbl;

    private String citySelect = new String();


    @FXML
    private void initialize() throws IOException {
        initCombo();                    //comboBoxSwitch
    }

    public void setModel(){
        MeteoModel meteo = new MeteoModel(countryNameLbl.toString(), cityNameLbl.toString(), descriptionLbl.toString(),
                temperatureLbl.toString(), pressureLbl.toString(), humidityLbl.toString(),
                windLbl.toString(), iconLbl.toString());
       // System.out.println(meteo.toString());
    }

    public void setLbl(JSONObject json){

        String city=json.get("name").toString(); //add lbl city
        cityNameLbl.setText(city);

        String sys = json.get("sys").toString();
        JSONObject sysJson=new JSONObject(sys);
        String cnt=sysJson.get("country").toString(); //add lbl country
        countryNameLbl.setText(cnt);

        String main = json.get("main").toString();
        JSONObject mainJson=new JSONObject(main);
        String temp=mainJson.get("temp_max").toString(); //add lbl temp_max
        temperatureLbl.setText(temp);

        String press=mainJson.get("pressure").toString(); //add lbl pressure
        pressureLbl.setText(press);

        String hum=mainJson.get("humidity").toString(); //add lbl humidity
        humidityLbl.setText(hum);

        String winds = json.get("wind").toString(); //add lbl wind
        JSONObject windJson=new JSONObject(winds);
        String wind=windJson.get("speed").toString(); 


        String timeStamp = new SimpleDateFormat("yyyy/MM/dd/          HH:mm:ss").format(Calendar.getInstance().getTime());
        timeLbl.setText(timeStamp);

        String icons= json.get("weather").toString();
        JSONArray iconsJson=new JSONArray(icons);
        String icon=iconsJson.getJSONObject(0).get("icon").toString(); //add lbl
        iconLbl.setGraphic(new ImageView("http://openweathermap.org/img/w/" + icon+ ".png"));
        iconLbl.setScaleX(2.5);
        iconLbl.setScaleY(2.5);

        String desc=iconsJson.getJSONObject(0).get("description").toString();
        descriptionLbl.setText(desc);
    }

    public void LogFile() throws IOException {
        String filename = "logFile.txt";
        FileWriter fileWriter = new FileWriter(filename,true);

        fileWriter.write(String.valueOf(countryNameLbl.getText()));
        fileWriter.write("\t");
        fileWriter.write(String.valueOf(cityNameLbl.getText()));
        fileWriter.write("\t");
        fileWriter.write(String.valueOf(descriptionLbl.getText()));
        fileWriter.write("\t");
        fileWriter.write(String.valueOf(temperatureLbl.getText()));
        fileWriter.write("\t");
        fileWriter.write(String.valueOf(pressureLbl.getText()));
        fileWriter.write("\t");
        fileWriter.write(String.valueOf(humidityLbl.getText()));
        fileWriter.write("\t");
        fileWriter.write(String.valueOf(windLbl.getText()));
        fileWriter.write("\t");
        fileWriter.write(String.valueOf(timeLbl.getText()));
        fileWriter.write("\n");

        fileWriter.close();
    }

    public void initCombo(){
        countryBox.setOnAction((event) -> {
            int selectedIndex = countryBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = countryBox.getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ComboBox.getValue(): " +countryBox.getValue());
            String cnt = countryBox.getValue().toString();

            cityBox.setEditable(true);
            cityBox.getItems().removeAll(cityBox.getItems());

            if (cnt.equals("RO")) {
                cityBox.getItems().add("Zalau");
                cityBox.getItems().add("Jibou");
                cityBox.getItems().add("cluj");
                cityBox.getItems().add("brasov");
            }
            if (cnt.equals("FR")) {
                cityBox.getItems().add("Tarascon");
                cityBox.getItems().add("Ploufragan");
            }
            if(cnt.equals("RU")) {
                cityBox.getItems().add("Moscow");
                cityBox.getItems().add("Razvilka");
            }
            if(cnt.equals("HU")) {
                cityBox.getItems().add("Budapesta");
                cityBox.getItems().add("Razvilka");
            }
            if(cnt.equals("GB")) {
                cityBox.getItems().add("London");
                cityBox.getItems().add("Southall");
            }
            if(cnt.equals("BE")) {
                cityBox.getItems().add("Brussels");
                cityBox.getItems().add("ternat");
            }
            if(cnt.equals("ES")) {
                cityBox.getItems().add("Kingdom of Spain");
                cityBox.getItems().add("Madrid");
            }
            if(cnt.equals("IT")) {
                cityBox.getItems().add("Milan");
                cityBox.getItems().add("Roma");
            }
            if(cnt.equals("DE")) {
                cityBox.getItems().add("Frankfurt");
                cityBox.getItems().add("Jena");
            }
            if(cnt.equals("BG")) {
                cityBox.getItems().add("burgas");
                cityBox.getItems().add("Yambol");
            }

            cityBox.setOnAction((event1 -> {
                Object cityBoxSelectedItem = cityBox.getSelectionModel().getSelectedItem();
                System.out.println("        CityBox.getValue(): " +cityBox.getValue());
                citySelect=cityBox.getValue().toString();

                try {
                    ApiWeather api = new ApiWeather(citySelect);
                    JSONObject json =api.getJson();
                    setLbl(json);
                    LogFile();

                    setModel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        });
    }
}