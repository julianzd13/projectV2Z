package com.example.projectV3S

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projectV3S.model.ReservasLocal
import kotlinx.android.synthetic.main.item_rv.view.*

class ReservasRVAdapter(
    context: Context, reservasLocalList: ArrayList<ReservasLocal>
) : RecyclerView.Adapter<ReservasRVAdapter.ReservasViewHolder>(){

    private var reservasList = emptyList<ReservasLocal>()
    private val context: Context

    init {
        this.reservasList=reservasLocalList
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
        val reservasLocal: ReservasLocal = reservasList[position]
        holder.binReserva(reservasLocal)
    }
    class ReservasViewHolder(
        itemView: View, context: Context
    ): RecyclerView.ViewHolder(itemView){

        private var context:Context
        init{
            this.context=context
        }

        fun binReserva(reserva: ReservasLocal){
            itemView.tv_cancha.text=reserva.cancha
            itemView.tv_estado.text=reserva.estado
            itemView.tv_fecha.text=reserva.fecha
            itemView.tv_hora.text=reserva.hora

            if(reserva.estado == "Pendiente")itemView.iv_estado.setImageResource(R.drawable.pendiente)
            if(reserva.estado == "Rechazado")itemView.iv_estado.setImageResource(R.drawable.rechazado)
            if(reserva.estado == "Aceptado")itemView.iv_estado.setImageResource(R.drawable.aceptado)
            if(reserva.estado == "Cancelado")itemView.iv_estado.setImageResource(R.drawable.cancelado)


            //itemView.iv_estado.setImageResource(reserva.imagen)

            itemView.setOnClickListener{
                Toast.makeText(context,reserva.cancha,Toast.LENGTH_SHORT).show()
                var intent = Intent(context,ReservaDetalleActivity::class.java)
                intent.putExtra("internet",0)
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