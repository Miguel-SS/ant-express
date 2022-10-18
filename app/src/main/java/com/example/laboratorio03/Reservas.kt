package com.example.laboratorio03

import android.app.SearchManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.*
import com.example.laboratorio03.AdaptadorReservas.ReservasAdapterListener
import com.example.laboratorio03.AdaptadorReservas.ViewHolderReserva
import com.example.laboratorio03.Logica.Usuario
import com.example.laboratorio03.Logica.Reserva
import com.example.laboratorio03.Logica.Vuelo
import com.example.laboratorio03.RecyclerItemTouchHelper.RecyclerItemTouchHelperListener
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList


class Reservas : AppCompatActivity(), ReservasAdapterListener,
    RecyclerItemTouchHelperListener {
    private var recyclerSolicitudes: RecyclerView? = null
    private var myAdapter: AdaptadorReservas? = null
    private var coordinatorLayout: CoordinatorLayout? = null
    private var searchView: SearchView? = null
    private var navigationView: NavigationView? = null
    private var miNavDrawer: DrawerLayout? = null
    private var User: Usuario? = null
    private var vuel: Button? = null
    private var reserv: Button? = null
    private var checkin: Button? = null
    var rlistaReservas : ArrayList<Reserva>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)
        User = intent.getSerializableExtra("UsuarioR") as Usuario?
        rlistaReservas = intent.getSerializableExtra("listaReservas") as ArrayList<Reserva>?
        Log.i("siiiANTESINIT",User?.getcorreo().toString())
        init()
    }

    fun init() {
        Log.i("siiiUNO",User?.getcorreo().toString())

        recyclerSolicitudes = findViewById(R.id.rcv_res)

        Log.i("siiiDOS",User?.getcorreo().toString())

        whiteNotificationBar(recyclerSolicitudes)

        Log.i("siiiTRES",User?.getcorreo().toString())
        myAdapter = AdaptadorReservas(applicationContext,ListaMisReservas.instanceListaMisReservas!!.buscarReserva(User?.getusuario())!!, this)

        Log.i("siiiCUATRO",User?.getcorreo().toString())
        Log.i("siiiRESERVASLISTA",rlistaReservas.toString())
        coordinatorLayout = findViewById(R.id.cord_reser)
        val manager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerSolicitudes?.setLayoutManager(manager)
        recyclerSolicitudes?.setItemAnimator(DefaultItemAnimator())
        recyclerSolicitudes?.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerSolicitudes?.setAdapter(myAdapter)
        Log.i("siiiADAPTER",
            ListaMisReservas.instanceListaMisReservas!!.buscarReserva(User?.getusuario())!!.toString()
        )
    }


    override fun onContactSelected(solid: Reserva?) {
       /* val intent = Intent(this, DetalleSolicitud::class.java)
        intent.putExtra("ReservaSelec", solid)
        startActivity(intent)*/
    }
/*
    fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {
        val listaSolicitudes: ListaMisReservas = ListaMisReservas.instanceListaMisReservas!!
        if (direction == ItemTouchHelper.START) {
            if (viewHolder is ViewHolderReserva) {
                // get the removed item name to display it in snack bar
                val name = listaSolicitudes.lista[viewHolder.getAdapterPosition()]!!.getUsuario()?.getnombreCompleto() +" "+
                        listaSolicitudes.lista[viewHolder.getAdapterPosition()]!!.getVuelo()?.getRuta()+" "+
                        listaSolicitudes.lista[viewHolder.getAdapterPosition()]!!.getVuelo()?.getHorario()
                // save the index deleted
                val deletedIndex = viewHolder.getAdapterPosition()
                //    remove the item from recyclerView
                myAdapter!!.removeItem(viewHolder.getAdapterPosition())
                //   showing snack bar with Undo option
                val snackbar = Snackbar.make(
                    coordinatorLayout!!,
                    "$name  se eliminó!", Snackbar.LENGTH_LONG
                )
                snackbar.setAction("UNDO") {
                    myAdapter!!.restoreItem(deletedIndex)
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }else{
            if(direction == ItemTouchHelper.END){
                if (viewHolder is ViewHolderReserva) {
                    val intent = Intent(this, EditJobAplication::class.java)
                    intent.putExtra("Data", listaSolicitudes.lista[viewHolder.getAdapterPosition()])
                    intent.putExtra("Position", viewHolder.getAdapterPosition())
                    this.finish()
                    startActivity(intent)
                }
            }
        }
    }*/



    private fun whiteNotificationBar(view: View?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view!!.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            window.statusBarColor = resources.getColor(R.color.colorPrimary)
        }
    }

    override fun onItemMove(source: Int, target: Int) {
        myAdapter!!.onItemMove(source, target)
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
