package com.kuiko.api.model;

public class WeatherDTO {

    private String communityCode;
    private String temperature;
    private String pressure;
    private String humidity;


    public WeatherDTO(String communityCode, String temperature, String pressure, String humidity) {
        this.communityCode = communityCode;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    
    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

}
