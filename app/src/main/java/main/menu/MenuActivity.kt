package main.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luiz.jokenpo.R
import kotlinx.android.synthetic.main.activity_menu.*
import main.play.PlayActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        playBt.setOnClickListener {
            startActivity(Intent(this, PlayActivity::class.java))
        }

        exitBt.setOnClickListener {
            finish()
        }
    }
}
