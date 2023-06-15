package org.example;
import java.time.LocalTime;
public class Picker
{
    String Id;

    LocalTime workStart;
    LocalTime workEnd;

    public Picker(String Id, LocalTime workStart, LocalTime workEnd)
    {
        this.Id = Id;
        this.workStart = workStart;
        this.workEnd = workEnd;

    }
    public String getId()
    {
        return Id;
    }

    public LocalTime getWorkStart()
    {
        return workStart;
    }

    public LocalTime getWorkEnd()
    {
        return workEnd;
    }


    public void assignPicker(LocalTime endTime)
    {
        this.workEnd = endTime;


    }

}
