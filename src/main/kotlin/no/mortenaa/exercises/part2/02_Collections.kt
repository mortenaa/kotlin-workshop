package no.mortenaa.exercises.part2

import no.mortenaa.data.AppInfo
import no.mortenaa.data.Category
import no.mortenaa.data.ContentRating
import no.mortenaa.data.Genre
//todo: Si veldig tydelig at de skal lage en klasse, hvor de skal lage en classe og at denskal implementere interfacet
/**
 *
 * These exercises use the data classes and enums defines in [AppInfo]
 *
 * The main data class keeps information on Android Play Store apps, and looks like this:
 *
 * data class AppInfo(
 *     val name: String,
 *     val reviews: Int,
 *     val price: Price = Price(0.0),
 *     val genres: Set<Genre> = emptySet(),
 *     val category: Category? = null,
 *     val rating: Rating? = null,
 *     val size: Size? = null,
 *     val installs: InstallBase? = null,
 *     val type: Type? = null,
 *     val contentRating: ContentRating? = null
 * )
 *
 * the data which is loaded from "src/main/resources/googleplaystore.csv" has some missing or bad values,
 * which is why many of the [AppInfo] properties are nullable.
 *
 */
interface AppStats {


    companion object {
        fun getAppStatsService(appInfoList: List<AppInfo>): AppStats {
            return AppStatsImpl(appInfoList)
        }
    }

    /**
     * The number of apps with a rating != null
     */
    fun ratedApps(): Int

    /**
     * Calculate the average rating for all apps in the list (excluding apps where rating == null)
     */
    fun averageRating(): Double

    /**
     * The average rating for all apps in a single category
     */
    fun averageRating(category: Category): Double

    /**
     * Return the most expensive app
     */
    fun mostExpensiveApp(): AppInfo

    /**
     * Return a list of categories, ordered by average rating for the category from highest to lowest
     */
    fun categoriesOrderedByRating(): List<Category>

    /**
     * Return a list of categories and number of apps in the category as a list of pairs, ordered from most to least
     */
    fun categoriesOrderedByNumberOfApps(): List<Pair<Category, Int>>

    /**
     * Return the average rating for a single Genre
     */
    fun averageRatingForGenre(genre: Genre): Double

    /**
     * Find the Genre with the highest average rating
     */
    fun highestRatedGenre(): Genre

    /**
     * Return your own implementation of the [CategoryStats] interface for the specified Category
     */
    fun categoryStats(category: Category): CategoryStats

    /**
     * Separate apps into groups according to ContentRating, and return as a Map from ContentRating => List of Apps
     */
    fun groupByContentRating(): Map<ContentRating, List<AppInfo>>

    /**
     * Return a Map from ContentRating => Average Rating
     */
    fun averageRatingByContentRating(): Map<ContentRating, Double>

    /**
     * Return a list with all apps that satisfy the given criteria. Order the list by number of reviews for each app
     */
    fun findApps(minRating: Double, maxPrice: Double, category: Category? = null, genre: Genre? = null): List<AppInfo>
}

interface CategoryStats {
    val name: String
    val size: Int
    val averageRating: Double
    val averagePrice: Double
}

