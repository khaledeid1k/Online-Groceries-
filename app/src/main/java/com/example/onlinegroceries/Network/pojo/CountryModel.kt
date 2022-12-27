package com.example.onlinegroceries.Network.pojo

data class CountryModel(
    val geonames: List<Geoname>,
)

data class Geoname(
    val continent: String,
    val capital: String,
    val languages: String,
    val geonameId: Long,
    val south: Double,
    val isoAlpha3: String,
    val north: Double,
    val fipsCode: String,
    val population: String,
    val east: Double,
    val isoNumeric: String,
    val areaInSqKm: String,
    val countryCode: String,
    val west: Double,
    val countryName: String,
    val postalCodeFormat: String,
    val continentName: String,
    val currencyCode: String,
)
