package me.dio.simulator.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.dio.simulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMatchesList()
        setupMatchesRefresh()
        setupFloatingActionButton()
    }

    private fun setupMatchesList() {
        // TODO Listar as partidas, consumindo nossa API
    }

    private fun setupMatchesRefresh() {
        // TODO Atualizar as partidas na ação de swipe
    }

    private fun setupFloatingActionButton() {
        // TODO Criar evento de click e simulação de partidas.
    }
}