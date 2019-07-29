package main.gameOver

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luiz.jokenpo.R
import kotlinx.android.synthetic.main.activity_game_over.*
import main.models.Game
import main.models.RockPaperScissors
import main.play.PlayActivity

class GameOverActivity : AppCompatActivity() {

    var points = 0
    var computerOption = RockPaperScissors.UNKNOWN
    var userOption = RockPaperScissors.UNKNOWN
    var game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        useIntentsFromPlayScreen()
        setImagesOptions()

        pointsTv.text = getString(R.string.your_points, points)


        restartEndGameBt.setOnClickListener {
            startActivity(Intent(this, PlayActivity::class.java))
            finish()
        }

        exitEndGameBt.setOnClickListener {
            finish()
        }
    }

    private fun setImagesOptions() {
        computerChoiceGameOverIv.setImageResource(game.getRockPaperScissorsResource(computerOption))
        playersChoiceGameOverIv.setImageResource(game.getRockPaperScissorsResource(userOption))

    }

    private fun useIntentsFromPlayScreen() {
        points = intent.getIntExtra("gamePoints", 0)
        computerOption = RockPaperScissors(intent.getIntExtra("gameComputerOption", -1))
        userOption = RockPaperScissors(intent.getIntExtra("gameUserOption", -1))
    }
}
