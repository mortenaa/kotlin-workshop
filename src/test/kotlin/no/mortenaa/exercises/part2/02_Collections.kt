package no.mortenaa.exercises.part2

import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.matchers.doubles.shouldNotBeExactly
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import no.mortenaa.data.AppInfo
import no.mortenaa.service.playstore.parseCsv

class CollectionsTest : StringSpec({

    val appInfoList: List<AppInfo> = parseCsv("src/main/resources/googleplaystore.csv")
    val appStats: AppStats = AppStats.getAppStatsService(appInfoList)

    "average rating" {
        appStats.averageRating() shouldBe(4.19).plusOrMinus(0.01)
    }

})