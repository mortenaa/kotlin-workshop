package no.mortenaa.exercises.part2

import no.mortenaa.data.AppInfo
import no.mortenaa.data.Category

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
            return AppStatsService(appInfoList)
            //TODO("Return your implementation of the AppStats interface here")
        }
    }

    /**
     * Calculate the average rating for all apps in the list (excluding apps where rating == null)
     */
    fun averageRating(): Double

    fun averageRating(category: Category): Double

    fun categoriesOrderedByRating(): List<Category>

    fun categoriesOrderedByNumberOfApps(): List<Pair<Category, Int>>

    fun categoryStats(category: Category): CategoryStats


}

interface CategoryStats {

    val name: String

    val size: Int

    val averageRating: Double

    val averagePrice: Double

}

class AppStatsService(val apps: List<AppInfo>) : AppStats {
    override fun categoryStats(category: Category): CategoryStats {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun averageRating(category: Category): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun categoriesOrderedByRating(): List<Category> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun categoriesOrderedByNumberOfApps(): List<Pair<Category, Int>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun averageRating(): Double {
        // TODO()
        return apps.mapNotNull { it.rating }.sumByDouble { it.stars } / apps.count { it.rating != null }
    }

}