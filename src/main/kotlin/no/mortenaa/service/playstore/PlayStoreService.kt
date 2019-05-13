package no.mortenaa.service.playstore

import com.opencsv.CSVReaderHeaderAware
import no.mortenaa.data.*
import java.io.FileReader
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import kotlin.math.round


/*

App,Category,Rating,Reviews,Size,Installs,Type,Price,Content Rating,Genres,Last Updated,Current Ver,Android Ver

Photo Editor & Candy Camera & Grid & ScrapBook,ART_AND_DESIGN,4.1,159,19M,"10,000+",Free,0,Everyone,Art & Design,"January 7, 2018",1.0.0,4.0.3 and up
Coloring book moana,ART_AND_DESIGN,3.9,967,14M,"500,000+",Free,0,Everyone,Art & Design;Pretend Play,"January 15, 2018",2.0.0,4.0.3 and up
"U Launcher Lite – FREE Live Cool Themes, Hide Apps",ART_AND_DESIGN,4.7,87510,8.7M,"5,000,000+",Free,0,Everyone,Art & Design,"August 1, 2018",1.2.4,4.0.3 and up

Issues:
Bad category = 1.9
Duplicate App names
Bad rating = 19, NaN
Size = double + m / k
Installs = 0, Free, 0+, 1+, etc
Type = 0, NaN, Paid, Free
Price = 0, $Double
Genre = G1;G2;... bad=February 11, 2018
LAst Updated = August 26, 2014
Version = String
AndroidVersion = String

*/

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
    val hist = Array<MutableMap<String, Int>>(13) { mutableMapOf() }
    val all: List<AppInfo> = CSVReaderHeaderAware(FileReader(filename)).map {
        val app = try {
            appInfoFromArray(it)
        } catch (exception: Exception) {
            println("Error: $it\n${it.toList()}")
            System.exit(1)
        } as AppInfo
        //println(app)
        //it.forEachIndexed { i, s -> uppdateHist(hist[i], s) }
        app
    }.toList()
    //println(all.size)
    //hist[12].toList().sortedBy { it.second }.forEach { println(it.first) }
    //all.forEach { println(it) }
    return all
}

fun uppdateHist(hist: MutableMap<String, Int>, s: String) {
    if (s.contains(";")) {
        s.split(";").forEach {
            hist.compute(it) { _, v -> if (v == null) 1 else v + 1 }
        }
    } else {
        hist.compute(s) { _, v -> if (v == null) 1 else v + 1 }
    }
}

fun main() {
    parseCsv("src/main/resources/googleplaystore.csv")
}

