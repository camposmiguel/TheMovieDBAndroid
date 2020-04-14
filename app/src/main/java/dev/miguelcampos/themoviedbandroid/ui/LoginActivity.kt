package dev.miguelcampos.themoviedbandroid.ui

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.miguelcampos.themoviedbandroid.MainActivity
import dev.miguelcampos.themoviedbandroid.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        text_view_app_name.typeface = Typeface.createFromAsset(
            assets,
            "blockletter.otf"
        )

        button_login.setOnClickListener{
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
            finish()
        }

    }
}
