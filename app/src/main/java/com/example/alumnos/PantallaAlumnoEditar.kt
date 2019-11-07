package com.example.alumnos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_pantalla_alumno_crear.*
import kotlinx.android.synthetic.main.fragment_pantalla_alumno_editar.*

class PantallaAlumnoEditar: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pantalla_alumno_editar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eitx_NumeroMatriculaEditar.setText(PantallaAlumnoEditarArgs.fromBundle(arguments!!).numeroMatricula)
        eitx_NombreEditar.setText(PantallaAlumnoEditarArgs.fromBundle(arguments!!).nombre)
        eitx_ApellidosEditar.setText(PantallaAlumnoEditarArgs.fromBundle(arguments!!).apellidos)
        eitx_NotaMediaEditar.setText(PantallaAlumnoEditarArgs.fromBundle(arguments!!).notaMedia)

        foatbt_GuardarEditar.setOnClickListener {
            try {
                eitx_NotaMediaEditar.text.toString().toInt()

                if((eitx_NombreEditar.length() > 0) and (eitx_NombreEditar.length() <= 50) and (eitx_ApellidosEditar.length() > 0) and (eitx_ApellidosEditar.length() <= 100) and (eitx_NotaMediaEditar.length() > 0) and (eitx_NotaMediaEditar.length() <= 2) and (eitx_NotaMediaEditar.text.toString().toInt() >= 0) and (eitx_NotaMediaEditar.text.toString().toInt() <= 10)) {
                    val url = "http://iesayala.ddns.net/pruebaAD/actualizarAlumno.php/?NumeroMatricula=" + eitx_NumeroMatriculaEditar.text.toString() + "&Nombre=" + eitx_NombreEditar.text.toString() + "&Apellidos=" + eitx_ApellidosEditar.text.toString() + "&NotaMedia=" + eitx_NotaMediaEditar.text.toString()
                    PantallaPrincipal.leerUrl(url)
                    Toast.makeText(activity,"Se ha creado correctamente.", Toast.LENGTH_SHORT).show()
                    activity?.onBackPressed()
                } else Toast.makeText(activity,"Error: Campos incorrectos.", Toast.LENGTH_SHORT).show()
            } catch (ex: NumberFormatException) {
                Toast.makeText(activity,"Error: Campos incorrectos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
