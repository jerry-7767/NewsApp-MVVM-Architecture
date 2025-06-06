package com.rishi.newsapp.utils

import com.rishi.newsapp.data.model.Country
import com.rishi.newsapp.data.model.Language

object Constants {
    const val API_KEY = "c5a9a8f090c64af09ccb4397ffc9390f"
//    const val API_KEY = "9f6482a584804376874b848980b7a044"
    var COUNTRY = "us"

    val COUNTRY_LIST: List<Country> = listOf(
        Country("Select Country", "na"),
        Country("United Arab Emirates", "ae"),
        Country("Argentina", "ar"),
        Country("Austria", "at"),
        Country("Australia", "au"),
        Country("Belgium", "be"),
        Country("Bulgaria", "bg"),
        Country("Brazil", "br"),
        Country("Canada", "ca"),
        Country("Switzerland", "ch"),
        Country("China", "cn"),
        Country("Colombia", "co"),
        Country("Cuba", "cu"),
        Country("Czech Republic", "cz"),
        Country("Germany", "de"),
        Country("Egypt", "eg"),
        Country("France", "fr"),
        Country("United Kingdom", "gb"),
        Country("Greece", "gr"),
        Country("Hong Kong", "hk"),
        Country("Hungary", "hu"),
        Country("Indonesia", "id"),
        Country("Ireland", "ie"),
        Country("Israel", "il"),
        Country("India", "in"),
        Country("Italy", "it"),
        Country("Japan", "jp"),
        Country("South Korea", "kr"),
        Country("Lithuania", "lt"),
        Country("Latvia", "lv"),
        Country("Morocco", "ma"),
        Country("Mexico", "mx"),
        Country("Malaysia", "my"),
        Country("Nigeria", "ng"),
        Country("Netherlands", "nl"),
        Country("Norway", "no"),
        Country("New Zealand", "nz"),
        Country("Philippines", "ph"),
        Country("Poland", "pl"),
        Country("Portugal", "pt"),
        Country("Romania", "ro"),
        Country("Serbia", "rs"),
        Country("Russia", "ru"),
        Country("Saudi Arabia", "sa"),
        Country("Sweden", "se"),
        Country("Singapore", "sg"),
        Country("Slovakia", "sk"),
        Country("Thailand", "th"),
        Country("Turkey", "tr"),
        Country("Taiwan", "tw"),
        Country("Ukraine", "ua"),
        Country("United States", "us"),
        Country("Venezuela", "ve"),
        Country("South Africa", "za")
    )

    val LANGUAGE_LIST : List<Language> = listOf(
        Language("Arabic", "ar"),
        Language("German", "de"),
        Language("English", "en"),
        Language("Spanish", "es"),
        Language("French", "fr"),
        Language("Hebrew", "he"),
        Language("Italian", "it"),
        Language("Dutch", "nl"),
        Language("Norwegian", "no"),
        Language("Portuguese", "pt"),
        Language("Russian", "ru"),
        Language("Swedish", "sv"),
        Language("Chinese", "zh")

    )
}