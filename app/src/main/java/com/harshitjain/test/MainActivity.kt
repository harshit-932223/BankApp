package com.harshitjain.test

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    val TAG = "Fi test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dateSeleced = false

        pan_number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    Log.d(TAG, "onTextChanged: p0.length=" + p0.length)
                    if (p0.length == 10) {
                        if (dateSeleced) {
                            nxt_btn.isEnabled = true
                        } else {
                            birth_date.callOnClick()
                        }
                        val inputMethodManager = this@MainActivity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(this@MainActivity.currentFocus
                            ?.windowToken, 0)
                    }
                }
            }
        })

        val myCalendar = Calendar.getInstance()
        val date =
            OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                var sdf = SimpleDateFormat("dd/MM/yy")
                birth_date.setText(sdf.format(myCalendar.time))
                dateSeleced=true
                if(pan_number.text.length==10){
                    nxt_btn.isEnabled = true
                }
            }

        birth_date.setOnClickListener {
           DatePickerDialog(this, date,myCalendar.get(Calendar.YEAR),
               myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DATE)).show()
        }

        disclaimer.movementMethod = LinkMovementMethod.getInstance()

        nxt_btn.isEnabled = false
        nxt_btn.setOnClickListener {
            Toast.makeText(applicationContext, "Details Submitted successfully", Toast.LENGTH_LONG).show()
            finish()
        }

        no_pan.setOnClickListener {
            finish()
        }
    }
}