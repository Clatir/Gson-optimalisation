package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderUtilsTest {

    @Test
    void sortOrdersUnsorted()
    {
        JsonArray ordersArray = new JsonArray();
        JsonArray expectedOrdersArray = new JsonArray();

        JsonObject order1 = new JsonObject();
        JsonObject order2 = new JsonObject();

        ordersArray.add(order1);
        ordersArray.add(order2);

        order1.addProperty("orderWeight","10.50");
        order2.addProperty("orderWeight","1.50");

        expectedOrdersArray.add(order2);
        expectedOrdersArray.add(order1);

        assertEquals(expectedOrdersArray,OrderUtils.sortOrders(ordersArray));

    }


    @Test
    void sortOrdersSorted()
    {
        JsonArray ordersArray = new JsonArray();
        JsonArray expectedOrdersArray = new JsonArray();

        JsonObject order1 = new JsonObject();
        JsonObject order2 = new JsonObject();

        ordersArray.add(order1);
        ordersArray.add(order2);

        order1.addProperty("orderWeight","1.50");
        order2.addProperty("orderWeight","10.50");

        expectedOrdersArray.add(order1);
        expectedOrdersArray.add(order2);

        assertEquals(expectedOrdersArray,OrderUtils.sortOrders(ordersArray));

    }

    @Test
    void sortOrdersEmpty()
    {
        JsonArray ordersArray = new JsonArray();
        JsonArray expectedOrdersArray = new JsonArray();
        assertEquals(expectedOrdersArray,OrderUtils.sortOrders(ordersArray));

    }

    @Test
    void ordersToPickers()
    {
        ArrayList<Picker> pickerObjectList = new ArrayList<>();
        JsonArray sortedOrdersArray = new JsonArray();
        JsonObject order1 = new JsonObject();
        JsonObject order2 = new JsonObject();
        JsonObject order3 = new JsonObject();

        Picker picker = new Picker("P1", LocalTime.of(8,0),LocalTime.of(0,0));
        pickerObjectList.add(picker);
        order1.addProperty("orderId", "1");
        order1.addProperty("pickingTime", "PT0H20M");
        order1.addProperty("orderWeight", "100.0");
        order1.addProperty("completeBy", "10:00");


        order2.addProperty("orderId", "2");
        order2.addProperty("pickingTime", "PT0H20M");
        order2.addProperty("orderWeight", "1.0");
        order2.addProperty("completeBy", "10:00");

        order3.addProperty("orderId", "3");
        order3.addProperty("pickingTime", "PT140H05M");
        order3.addProperty("orderWeight", "20.0");
        order3.addProperty("completeBy", "10:00");

        sortedOrdersArray.add(order1);
        sortedOrdersArray.add(order2);
        sortedOrdersArray.add(order3);


        StringBuilder result = new StringBuilder();


        OrderUtils.ordersToPickers(LocalTime.of(8, 0), LocalTime.of(12, 0), pickerObjectList, sortedOrdersArray, (pickerObj, orderId, assignmentTime) -> {
            result.append(picker.getId()).append(" order-").append(orderId).append(" ").append(assignmentTime).append("\n");
        });

        String expectedResult = "P1 order-1 08:00"+"\n"+"P1 order-2 08:20"+"\n";
        assertEquals(expectedResult, result.toString());
    }


}
