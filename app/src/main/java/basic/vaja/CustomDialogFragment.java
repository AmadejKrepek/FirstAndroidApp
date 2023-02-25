package basic.vaja;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

public class CustomDialogFragment extends DialogFragment {
    EditText name, surname, date, heartRate, SO2, temperature;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();
        name = (EditText) activity.findViewById(R.id.editIme);
        surname = (EditText) activity.findViewById(R.id.editPriimek);
        date = (EditText) activity.findViewById(R.id.in_date);
        heartRate = (EditText) activity.findViewById(R.id.editHeartRate);
        SO2 = (EditText) activity.findViewById(R.id.editBlood);
        temperature = (EditText) activity.findViewById(R.id.editTemperature);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to preview data?")
                .setPositiveButton("Preview", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent in = new Intent(activity.getApplicationContext(), SummaryActivity.class);
                        in.putExtra("name", name.getText().toString());
                        in.putExtra("surname", surname.getText().toString());
                        in.putExtra("dateOfBirth", date.getText().toString());
                        in.putExtra("heartRate", heartRate.getText().toString());
                        in.putExtra("SO2", SO2.getText().toString());
                        in.putExtra("temperature", temperature.getText().toString());

                        startActivity(in);
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Don't want to confirm data
                    }
                });
        return builder.create();
    }
}
