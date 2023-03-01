package basic.vaja

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import basic.vaja.EditActivity
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var saveBtn: Button? = null
    var editBtn: Button? = null
    var btnDatePicker: Button? = null
    var txtDate: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        saveBtn = findViewById<View>(R.id.btnSend) as Button
        editBtn = findViewById<View>(R.id.btnEdit) as Button
        btnDatePicker = findViewById<View>(R.id.btn_date) as Button
        txtDate = findViewById<View>(R.id.in_date) as EditText
        saveBtn!!.setOnClickListener {
            val fragment = CustomDialogFragment()
            fragment.show(supportFragmentManager, "custom_dialog")
        }
        editBtn!!.setOnClickListener { view ->
            val i = Intent(view.context, EditActivity::class.java)
            startActivity(i)
        }
        btnDatePicker!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v === btnDatePicker) {
            // Get current date
            val c = Calendar.getInstance()
            val mYear = c[Calendar.YEAR]
            val mMonth = c[Calendar.MONTH]
            val mDay = c[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(this,
                { datePicker, year, monthOfYear, dayOfMonth -> txtDate!!.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }
    }
}