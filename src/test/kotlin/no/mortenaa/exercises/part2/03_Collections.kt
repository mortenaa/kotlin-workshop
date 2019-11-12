package no.mortenaa.exercises.part2

import io.kotlintest.fail
import io.kotlintest.matchers.beInstanceOf
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.matchers.doubles.shouldNotBeExactly
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec
import no.mortenaa.data.AppInfo
import no.mortenaa.data.Category
import no.mortenaa.data.ContentRating
import no.mortenaa.data.Genre
import no.mortenaa.service.playstore.parseCsv
import java.lang.Exception

class CollectionsTest : StringSpec({

    val appInfoList: List<AppInfo> = parseCsv("src/main/resources/googleplaystore.csv")

    "1. Return an implementation of AppStats" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        appStats should beInstanceOf<AppStats>()
    }

    "2. number of rated apps" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        appStats.ratedApps() shouldBe 9366
    }

    "3. average rating" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        appStats.averageRating() shouldBe(4.19).plusOrMinus(0.01)
    }


    "4. average rating in art and design" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        appStats.averageRating(Category.ART_AND_DESIGN) shouldBe(4.35).plusOrMinus(0.01)
    }

    "5. The most expensive app in store" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        appStats.mostExpensiveApp().name shouldBe "I'm Rich - Trump Edition"
    }

    "6. total number of reviews" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        appStats.totalReviews() shouldBe 4817617393L
    }

    "7. Categories sorted by average rating in the category" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        val categoriesByRating = appStats.categoriesOrderedByRating()
        categoriesByRating.first() shouldBe Category.EVENTS
        categoriesByRating.last() shouldBe Category.DATING
    }

    "8. Categories sorted by number of apps in the category" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        val categoriesByNumberOfApps = appStats.categoriesOrderedByNumberOfApps()
        categoriesByNumberOfApps.first() shouldBe Pair(Category.FAMILY, 1972)
        categoriesByNumberOfApps.last() shouldBe Pair(Category.BEAUTY, 53)
    }

    "9. Average rating for TRIVIA" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        appStats.averageRatingForGenre(Genre.TRIVIA) shouldBe (4.039).plusOrMinus(0.01)
    }

    "10. Highest rated genre" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        appStats.highestRatedGenre() shouldBe Genre.EVENTS
    }

    "11. Implement CategoryStats" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        val categoryStat = appStats.categoryStats(Category.EVENTS)
        categoryStat.name shouldBe Category.EVENTS.name
        categoryStat.averagePrice shouldBe(1.718).plusOrMinus(0.01)
        categoryStat.size shouldBe 64
        categoryStat.averageRating shouldBe(4.435).plusOrMinus(0.01)
    }

    "12. ContentRating map" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        val groups = appStats.groupByContentRating()
        groups.size shouldBe 6
        groups[ContentRating.EVERYONE]?.size shouldBe 8714
        val appInfo = appInfoList.find { it.contentRating == ContentRating.TEEN }
        groups[ContentRating.TEEN]!! shouldContain appInfo
    }

    "13. ContentRating Average" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        val contentRatingMap = appStats.averageRatingByContentRating()
        contentRatingMap.size shouldBe 6
        contentRatingMap[ContentRating.ADULTS_18] shouldBe(4.3).plusOrMinus(0.01)
        contentRatingMap[ContentRating.UNRATED] shouldBe(4.1).plusOrMinus(0.01)
    }

    "14. Top rated apps" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        val topRated = appStats.topRated(10)
        topRated[0] shouldBe Triple("Ríos de Fe", 5.0, 141)
        topRated[4] shouldBe Triple("Master E.K", 5.0, 90)
        topRated[9] shouldBe Triple("Ek Vote", 5.0, 43)
    }

    "15. Find apps" {
        val appStats: AppStats = AppStats.getAppStatsService(appInfoList)
        appStats.findApps(minRating = 4.0, maxPrice = 10.0)
            .first().name shouldBe "Facebook"
        appStats.findApps(minRating = 4.5, maxPrice = 10.0, category = Category.BEAUTY)
            .first().name shouldBe "ipsy: Makeup, Beauty, and Tips"
        appStats.findApps(minRating = 3.5, maxPrice = 1.0, genre = Genre.BRAIN_GAMES)
            .first().name shouldBe "Where's My Water? Free"
    }
})