package org.example;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class StoreUtils {
    public static LocalTime setStartTime(JsonObject storeObject)
    {
        try
        {
            if (storeObject.has("pickingStartTime"))

                return LocalTime.parse(storeObject.get("pickingStartTime").getAsString());
            return LocalTime.of(0, 0);
        }
        catch (DateTimeParseException e)
        {
            return LocalTime.of(0, 0);
        }



    }

    public static LocalTime setEndTime(JsonObject storeObject)
    {
        try
        {
            if (storeObject.has("pickingEndTime"))
                return LocalTime.parse(storeObject.get("pickingEndTime").getAsString());
            return LocalTime.of(0, 0);
        }
        catch (DateTimeParseException e)
        {
            return LocalTime.of(0, 0);
        }




    }

    public static ArrayList<String> setPickersList(JsonObject storeObject)
    {   ArrayList<String> pickersList = new ArrayList<>();
        if (storeObject.has("pickers"))
        {
            JsonElement pickersElement = storeObject.get("pickers");
            if (pickersElement.isJsonArray()) {
                for (JsonElement picker : pickersElement.getAsJsonArray())
                    pickersList.add(picker.getAsString());
            }

        }
        return pickersList;
    }

    public static ArrayList<Picker> setPickerObjectList(ArrayList<String> pickersList,LocalTime startTime)
    {   ArrayList<Picker> pickerObjectList = new ArrayList<>();
        LocalTime endTime = LocalTime.of(0,0);
        for(String picker : pickersList)
        {
            assert endTime != null;
            pickerObjectList.add(new Picker(picker,startTime, endTime));
        }
        return pickerObjectList;
    }
}
