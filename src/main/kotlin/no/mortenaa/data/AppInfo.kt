package no.mortenaa.data

data class AppInfo(
    val name: String,
    val reviews: Int,
    val price: Price = Price(0.0),
    val genres: Set<Genre> = emptySet(),
    val category: Category? = null,
    val rating: Rating? = null,
    val size: Size? = null,
    val installs: InstallBase? = null,
    val type: Type? = null,
    val contentRating: ContentRating? = null
)

data class Rating(val stars: Double)

data class Size(val bytes: Long)

data class InstallBase(val moreThan: Long, val description: String)

data class Price(val dollars: Double)

enum class ContentRating(val description: String) {
    UNRATED("Unrated"),
    ADULTS_18("Adults only 18+"),
    EVERYONE_10("Everyone 10+"),
    MATURE_17("Mature 17+"),
    TEEN("Teen"),
    EVERYONE("Everyone")
}

enum class Genre(val description: String) {
    MUSIC_AND_AUDIO("Music & Audio"),
    MUSIC("Music"),
    WORD("Word"),
    CREATIVITY("Creativity"),
    TRIVIA("Trivia"),
    CASINO("Casino"),
    MUSIC_AND_VIDEO("Music & Video"),
    CARD("Card"),
    BEAUTY("Beauty"),
    COMICS("Comics"),
    PARENTING("Parenting"),
    BOARD("Board"),
    EVENTS("Events"),
    ART_AND_DESIGN("Art & Design"),
    BRAIN_GAMES("Brain Games"),
    WEATHER("Weather"),
    AUTO_AND_VEHICLES("Auto & Vehicles"),
    LIBRARIES_AND_DEMO("Libraries & Demo"),
    HOUSE_AND_HOME("House & Home"),
    PRETEND_PLAY("Pretend Play"),
    ADVENTURE("Adventure"),
    STRATEGY("Strategy"),
    EDUCATIONAL("Educational"),
    RACING("Racing"),
    ROLE_PLAYING("Role Playing"),
    FOOD_AND_DRINK("Food & Drink"),
    MAPS_AND_NAVIGATION("Maps & Navigation"),
    ACTION_AND_ADVENTURE("Action & Adventure"),
    PUZZLE("Puzzle"),
    VIDEO_PLAYERS_AND_EDITORS("Video Players & Editors"),
    SIMULATION("Simulation"),
    BOOKS_AND_REFERENCE("Books & Reference"),
    DATING("Dating"),
    ARCADE("Arcade"),
    TRAVEL_AND_LOCAL("Travel & Local"),
    SHOPPING("Shopping"),
    CASUAL("Casual"),
    NEWS_AND_MAGAZINES("News & Magazines"),
    SOCIAL("Social"),
    PHOTOGRAPHY("Photography"),
    HEALTH_AND_FITNESS("Health & Fitness"),
    FINANCE("Finance"),
    ACTION("Action"),
    LIFESTYLE("Lifestyle"),
    COMMUNICATION("Communication"),
    PERSONALIZATION("Personalization"),
    SPORTS("Sports"),
    PRODUCTIVITY("Productivity"),
    BUSINESS("Business"),
    MEDICAL("Medical"),
    ENTERTAINMENT("Entertainment"),
    EDUCATION("Education"),
    TOOLS("Tools")
}

enum class Type { FREE, PAID }

enum class Category {
    BEAUTY,
    COMICS,
    PARENTING,
    EVENTS,
    ART_AND_DESIGN,
    WEATHER,
    AUTO_AND_VEHICLES,
    LIBRARIES_AND_DEMO,
    HOUSE_AND_HOME,
    FOOD_AND_DRINK,
    MAPS_AND_NAVIGATION,
    ENTERTAINMENT,
    EDUCATION,
    VIDEO_PLAYERS,
    BOOKS_AND_REFERENCE,
    DATING,
    TRAVEL_AND_LOCAL,
    SHOPPING,
    NEWS_AND_MAGAZINES,
    SOCIAL,
    PHOTOGRAPHY,
    HEALTH_AND_FITNESS,
    FINANCE,
    LIFESTYLE,
    SPORTS,
    COMMUNICATION,
    PERSONALIZATION,
    PRODUCTIVITY,
    BUSINESS,
    MEDICAL,
    TOOLS,
    GAME,
    FAMILY,
}