package com.example.task_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() ,ContinentAdapter.ItemClickListener{
    private var recyclerView: RecyclerView? = null
    private var adapter:ContinentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<View>(R.id.card_recycler_view) as RecyclerView
        recyclerView!!.layoutManager=LinearLayoutManager(this@MainActivity)
        lifecycleScope.launchWhenResumed {
            val response = apolloClient(this@MainActivity).query(GetContinentsQuery()).execute()
            response.data?.continents?.forEach {
                println("name : ${it.name} | code : ${it.code} | number of countries : ${it.countries.size}")
            }
            adapter= response.data?.let { ContinentAdapter(it) }
            adapter?.setClickListener(this@MainActivity)
            recyclerView!!.adapter=adapter



        }


    }

    override fun onItemClick(view: View?, position: Int) {
        val intent= Intent(this,CountryActivity::class.java)
        intent.putExtra("code",adapter?.getItem(position))
        startActivity(intent)
    }


}