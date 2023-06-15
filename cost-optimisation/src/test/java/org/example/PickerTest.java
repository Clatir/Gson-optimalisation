package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class PickerTest {

    @Test
    void getId()
    {
        Picker picker = new Picker("125",LocalTime.of(7,0,0),LocalTime.of(15,0,0));
        assertEquals("125",picker.getId());

    }

    @Test
    void getWorkStart()
    {
        Picker picker = new Picker("125",LocalTime.of(7,0,0),LocalTime.of(15,0,0));
        assertEquals(LocalTime.of(7,0,0),picker.getWorkStart());

    }
    @Test
    void getWorkEnd()
    {
        Picker picker = new Picker("125",LocalTime.of(7,0,0),LocalTime.of(15,0,0));
        assertEquals(LocalTime.of(15,0,0),picker.getWorkEnd());

    }

    @Test
    void assignPicker()
    {
        Picker picker = new Picker("125",LocalTime.of(7,0,0),LocalTime.of(15,0,0));
        picker.assignPicker(LocalTime.of(15,0,0));
        assertEquals(LocalTime.of(15,0,0),picker.getWorkEnd());

    }



}

