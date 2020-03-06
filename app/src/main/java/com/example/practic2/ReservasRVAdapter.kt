package com.example.practic2

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv.view.*

class ReservasRVAdapter(
    context: Context,reservasList: ArrayList<Reservas>
) : RecyclerView.Adapter<ReservasRVAdapter.ReservasViewHolder>(){

    private var reservasList = emptyList<Reservas>()
    private val context: Context

    init {
        this.reservasList=reservasList
        this.context = context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservasRVAdapter.ReservasViewHolder {
        var itemView =LayoutInflater.from(context).inflate(R.layout.item_rv,parent,false)
        return ReservasViewHolder(itemView,context)
    }

    override fun getItemCount(): Int {
        return reservasList.size
    }

    override fun onBindViewHolder(
        holder: ReservasRVAdapter.ReservasViewHolder,
        position: Int)
    {
        val reservas: Reservas = reservasList[position]
        holder.binReserva(reservas)
    }
    class ReservasViewHolder(
        itemView: View, context: Context
    ): RecyclerView.ViewHolder(itemView){

        private var context:Context
        init{
            this.context=context
        }

        fun binReserva(reserva:Reservas){
            itemView.tv_cancha.text=reserva.cancha
            itemView.tv_estado.text=reserva.estado
            itemView.iv_estado.setImageResource(reserva.imagen)
            itemView.setOnClickListener{
                Toast.makeText(context,reserva.cancha,Toast.LENGTH_SHORT).show()
                var intent = Intent(context,ReservaDetalleActivity::class.java)
               intent.putExtra("reserva",reserva).addFlags(FLAG_ACTIVITY_NEW_TASK)

               context.startActivity(intent)
               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                //context.startActivity(intent)

            }
            itemView.setOnLongClickListener{
                Toast.makeText(context,reserva.cancha+"Long",Toast.LENGTH_SHORT).show()
                return@setOnLongClickListener true
            }




        }




    }



}