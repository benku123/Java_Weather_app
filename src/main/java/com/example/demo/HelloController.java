package com.example.demo;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.net.URLConnection;
import java.text.SimpleDateFormat;

public class HelloController {

    @FXML
    private TextField City;
    @FXML
    private Text time;

    @FXML
    private Button CityName;

    @FXML
    private Text feels;

    @FXML
    private Text temp;

    @FXML
    private Text temp_mn;

    @FXML
    private Text temp_mx;

    @FXML
    void initialize() {
        time_cur();
        CityName.setOnAction(event -> {
            time_cur();
            String NameCity = City.getText().trim();

            if (!NameCity.equals("")) {

                String getUrl = getUrl("https://api.openweathermap.org/data/2.5/weather?q=" + NameCity + "&appid=470cb05725b34ffbb43e63e4f0120eba");
                if (!getUrl.isEmpty()) {
                    JSONObject obj = new JSONObject(getUrl);
                    temp.setText("Feels: " + obj.getJSONObject("main").getDouble("temp"));
                    feels.setText("Feels: " + obj.getJSONObject("main").getDouble("feels_like"));
                    temp_mx.setText("Maximum: " + obj.getJSONObject("main").getDouble("temp_max"));
                    temp_mn.setText("Minimum: " + obj.getJSONObject("main").getDouble("temp_min"));
                }
            }
        });
}

    private void time_cur()
    {
        Calendar time_current;
        time_current = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        time.setText(format.format(time_current.getTime()));
    }
    private static String getUrl(String urlString) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlString);
            URLConnection urlcon = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }

            bufferedReader.close();
        } catch (Exception ex) {
            System.out.println("Wrong Input");
        }
        return  content.toString();
    }
}