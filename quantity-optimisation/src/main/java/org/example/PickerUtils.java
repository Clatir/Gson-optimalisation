package org.example;


import java.time.LocalTime;
import java.util.ArrayList;

public class PickerUtils {
    //Returns the index of the first available picker
    public static int getAvailablePickerIndex(ArrayList<Picker> pickerArrList, LocalTime timeNow)
    {
        for(Picker picker : pickerArrList)
        {

            if(picker.getWorkEnd().compareTo(timeNow)<=0)
            {
                return pickerArrList.indexOf(picker);
            }

        }
        return -1;

    }


}
