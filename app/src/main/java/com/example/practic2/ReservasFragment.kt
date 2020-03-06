package com.example.practic2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_reservas.view.*

class ReservasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_reservas,
            container,
            false)

        var reservasList: MutableList<Reservas> = ArrayList()

        reservasList.add(
            Reservas(context!!.getString(R.string.reserva1),
                "Aceptado",
                "15/04/2020",
                "14:00-15:00",

                        R.drawable.aceptado
            )

        )

        reservasList.add(
            Reservas(context!!.getString(R.string.reserva2),
                "Rechazado",
                "11/05/2020",
                "10:00-11:00",
                R.drawable.rechazado
            )

        )

        reservasList.add(
            Reservas(context!!.getString(R.string.reserva3),
                "Pendiente",
                "16/07/2020",
                "12:00-13:00",
                R.drawable.pendiente
            )

        )

        reservasList.add(
                Reservas(context!!.getString(R.string.reserva4),
                    "Cancelado",
                    "19/07/2020",
                    "7:00-8:00",
                    R.drawable.cancelado
                )

                )
        reservasList.add(
            Reservas(context!!.getString(R.string.reserva1),
                "Aceptado",
                "19/07/2020",
                "7:00-8:00",
                R.drawable.aceptado
            )

        )

        view.rv_reservas.setHasFixedSize(true)
        view.rv_reservas.layoutManager = LinearLayoutManager(
         activity?.applicationContext,
            RecyclerView.VERTICAL,
            false
        )

        val reservasRVAdapter = ReservasRVAdapter(
            activity!!.applicationContext,
            reservasList as ArrayList
        )

        view.rv_reservas.adapter = reservasRVAdapter


        return view
    }

}