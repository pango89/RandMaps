package com.example;
import com.randmcnally.IrApi;

public class RandMcnally
{
    public IrApi irApi;
    public RandMcnally(IrApi irApi)
    {
        this.irApi = irApi;
    }

    public int Initialize()
    {
        Object[] arg = new Object[7];
        for (int i = 0; i < 6; i++)
        {
            arg[i] = new String("");
        }
        arg[6] = -1;

        int err = this.irApi.irSAInitialize(arg);
        int apiHandle = ((Integer)arg[6]).intValue();
        // System.out.println(err);
        // System.out.println(apiHandle);
        this.LogStatus(arg, err, "Initialize");
        return apiHandle;
    }

    public void UnInitialize()
    {
        this.irApi.irUnInitialize();
    }

    public void ClearPreviousLocations(int apiHandle)
    {
        Object[] arg = new Object[1];
        arg[0] = apiHandle;
        int err = this.irApi.irClearRouteLocations(arg);
        this.LogStatus(arg, err, "ClearPreviousLocations");
    }

    public void ValidateLocation(Location loc, int apiHandle)
    {
        Object[] arg = new Object[8];
        arg[0] = apiHandle;
        arg[1] = new String(loc.City);
        // arg[1] = new String(String.valueOf(loc.Latitude) + " " + String.valueOf(loc.Longitude));
        arg[2] = new String(loc.County);
        arg[3] = new String(loc.State);
        arg[4] = new String("MI");
        arg[5] = 0;
        arg[6] = 0;
        arg[7] = -1;

        int err = this.irApi.irValidateListCount(arg);
        this.LogStatus(arg, err, "ValidateLocation");
        int iNumRec = ((Integer)arg[6]).intValue();
        int iClosestMatch = ((Integer)arg[7]).intValue();

        arg = new Object[9];
        arg[0] = apiHandle;
        for (int i = 2; i < 9; i++)
        {
            arg[i] = new String("");
        }

        for (int i = 0; i < iNumRec; i++)
        {
            arg[1] = i + 1;
            err = this.irApi.irValidateListRecord(arg);
            this.LogStatus(arg, err, "ValidateLocation.ValidateListRecord" + i);

            for (int j = 2; j < 9; j++)
            {
                System.out.println(arg[j].toString());
            }
        }
    }

    public void AddLocation(Location location, int apiHandle)
    {
        Object[] arg = new Object[4];
        arg[0] = apiHandle;
        arg[1] = location.City;
        arg[2] = location.County;
        arg[3] = location.State;

        int err = this.irApi.irAddLocation(arg);
        this.LogStatus(arg, err, "AddLocation");
    }

    public void DoRoute(int apiHandle)
    {
        Object[] arg = new Object[2];
        arg[0] = apiHandle;
        arg[1] = new String("PR"); //sType

        int err = this.irApi.irRoute(arg);
        this.LogStatus(arg, err, "DoRoute");
    }

    public void GetTotalMileage(int apiHandle)
    {
        Object[] arg = new Object[2];
        arg[0] = apiHandle;
        arg[1] = 0;

        int err = this.irApi.irGetTotalMileageRecords(arg);
        this.LogStatus(arg, err, "GetTotalMileageRecords");

        int iNumRecords = ((Integer)arg[1]).intValue();
        arg = new Object[16];
        arg[0] = apiHandle;

        for (int i = 0; i < iNumRecords; i++)
        {
            arg[1] = i + 1;
            for (int j = 2; j < 16; j++)
            {
                if(j != 11)
                {
                    arg[j] = new String("");
                }
                else
                {
                    arg[j] = 0.0f;
                }
            }

            err = this.irApi.irGetMileageInfo(arg);
            this.LogStatus(arg, err, "GetTotalMileageRecords.GetMileageInfo" + i);

            System.out.println(((Float)arg[11]).floatValue());
        }
    }

    private void LogStatus(Object[] arg, int err, String caller)
    {
        if(err >= 0)
        {
            System.out.println("Success in : " + caller);
        }
        else
        {
            System.out.println("Error Code : " + this.irApi.irGetLastErrorCode(arg) + " in " + caller);
            System.out.println("Error String : " + this.irApi.irGetLastErrorString(arg) + " in " + caller);
        }
    }
}
