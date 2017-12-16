package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by a on 11/27/15.
 */
public class WeatherDetail {
    @SerializedName("main")
    public String visibility;
    public String description;
    public String icon;
}
