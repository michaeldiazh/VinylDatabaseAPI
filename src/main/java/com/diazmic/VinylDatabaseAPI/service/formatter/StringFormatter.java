package com.diazmic.VinylDatabaseAPI.service.formatter;

public class StringFormatter {

    public static String formatSeconds(int totalSeconds) {
        int minutes = Math.floorDiv(totalSeconds,60);
        int seconds = totalSeconds - (60*minutes);
        return minutes + ":" + seconds;
    }
}
