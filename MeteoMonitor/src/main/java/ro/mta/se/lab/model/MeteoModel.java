package ro.mta.se.lab.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MeteoModel {

    StringProperty countryName;
    StringProperty cityName;
    StringProperty description;
    StringProperty temperature;
    StringProperty pressure;
    StringProperty humidity;
    StringProperty wind;
    StringProperty iconID;

    public MeteoModel(String country, String nume, String desc, String temp, String press, String humid, String wind, String icon) {
        this.countryName = new SimpleStringProperty(country);
        this.cityName = new SimpleStringProperty(nume);
        this.description = new SimpleStringProperty(desc);
        this.temperature = new SimpleStringProperty(temp);
        this.pressure = new SimpleStringProperty(press);
        this.humidity = new SimpleStringProperty(humid);
        this.wind = new SimpleStringProperty(wind);
        this.iconID = new SimpleStringProperty(icon);
    }

    public String getCountryName() {
        return countryName.get();
    }

    public void setCountryName(String country) {
        this.countryName.set(country);
    }

    public StringProperty countryNameProperty() {
        return cityName;
    }

    public String getCityName() {
        return cityName.get();
    }

    public void setCityName(String cityName) {
        this.cityName.set(cityName);
    }

    public StringProperty cityNameProperty() {
        return cityName;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getTemperature() {
        return temperature.get();
    }

    public void setTemperature(String temperature) {
        this.temperature.set(temperature);
    }

    public StringProperty temperatureProperty() {
        return temperature;
    }

    public String getPressure() {
        return pressure.get();
    }

    public void setPressure(String pressure) {
        this.pressure.set(pressure);
    }

    public StringProperty pressureProperty() {
        return pressure;
    }

    public String getHumidity() {
        return humidity.get();
    }

    public void setHumidity(String humidity) {
        this.humidity.set(humidity);
    }

    public StringProperty humidityProperty() {
        return humidity;
    }

    public String getWind() {
        return wind.get();
    }

    public void setWind(String wind) {
        this.wind.set(wind);
    }

    public StringProperty windProperty() {
        return wind;
    }

    public String getIconID() {
        return iconID.get();
    }

    public void setIconID(String iconID) {
        this.iconID.set(iconID);
    }

    public StringProperty iconIDProperty() {
        return iconID;
    }

}
