package org.example;

import java.time.LocalTime;

public interface OrderAssignmentObserver {
    void onOrderAssigned(Picker picker, String orderId, LocalTime timeAssigned);
}
