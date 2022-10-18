package com.example.laboratorio03

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class Check : AppCompatActivity(), View.OnClickListener {
    var layout: ViewGroup? = null
    var seats = ("_______/"
            + "_UUUU_AAAA/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA/"
            + "_______/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA"
            + "__________/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA/"
            + "_AAAA_AAAA"
            + "__________/")
    var seatViewList: MutableList<TextView> = ArrayList()
    var seatSize = 100
    var seatGaping = 10
    var STATUS_AVAILABLE = 1
    var STATUS_BOOKED = 2
    var STATUS_RESERVED = 3
    var selectedIds = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        layout = findViewById(R.id.layoutSeat)
        Log.i("Siii","si entró")
        seats = "/$seats"
        val layoutSeat = LinearLayout(this)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutSeat.orientation = LinearLayout.VERTICAL
        layoutSeat.layoutParams = params
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping)
        layout?.addView(layoutSeat)
        var layout: LinearLayout? = null
        var count = 0
        for (index in 0 until seats.length) {
            if (seats[index] == '/') {
                layout = LinearLayout(this)
                layout.orientation = LinearLayout.HORIZONTAL
                layoutSeat.addView(layout)
            } else if (seats[index] == 'U') {
                count++
                val view = TextView(this)
                val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                view.layoutParams = layoutParams
                view.setPadding(0, 0, 0, 2 * seatGaping)
                view.id = count
                view.gravity = Gravity.CENTER
                view.setBackgroundResource(R.drawable.ic_seats_booked)
                view.setTextColor(Color.WHITE)
                view.tag = STATUS_BOOKED
                view.text = count.toString() + ""
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9f)
                layout!!.addView(view)
                seatViewList.add(view)
                view.setOnClickListener(this)
            } else if (seats[index] == 'A') {
                count++
                val view = TextView(this)
                val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                view.layoutParams = layoutParams
                view.setPadding(0, 0, 0, 2 * seatGaping)
                view.id = count
                view.gravity = Gravity.CENTER
                view.setBackgroundResource(R.drawable.ic_seats_book)
                view.text = count.toString() + ""
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9f)
                view.setTextColor(Color.BLACK)
                view.tag = STATUS_AVAILABLE
                layout!!.addView(view)
                seatViewList.add(view)
                view.setOnClickListener(this)
            } else if (seats[index] == 'R') {
                count++
                val view = TextView(this)
                val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                view.layoutParams = layoutParams
                view.setPadding(0, 0, 0, 2 * seatGaping)
                view.id = count
                view.gravity = Gravity.CENTER
                view.setBackgroundResource(R.drawable.ic_seats_reserved)
                view.text = count.toString() + ""
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9f)
                view.setTextColor(Color.WHITE)
                view.tag = STATUS_RESERVED
                layout!!.addView(view)
                seatViewList.add(view)
                view.setOnClickListener(this)
            } else if (seats[index] == '_') {
                val view = TextView(this)
                val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                view.layoutParams = layoutParams
                view.setBackgroundColor(Color.TRANSPARENT)
                view.text = ""
                layout!!.addView(view)
            }
        }
    }

    override fun onClick(view: View) {
        if (view.tag as Int == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.id.toString() + ",")) {
                selectedIds = selectedIds.replace(view.id.toString() + ",", "")
                view.setBackgroundResource(R.drawable.ic_seats_book)
            } else {
                selectedIds = selectedIds + view.id + ","
                view.setBackgroundResource(R.drawable.ic_seats_selected)
            }
        } else if (view.tag as Int == STATUS_BOOKED) {
            Toast.makeText(this, "Seat " + view.id + " is Booked", Toast.LENGTH_SHORT).show()
        } else if (view.tag as Int == STATUS_RESERVED) {
            Toast.makeText(this, "Seat " + view.id + " is Reserved", Toast.LENGTH_SHORT).show()
        }
    }
}
