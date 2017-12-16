package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by a on 11/27/15.
 */
public class WeatherResponse {
    public int id;
    public String name;
    @SerializedName("coord")
    public Coordinates coordinates;
    public List<WeatherDetail> weather;
    @SerializedName("main")
    public NumericalData data;
    public Wind wind;
    public Clouds clouds;
    @SerializedName("sys")
    public LocationInfo locationInfo;
    @SerializedName("dt")
    public long lastUpdated;
    @SerializedName("cod")
    public int response;

}
