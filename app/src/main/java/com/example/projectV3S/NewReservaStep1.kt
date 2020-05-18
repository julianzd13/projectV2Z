package com.example.projectV3S

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.projectV3S.Room.NewResRoom
import com.example.projectV3S.Room.NewresDAO

import com.example.projectV3S.UTILS.Constantes
import com.example.projectV3S.model.Escenario
import com.example.projectV3S.model.NuevaReservRoom
import com.example.projectV3S.model.Reservas
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_new_reserva_step1.*
import java.text.SimpleDateFormat
import java.util.*

class NewReservaStep1 : AppCompatActivity(), OnMapReadyCallback{


    lateinit var adapter : ArrayAdapter<String?>
    var lista: ArrayList<String?> = ArrayList()
    var myList: ListView? = null
    var mysearch: SearchView? = null

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    var longitudef : String? = "-75.5701685"
    var latitudef : String? = "6.267651"
    var mapmarker : String? = "UdeA"

    var horafHoradRVA : String? = Constantes.EMPTY

    private var cal = Calendar.getInstance()
    var  fecha : String? = Constantes.EMPTY

    var canchaselec : String? = null
    var num_partici : String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_reserva_step1)

        eliminar_reserva_enpro()

        searchView.isIconifiedByDefault = false

        mapView = findViewById(R.id.mapcancha)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        val dataSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM-dd-yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fecha = sdf.format(cal.time).toString()

                comprobarfecha(fecha!!)

                //et_new_fecha_inten.setText(fecha)
                //Log.d("Fecha",fecha)
                //date = sdf.format(cal.time).toString

            }

        }

        et_new_fecha_inten.setOnClickListener {

            if(canchaselec == null){
                Toast.makeText(this, "Primero la cancha", Toast.LENGTH_SHORT).show()
            }else {
                DatePickerDialog(
                    this,
                    dataSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)

                ).show()
            }
        }

        et_new_horari_inten.setOnClickListener {

            if (canchaselec!=null && fecha!=Constantes.EMPTY ) {
                var intent = Intent(this, VistaHoraDispActivity::class.java)
                //startActivity(intent)
                intent.putExtra("Fecha", fecha)
                intent.putExtra("Cancha", canchaselec)
                startActivityForResult(intent, 2233)
            } else{
                Toast.makeText(this, "Primero la cancha y la fecha", Toast.LENGTH_SHORT).show()
            }

        }


        //val database = FirebaseDatabase.getInstance()
        //val myRef = database.getReference("escenarios")

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("escenarios")


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w("quepasomal", "Failed to read value.", error.toException())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val canchan = snapshot.getValue(Escenario::class.java)
                    lista.add(canchan!!.nombre)
                }
            }
        })

        //lista.add("Bolera")
        //lista.add("Voley")
        //lista.add("Fútbol")
        //lista.add("Piscina")


        myList = this.findViewById(R.id.listView)
        mysearch=this.findViewById(R.id.searchView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,lista)
        myList!!.adapter = adapter



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                firebaseSearchSub(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //firebaseSearchChan(newText)
                /*if (newText!!.isNotEmpty()){
                    displaylist.clear()
                    val search = newText.toLowerCase()
                }else{
                    displaylist.clear()
                    myRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (snapshot in dataSnapshot.children) {
                                val escenarion = snapshot.getValue(Escenario::class.java)
                                Log.d("quepasobien", "Value is: ${escenarion?.nombre}")
                                displaylist.add(escenarion?.nombre.toString())
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Failed to read value
                            Log.w("quepasomal", "Failed to read value.", error.toException())
                        }
                    })
                }*/

                listView.visibility = View.VISIBLE

                adapter!!.filter.filter(newText)

                listView.setOnItemClickListener { adapterView, view, position, id ->


                    val listescen: String = myList!!.getItemAtPosition(position).toString()
                    mysearch!!.setQuery(listescen, true)
                    listView.visibility = View.GONE
                }
                return false
            }

        })


        /*myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (snapshot in dataSnapshot.children) {
                    val canchan = snapshot.getValue(Escenario::class.java)
                    Log.d("quepasobien", "Value is: ${canchan?.nombre}")
                    textView3.text = canchan?.nombre + canchan?.id
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("quepasomal", "Failed to read value.", error.toException())
            }
        })*/

        bt_conti_to_step2.setOnClickListener {
            if (horafHoradRVA == Constantes.EMPTY || canchaselec == null || fecha == Constantes.EMPTY || num_partici == null) {
                Toast.makeText(this, getText(R.string.error_login), Toast.LENGTH_SHORT).show()
            }else{

                val nuevareservroom = NuevaReservRoom(1, canchaselec, fecha, horafHoradRVA)
                val newresDAO = NewResRoom.database1.NewresDAO()
                newresDAO.insertNuevares(nuevareservroom)


                intent = Intent(this, NewReservaStep2::class.java)
                intent.putExtra("Numpartici", num_partici)
                startActivity(intent)
                //Toast.makeText(this, "CONTINUARA", Toast.LENGTH_SHORT).show()
            }
        }


    }




