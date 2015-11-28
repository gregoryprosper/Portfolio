package data;


import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import model.WeatherResponse;
import util.Utils;

/**
 * Created by Gregory Prosper on 11/27/15.
 */
public class WeatherHttpClient {

    public WeatherResponse getWeather(String location){
        HttpURLConnection connection = null;
        WeatherResponse weatherResponse = null;

        try {
            location = location.replace(" ", "+");
            connection = (HttpURLConnection) (new URL(Utils.BASE_URL + location + Utils.METRICS + Utils.APP_ID)).openConnection();
            InputStream in = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

            weatherResponse = new Gson().fromJson(bufferedReader, WeatherResponse.class);

            bufferedReader.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return weatherResponse;
    }
}
