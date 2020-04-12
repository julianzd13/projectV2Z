package com.example.practica3

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.practica3.model.Reservas
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.item_horario.view.*
import java.util.ArrayList

class HoradispRVAdapter(
    private val context: Context, private val horariosdispolist : ArrayList<Reservas>
) : RecyclerView.Adapter<HoradispRVAdapter.HoradispViewHolder>(){

    //private var horariosdispolist = emptyList<HoraDispo>()
    //private val context: Context

    /*init {
        this.horariosdispolist = horariosdispolist
        this.context = context
    }*/

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HoradispRVAdapter.HoradispViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_horario,parent,false)
        return HoradispViewHolder(itemView, context)

    }


    override fun getItemCount(): Int= horariosdispolist.size


    override fun onBindViewHolder(
        holder: HoradispRVAdapter.HoradispViewHolder,
        position: Int)
    {
        val horario: Reservas = horariosdispolist[position]
        holder.binHorariodisp(horario)
    }

    class HoradispViewHolder(
        itemView: View, context: Context
    ): RecyclerView.ViewHolder(itemView) {

        private var context:Context
        init{
            this.context=context
        }

        @SuppressLint("RestrictedApi")
        fun binHorariodisp(horario: Reservas) {

            itemView.tv_hora_from_fire.text = horario.hora

            itemView.setOnClickListener {

                val hora = horario.hora

                Toast.makeText(context,horario.hora, Toast.LENGTH_SHORT).show()
                val intent = Intent(context, NewReservaStep1::class.java)
                intent.putExtra("horaselec",hora)

                getActivity(context)!!.startActivity(intent)

                //getActivity(context)!!.setResult(Activity.RESULT_OK, intent)
                //getActivity(context)!!.finish()

            }

            itemView.setOnLongClickListener{
                val hora = horario.hora
                Toast.makeText(context,hora, Toast.LENGTH_SHORT).show()
                return@setOnLongClickListener true
            }

        }

    }





}