package com.diazmic.VinylDatabaseAPI.service.formatter;

public class IntegerFormatter {

    public static int formatSeconds(String length) {
        String[] stringArr = length.split(":");
        int secondsFromMinutes = Integer.getInteger(stringArr[1]) * 60;
        return secondsFromMinutes + Integer.getInteger(stringArr[1]);
    }
}
