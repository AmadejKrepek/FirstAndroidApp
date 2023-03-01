package basic.vaja

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class SummaryActivity : AppCompatActivity() {
    private var viewModal: ViewModal? = null
    var textPreviewData: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        // passing a data from view modal.
        viewModal = ViewModelProviders.of(this).get(ViewModal::class.java)
        textPreviewData = findViewById<View>(R.id.textPreviewData) as TextView
        val name = intent.getStringExtra("name")
        val surname = intent.getStringExtra("surname")
        val dateOfBirth = intent.getStringExtra("dateOfBirth")
        val heartRate = intent.getStringExtra("heartRate")
        val so2 = intent.getStringExtra("SO2")
        val temperature = intent.getStringExtra("temperature")
        val completeData = """
            Name: $name
            Surname: $surname
            Date Of Birth: $dateOfBirth
            Heart Rate: $heartRate
            SO2: $so2
            Body Temperature: $temperature
            """.trimIndent()
        textPreviewData!!.text = completeData
        val model = UserEntity(name, surname, dateOfBirth, heartRate, so2, temperature)
        viewModal!!.insert(model)
        val users = viewModal!!.users
        val user = users.stream()
            .filter { u: UserEntity? -> u!!.name == model.name && u.surname == model.surname && u.heartRate == model.heartRate && u.so2 == model.so2 && u.bodyTemperature == model.bodyTemperature }
            .findAny()
            .orElse(null)
        if (user == null) {
            Toast.makeText(applicationContext, "Failed saving to database!", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(applicationContext, "Saved to database!", Toast.LENGTH_SHORT).show()
        }
    }
}