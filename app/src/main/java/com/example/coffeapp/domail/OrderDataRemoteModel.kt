package com.example.coffeapp.domail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderDataRemoteModel(
    val drinkName: String,

    val drinkSize: String?,

    val drinkOptions: List<String>,
): Parcelable
