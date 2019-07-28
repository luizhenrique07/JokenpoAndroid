package main.models

import android.util.Log
import androidx.lifecycle.MutableLiveData

class Game {
    val userOption = MutableLiveData<RockPaperScissors>()
    val computerOption = MutableLiveData<RockPaperScissors>()
    val result = MutableLiveData<Result>()
    var points: Int = 0

    init {
        userOption.value = RockPaperScissors.UNKNOWN
        computerOption.value = RockPaperScissors.UNKNOWN
    }

    fun start(userOption: RockPaperScissors) {
        this.userOption.value = userOption
        setComputerOption()
        setResult()
    }

    private fun setComputerOption() {
        val randomOption = (0..2).shuffled().first()
        computerOption.value = RockPaperScissors.values()[randomOption]
    }


    fun setResult() {
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


    fun isTie(): Boolean {
        return computerOption.value == userOption.value
    }
}