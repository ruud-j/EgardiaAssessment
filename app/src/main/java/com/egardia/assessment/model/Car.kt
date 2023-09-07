package com.egardia.assessment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.NumberFormat
import java.util.Locale

@Parcelize
data class Car(
    val make: String,
    val model: String,
    val price: String,
    val year: String,
    val km: String,
    val picture: String,
) : Parcelable {
    val formattedPrice: String
        get() = NumberFormat.getCurrencyInstance(Locale("nl", "NL")).format(price.toDouble())

    val formattedKm: String
        get() = NumberFormat.getInstance(Locale("nl", "NL")).format(Integer.parseInt(km)) + " km"
}