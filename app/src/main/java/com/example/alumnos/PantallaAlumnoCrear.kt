package com.example.alumnos

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_pantalla_alumno_crear.*
import java.io.IOException
import java.net.URL

class PantallaAlumnoCrear : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pantalla_alumno_crear, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foatbt_GuardarCrear.setOnClickListener {
            try {
                eitx_NumeroMatriculaCrear.text.toString().toInt()
                eitx_NotaMediaCrear.text.toString().toInt()

                if((eitx_NumeroMatriculaCrear.length() > 0) and (eitx_NumeroMatriculaCrear.length() <= 10) and (eitx_NombreCrear.length() > 0) and (eitx_NombreCrear.length() <= 50) and (eitx_ApellidosCrear.length() > 0) and (eitx_ApellidosCrear.length() <= 100) and (eitx_NotaMediaCrear.length() > 0) and (eitx_NotaMediaCrear.length() <= 2) and (eitx_NotaMediaCrear.text.toString().toInt() >= 0) and (eitx_NotaMediaCrear.text.toString().toInt() <= 10)) {
                    val url = "http://iesayala.ddns.net/pruebaAD/insertarAlumno.php/?NumeroMatricula=" + eitx_NumeroMatriculaCrear.text.toString() + "&Nombre=" + eitx_NombreCrear.text.toString() + "&Apellidos=" + eitx_ApellidosCrear.text.toString() + "&NotaMedia=" + eitx_NotaMediaCrear.text.toString()
                    if(!PantallaPrincipal.leerUrl(url).contains("Duplicate entry '" + eitx_NumeroMatriculaCrear.text.toString() + "' for key 'PRIMARY'")) {
                        Toast.makeText(activity,"Se ha creado correctamente.", Toast.LENGTH_SHORT).show()
                        activity?.onBackPressed()
                    } else Toast.makeText(activity,"Error: Número de matrícula duplicado.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(activity,"Error: Campos incorrectos.", Toast.LENGTH_SHORT).show()
            } catch (ex: NumberFormatException) {
                Toast.makeText(activity,"Error: Campos incorrectos..", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
