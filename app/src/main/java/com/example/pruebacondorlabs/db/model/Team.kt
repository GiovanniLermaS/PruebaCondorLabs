package com.example.pruebacondorlabs.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Team(
    @PrimaryKey @SerializedName("idTeam") var idTeam: Int,
    @SerializedName("strTeam") var strTeam: String? = null,
    @SerializedName("strStadium") var strStadium: String? = null,
    @SerializedName("strDescriptionEN") var strDescriptionEn: String? = null,
    @SerializedName("strDescriptionES") var strDescriptionEs: String? = null,
    @SerializedName("intFormedYear") var intFormedYear: Int? = null,
    @SerializedName("strTeamBadge") var strTeamBadge: String? = null,
    @SerializedName("strTeamJersey") var strTeamJersey: String? = null,
    @SerializedName("strWebsite") var strWebsite: String? = null,
    @SerializedName("strFacebook") var strFacebook: String? = null,
    @SerializedName("strTwitter") var strTwitter: String? = null,
    @SerializedName("strInstagram") var strInstagram: String? = null,
    @SerializedName("strYoutube") var strYoutube: String? = null,
) : Serializable