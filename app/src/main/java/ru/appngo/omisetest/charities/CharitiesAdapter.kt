package ru.appngo.omisetest.charities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.appngo.omisetest.R
import ru.appngo.omisetest.charities.data.Charity

class CharitiesAdapter(
    private val list: List<Charity>
) : RecyclerView.Adapter<CharitiesAdapter.CharityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharityViewHolder {
        return CharityViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_charity, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CharityViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class CharityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(charity: Charity) {
            itemView.findViewById<TextView>(R.id.charity_name).text = charity.name
            Picasso.get()
                    .load(charity.logoUrl)
                    .into(itemView.findViewById<ImageView>(R.id.charity_logo))
        }
    }
}
