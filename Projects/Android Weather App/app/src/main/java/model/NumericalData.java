package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by a on 11/27/15.
 */
public class NumericalData {
    public float temp;
    public float pressure;
    public float humidity;
    @SerializedName("temp_min")
    public float tempMin;
    @SerializedName("temp_max")
    public float tempMax;
    @SerializedName("sea_level")
    public float seaLevel;
    @SerializedName("grnd_level")
    public float grndLevel;
}
