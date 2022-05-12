package com.example.task_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountriesAdapter constructor(countries :FindCountriesOfAContinentQuery.Data)
    :RecyclerView.Adapter<CountriesAdapter.ForecastHolder>(){


    private val countries=countries.continent?.countries
    private var mClickListener: CountriesAdapter.ItemClickListener? = null
    inner class ForecastHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        private val countryName: TextView = view.findViewById(R.id.textView3)
        private val statesNumber: TextView = view.findViewById(R.id.textView4)
        private val imgView:ImageView=view.findViewById(R.id.imageView3)
        fun setContinentData(country: FindCountriesOfAContinentQuery.Country) {
            countryName.text = country.name
            if(country.states.isEmpty())
                statesNumber.text = "0"
            else statesNumber.text="+"+country.states.size.toString()
            countryName.setOnClickListener(this)
            statesNumber.setOnClickListener(this)
            imgView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            mClickListener?.onItemClick(p0,adapterPosition)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountriesAdapter.ForecastHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return ForecastHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesAdapter.ForecastHolder, position: Int) {
        holder.setContinentData(countries!![position])
    }

    override fun getItemCount(): Int {
        return countries!!.size
    }
    fun setClickListener(itemClickListener: ItemClickListener){
        mClickListener=itemClickListener
    }
    interface ItemClickListener{
        fun onItemClick(view: View?,position: Int)
    }

    fun getItem(id:Int) :String{

        return countries!![id].code
    }
}