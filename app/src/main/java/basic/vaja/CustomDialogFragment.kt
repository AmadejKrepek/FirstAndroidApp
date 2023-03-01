package basic.vaja

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class CustomDialogFragment : DialogFragment() {
    var name: EditText? = null
    var surname: EditText? = null
    var date: EditText? = null
    var heartRate: EditText? = null
    var SO2: EditText? = null
    var temperature: EditText? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity
        name = activity!!.findViewById<View>(R.id.editIme) as EditText
        surname = activity.findViewById<View>(R.id.editPriimek) as EditText
        date = activity.findViewById<View>(R.id.in_date) as EditText
        heartRate = activity.findViewById<View>(R.id.editHeartRate) as EditText
        SO2 = activity.findViewById<View>(R.id.editBlood) as EditText
        temperature = activity.findViewById<View>(R.id.editTemperature) as EditText
        val builder = AlertDialog.Builder(getActivity())
        builder.setMessage("Do you want to save your record?")
            .setPositiveButton("Save and Preview") { dialogInterface, i ->
                val `in` = Intent(activity.applicationContext, SummaryActivity::class.java)
                `in`.putExtra("name", name!!.text.toString())
                `in`.putExtra("surname", surname!!.text.toString())
                `in`.putExtra("dateOfBirth", date!!.text.toString())
                `in`.putExtra("heartRate", heartRate!!.text.toString())
                `in`.putExtra("SO2", SO2!!.text.toString())
                `in`.putExtra("temperature", temperature!!.text.toString())
                startActivity(`in`)
            }
            .setNegativeButton("Close") { dialogInterface, i ->
                // Don't want to confirm data
            }
        return builder.create()
    }
}