package org.example;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.time.LocalTime;



public class Main {





    public static void main(String[] args) {




        String storeFile = "src/main/java/org/example/store.json";
        String ordersPath = "src/main/java/org/example/orders.json";



        //Creating File Readers for orders.json and store.json

        FileReader ordersReader;

        {
            try {
                ordersReader = new FileReader(ordersPath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Could not find orders file");
            }
        }

        FileReader storeReader;

        {
            try {
                storeReader = new FileReader(storeFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Could not find store file");
            }
        }



        //Setting variables to process json files in the gson library

        Gson gson = new Gson();
        ArrayList<String> pickersList;
        JsonElement storeElement = gson.fromJson(storeReader, JsonElement.class);
        JsonElement ordersElement = gson.fromJson(ordersReader, JsonElement.class);
        JsonArray ordersArray = ordersElement.getAsJsonArray();
        LocalTime startTime;
        LocalTime endTime;
        ArrayList<Picker> pickerObjectList;
        JsonObject storeObject = storeElement.getAsJsonObject();
        OrderUtils.addWeightProperty(ordersArray);


        //Sorting orders based on pickingTime/orderValue ratio
        JsonArray sortedOrdersArray = OrderUtils.sortOrders(ordersArray);



        //Setting variables from store.json
        startTime = StoreUtils.setStartTime(storeObject);
        endTime=StoreUtils.setEndTime(storeObject);
        pickersList = StoreUtils.setPickersList(storeObject);
        pickerObjectList = StoreUtils.setPickerObjectList(pickersList,startTime);




        //Assigning orders to pickers and printing the result
        OrderUtils.ordersToPickers(startTime, endTime, pickerObjectList, sortedOrdersArray, new OrderAssignmentObserver() {
            @Override
            public void onOrderAssigned(Picker picker, String orderId, LocalTime timeAssigned) {
                //System.out.println(picker.getId() + orderId + timeAssigned + "OBS");
            }
        });



    }
}