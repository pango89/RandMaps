package com.example;

public class Location
{
    public String City;
    public String County;
    public String State;
    public int ZipCode;
    public int SPLCCode;
    public String PostalCode;
    public float Latitude;
    public float Longitude;

    public Location(String city, String county, String state, int zipCode, int SPLCCode, String postalCode, float latitude, float longitude)
    {
        City = city;
        County = county;
        State = state;
        ZipCode = zipCode;
        this.SPLCCode = SPLCCode;
        PostalCode = postalCode;
        Latitude = latitude;
        Longitude = longitude;
    }
}
