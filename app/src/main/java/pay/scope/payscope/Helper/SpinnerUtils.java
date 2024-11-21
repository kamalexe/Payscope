package pay.scope.payscope.Helper;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import pay.scope.payscope.Model.SelectedBankModel;

public class SpinnerUtils {

    public static ArrayAdapter<String> createCustomAdapter(Activity activity, List<String> items) {
        return new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, items) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(13);
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                view.setBackgroundColor(Color.WHITE);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(13);
                return view;
            }
        };
    }

    public static void setUpSpinner(Activity activity, Spinner spinner, List<String> items) {
        ArrayAdapter<String> adapter = createCustomAdapter(activity, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}

