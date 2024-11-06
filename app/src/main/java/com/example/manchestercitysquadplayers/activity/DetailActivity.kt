package com.example.manchestercitysquadplayers.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.manchestercitysquadplayers.R
import com.example.manchestercitysquadplayers.data.Player

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Player Information"

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailOrigin: TextView = findViewById(R.id.tv_detail_origin)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)
        val bShare: Button = findViewById(R.id.action_share)

        @Suppress("DEPRECATION") val player = intent.getParcelableExtra<Player>("EXTRA_PLAYER")

        if (player != null) {
            tvDetailName.text = player.name
            tvDetailOrigin.text = player.nationality
            tvDetailDescription.text = player.description
            ivDetailPhoto.setImageResource(player.photo)

            bShare.setOnClickListener {
                val shareIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TITLE, "${player.name} - ${player.nationality}")
                    putExtra(Intent.EXTRA_TEXT, player.description)
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share"))
            }
        }
    }
}