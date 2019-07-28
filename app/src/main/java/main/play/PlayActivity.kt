package main.play

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.luiz.jokenpo.R
import kotlinx.android.synthetic.main.activity_play.*
import main.gameOver.GameOverActivity
import main.models.Game
import main.models.Result
import main.models.RockPaperScissors

class PlayActivity : AppCompatActivity() {

    val game = Game()
    var userOption = RockPaperScissors.UNKNOWN
    var computerOption = RockPaperScissors.UNKNOWN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        game.userOption.observe(this, Observer {
            userOption = it
        })

        game.computerOption.observe(this, Observer {
            computerOption = it
        })

        game.result.observe(this, Observer {
            if (it != Result.UNKNOWN) {
                if (it == Result.DEFEAT) {
                    goToGameOverScreen()
                } else {
                    resultTv.text = getResultTextMessage(it)
                    setImageView(userOption, computerOption)
                }

            }
        })

        rockIv.setOnClickListener {
            game.start(RockPaperScissors.ROCK)
        }

        paperIv.setOnClickListener {
            game.start(RockPaperScissors.PAPER)
        }

        scissorIv.setOnClickListener {
            game.start(RockPaperScissors.SCISSORS)
        }
    }

    fun goToGameOverScreen() {
        startActivity(Intent(this, GameOverActivity::class.java))
        finish()
    }

    fun getResultTextMessage(result: Result): String {
        when (result) {
            Result.WIN -> return "Venceu!"
            Result.TIE -> return "Empate!"
            else -> return ""
        }
    }

    fun setImageView(user: RockPaperScissors, computer: RockPaperScissors) {
        if (user != RockPaperScissors.UNKNOWN && computer != RockPaperScissors.UNKNOWN) {
            playersChoiceIv.setImageResource(getImageResource(user))
            computersChoiceIv.setImageResource(getImageResource(computer))
        }
    }

    fun getImageResource(option: RockPaperScissors): Int {
        when (option) {
            RockPaperScissors.SCISSORS -> return R.drawable.pixel_scissors
            RockPaperScissors.ROCK -> return R.drawable.pixel_rock
            RockPaperScissors.PAPER -> return R.drawable.pixel_paper
        }
        return R.drawable.logo
    }
}
