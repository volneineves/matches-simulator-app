package me.dio.simulator.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.dio.simulator.databinding.MatchItemBinding
import me.dio.simulator.domain.Match
import me.dio.simulator.ui.DetailActivity

class MatchesAdapter(
    val matches: List<Match>
) : RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: MatchItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: MatchItemBinding = MatchItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val match = matches[position]

        // Adapta os dados da partida (recuperada da API) para o nosso layout.
        Glide.with(context).load(match.homeTeam.image).circleCrop().into(holder.binding.ivHomeTeam)
        holder.binding.tvHomeTeamName.text = match.homeTeam.name
        holder.binding.tvHomeTeamScore.text = match.homeTeam.score.toString()
        Glide.with(context).load(match.awayTeam.image).circleCrop().into(holder.binding.ivAwayTeam)
        holder.binding.tvAwayTeamName.text = match.awayTeam.name
        holder.binding.tvAwayTeamScore.text = match.awayTeam.score.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.Extras.MATCH, match)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = matches.size
}