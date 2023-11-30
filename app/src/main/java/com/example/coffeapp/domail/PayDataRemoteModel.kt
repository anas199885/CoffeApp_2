package com.example.coffeapp.domail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PayDataRemoteModel(

    val fullName: String,

    val phoneNumber: String,

    val pickupTime: String,

    val cardType: String,

    val cardNumber: String,

    val cardExpiryMonth: String,

    val cardExpiryYear: String,

    val cvvNumber: String,

    val orderInfo: OrderDataRemoteModel,
): Parcelable
