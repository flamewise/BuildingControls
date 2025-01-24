package com.example.buildingcontrols;

import com.example.buildingcontrols.models.Apartment;
import com.example.buildingcontrols.models.CommonRoom;
import com.example.buildingcontrols.models.CommonRoomType;

public class Main {
    public static void main(String[] args) {
        // Create a Gym CommonRoom
        CommonRoom gym = new CommonRoom("CR101", 50, CommonRoomType.GYM);

        // Display Gym details
        System.out.println(gym);

        // Create a Library CommonRoom
        CommonRoom library = new CommonRoom("CR102", 30, CommonRoomType.LIBRARY);

        // Display Library details
        System.out.println(library);

        // Update the type and capacity of the gym
        gym.setType(CommonRoomType.LAUNDRY);
        gym.setCapacity(40);

        // Display updated Gym details
        System.out.println(gym);
    }
}
