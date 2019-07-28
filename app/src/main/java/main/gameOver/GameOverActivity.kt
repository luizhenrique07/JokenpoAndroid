package main.gameOver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luiz.jokenpo.R
import kotlinx.android.synthetic.main.activity_game_over.*
import main.play.PlayActivity

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        restartEndGameBt.setOnClickListener {
            startActivity(Intent(this, PlayActivity::class.java))
        }

        exitEndGameBt.setOnClickListener {
            finish()
        }
    }
}
