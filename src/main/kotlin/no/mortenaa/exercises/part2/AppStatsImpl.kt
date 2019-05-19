package no.mortenaa.exercises.part2

import no.mortenaa.data.AppInfo
import no.mortenaa.data.Category
import no.mortenaa.data.ContentRating
import no.mortenaa.data.Genre
import java.lang.RuntimeException

class AppStatsImpl(val appInfoList: List<AppInfo>) : AppStats {
    override fun totalReviews(): Long {
        return appInfoList.map { it.reviews.toLong() }.sum()
    }

    override fun ratedApps(): Int {
        return appInfoList.count { it.rating != null }
    }

    override fun averageRating(): Double {
        return appInfoList
            .map { it.rating }
            .filterNotNull()
            .map { it.stars }
            .average()
    }

    override fun averageRating(category: Category): Double {
        return appInfoList
            .filter { category == it.category }
            .map { it.rating }
            .filterNotNull()
            .map { it.stars }
            .average()
    }

    override fun mostExpensiveApp(): AppInfo {
        return appInfoList.maxBy { it.price.dollars } ?: throw RuntimeException("No apps in store")
    }

    //todo: må vi force !! her?
    override fun categoriesOrderedByRating(): List<Category> {
        return appInfoList.filter { it.category != null }
            .groupBy { it.category }
            .map { it.key to averageRating(it.key!!)}
            .sortedByDescending { it.second }
            .map { it.first!! }
    }

    override fun categoriesOrderedByNumberOfApps(): List<Pair<Category, Int>> = appInfoList
        .filter { it.category != null }
        .groupBy { it.category }
        .map { it.key!! to it.value.size }
        .sortedByDescending { it.second }

    override fun averageRatingForGenre(genre: Genre) = appInfoList
        .filter { it.genres.contains(genre) }
        .filter { it.rating != null }
        .map { it.rating!!.stars }
        .average()

    override fun highestRatedGenre(): Genre {
        val genreAndRating: List<Pair<Genre, Double>> = Genre.values().fold(emptyList()){ list, genre ->
            list + Pair(genre, averageRatingForGenre(genre))
        }
        return genreAndRating.sortedByDescending { it.second }
            .first().first
    }

    override fun categoryStats(category: Category): CategoryStats {
        fun numberOfAppsInCategory() = appInfoList
            .filter { category == it.category }
            .count()
        fun averagePriceInCategory() = appInfoList
            .filter { category == it.category }
            .map { it.price.dollars }
            .average()

        return CategoryStatsImpl(category.name, numberOfAppsInCategory(), averageRating(category), averagePriceInCategory())
    }

    override fun groupByContentRating(): Map<ContentRating, List<AppInfo>> {
        return appInfoList
            .filter { it.contentRating != null }
            .groupBy { it.contentRating!! }
    }

    override fun averageRatingByContentRating(): Map<ContentRating, Double> {
        fun averageRating(apps: List<AppInfo>): Double {
            return apps.filter { it.rating != null }
                .map { it.rating!!.stars }
                .average()
        }

        return groupByContentRating()
            .mapValues { averageRating(it.value) }
    }

    override fun topRated(n: Int): List<Triple<String, Double, Int>> {
        return appInfoList
            .filter { it.rating != null }
            .sortedWith(compareByDescending<AppInfo> { it.rating!!.stars }
                .then(compareByDescending { it.reviews }))
            .map { Triple(it.name, it.rating!!.stars, it.reviews) }
            .take(n)

    }

    override fun findApps(minRating: Double, maxPrice: Double, category: Category?, genre: Genre?): List<AppInfo> {
        return appInfoList
            .filter { it.rating?.stars ?: Double.MIN_VALUE >= minRating }
            .filter { it.price.dollars <= maxPrice }
            .filter { category == null || (category == it.category) }
            .filter { genre == null || it.genres.contains(genre) }
            .sortedByDescending { it.reviews }
    }
}

data class CategoryStatsImpl(
    override val name: String,
    override val size: Int,
    override val averageRating: Double,
    override val averagePrice: Double
) : CategoryStats