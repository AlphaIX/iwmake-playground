package com.iwmake.designpattern.builder;

import com.iwmake.designpattern.builder.item.ChickenBurger;
import com.iwmake.designpattern.builder.item.Coke;
import com.iwmake.designpattern.builder.item.Pepsi;
import com.iwmake.designpattern.builder.item.VegBurger;

/**
 * MealBuilder 创建Meal对象
 * @author Dylan
 * @since 2020-11-06
 */
public class MealBuilder {

    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
