package com.example.moviecatalog.utils

object MappingHelper {

    fun stringToList(string: String): List<String> {
        return string.split(",").map { it.trim() }
    }
}