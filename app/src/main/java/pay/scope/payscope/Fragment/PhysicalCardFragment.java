package pay.scope.payscope.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.MyCartAdapter;
import pay.scope.payscope.Model.MyCartModel;
import pay.scope.payscope.R;


public class PhysicalCardFragment extends Fragment {
    RecyclerView myCartrecycler;
    int selectedCard = -1;
    Spinner MyCartSpinner;
    String[] CardString = {"Sort By Rent"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_physical_card, container, false);

        myCartrecycler = view.findViewById(R.id.myCartrecycler);
        MyCartSpinner = view.findViewById(R.id.MyCartSpinner);


        selectedCard = 0;

        List<MyCartModel> myCartModelList = new ArrayList<>();
        myCartModelList.add(new MyCartModel("Deniel Alexander", "Send", "Today", 123.45, R.drawable.logo));
        myCartModelList.add(new MyCartModel("Deniel Alexander", "Received", "Today", 123.45, R.drawable.logo));
        myCartModelList.add(new MyCartModel("Deniel Alexander", "Send", "Today", 123.45, R.drawable.logo));
        myCartModelList.add(new MyCartModel("Deniel Alexander", "Received", "Today", 123.45, R.drawable.logo));
        myCartModelList.add(new MyCartModel("Deniel Alexander", "Send", "Today", 123.45, R.drawable.logo));
        myCartModelList.add(new MyCartModel("Deniel Alexander", "Received", "Today", 123.45, R.drawable.logo));

        // Set up RecyclerView
        myCartrecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        MyCartAdapter adapter = new MyCartAdapter(getContext(), myCartModelList);
        myCartrecycler.setAdapter(adapter);

        ArrayAdapter<String> ad = getArrayAdapter();
        MyCartSpinner.setAdapter(ad);


        return view;
    }

    @NonNull
    private ArrayAdapter<String> getArrayAdapter() {
        ArrayAdapter<String> ad = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, CardString) {
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
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return ad;
    }


}