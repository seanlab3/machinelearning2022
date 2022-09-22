package com.dcca.jane.mmy.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.List;

/**
 * Created by Luke on 04/02/2018.
 *
 * DAO for ingredients. Work in progress
 */

@Dao
@TypeConverters(DateConverter.class)
public interface IngredientDao {

    @Query("select * from Ingredient where id = :id")
    Ingredient loadIngredientById(long id);

    @Query("SELECT * FROM Ingredient WHERE Ingredient.meal_id LIKE :mealId")
    List<Ingredient> findIngredientsOfMeal(long mealId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertIngredient(Ingredient ingredient);


    @Query("DELETE FROM Ingredient")
    void deleteAll();

    @Query("DELETE FROM Ingredient " +
            "WHERE Ingredient.id = :ingredientId")
    void deleteIngredientById(long ingredientId);

    @Query("DELETE FROM Ingredient " +
            "WHERE Ingredient.meal_id = :mealId")
    void deleteIngredientByMealId(long mealId);
}
