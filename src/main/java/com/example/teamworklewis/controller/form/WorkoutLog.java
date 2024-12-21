package com.example.teamworklewis.controller.form;

import javafx.fxml.FXMLLoader;

import java.time.LocalDate;

public class WorkoutLog {
    private LocalDate date;
    private String workoutType;
    private String intensity;
    private int caloriesBurnt;

    public WorkoutLog(LocalDate date, String workoutType, String intensity, int caloriesBurnt) {
        this.date = date;
        this.workoutType = workoutType;
        this.intensity = intensity;
        this.caloriesBurnt = caloriesBurnt;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public String getIntensity() {
        return intensity;
    }

    public int getCaloriesBurnt() {
        return caloriesBurnt;
    }
}
