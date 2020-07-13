package com.example.optimal_buzz.util

import android.icu.lang.UProperty.MATH
import com.example.optimal_buzz.model.BuzzConstants
import com.example.optimal_buzz.model.Drink


object DrinkUtil {
    @JvmStatic

/*Given minutes drinking and a number of standard drinks, return a float representing peak blood alcohol content*/
    fun calculateBAC(
        standardDrinks: Float,
        minutesDrinking: Float,
        weight: Float,
        isFemale: Boolean,
        isMetric: Boolean
    ): Float {
        var hours = minutesDrinking / 60
        var userWeight = weight
        var BW = BuzzConstants.MALE_BW
        var MR = BuzzConstants.MALE_MB
        if (isFemale) {
            BW = BuzzConstants.FEMALE_BW
            MR = BuzzConstants.FEMALE_MB
        }
        if (!isMetric) {
            userWeight *= BuzzConstants.LB_TO_KG
        }
        return ((BuzzConstants.BODY_WATER * standardDrinks * BuzzConstants.SWEDISH_NUM) / (BW * userWeight)) - (MR * hours)
    }

    fun accumulateStandardDrink(d: Drink): Float {
        return (d.ml * d.ABV) / 10F
    }

    /*Given a drink context (ML, ABV) suggest the period over which to drink it to reach 0.055 BAC*/
    fun suggestDrink(standardDrinks: Float,
                     weight: Float,
                     isFemale: Boolean,
                     isMetric: Boolean) : Float {

        var userWeight = weight
        var BW = BuzzConstants.MALE_BW
        var MR = BuzzConstants.MALE_MB
        if (isFemale) {
            BW = BuzzConstants.FEMALE_BW
            MR = BuzzConstants.FEMALE_MB
        }
        if (!isMetric) {
            userWeight *= BuzzConstants.LB_TO_KG
        }

        var output =
            (((BuzzConstants.BODY_WATER * standardDrinks * BuzzConstants.SWEDISH_NUM) / (BW * userWeight)) - 0.055F) / MR

        if (output <= 0) {
            return 0F
        } else
    return output
    }
    }
