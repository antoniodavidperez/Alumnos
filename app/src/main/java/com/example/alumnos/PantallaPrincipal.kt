package com.example.alumnos

import android.app.AlertDialog
import android.os.Bundle
import android.os.StrictMode
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_pantalla_principal.*
import java.io.IOException
import java.net.URL
import java.util.ArrayList

class PantallaPrincipal: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pantalla_principal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foatbt_Annadir.setOnClickListener {
            findNavController().navigate(R.id.action_pantallaPrincipal_to_pantallaAlumnoCrear)
        }

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        try {
            val gson = Gson()
            val json = leerUrl("http://iesayala.ddns.net/pruebaAD/seleccionarAlumnos.php")
            val alumnos = gson.fromJson(json, AlumnosArray::class.java)

            rcve_ListaAlumnos.layoutManager = LinearLayoutManager(activity)
            val adaptador = AdaptadorRecyclerView(alumnos.alumnos, this)
            rcve_ListaAlumnos.adapter = adaptador
        } catch (ex: Exception){
            "Error with ${ex.message}."
        }
    }

    companion object {
        fun leerUrl(urlString: String): String {
            val response = try {
                URL(urlString).openStream().bufferedReader().use { it.readText() }
            } catch (ex: IOException) {
                "Error with ${ex.message}."
            }

            return response
        }
    }
}
