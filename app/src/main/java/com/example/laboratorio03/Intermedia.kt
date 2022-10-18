package com.example.laboratorio03


import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio03.Logica.*
import com.example.laboratorio03.Logica.Reserva
import com.google.android.material.navigation.NavigationView
import java.util.ArrayList


class Intermedia : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    AdaptadorServicios.SolicitudesAdapterListener,
    RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    private var navigationView: NavigationView? = null
    private var miNavDrawer: DrawerLayout? = null
    private var User: Usuario? = null
    private var vuel: Button? = null
    private var reserv: Button? = null
    private var listaR : ArrayList<Reserva?>? = null
    private var checkin: Button? = null
    var rlistaReservas : ArrayList<Reserva>? = null
    private var recyclerVuelos: RecyclerView? = null
    private var myAdapter: AdaptadorServicios? = null
    private var coordinatorLayout: CoordinatorLayout? = null
    private var searchView: SearchView? = null
    private var listaVuelos: ArrayList<Vuelo?>? = null
    private var listaServicios: ArrayList<Servicio?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intermedia)
        User = intent.getSerializableExtra("UsuarioR") as Usuario?
        rlistaReservas = intent.getSerializableExtra("listaReservas") as ArrayList<Reserva>?
        val miToolBar = findViewById<Toolbar>(R.id.tb_toolbar)
        setSupportActionBar(miToolBar)
        miNavDrawer = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navView)
        navigationView?.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(
            this, miNavDrawer, miToolBar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        var header :View? = navigationView?.getHeaderView(0)
        var nc :TextView? = header?.findViewById(R.id.nomcompleto)
        nc?.setText(User?.getnombreCompleto())
        var ce :TextView? = header?.findViewById(R.id.correo)
        ce?.setText(User?.getcorreo())

        miNavDrawer?.addDrawerListener(toggle)
        toggle.syncState()

        vuel = findViewById<View>(R.id.vuelos) as Button
        vuel!!.setOnClickListener {
            val intent = Intent(this@Intermedia, Intermedia::class.java)
            Log.i("siiiIntermediaUsuario",User?.getusuario().toString())
            intent.putExtra("UsuarioR", User)
            intent.putExtra("listaReservas", rlistaReservas)
            Log.i("siiiRESERVASLISTA",rlistaReservas.toString())
            startActivity(intent)
        }

        reserv = findViewById<View>(R.id.misreservas) as Button
        reserv!!.setOnClickListener {
            val intent = Intent(this@Intermedia, Reservas::class.java)
            intent.putExtra("UsuarioR", User)
            intent.putExtra("listaReservas", rlistaReservas)
            Log.i("siiiRESERVASLISTA",rlistaReservas.toString())
            startActivity(intent)
        }

        checkin = findViewById<View>(R.id.checkin) as Button
        checkin!!.setOnClickListener {
            val intent = Intent(this@Intermedia, CheckIn::class.java)
            Log.i("siiiIntermediaUsuario",User?.getusuario().toString())
            intent.putExtra("UsuarioR", User)
            intent.putExtra("listaReservas", rlistaReservas)
            Log.i("siiiRESERVASLISTA",rlistaReservas.toString())
            startActivity(intent)
        }
        //listaVuelos = ModelData.instanceModelData!!.getVueloLista()
        listaServicios = ModelData.instanceModelData!!.getServicioLista()
        recyclerVuelos = findViewById(R.id.rcv_solicitudes)
        whiteNotificationBar(recyclerVuelos)
        //myAdapter = AdaptadorVuelos(listaVuelos!!, this)
        myAdapter = AdaptadorServicios(applicationContext, listaServicios!!, this)

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

    fun buscar(view: View?) {
        val intent = Intent(this, Reservas::class.java)
        listaR = ListaMisReservas.instanceListaMisReservas!!.buscarReserva(User?.getusuario())
        intent.putExtra("UsuarioR", User)
        Log.i("siiiBuscaVuelosUsuario2",User?.getusuario().toString())
        intent.putExtra("ListaVuelos", listaR)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            moveTaskToBack(true)
            //super.onBackPressed();
        }
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
        menuInflater.inflate(R.menu.nav_drawer, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val intent: Intent
        when (item.itemId) {
            R.id.nav_salirAplicacion -> {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_cambiarClave -> {
                intent = Intent(this, CambiarClave::class.java)
                intent.putExtra("Usuario", User)
                startActivity(intent)
            }
            R.id.miPerfil ->{
                intent = Intent(this, DetalleUsuario::class.java)
                intent.putExtra("Usuario", User)
                startActivity(intent)
            }
            R.id.gotP ->{
                intent = Intent(this, GotPremium::class.java)
                startActivity(intent)
            }
        }
        return false
    }

    /*override fun onContactSelected(v: Vuelo?) {
        val intent = Intent(this, ComprarBoleto::class.java)
        intent.putExtra("VueloSeleccionado", v)
        intent.putExtra("UsuarioR", User)
        val precioTot: Int? =  v!!.getPrecio()
        intent.putExtra("PrecioTotal", precioTot)
        Log.i("siiiVueloVSelec", v.toString())
        startActivity(intent)
    }*/


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

    override fun onContactSelected(ser: Servicio?) {
        val intent = Intent(this, ComprarBoleto::class.java)
        intent.putExtra("ServicioSeleccionado", ser)
        intent.putExtra("UsuarioR", User)
        intent.putExtra("PrecioTotal", ser!!.getCosto())
        startActivity(intent)
    }
}
