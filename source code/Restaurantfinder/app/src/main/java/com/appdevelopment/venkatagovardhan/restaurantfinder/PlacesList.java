package com.appdevelopment.venkatagovardhan.restaurantfinder;

/**
 * Created by Venkata Govardhan on 3/11/2016.
 */
import java.util.List;
public class PlacesList {
    @Key
    public String status;

    @Key
    public List<Restaurant> results;
}
