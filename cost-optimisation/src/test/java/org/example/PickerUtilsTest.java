package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PickerUtilsTest {
    @Test
    void getAvailablePickerIndex()
    {
        ArrayList<Picker> pickerArrList = new ArrayList<>();
        LocalTime timeNow = LocalTime.of(10,0,0);
        pickerArrList.add(new Picker("1",LocalTime.of(7,0,0),LocalTime.of(15,0,0)));
        pickerArrList.add(new Picker("2",LocalTime.of(7,0,0),LocalTime.of(9,0,0)));



        assertEquals(1,PickerUtils.getAvailablePickerIndex(pickerArrList,timeNow));



    }

    @Test
    void getAvailablePickerIndexEdgeCase()
    {
        ArrayList<Picker> pickerArrList = new ArrayList<>();
        LocalTime timeNow = LocalTime.of(10,0,0);
        pickerArrList.add(new Picker("1",LocalTime.of(7,0,0),LocalTime.of(10,0,0)));
        pickerArrList.add(new Picker("2",LocalTime.of(7,0,0),LocalTime.of(11,0,0)));
        assertEquals(0,PickerUtils.getAvailablePickerIndex(pickerArrList,timeNow));
    }

    @Test
    void getAvailablePickerIndexNotAvailable()
    {
        ArrayList<Picker> pickerArrList = new ArrayList<>();
        LocalTime timeNow = LocalTime.of(10,0,0);
        pickerArrList.add(new Picker("1",LocalTime.of(7,0,0),LocalTime.of(12,0,0)));
        pickerArrList.add(new Picker("2",LocalTime.of(7,0,0),LocalTime.of(11,0,0)));
        assertEquals(-1,PickerUtils.getAvailablePickerIndex(pickerArrList,timeNow));
    }



}