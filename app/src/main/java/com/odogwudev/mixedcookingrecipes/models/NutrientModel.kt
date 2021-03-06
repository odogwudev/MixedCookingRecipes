package com.odogwudev.mixedcookingrecipes.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NutrientModel(

    val label: String,
    val quantity: Double,
    val unit: String
) : LocalModel, Parcelable {
    constructor() : this(
        "", 0.0, ""
    )
}