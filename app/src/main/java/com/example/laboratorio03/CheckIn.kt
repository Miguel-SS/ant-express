package com.example.laboratorio03

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView

class CheckIn : AppCompatActivity() {

    private var searchView: SearchView? = null
    private var myAdapter: AdaptadorReservas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        // Associate searchable configuration with the SearchView   !IMPORTANT
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search)
            .actionView as SearchView
        searchView!!.setSearchableInfo(
            searchManager
                .getSearchableInfo(componentName)
        )
        searchView!!.maxWidth = Int.MAX_VALUE
        // listening to search query text change, every type on input
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                myAdapter!!.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                myAdapter!!.filter.filter(query)
                return false
            }
        })
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }
}