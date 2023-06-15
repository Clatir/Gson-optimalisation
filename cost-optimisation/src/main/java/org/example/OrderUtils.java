package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public class OrderUtils {

    //Sorting orders based on pickingTime/orderValue ratio
    public static JsonArray sortOrders(JsonArray ordersArray)
    {
        ArrayList<JsonElement> sortedOrders = new ArrayList<>();
        ordersArray.forEach(sortedOrders::add);
        sortedOrders.sort(Comparator.comparing(o -> Float.valueOf(o.getAsJsonObject().get("orderWeight").getAsString())));


        JsonArray sortedOrdersArray = new JsonArray();
        sortedOrders.forEach(sortedOrdersArray::add);
        return sortedOrdersArray;
    }


    public static void addWeightProperty(JsonArray ordersArray)
    {
        for(JsonElement orderElement : ordersArray)
        {
            JsonObject orderElementObject = orderElement.getAsJsonObject();
            double orderValue = orderElementObject.get("orderValue").getAsDouble();
            Duration orderDuration = Duration.parse(orderElementObject.get("pickingTime").getAsString());
            double orderWeight = orderDuration.toMinutes() / orderValue;
            orderElementObject.addProperty("orderWeight", orderWeight);
        }
    }

    //Assigning orders to pickers and printing the result
    public static void ordersToPickers(LocalTime startTime, LocalTime endTime, ArrayList<Picker> pickerObjectList, JsonArray sortedOrdersArray, OrderAssignmentObserver observer)
    {
        int currentPicker;
        int currentOrder = 0;
        int ordersCount = sortedOrdersArray.size();
        LocalTime currentTime = startTime;




        while(currentOrder<ordersCount)
        {
            boolean orderIncrementedFlag = false;
            Duration currentOrderDuration = Duration.parse(sortedOrdersArray.get(currentOrder).getAsJsonObject().get("pickingTime").getAsString());
            LocalTime currrentOrderDeadLine = LocalTime.parse(sortedOrdersArray.get(currentOrder).getAsJsonObject().get("completeBy").getAsString());

            if((PickerUtils.getAvailablePickerIndex(pickerObjectList,currentTime)) != -1)
            {
                if (Duration.between(currentTime,currrentOrderDeadLine).compareTo(currentOrderDuration)>=0)

                {


                    currentPicker = PickerUtils.getAvailablePickerIndex(pickerObjectList,currentTime);
                    pickerObjectList.get(currentPicker).assignPicker(currentTime.plus(currentOrderDuration));

                    String oderId = sortedOrdersArray.get(currentOrder).getAsJsonObject().get("orderId").getAsString();

                    if(observer != null)
                        observer.onOrderAssigned(pickerObjectList.get(currentPicker),oderId,currentTime);

                    System.out.print(pickerObjectList.get(currentPicker).getId() + " "+sortedOrdersArray.get(currentOrder).getAsJsonObject().get("orderId").getAsString()+" ");
                    System.out.println(currentTime);


                }

                else
                {

                    currentOrder++;
                    orderIncrementedFlag = true;

                }
                if(!orderIncrementedFlag)
                {
                    currentOrder++;
                }

            }
            else
            {
                if(currentTime.equals(endTime))
                {

                    break;
                }
                currentTime = currentTime.plusMinutes(1);
            }


        }
    }
}
