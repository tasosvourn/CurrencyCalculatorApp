package com.example.currencycalculatorapp.features

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.currencycalculatorapp.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CalculatorCurrencyConverterActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(CalculatorCurrencyConverterActivity::class.java)

    @Test
    fun calculatorCurrencyConverterScreenTest() {
        val buttonZero = onView(
            allOf(
                withId(R.id.button_0),
                withText("0"),
                isDisplayed()
            )
        )
        buttonZero.check(matches(isDisplayed()))

        val buttonOne = onView(
            allOf(
                withId(R.id.button_1),
                withText("1"),
                isDisplayed()
            )
        )
        buttonOne.check(matches(isDisplayed()))

        val buttonTwo = onView(
            allOf(
                withId(R.id.button_2),
                withText("2"),
                isDisplayed()
            )
        )
        buttonTwo.check(matches(isDisplayed()))

        val buttonThree = onView(
            allOf(
                withId(R.id.button_3),
                withText("3"),
                isDisplayed()
            )
        )
        buttonThree.check(matches(isDisplayed()))

        val buttonFour = onView(
            allOf(
                withId(R.id.button_4),
                withText("4"),
                isDisplayed()
            )
        )
        buttonFour.check(matches(isDisplayed()))

        val buttonFive = onView(
            allOf(
                withId(R.id.button_5),
                withText("5"),
                isDisplayed()
            )
        )
        buttonFive.check(matches(isDisplayed()))

        val buttonSix = onView(
            allOf(
                withId(R.id.button_6),
                withText("6"),
                isDisplayed()
            )
        )
        buttonSix.check(matches(isDisplayed()))

        val buttonSeven = onView(
            allOf(
                withId(R.id.button_7),
                withText("7"),
                isDisplayed()
            )
        )
        buttonSeven.check(matches(isDisplayed()))

        val buttonEight = onView(
            allOf(
                withId(R.id.button_8),
                withText("8"),
                isDisplayed()
            )
        )
        buttonEight.check(matches(isDisplayed()))

        val buttonNine = onView(
            allOf(
                withId(R.id.button_9),
                withText("9"),
                isDisplayed()
            )
        )
        buttonNine.check(matches(isDisplayed()))

        val buttonPoint = onView(
            allOf(
                withId(R.id.button_decimal_point),
                withText("."),
                isDisplayed()
            )
        )
        buttonPoint.check(matches(isDisplayed()))

        val buttonEquals = onView(
            allOf(
                withId(R.id.button_equals),
                withText("="),
                isDisplayed()
            )
        )
        buttonEquals.check(matches(isDisplayed()))

        val buttonPlus = onView(
            allOf(
                withId(R.id.button_plus),
                withText("+"),
                isDisplayed()
            )
        )
        buttonPlus.check(matches(isDisplayed()))

        val buttonMinus = onView(
            allOf(
                withId(R.id.button_minus),
                withText("-"),
                isDisplayed()
            )
        )
        buttonMinus.check(matches(isDisplayed()))

        val buttonTimes = onView(
            allOf(
                withId(R.id.button_times),
                withText("*"),
                isDisplayed()
            )
        )
        buttonTimes.check(matches(isDisplayed()))

        val buttonDivisor = onView(
            allOf(
                withId(R.id.button_divisor),
                withText("/"),
                isDisplayed()
            )
        )
        buttonDivisor.check(matches(isDisplayed()))

        val buttonDelete = onView(
            allOf(
                withId(R.id.button_delete),
                withText("C"),
                isDisplayed()
            )
        )
        buttonDelete.check(matches(isDisplayed()))

        val buttonAllClear = onView(
            allOf(
                withId(R.id.button_all_clear),
                withText("AC"),
                isDisplayed()
            )
        )
        buttonAllClear.check(matches(isDisplayed()))

        val textviewFrom = onView(
            allOf(
                withId(R.id.text_from),
                withText("From"),
                isDisplayed()
            )
        )
        textviewFrom.check(matches(withText("From")))

        val textviewTo = onView(
            allOf(
                withId(R.id.text_to),
                withText("To"),
                isDisplayed()
            )
        )
        textviewTo.check(matches(withText("To")))

        buttonOne.perform(click())
        buttonPlus.perform(click())
        buttonTwo.perform(click())

        var textviewExpression = onView(
            allOf(
                withId(R.id.expression),
                withText("1+2"),
                isDisplayed()
            )
        )
        textviewExpression.check(matches(withText("1+2")))

        buttonEquals.perform(click())

        var textviewResult = onView(
            allOf(
                withId(R.id.result),
                withText("3"),
                isDisplayed()
            )
        )
        textviewResult.check(matches(withText("3")))

        buttonDelete.perform(click())

        textviewExpression = onView(
            allOf(
                withId(R.id.expression),
                withText("1+"),
                isDisplayed()
            )
        )
        textviewExpression.check(matches(withText("1+")))

        textviewResult = onView(
            allOf(
                withId(R.id.result),
                withText(""),
                isDisplayed()
            )
        )
        textviewResult.check(matches(withText("")))

        buttonAllClear.perform(click())

        textviewExpression = onView(
            allOf(
                withId(R.id.expression),
                withText(""),
                isDisplayed()
            )
        )
        textviewExpression.check(matches(withText("")))
        textviewResult.check(matches(withText("")))
    }
}