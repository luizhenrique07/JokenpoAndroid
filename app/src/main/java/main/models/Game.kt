package main.models

import androidx.lifecycle.MutableLiveData
import com.luiz.jokenpo.R

class Game {
    val userOption = MutableLiveData<RockPaperScissors>()
    val computerOption = MutableLiveData<RockPaperScissors>()
    val result = MutableLiveData<Result>()
    var points: Int = 0

    init {
        userOption.value = RockPaperScissors.UNKNOWN
        computerOption.value = RockPaperScissors.UNKNOWN
    }

    fun makeMove(userOption: RockPaperScissors) {
        this.userOption.value = userOption
        setComputerOption()
        setResult()
        calculatePoints()
    }

    private fun setComputerOption() {
        val randomOption = (0..2).shuffled().first()
        computerOption.value = RockPaperScissors.values()[randomOption]
    }


    private fun setResult() {
        if (isTie()) {
            result.value = Result.TIE
            return
        }

        when (userOption.value) {
            RockPaperScissors.ROCK ->
                if (computerOption.value == RockPaperScissors.PAPER)
                    result.value = Result.DEFEAT
                else
                    result.value = Result.WIN
            RockPaperScissors.PAPER ->
                if (computerOption.value == RockPaperScissors.SCISSORS)
                    result.value = Result.DEFEAT
                else
                    result.value = Result.WIN
            RockPaperScissors.SCISSORS ->
                if (computerOption.value == RockPaperScissors.ROCK)
                    result.value = Result.DEFEAT
                else
                    result.value = Result.WIN
        }
    }

    private fun calculatePoints() {
        when (result.value) {
            Result.WIN -> points += Result.WIN.point
            Result.TIE -> points += Result.TIE.point
        }
    }

    private fun isTie(): Boolean {
        return computerOption.value == userOption.value
    }

    fun getRockPaperScissorsResource(option: RockPaperScissors): Int {
        when (option) {
            RockPaperScissors.SCISSORS -> return R.drawable.pixel_scissors
            RockPaperScissors.ROCK -> return R.drawable.pixel_rock
            RockPaperScissors.PAPER -> return R.drawable.pixel_paper
        }
        return R.drawable.logo
    }
}