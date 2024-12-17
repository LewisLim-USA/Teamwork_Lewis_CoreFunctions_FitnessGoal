package com.example.teamworklewis.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Meal implements Serializable {
    private String mealName;
    private int calories;
    private int protein;
    private int fat;
    private int carbs;
    private LocalDate date;

    // Constructor
    public Meal(String mealName, int calories, int protein, int fat, int carbs) {
        this.mealName = mealName;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    // Getters and Setters
    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
