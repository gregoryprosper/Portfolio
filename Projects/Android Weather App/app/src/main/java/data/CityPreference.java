package data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by a on 11/28/15.
 */
public class CityPreference {
    private static SharedPreferences prefs;

    public  static String getCity(Context context){
        prefs = context.getSharedPreferences("WeatherID",Context.MODE_PRIVATE);
        return prefs.getString("city", "Pompano,us");
    }

    public static void setCity(Context context,String location){
        prefs = context.getSharedPreferences("WeatherID",Context.MODE_PRIVATE);
        prefs.edit().putString("city", location).commit();
    }
}
