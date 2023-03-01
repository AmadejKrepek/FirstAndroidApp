package basic.vaja

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddUserActivity : AppCompatActivity() {
    private var nameEdt: EditText? = null
    private var surnameEdt: EditText? = null
    private var dateOfBirthEdt: EditText? = null
    private var heartRateEdt: EditText? = null
    private var SO2Edt: EditText? = null
    private var temperatureEdt: EditText? = null
    private var userBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        // initializing our variables for each view.
        nameEdt = findViewById(R.id.idEdtName)
        surnameEdt = findViewById(R.id.idEdtSurname)
        dateOfBirthEdt = findViewById(R.id.idEdtDateOfBirth)
        heartRateEdt = findViewById(R.id.idEdtHeartRate)
        SO2Edt = findViewById(R.id.idEdtSO2)
        temperatureEdt = findViewById(R.id.idEdtTemperature)
        userBtn = findViewById(R.id.idBtnSaveUser)

        // below line is to get intent as we
        // are getting data via an intent.
        val intent = intent
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            nameEdt?.setText(intent.getStringExtra(EXTRA_NAME))
            surnameEdt?.setText(intent.getStringExtra(EXTRA_SURNAME))
            dateOfBirthEdt?.setText(intent.getStringExtra(EXTRA_DATE_OF_BIRTH))
            heartRateEdt?.setText(intent.getStringExtra(EXTRA_HEART_RATE))
            SO2Edt?.setText(intent.getStringExtra(EXTRA_SO2))
            temperatureEdt?.setText(intent.getStringExtra(EXTRA_TEMPERATURE))
        }
        // adding on click listener for our save button.
        userBtn?.setOnClickListener(View.OnClickListener { // getting text value from edittext and validating if
            // the text fields are empty or not.
            val name = nameEdt?.getText().toString()
            val surname = surnameEdt?.getText().toString()
            val dateOfBirth = dateOfBirthEdt?.getText().toString()
            val heartRate = heartRateEdt?.getText().toString()
            val so2 = SO2Edt?.getText().toString()
            val bodyTemperature = temperatureEdt?.getText().toString()
            if (name.isEmpty() || surname.isEmpty() || dateOfBirth.isEmpty()) {
                Toast.makeText(
                    this@AddUserActivity,
                    "Please enter the valid user details.",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            // calling a method to save our user.
            saveUser(name, surname, dateOfBirth, heartRate, so2, bodyTemperature)
        })
    }

    private fun saveUser(
        name: String, surname: String, dateOfBirth: String,
        heartRate: String, so2: String, bodyTemperature: String
    ) {
        // inside this method we are passing
        // all the data via an intent.
        val data = Intent()

        // in below line we are passing all our user detail.
        data.putExtra(EXTRA_NAME, name)
        data.putExtra(EXTRA_SURNAME, surname)
        data.putExtra(EXTRA_DATE_OF_BIRTH, dateOfBirth)
        data.putExtra(EXTRA_HEART_RATE, heartRate)
        data.putExtra(EXTRA_SO2, so2)
        data.putExtra(EXTRA_TEMPERATURE, bodyTemperature)
        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id)
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data)

        // displaying a toast message after adding the data
        Toast.makeText(this, "user has been saved to Room Database. ", Toast.LENGTH_SHORT).show()
    }

    companion object {
        // creating a constant string variable for our
        // user name, surname and dateOfBirth.
        const val EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID"
        const val EXTRA_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_NAME"
        const val EXTRA_SURNAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_user_SURNAME"
        const val EXTRA_DATE_OF_BIRTH =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_user_DATE_OF_BIRTH"
        const val EXTRA_HEART_RATE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_user_HEART_RATE"
        const val EXTRA_SO2 = "com.gtappdevelopers.gfgroomdatabase.EXTRA_user_SO2"
        const val EXTRA_TEMPERATURE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_user_TEMPERATURE"
    }
}