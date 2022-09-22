package com.dcca.jane.mmy.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.List;

/**
 * Created by Luke on 03/02/2018.
 *
 * Interface for accessing Meal data & some Ingredient Data. Work in progress
 */
@Dao
@TypeConverters(DateConverter.class)
public interface MealDao {
//
//    @Query("SELECT * From Meal")
//    List<Meal> findAllMeals();
//
//
//    @Query("SELECT * FROM Meal WHERE Meal.id = :mealId")
//    Meal findMealById(long mealId);

    @Query("SELECT * From Meal " +
            "WHERE Meal.mealTime > :dayStart " +
            "AND Meal.mealTime < :dayEnd")
    List<Meal> findAllMealsByDay(Date dayStart, Date dayEnd);

    @Query("SELECT Meal.mealType From Meal " +
            "WHERE Meal.id = :mealId ")
    int retrieveMealType(long mealId);


    @Query("SELECT Meal.mealTime From Meal " +
            "WHERE Meal.id = :mealId ")
    Date retrieveMealTime(long mealId);


    @Query("SELECT * From Meal " +
            "INNER JOIN Ingredient ON Meal.id = Ingredient.meal_id " +
            "WHERE Meal.mealType LIKE :mealType " +
            "AND Meal.mealTime > :dayStart " +
            "AND Meal.mealTime < :dayEnd")
    List<Ingredient> findMealIngredientsByDayandType(int mealType, Date dayStart, Date dayEnd);

    @Query("SELECT Meal.id From Meal " +
            "WHERE Meal.mealType LIKE :mealType " +
            "AND Meal.mealTime > :dayStart " +
            "AND Meal.mealTime < :dayEnd")
    long findMealIdByDayandType(int mealType, Date dayStart, Date dayEnd);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMeal(Meal meal);

    @Query("DELETE FROM Meal")
    void deleteAll();

    @Query("DELETE FROM Meal "  +
            "WHERE Meal.id = :mealId ")
    void deleteMealById(long mealId);
}
