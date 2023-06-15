package org.example;

import com.google.gson.JsonArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.JsonObject;
import java.time.LocalTime;
import java.util.ArrayList;

class StoreUtilsTest {

    @Test
    void setStartTime()
    {
        JsonObject storeObject = new JsonObject();
        String startTime = "09:00";
        storeObject.addProperty("pickingStartTime",startTime);
        assertEquals(LocalTime.parse(startTime),StoreUtils.setStartTime(storeObject));

    }

    @Test
    void setStartTimePropertyNotExisting()
    {
        JsonObject storeObject = new JsonObject();
        assertEquals(LocalTime.of(0,0),StoreUtils.setStartTime(storeObject));

    }

    @Test
    void setStartTimeWrongTime()
    {
        JsonObject storeObject = new JsonObject();
        String startTime = "aaa";
        storeObject.addProperty("pickingStartTime",startTime);
        assertEquals(LocalTime.of(0,0),StoreUtils.setStartTime(storeObject));

    }

    @Test
    void setEndTime()
    {
        JsonObject storeObject = new JsonObject();
        String endTime = "12:00";
        storeObject.addProperty("pickingEndTime",endTime);
        assertEquals(LocalTime.parse(endTime),StoreUtils.setEndTime(storeObject));
    }

    @Test
    void setEndTimePropertyNotExisting()
    {
        JsonObject storeObject = new JsonObject();
        assertEquals(LocalTime.of(0,0),StoreUtils.setEndTime(storeObject));

    }


    @Test
    void setEndTimeWrongTime()
    {
        JsonObject storeObject = new JsonObject();
        String endTime = "aaa";
        storeObject.addProperty("pickingEndTime",endTime);
        assertEquals(LocalTime.of(0,0),StoreUtils.setEndTime(storeObject));

    }

    @Test
    void setPickersList() {
        JsonObject storeObject = new JsonObject();
        JsonArray pickersArray = new JsonArray();

        pickersArray.add("P1");
        pickersArray.add("P2");
        pickersArray.add("P3");

        storeObject.add("pickers",pickersArray);
        ArrayList<String> expectedArrList = new ArrayList<>();

        expectedArrList.add("P1");
        expectedArrList.add("P2");
        expectedArrList.add("P3");

        assertEquals(expectedArrList,StoreUtils.setPickersList(storeObject));
    }

    @Test
    void setPickersListWithoutPickers() {
        JsonObject storeObject = new JsonObject();
        JsonArray pickersArray = new JsonArray();
        storeObject.add("pickers",pickersArray);
        ArrayList<String> expectedArrList = new ArrayList<>();
        assertEquals(expectedArrList,StoreUtils.setPickersList(storeObject));
    }

    @Test
    void setPickerObjectListPickerId()
    {
        ArrayList<String> pickersList = new ArrayList<>();
        pickersList.add("P1");
        pickersList.add("P2");
        pickersList.add("P3");
        LocalTime startTime = LocalTime.of(9,0);
        ArrayList<Picker> pickerObjectList;
        pickerObjectList = StoreUtils.setPickerObjectList(pickersList,startTime);
        for(int i=0;i<pickerObjectList.size();i++)
            assertEquals(pickersList.get(i),pickerObjectList.get(i).getId());

    }

    @Test
    void setPickerObjectListPickerstartTime()
    {
        ArrayList<String> pickersList = new ArrayList<>();
        pickersList.add("P1");
        pickersList.add("P2");
        pickersList.add("P3");
        LocalTime startTime = LocalTime.of(9,0);

        ArrayList<Picker> pickerObjectList;
        pickerObjectList = StoreUtils.setPickerObjectList(pickersList,startTime);
        for (Picker picker : pickerObjectList)
            assertEquals(startTime, picker.getWorkStart());


    }


    @Test
    void setPickerObjectListPickerEndTime()
    {
        ArrayList<String> pickersList = new ArrayList<>();
        pickersList.add("P1");
        pickersList.add("P2");
        pickersList.add("P3");
        LocalTime startTime = LocalTime.of(9,0);

        ArrayList<Picker> pickerObjectList;
        pickerObjectList = StoreUtils.setPickerObjectList(pickersList,startTime);
        for (Picker picker : pickerObjectList)
            assertEquals(LocalTime.of(0,0), picker.getWorkEnd());


    }
}