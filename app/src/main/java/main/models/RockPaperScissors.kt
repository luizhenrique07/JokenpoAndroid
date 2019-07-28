package main.models


enum class RockPaperScissors(val id: Int) {
    ROCK(0),
    PAPER(1),
    SCISSORS(2),
    UNKNOWN(-1);

    companion object {
        operator fun invoke(id: Int) = RockPaperScissors.values().find { it.id == id } ?: UNKNOWN
    }
}