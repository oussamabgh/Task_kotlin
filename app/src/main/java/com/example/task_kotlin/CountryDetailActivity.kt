package com.example.task_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CountryDetailActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapter:StatesAdapter? = null
    private var emptyView: TextView? = null
    var code: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.country_detail)
        code=intent.getStringExtra("code")
        recyclerView = findViewById<View>(R.id.card_recycler_view_states) as RecyclerView
        emptyView = findViewById<View>(R.id.empty_view) as TextView
        recyclerView!!.layoutManager= LinearLayoutManager(this)
        var countryName =findViewById<View>(R.id.tv_CountryName) as TextView
        var countryCode =findViewById<View>(R.id.tx_codeCountry) as TextView
        var countryCapital =findViewById<View>(R.id.tx_Capital) as TextView
        var countryLa =findViewById<View>(R.id.tx_NativeLanguage) as TextView
        var countryEmoji=findViewById<View>(R.id.iv_emoji) as TextView
        var btn=findViewById<View>(R.id.previousbutton) as Button
        btn.setOnClickListener {
            this.finish()
        }
        lifecycleScope.launchWhenResumed {
            val response = apolloClient(this@CountryDetailActivity).query(GetCountryDetailQuery(code!!)).execute()
            countryName.text=response.data?.country?.name
            countryCode.text=code!!
            countryCapital.text=response.data?.country?.capital
            countryLa.text= response.data?.country?.languages!![0].name
            countryEmoji.text = code!!.countryCodeToUnicodeFlag()



            if (response.data?.country!!.states?.isEmpty()) {
                recyclerView!!.visibility = View.GONE
                emptyView!!.visibility = View.VISIBLE
            } else {
                adapter= response.data?.let { StatesAdapter(it) }
                recyclerView!!.adapter=adapter
                recyclerView!!.visibility = View.VISIBLE
                emptyView!!.visibility = View.GONE
            }

        }


    }

    fun String.countryCodeToUnicodeFlag(): String {
        return this
            .filter { it in 'A'..'Z' }
            .map { it.toByte() }
            .flatMap { char ->
                listOf(
                    // First UTF-16 char is \uD83C
                    0xD8.toByte(),
                    0x3C.toByte(),
                    // Second char is \uDDE6 for A and increments from there
                    0xDD.toByte(),
                    (0xE6.toByte() + (char - 'A'.toByte())).toByte()
                )
            }
            .toByteArray()
            .let { bytes ->
                String(bytes, Charsets.UTF_16)
            }
    }


}