package com.example.manchestercitysquadplayers.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.manchestercitysquadplayers.R
import com.example.manchestercitysquadplayers.adapter.SquadPlayerListAdapter
import com.example.manchestercitysquadplayers.data.Player

class MainActivity : AppCompatActivity() {
    private lateinit var rvPlayers: RecyclerView
    private val listPlayers = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPlayers = findViewById(R.id.rv_players)
        rvPlayers.setHasFixedSize(true)

        listPlayers.addAll(getPlayerList())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getPlayerList(): ArrayList<Player> {
        val dataName = resources.getStringArray(R.array.players_name_data)
        val dataOrigin = resources.getStringArray(R.array.players_nationality_data)
        val dataDescription = resources.getStringArray(R.array.players_description_data)
        val dataPhoto = resources.obtainTypedArray(R.array.players_photo_data)
        val playerList = ArrayList<Player>()

        for (i in dataName.indices) {
            val player = Player(dataName[i], dataOrigin[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            playerList.add(player)
        }

        return playerList
    }

    private fun showRecyclerList() {
        rvPlayers.layoutManager = LinearLayoutManager(this)
        val squadPlayerListAdapter = SquadPlayerListAdapter(listPlayers)
        rvPlayers.adapter = squadPlayerListAdapter

        squadPlayerListAdapter.setOnItemClickCallback(object : SquadPlayerListAdapter.OnItemClickCallback {
            override fun onItemClicked(player: Player) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("EXTRA_PLAYER", player)
                startActivity(intentToDetail)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intentToAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentToAbout)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}