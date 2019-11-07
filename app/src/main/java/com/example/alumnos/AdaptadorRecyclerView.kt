package com.example.alumnos

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import android.content.DialogInterface




class AdaptadorRecyclerView(var listaAlumnos: ArrayList<Alumno>, var pantallaPrincipal: PantallaPrincipal): RecyclerView.Adapter<AdaptadorRecyclerView.MiViewHolder>() {
    class MiViewHolder(view: View, var pantallaPrincipal: PantallaPrincipal): RecyclerView.ViewHolder(view) {
        fun enlazaItems(alumno: Alumno) {
            val numeroMatricula: TextView = itemView.findViewById(R.id.txve_NumeroMatricula)
            val nombre: TextView = itemView.findViewById(R.id.txve_Nombre)
            val apellidos: TextView = itemView.findViewById(R.id.txve_Apellidos)
            val notaMedia: TextView = itemView.findViewById(R.id.txve_NotaMedia)

            numeroMatricula.text = alumno.numeroMatricula
            nombre.text = alumno.nombre
            apellidos.text = alumno.apellidos
            notaMedia.text = alumno.notaMedia

            itemView.setOnClickListener {
                var action = PantallaPrincipalDirections.actionPantallaPrincipalToPantallaAlumno(nombre.text.toString(), apellidos.text.toString(), notaMedia.text.toString(), numeroMatricula.text.toString())
                pantallaPrincipal.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view,parent,false)
        return MiViewHolder(view, pantallaPrincipal)
    }

    override fun getItemCount(): Int {
        return listaAlumnos.size
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        holder.enlazaItems(listaAlumnos[position])
    }
}