//////////////////////////METODOS////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun comprobarfecha(fecha: String) {

        val auth: FirebaseAuth
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("reservas").child(fecha).child(canchaselec.toString())

        var existereser = false
        myRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children){
                    val reser = snapshot.getValue(Reservas::class.java)
                    if (reser!!.iduser == user!!.uid){
                        Toast.makeText(this@NewReservaStep1, "Ya tiene reserva en esta fecha", Toast.LENGTH_SHORT).show()
                        et_new_fecha_inten.setText(Constantes.EMPTY)
                        existereser = true
                    }
                }
                if (!existereser) {
                    et_new_fecha_inten.setText(fecha)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("quepasomal", "Failed to read value.", error.toException())
            }

        })
    }

    private fun firebaseSearchSub(searchText: String?) {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("escenarios")

        var existecancha = false

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snapshot in dataSnapshot.children) {
                    val canchan = snapshot.getValue(Escenario::class.java)
                    if (canchan!!.nombre.equals(searchText)) {
                        //val canchaselec = usuarion.telefono?.let { myRef.child(it) }
                        canchaselec = canchan.nombre
                        mapmarker = canchaselec
                        longitudef = canchan.longitude
                        latitudef = canchan.latitude
                        num_partici = canchan.integran
                        Log.d("oeooeoe", "Value is: ${canchaselec}")
                        textView3.text = "Descripcion: " + canchan.descripcion.toString()
                        existecancha = true
                        textView3.visibility = View.VISIBLE

                        val positionCancha = LatLng(latitudef!!.toDouble(), longitudef!!.toDouble())

                        mMap.addMarker(
                            MarkerOptions()
                                .position(positionCancha)
                                .title(mapmarker)
                        )
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(positionCancha, 15.0F))
                        mapcancha.visibility = View.VISIBLE

                    }
                }
                if (!existecancha) {
                    Toast.makeText(this@NewReservaStep1, "No existe", Toast.LENGTH_SHORT).show()
                    canchaselec = null
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("quepasomal", "Failed to read value.", error.toException())
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2233 && resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
        }
        if (requestCode == 2233 && resultCode == Activity.RESULT_OK){

            Toast.makeText(this, "BBIIIEENNNN", Toast.LENGTH_SHORT).show()

            var datafhora = data?.extras
            horafHoradRVA = datafhora?.getString("horaselec").toString()
            et_new_horari_inten.setText(horafHoradRVA)

            //Log.d("oooe", horafHoradRVA)

        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!

        // Add a marker in Sydney and move the camera
        val positionCancha = LatLng(latitudef!!.toDouble(), longitudef!!.toDouble())

        mMap.addMarker(
            MarkerOptions()
                .position(positionCancha)
                .title(mapmarker)
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(positionCancha, 14.0F))
        //mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
    }

    private fun eliminar_reserva_enpro() {
        val newresDAO: NewresDAO = NewResRoom.database1.NewresDAO()
        val nuevaReservRoom = newresDAO.searchEscenari()
        if (nuevaReservRoom != null) {
            newresDAO.deleteReserva(nuevaReservRoom) //Borro Datos de Room
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        eliminar_reserva_enpro()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }


}
