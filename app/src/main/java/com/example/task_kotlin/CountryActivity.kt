package com.example.task_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_kotlin.R

class CountryActivity : AppCompatActivity(),CountriesAdapter.ItemClickListener {
    private var recyclerView: RecyclerView? = null
    var code: String? = null
    private lateinit var adapter:CountriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        code=intent.getStringExtra("code")
        recyclerView = findViewById<View>(R.id.card_recycler_view_countries) as RecyclerView
        var btn=findViewById<View>(R.id.previousbutton2) as Button
        btn.setOnClickListener {
            this.finish()
        }
        recyclerView!!.layoutManager= LinearLayoutManager(this)
        lifecycleScope.launchWhenResumed {
            val response = apolloClient(this@CountryActivity).query(FindCountriesOfAContinentQuery(code!!)).execute()
            adapter= response.data?.let { CountriesAdapter(it) }!!
            adapter.setClickListener(this@CountryActivity)
            recyclerView!!.adapter=adapter



        }



    }

    override fun onItemClick(view: View?, position: Int) {
        val intent= Intent(this,CountryDetailActivity::class.java)
        intent.putExtra("code",adapter.getItem(position))
        startActivity(intent)
        println("clickkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")
    }
}