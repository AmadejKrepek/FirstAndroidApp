package basic.vaja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    TextView textPreviewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        textPreviewData = (TextView) findViewById(R.id.textPreviewData);

        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        String dateOfBirth = getIntent().getStringExtra("dateOfBirth");
        String heartRate = getIntent().getStringExtra("heartRate");
        String so2 = getIntent().getStringExtra("SO2");
        String temperature = getIntent().getStringExtra("temperature");
        String completeData = "Name: " + name + "\n" + "Surname: " + surname + "\n"
                + "Date Of Birth: " + dateOfBirth + "\n"
                + "Heart Rate: " + heartRate + "\n"
                + "SO2: " + so2 + "\n"
                + "Body Temperature: " + temperature;
        textPreviewData.setText(completeData);

        Toast.makeText(getApplicationContext(), "Saved to database!", Toast.LENGTH_SHORT).show();
    }
}