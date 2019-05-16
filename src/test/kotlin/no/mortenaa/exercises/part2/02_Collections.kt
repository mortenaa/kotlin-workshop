package no.mortenaa.exercises.part2

import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.matchers.doubles.shouldNotBeExactly
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec
import no.mortenaa.data.AppInfo
import no.mortenaa.data.Category
import no.mortenaa.data.Genre
import no.mortenaa.service.playstore.parseCsv

class FunctionsTest : StringSpec({

    val appInfoList: List<AppInfo> = parseCsv("src/main/resources/googleplaystore.csv")
    val appStats: AppStats = AppStats.getAppStatsService(appInfoList)

    "average rating" {
        appStats.averageRating() shouldBe(4.19).plusOrMinus(0.01)
    }

    "number of rated apps" {
        appStats.ratedApps() shouldBe 9366
    }

    "average rating in art and design" {
        appStats.averageRating(Category.ART_AND_DESIGN) shouldBe(4.35).plusOrMinus(0.01)
    }

    "The most expensive app in store" {
        appStats.mostExpensiveApp().name shouldBe "I'm Rich - Trump Edition"
    }

    "Categories sorted by average price in the category" {
        val categoriesByRating = appStats.categoriesOrderedByRating()
        categoriesByRating.first() shouldBe Category.EVENTS
        categoriesByRating.last() shouldBe Category.DATING
    }

    "Categories sorted by number of apps in the category" {
        val categoriesByNumberOfApps = appStats.categoriesOrderedByNumberOfApps()
        categoriesByNumberOfApps.first() shouldBe Pair(Category.FAMILY, 1972)
        categoriesByNumberOfApps.last() shouldBe Pair(Category.BEAUTY, 53)
    }

    "Average rating for TRIVIA" {
        appStats.averageRatingForGenre(Genre.TRIVIA) shouldBe (4.039).plusOrMinus(0.01)
    }

    "Highest rated genre" {
        appStats.highestRatedGenre() shouldBe Genre.EVENTS
    }

    "Implement CategoryStats" {
       val categoryStat = appStats.categoryStats(Category.EVENTS)
        categoryStat.name shouldBe Category.EVENTS.name
        categoryStat.averagePrice shouldBe(1.718).plusOrMinus(0.01)
        categoryStat.size shouldBe 64
        categoryStat.averageRating shouldBe(4.435).plusOrMinus(0.01)
    }
})