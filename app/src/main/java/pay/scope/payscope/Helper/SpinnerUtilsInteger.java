package pay.scope.payscope.Helper;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class SpinnerUtilsInteger {
    public static ArrayAdapter<Integer> createCustomAdapter(Activity activity, List<Integer> items) {
        return new ArrayAdapter<Integer>(activity, android.R.layout.simple_spinner_item, items) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(13);
                textView.setText(String.valueOf(getItem(position))); // Convert Integer to String
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                view.setBackgroundColor(Color.WHITE);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(13);
                textView.setText(String.valueOf(getItem(position))); // Convert Integer to String
                return view;
            }
        };
    }

    public static void setUpSpinner(Activity activity, Spinner spinner, List<Integer> items) {
        ArrayAdapter<Integer> adapter = createCustomAdapter(activity, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


}
