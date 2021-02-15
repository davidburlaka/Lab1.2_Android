package com.company;

import java.time.LocalDateTime;

public class TimeDB {


    private int hours;
    private int minutes;
    private int seconds;

    TimeDB() {
        hours = 0;
        minutes = 0;
        seconds = 0;
    }

    TimeDB(Integer hours, Integer minutes, Integer seconds){
        if (hours >= 0 && hours <= 23
        && minutes >= 0 && minutes <=59
        && seconds >= 0 && seconds <=59) {
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        }
        else System.out.println("Input error");
    }

    TimeDB(LocalDateTime date) {
        this.hours = date.getHour();
        this.minutes = date.getMinute();
        this.seconds = date.getSecond();
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String toString() {
        String timeOfDay = "";
        String oHours;
        if (hours > 12){
            timeOfDay = "PM";
            oHours = String.valueOf(hours - 12);
        }
        else {
            timeOfDay = "AM";
            if (hours < 9)
                oHours = "0" + String.valueOf(hours);
            else oHours = String.valueOf(hours);
        }
        return oHours + ":" + (minutes < 9 ? "0" + minutes : minutes) + ":" +
                (seconds < 9 ? "0" + seconds : seconds) + " " + timeOfDay;
    }

    public TimeDB plusDate(TimeDB time) {
        TimeDB newTime = new TimeDB();

        if(seconds + time.getSeconds() > 59) {
            newTime.setSeconds(seconds + time.getSeconds() - 60);
            newTime.setMinutes(1);
        }
        else newTime.setSeconds(seconds + time.getSeconds());

        if(newTime.getMinutes() + minutes + time.getMinutes() > 59) {
            newTime.setMinutes(newTime.getMinutes() + minutes + time.getMinutes() - 60);
            newTime.setHours(1);
        }
        else newTime.setMinutes(newTime.getMinutes() + minutes + time.getMinutes());

        if(newTime.getHours() + hours + time.getHours() > 23)
            newTime.setHours(newTime.getHours() + hours + time.getHours() - 24);
        else newTime.setHours(newTime.getHours() + hours + time.getHours());

        return newTime;
    }

    public TimeDB minusDate(TimeDB time) {
        TimeDB newTime = new TimeDB();

        if(seconds - time.getSeconds() < 0) {
            newTime.setSeconds(60 + seconds - time.getSeconds());
            newTime.setMinutes(-1);
        }
        else newTime.setSeconds(seconds - time.getSeconds());

        if(newTime.getMinutes() + minutes - time.getMinutes() < 0) {
            newTime.setMinutes(60 + newTime.getMinutes() + minutes - time.getMinutes());
            newTime.setHours(-1);
        }
        else newTime.setMinutes(newTime.getMinutes() + minutes - time.getMinutes());

        if(newTime.getHours() + hours - time.getHours() < 0)
            newTime.setHours(24 + newTime.getHours() + hours - time.getHours());
        else newTime.setHours(newTime.getHours() + hours - time.getHours());

        return newTime;
    }

    public static TimeDB sumDate(TimeDB time1, TimeDB time2) {
        TimeDB newTime = new TimeDB();

        if(time1.getSeconds() + time2.getSeconds() > 59) {
            newTime.setSeconds(time1.getSeconds() + time2.getSeconds() - 60);
            newTime.setMinutes(1);
        }
        else newTime.setSeconds(time1.getSeconds() + time2.getSeconds());

        if(newTime.getMinutes() + time1.getMinutes() + time2.getMinutes() > 59) {
            newTime.setMinutes(newTime.getMinutes() + time1.getMinutes() + time2.getMinutes() - 60);
            newTime.setHours(1);
        }
        else newTime.setMinutes(newTime.getMinutes() + time1.getMinutes() + time2.getMinutes());

        if(newTime.getHours() + time1.getHours() + time2.getHours() > 23)
            newTime.setHours(newTime.getHours() + time1.getHours() + time2.getHours() - 24);
        else newTime.setHours(newTime.getHours() + time1.getHours() + time2.getHours());

        return newTime;
    }

    public static TimeDB subDate(TimeDB time1, TimeDB time2) {
        TimeDB newTime = new TimeDB();

        if(time1.getSeconds() - time2.getSeconds() < 0) {
            newTime.setSeconds(60 + time1.getSeconds() - time2.getSeconds());
            newTime.setMinutes(-1);
        }
        else newTime.setSeconds(time1.getSeconds() - time2.getSeconds());

        if(newTime.getMinutes() + time1.getMinutes() - time2.getMinutes() < 0) {
            newTime.setMinutes(60 + newTime.getMinutes() + time1.getMinutes() - time2.getMinutes());
            newTime.setHours(-1);
        }
        else newTime.setMinutes(newTime.getMinutes() + time1.getMinutes() - time2.getMinutes());

        if(newTime.getHours() + time1.getHours() - time2.getHours() < 0)
            newTime.setHours(24 + newTime.getHours() + time1.getHours() - time2.getHours());
        else newTime.setHours(newTime.getHours() + time1.getHours() - time2.getHours());

        return newTime;
    }

}
