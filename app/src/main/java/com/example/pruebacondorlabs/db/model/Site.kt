package com.example.pruebacondorlabs.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Site(
    @PrimaryKey @ColumnInfo(name = "default_currency_id") var defaultCurrencyId: String,
    @ColumnInfo(name = "id") var id: String? = null,
    @ColumnInfo(name = "name") var name: String? = null,
)