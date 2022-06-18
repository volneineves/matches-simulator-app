package me.dio.simulator.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import me.dio.simulator.data.MatchesApi
import me.dio.simulator.databinding.ActivityMainBinding
import me.dio.simulator.domain.Match
import me.dio.simulator.ui.adapter.MatchesAdapter
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var matchesApi: MatchesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHttpClient()
        setupMatchesList()
        setupMatchesRefresh()
        setupFloatingActionButton()
    }

    private fun setupHttpClient() {
        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl("https://volneineves.github.io/matches-simulator-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        matchesApi = retrofit.create(MatchesApi::class.java)
    }

    private fun setupMatchesList() {
        binding.rvMatches.setHasFixedSize(true)
        binding.rvMatches.layoutManager = LinearLayoutManager(this)
        findMatchesFromApi()
    }

    private fun setupMatchesRefresh() =
        binding.srlMatches.setOnRefreshListener(this::findMatchesFromApi)


    private fun setupFloatingActionButton() {
        binding.fabSimulate.setOnClickListener { view ->
            view.animate().rotationBy(360F).setDuration(500)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                    }
                })
        }
    }

    private fun findMatchesFromApi() {
        binding.srlMatches.isRefreshing = true
        matchesApi.getMatches().enqueue(object : Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    val matches: List<Match>? = response.body();
                    val matchesAdapter = matches?.let { MatchesAdapter(it) }
                    binding.rvMatches.adapter = matchesAdapter
                    //                    Log.i("SIMULATOR", "Deu tudo certo! Partidas: " + matches?.size)
                } else Log.i("SIMULATOR", "Deu tudo errado: ${response.message()}")
                binding.srlMatches.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                binding.srlMatches.isRefreshing = false
                Log.i("SIMULATOR", "Deu tudo errado: ${t.message}")
            }
        })
    }
}