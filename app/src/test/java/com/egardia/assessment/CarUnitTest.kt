package com.egardia.assessment

import com.egardia.assessment.model.Car
import org.junit.Assert
import org.junit.Test

class CarUnitTest {
    @Test
    fun priceFormattingIsCorrect() {
        var car = Car(
            make = "",
            model = "",
            price = "7600.00",
            year = "",
            km = "",
            picture = ""
        )

        Assert.assertEquals(car.formattedPrice, "€\u00A07.600,00")

        car = Car(
            make = "",
            model = "",
            price = "7600.01",
            year = "",
            km = "",
            picture = ""
        )
        Assert.assertEquals(car.formattedPrice, "€\u00A07.600,01")

        car = Car(
            make = "",
            model = "",
            price = "1000000",
            year = "",
            km = "",
            picture = ""
        )
        Assert.assertEquals(car.formattedPrice, "€\u00A01.000.000,00")

        car = Car(
            make = "",
            model = "",
            price = "1.50",
            year = "",
            km = "",
            picture = ""
        )
        Assert.assertEquals(car.formattedPrice, "€\u00A01,50")
    }

    @Test
    fun kmFormattingIsCorrect() {
        var car = Car(
            make = "",
            model = "",
            price = "",
            year = "",
            km = "245000",
            picture = ""
        )

        Assert.assertEquals(car.formattedKm, "245.000 km")

        car = Car(
            make = "",
            model = "",
            price = "",
            year = "",
            km = "1",
            picture = ""
        )

        Assert.assertEquals(car.formattedKm, "1 km")

        car = Car(
            make = "",
            model = "",
            price = "",
            year = "",
            km = "1000000000",
            picture = ""
        )

        Assert.assertEquals(car.formattedKm, "1.000.000.000 km")
    }
}