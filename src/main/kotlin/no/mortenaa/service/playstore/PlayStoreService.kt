package no.mortenaa.service.playstore

import com.opencsv.CSVReaderHeaderAware
import no.mortenaa.data.*
import java.io.FileReader
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import kotlin.math.round

private fun parseCategory(s: String): Category? =
    try { Category.valueOf(s.trim()) } catch (e: IllegalArgumentException) { null }

private fun parseContentRating(s: String): ContentRating? =
    ContentRating.values().find { it.description == s }

private fun parseRating(s: String): Rating? =
    s.toDoubleOrNull()?.let { if (it in 0.0..5.0) Rating(it) else null }

private fun parseSize(s: String): Size? =
    when (s.last().toUpperCase()) {
        'K' -> Size((s.take(s.length - 1).toDouble() * 1000).toLong())
        'M' -> Size((s.take(s.length - 1).toDouble() * 1000000).toLong())
        else -> try {
            Size(s.toLong())
        } catch (_: NumberFormatException) { null }
    }

private fun parseInstalls(s: String): InstallBase? =
    s.replace(",", "").replace("+","").toLongOrNull()?.let { InstallBase(it, s) }

private fun parseType(s: String): Type? =
    when (s) {
        "Paid" -> Type.PAID
        "Free" -> Type.FREE
        else -> null
    }

private fun parsePrice(s: String): Price =
    try {
        s.replace("$","").toDouble().let { Price(it) }
    } catch (_: NumberFormatException) {
        Price(0.0)
    }

private fun parseGenres(s: String): Set<Genre> =
    s.split(";").mapNotNull { g -> Genre.values().find { it.description == g } }.toSet()

fun appInfoFromArray(values: Array<String>): AppInfo {
    return AppInfo(
        name = values[0],
        category = parseCategory(values[1]),
        rating = parseRating(values[2]),
        reviews = parseReviews(values[3]),
        size = parseSize(values[4]),
        installs = parseInstalls(values[5]),
        type = parseType(values[6]),
        price = parsePrice(values[7]),
        contentRating = parseContentRating(values[8]),
        genres = parseGenres(values[9])
    )
}

fun parseReviews(s: String): Int =
    when (s.last()) {
        'M' -> round(s.take(s.length-1).toDouble() * 1000000).toInt()
        'K' -> round(s.take(s.length-1).toDouble() * 1000).toInt()
        else -> s.toInt()
    }


fun parseCsv(filename: String): List<AppInfo> {
    val all: List<AppInfo> = CSVReaderHeaderAware(FileReader(filename)).map {
        val app = try {
            appInfoFromArray(it)
        } catch (exception: Exception) {
            println("Error: $it\n${it.toList()}")
            System.exit(1)
        } as AppInfo
        app
    }.toList()
    return all
}

fun main() {
    parseCsv("src/main/resources/googleplaystore.csv")
}

