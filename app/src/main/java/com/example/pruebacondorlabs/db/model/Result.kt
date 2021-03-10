package com.example.pruebacondorlabs.db.model

import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("strEvent")
    var strEvent: String? = null

    @SerializedName("strLeague")
    var strLeague: String? = null

    @SerializedName("strVenue")
    var strVenue: String? = null

    @SerializedName("strTimestamp")
    var strTimestamp: String? = null

    @SerializedName("strThumb")
    var strThumb: String? = null
}