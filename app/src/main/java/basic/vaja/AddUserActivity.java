package basic.vaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {

    private EditText nameEdt, surnameEdt, dateOfBirthEdt;
    private Button userBtn;

    // creating a constant string variable for our
    // course name, surname and dateOfBirth.
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_NAME";
    public static final String EXTRA_SURNAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DESCRIPTION";
    public static final String EXTRA_DATE_OF_BIRTH = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DURATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        // initializing our variables for each view.
        nameEdt = findViewById(R.id.idEdtName);
        surnameEdt = findViewById(R.id.idEdtSurname);
        dateOfBirthEdt = findViewById(R.id.idEdtDateOfBirth);
        userBtn = findViewById(R.id.idBtnSaveUser);

        // below line is to get intent as we
        // are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            nameEdt.setText(intent.getStringExtra(EXTRA_NAME));
            surnameEdt.setText(intent.getStringExtra(EXTRA_SURNAME));
            dateOfBirthEdt.setText(intent.getStringExtra(EXTRA_DATE_OF_BIRTH));
        }
        // adding on click listener for our save button.
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text value from edittext and validating if
                // the text fields are empty or not.
                String name = nameEdt.getText().toString();
                String surname = surnameEdt.getText().toString();
                String dateOfBirth = dateOfBirthEdt.getText().toString();
                if (name.isEmpty() || surname.isEmpty() || dateOfBirth.isEmpty()) {
                    Toast.makeText(AddUserActivity.this, "Please enter the valid course details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to save our course.
                saveUser(name, surname, dateOfBirth);
            }
        });
    }

    private void saveUser(String name, String surname, String dateOfBirth) {
        // inside this method we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_SURNAME, surname);
        data.putExtra(EXTRA_DATE_OF_BIRTH, dateOfBirth);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data
        Toast.makeText(this, "Course has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }
}