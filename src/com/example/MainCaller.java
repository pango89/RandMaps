package com.example;
import com.randmcnally.IrApi;

public class MainCaller
{
    public static void main(String[] args)
    {
        IrApi irApi = new IrApi();
        RandMcnally rm = new RandMcnally(irApi);

        int apiHandle = rm.Initialize();

        rm.ClearPreviousLocations(apiHandle);

        Location[] locations = new Location[2];
        locations[0] = new Location("Kansas City", "", "KS", 0,0 ,  "", 39.11417100f, -94.62745700f);
        locations[1] = new Location("Seattle", "", "WA", 0,0 ,  "", 47.60621000f, -122.33207100f);

        for (int i = 0; i < locations.length; i++)
        {
            rm.ValidateLocation(locations[i], apiHandle);
            // rm.AddLocation(locations[i], apiHandle);
        }

        // rm.DoRoute(apiHandle);
        // rm.GetTotalMileage(apiHandle);

        rm.UnInitialize();
    }
}
