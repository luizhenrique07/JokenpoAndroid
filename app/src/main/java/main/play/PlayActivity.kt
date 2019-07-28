package main.play

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            game.makeMove(RockPaperScissors.ROCK)
        }

        paperIv.setOnClickListener {
            game.makeMove(RockPaperScissors.PAPER)
        }

        scissorIv.setOnClickListener {
            game.makeMove(RockPaperScissors.SCISSORS)
        }
    }

    private fun goToGameOverScreen() {
        var intent = Intent(this, GameOverActivity::class.java)
        sendDataToScreen(intent)
        startActivity(intent)
        finish()
    }

    private fun sendDataToScreen(intent: Intent) {
        intent.putExtra("gamePoints", game.points)
        intent.putExtra("gameUserOption", userOption.id)
        intent.putExtra("gameComputerOption", computerOption.id)
    }

    private fun getResultTextMessage(result: Result): String {
        when (result) {
            Result.WIN -> return "Venceu!"
            Result.TIE -> return "Empate!"
            else -> return ""
        }
    }

    private fun setImageView(user: RockPaperScissors, computer: RockPaperScissors) {
        if (user != RockPaperScissors.UNKNOWN && computer != RockPaperScissors.UNKNOWN) {
            playersChoiceIv.setImageResource(game.getRockPaperScissorsResource(user))
            computersChoiceIv.setImageResource(game.getRockPaperScissorsResource(computer))
        }
    }
}
