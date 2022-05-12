package com.example.task_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StatesAdapter constructor(country :GetCountryDetailQuery.Data)
    :RecyclerView.Adapter<StatesAdapter.ForecastHolder>(){

    private val states=country.country?.states
    inner class ForecastHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val stateName: TextView = view.findViewById(R.id.textView5)


        fun setStateData(state:GetCountryDetailQuery.State) {
            stateName.text = state.name


        }



    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatesAdapter.ForecastHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_states, parent, false)
        return ForecastHolder(view)
    }

    override fun onBindViewHolder(holder: StatesAdapter.ForecastHolder, position: Int) {
        holder.setStateData(states!![position])
    }

    override fun getItemCount(): Int {
        return states!!.size
    }
}