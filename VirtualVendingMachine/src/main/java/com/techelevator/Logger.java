package com.techelevator;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Logger {

    public void write(String message){


        File logFile = new File("log.txt");
        PrintWriter writer = null;

        if(logFile.exists()){
            try {
                writer = new PrintWriter(new FileOutputStream(logFile, true));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                writer = new PrintWriter(logFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month =now.getMonthValue();
        int day = now.getDayOfMonth();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime nowinTime = LocalTime.now();
        int hour = nowinTime.getHour();
        int minute = nowinTime.getMinute();
        String amPM;
        if (hour > 12){
            hour = hour - 12;
            amPM = "PM";
        } else {
            amPM = "AM";
        }

        String americanTimeFormat = hour + ":" +  minute;
        String americanDateFormat = month + "/" + day + "/" + year;
        if (minute < 10){
            americanTimeFormat = hour + ":0" +  minute;
        }

        writer.append(americanDateFormat + " " + americanTimeFormat + " " + amPM + " " + message );
        writer.flush();
        writer.close();

    }




}
