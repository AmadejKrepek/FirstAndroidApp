package basic.vaja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SummaryActivity extends AppCompatActivity {
    private ViewModal viewModal;
    TextView textPreviewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // passing a data from view modal.
        viewModal = ViewModelProviders.of(this).get(ViewModal.class);

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

        UserEntity model = new UserEntity(name, surname, dateOfBirth, heartRate, so2, temperature);
        viewModal.insert(model);
        List<UserEntity> users = viewModal.getUsers();
        UserEntity user = users.stream()
                .filter(u -> u.getName().equals(model.getName()) &&
                        u.getSurname().equals(model.getSurname()) &&
                        u.getHeartRate().equals(model.getHeartRate()) &&
                        u.getSo2().equals(model.getSo2()) &&
                        u.getBodyTemperature().equals(model.getBodyTemperature()))
                .findAny()
                .orElse(null);
        if (user == null) {
            Toast.makeText(getApplicationContext(), "Failed saving to database!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Saved to database!", Toast.LENGTH_SHORT).show();
        }
    }
}