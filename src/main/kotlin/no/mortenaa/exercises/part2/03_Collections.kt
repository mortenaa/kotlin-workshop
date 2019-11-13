package no.mortenaa.exercises.part2

import no.mortenaa.data.AppInfo
import no.mortenaa.data.Category
import no.mortenaa.data.ContentRating
import no.mortenaa.data.Genre

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


    /**
     * 1. Make a class
     *
     * Make a class that implements the [AppStats] interface, and return an
     * instance of your class here.
     *
     * (you need to keep a reference to the given 'appInfoList' in your Class,
     * as the rest of the exercises will work on the data in that List)
     */
    companion object {
        fun getAppStatsService(appInfoList: List<AppInfo>): AppStats {
            TODO("Return your implementation of the AppStats interface here")
        }
    }

    /**
     * 2. Number of apps with Rating
     *
     * Return the number of apps with a rating != null
     */
    fun ratedApps(): Int

    /**
     * 3. Average Rating
     *
     * Calculate the average rating for all apps in the list (excluding apps where rating == null)
     */
    fun averageRating(): Double

    /**
     * 4. Average Rating for Category
     *
     * The average rating for all apps in a single category
     * (excluding apps without rating)
     */
    fun averageRating(category: Category): Double

    /**
     * 5. Most Expensive
     *
     * Return the most expensive app
     */
    fun mostExpensiveApp(): AppInfo

    /**
     * 6. Total reviews
     *
     * Return the total number of reviews for all apps
     */
    fun totalReviews(): Long
    
    /**
     * 7. Categories ordered by rating
     *
     * Return a list of categories, ordered by average rating for the category from highest to lowest
     * (excluding apps without rating when calculating average rating)
     */
    fun categoriesOrderedByRating(): List<Category>

    /**
     * 8. Categories ordered by apps
     *
     * Return a list of categories and number of apps in the category as a list of pairs, ordered from most to least
     */
    fun categoriesOrderedByNumberOfApps(): List<Pair<Category, Int>>

    /**
     * 9. Average rating for Genre
     *
     * Return the average rating for a single Genre
     */
    fun averageRatingForGenre(genre: Genre): Double

    /**
     * 10. Highest rated Genre
     *
     * Find the Genre with the highest average rating
     */
    fun highestRatedGenre(): Genre

    /**
     * 11. CategoryStats interface
     *
     * Return your own implementation of the [CategoryStats] interface for the specified Category
     * (See at the bottom of this file for the interface)
     */
    fun categoryStats(category: Category): CategoryStats

    /**
     * 12. ContentRating map
     *
     * Separate apps into groups according to ContentRating, and return as a Map from ContentRating => List of Apps
     */
    fun groupByContentRating(): Map<ContentRating, List<AppInfo>>

    /**
     * 13. Return a Map from ContentRating => Average Rating
     */
    fun averageRatingByContentRating(): Map<ContentRating, Double>

    /**
     * 14. Top rated list
     *
     * Return a list of the Top [n] highest rated apps. If some apps have the same rating, order them by number of reviews
     * The return value is a [List] of [Triple]'s with app name, rating and number of reviews
     */
    fun topRated(n: Int): List<Triple<String, Double, Int>>

    /**
     * 15. Find Apps by criteria
     *
     * Return a list with all apps that satisfy the given criteria. Order the list by number of reviews for each app
     * (with regards to minRating a missing rating should be considered as 0 stars)
     */
    fun findApps(minRating: Double, maxPrice: Double, category: Category? = null, genre: Genre? = null): List<AppInfo>
}

interface CategoryStats {
    val name: String
    val size: Int
    val averageRating: Double
    val averagePrice: Double
}


