package pay.scope.payscope.Helper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> items;

    public CustomSpinnerAdapter(Context context, List<String> items) {
        super(context, android.R.layout.simple_spinner_item, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(items.get(position));
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(13); // Customize text size here
        return convertView;
    }

    @NonNull
    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        convertView.setBackgroundColor(Color.WHITE);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(items.get(position));
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(13); // Customize text size here
        return convertView;
    }
}
