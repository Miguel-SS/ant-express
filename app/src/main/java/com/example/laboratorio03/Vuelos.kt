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
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.*
import com.example.laboratorio03.AdaptadorVuelos.SolicitudesAdapterListener
import com.example.laboratorio03.Logica.Usuario
import com.example.laboratorio03.Logica.Vuelo
import com.example.laboratorio03.RecyclerItemTouchHelper.RecyclerItemTouchHelperListener
import com.google.android.material.navigation.NavigationView
import java.util.ArrayList


class Vuelos : AppCompatActivity(), SolicitudesAdapterListener, RecyclerItemTouchHelperListener {
    //, NavigationView.OnNavigationItemSelectedListener {
    private var recyclerVuelos: RecyclerView? = null
    private var myAdapter: AdaptadorVuelos? = null
    private var coordinatorLayout: CoordinatorLayout? = null
    private var searchView: SearchView? = null
    private val navigationView: NavigationView? = null
    private val miNavDrawer: DrawerLayout? = null
    private var listaVuelos: ArrayList<Vuelo?>? = null
    private var User: Usuario? = null
    private var Pasajeros: Int? = null
    private  var ModDat: ModelData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitudes)
        User = intent.getSerializableExtra("UsuarioR") as Usuario?
        User = intent.getSerializableExtra("UsuarioR") as Usuario?
        Pasajeros = intent.getSerializableExtra("Pasajeros") as Int?
        Log.i("siiiVuelosUsuario1",User?.getusuario().toString())
        init()
    }

    fun init() {
        //val toolbar = findViewById<Toolbar>(R.id.toolBar_solicitudes)
        //setSupportActionBar(toolbar)
        //listaVuelos = intent.getSerializableExtra("ListaVuelos") as ArrayList<Vuelo?>
        listaVuelos = ModelData.instanceModelData!!.getVueloLista()
        recyclerVuelos = findViewById(R.id.rcv_solicitudes)
        whiteNotificationBar(recyclerVuelos)
        myAdapter = AdaptadorVuelos(listaVuelos!!, this)

        coordinatorLayout = findViewById(R.id.cord_solicitudes)
        val manager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerVuelos?.setLayoutManager(manager)
        recyclerVuelos?.setItemAnimator(DefaultItemAnimator())
        recyclerVuelos?.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerVuelos?.setAdapter(myAdapter)
    }

    override fun onContactSelected(v: Vuelo?) {
        val intent = Intent(this, ComprarBoleto::class.java)
        intent.putExtra("VueloSeleccionado", v)
        intent.putExtra("UsuarioR", User)
        val precioTot: Int? = v!!.getPrecio()
        intent.putExtra("PrecioTotal", precioTot)
        Log.i("siiiVueloVSelec", v.toString())
        startActivity(intent)
    }



 private fun whiteNotificationBar(view: View?) {
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
         var flags = view!!.systemUiVisibility
         flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
         view.systemUiVisibility = flags
         window.statusBarColor = Color.TRANSPARENT
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
     } else
         super.onOptionsItemSelected(item)
 }

}
