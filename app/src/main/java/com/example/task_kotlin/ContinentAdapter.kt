package com.example.task_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContinentAdapter constructor(continents: GetContinentsQuery.Data)
    : RecyclerView.Adapter<ContinentAdapter.ForecastHolder>() {
   private val continents=continents.continents
    private var mClickListener:ItemClickListener? = null
    inner class ForecastHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        private val continentName: TextView = view.findViewById(R.id.textView)
        private val countriesNumber: TextView = view.findViewById(R.id.textView2)
        private val lay:LinearLayout =view.findViewById(R.id.lnContinent)
        fun setContinentData(continent: GetContinentsQuery.Continent) {
            continentName.text = continent.name
            if(continent.countries.isEmpty())
            countriesNumber.text = "0"
            else countriesNumber.text="+"+continent.countries.size.toString()
            lay.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            mClickListener?.onItemClick(p0,adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_continent, parent, false)
        return ForecastHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastHolder, position: Int) {
        holder.setContinentData(continents[position])
    }

    override fun getItemCount(): Int {
        return continents.size
    }

    fun setClickListener(itemClickListener: ItemClickListener){
        mClickListener=itemClickListener
    }
    interface ItemClickListener{
        fun onItemClick(view: View?,position: Int)
    }

    fun getItem(id:Int) :String{
        return continents[id].code
    }

}