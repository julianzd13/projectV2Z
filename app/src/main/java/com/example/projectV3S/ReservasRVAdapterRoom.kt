package com.example.projectV3S

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectV3S.model.ReservasLocalRoom
import kotlinx.android.synthetic.main.item_rv.view.*
import java.util.ArrayList

class ReservasRVAdapterRoom(context: Context, reservasLocalRoomList: ArrayList<ReservasLocalRoom>
) : RecyclerView.Adapter<ReservasRVAdapterRoom.ReservasRoomViewHolder>() {

    private var reservasListRoom = emptyList<ReservasLocalRoom>()
    private val context : Context

    init {
        this.reservasListRoom = reservasLocalRoomList
        this.context = context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservasRVAdapterRoom.ReservasRoomViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_rv,parent,false)
        return ReservasRoomViewHolder(itemView,context)
    }

    override fun getItemCount(): Int {
        return reservasListRoom.size
    }

    override fun onBindViewHolder(
        holder: ReservasRVAdapterRoom.ReservasRoomViewHolder,
        position: Int
    ) {
        val reservasLocalRoom = reservasListRoom[position]
        holder.binReservaRoom(reservasLocalRoom)
    }

    class ReservasRoomViewHolder(
        itemView: View, context: Context
    ): RecyclerView.ViewHolder(itemView){

        private var context: Context
        init {
            this.context=context
        }

        fun binReservaRoom(reservaRoom: ReservasLocalRoom){

            itemView.tv_cancha.text = reservaRoom.escen
            itemView.tv_estado.text = reservaRoom.estado
            itemView.tv_fecha.text = reservaRoom.fecha
            itemView.tv_hora.text = reservaRoom.hora

            if(reservaRoom.estado == "Pendiente")itemView.iv_estado.setImageResource(R.drawable.pendiente)
            if(reservaRoom.estado == "Rechazado")itemView.iv_estado.setImageResource(R.drawable.rechazado)
            if(reservaRoom.estado == "Aceptado")itemView.iv_estado.setImageResource(R.drawable.aceptado)
            if(reservaRoom.estado == "Cancelado")itemView.iv_estado.setImageResource(R.drawable.cancelado)


            itemView.setOnClickListener {
                var intent = Intent(context,ReservaDetalleActivity::class.java)
                intent.putExtra("internet",1)
                intent.putExtra("reservaRoom", reservaRoom).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }


        }

    }

}