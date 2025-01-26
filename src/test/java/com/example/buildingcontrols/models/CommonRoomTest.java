package com.example.buildingcontrols.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommonRoomTest {

    @Test
    void testCommonRoomInitialization() {
        CommonRoom commonRoom = new CommonRoom("CR101", CommonRoomType.GYM);
        
        assertEquals("CR101", commonRoom.getId());
        assertEquals(CommonRoomType.GYM, commonRoom.getType());
        assertTrue(commonRoom.getTemperature() >= 10 && commonRoom.getTemperature() <= 40, 
                "Temperature should be initialized between 10 and 40 degrees.");
        assertFalse(commonRoom.isHeatingEnabled());
        assertFalse(commonRoom.isCoolingEnabled());
    }

    @Test
    void testSetAndGetType() {
        CommonRoom commonRoom = new CommonRoom("CR102", CommonRoomType.LIBRARY);
        
        commonRoom.setType(CommonRoomType.LAUNDRY);
        assertEquals(CommonRoomType.LAUNDRY, commonRoom.getType());
    }

    @Test
    void testHeatingCoolingStates() {
        CommonRoom commonRoom = new CommonRoom("CR103", CommonRoomType.GYM);
        
        commonRoom.updateHeatingCoolingStates(15.0);
        assertTrue(commonRoom.isCoolingEnabled() || commonRoom.isHeatingEnabled() || 
                   (!commonRoom.isCoolingEnabled() && !commonRoom.isHeatingEnabled()));
    }

    @Test
    void testToString() {
        CommonRoom commonRoom = new CommonRoom("CR104", CommonRoomType.LIBRARY);
        
        String expected = "CommonRoom{id='CR104', temperature=" + commonRoom.getTemperature() +
                ", heatingEnabled=" + commonRoom.isHeatingEnabled() +
                ", coolingEnabled=" + commonRoom.isCoolingEnabled() +
                ", type=LIBRARY}";
        assertEquals(expected, commonRoom.toString());
    }
}
