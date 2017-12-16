package gprosper.org.weather;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

import data.CityPreference;
import data.WeatherHttpClient;
import model.WeatherResponse;
import util.Utils;


public class MainActivity extends AppCompatActivity {
    private TextView cityText, tempText, windText, cloudText, pressureText, humidityText, sunriseText, sunsetText, updateText;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();
        getWeather(CityPreference.getCity(this));
    }

    private void setUp(){
        cityText = (TextView) findViewById(R.id.cityText);
        tempText = (TextView) findViewById(R.id.tempText);
        windText = (TextView) findViewById(R.id.windText);
        cloudText = (TextView) findViewById(R.id.cloudText);
        pressureText = (TextView) findViewById(R.id.pressureText);
        humidityText = (TextView) findViewById(R.id.humidityText);
        sunriseText = (TextView) findViewById(R.id.sunriseText);
        sunsetText = (TextView) findViewById(R.id.sunsetText);
        updateText = (TextView) findViewById(R.id.updateText);
        icon = (ImageView) findViewById(R.id.icon);
    }

    private void getWeather(String location) {
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{location});
    }

    private class WeatherTask extends AsyncTask<String, Void, WeatherResponse>{

        @Override
        protected WeatherResponse doInBackground(String... params) {
            WeatherResponse weather = new WeatherHttpClient().getWeather(params[0]);
            return weather;
        }

        @Override
        protected void onPostExecute(WeatherResponse weatherResponse) {
            super.onPostExecute(weatherResponse);

            if (weatherResponse.response == 404){
                Toast.makeText(MainActivity.this,"Error Retrieving Weather", Toast.LENGTH_LONG).show();

                //Set City Preferences to default
                CityPreference.setCity(MainActivity.this, "Pompano,us");
                getWeather(CityPreference.getCity(MainActivity.this));

                return;
            }

            //Show Weather Info
            showWeather(weatherResponse);

            //Show Image icon
            DownloadImageTask downloadImageTask = new DownloadImageTask();
            downloadImageTask.execute(new String[]{weatherResponse.weather.get(0).icon});
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadImage(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            icon.setImageBitmap(bitmap);
        }

        private Bitmap downloadImage(String code){
            HttpURLConnection connection = null;
            Bitmap image = null;
            try {
                connection = (HttpURLConnection) new URL(Utils.ICON_URL + code + ".png").openConnection();
                if (connection.getResponseCode() == 200){
                    image = BitmapFactory.decodeStream(connection.getInputStream());
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return image;
        }
    }

    private void showWeather(WeatherResponse weather) {
        DateFormat df = DateFormat.getTimeInstance();

        cityText.setText(weather.name + ", " + weather.locationInfo.country);
        tempText.setText(weather.data.temp + " °C");
        windText.setText("Wind: " + weather.wind.speed + " mps");
        cloudText.setText("Condition: " + weather.weather.get(0).visibility + " (" + weather.weather.get(0).description + ")");
        pressureText.setText("Pressure: " + weather.data.pressure + " hPa");
        humidityText.setText("Humidity: " + weather.data.humidity + " %");
        sunriseText.setText("Sunrise: " + df.format(new Date(weather.locationInfo.sunrise * 1000)));
        sunsetText.setText("Sunset: " + df.format(new Date(weather.locationInfo.sunset * 1000)));
        updateText.setText("Last Updated: " + df.format(new Date(weather.lastUpdated * 1000)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.change_cityId:
                changeLocation();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changeLocation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Locations");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Boca Raton,US");
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newLocation = input.getText().toString();
                CityPreference.setCity(MainActivity.this, newLocation);
                getWeather(CityPreference.getCity(MainActivity.this));
            }
        });
        builder.setNegativeButton("Cancel", null);

        builder.show();
    }
}
