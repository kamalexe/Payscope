package pay.scope.payscope.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Locale;

import pay.scope.payscope.R;

public class LanguageChangeActivity extends AppCompatActivity {

    ListView listview;
    ArrayList<String> arrayList = new ArrayList<>();
    MaterialToolbar my_language_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change);

        listview = findViewById(R.id.listview);
        my_language_toolbar = findViewById(R.id.my_language_toolbar);

        my_language_toolbar.setNavigationOnClickListener(v -> finish());

        arrayList.add("English");
        arrayList.add("हिंदी");
        arrayList.add("मराठी");
        arrayList.add("বাংলা");
        arrayList.add("Français");
        arrayList.add("اردو");
        arrayList.add("ગુજરાતી");
        arrayList.add("italiana");
        arrayList.add("日本語");
        arrayList.add("తెలుగు");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.language_list_item, R.id.text_view, arrayList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener((adapterView, view, position, id) -> {
            String selectedLanguage = arrayList.get(position);
            switch (selectedLanguage) {
                case "English":
                    setLocale("en");
                    break;
                case "हिंदी":
                    setLocale("hi");
                    break;
                case "मराठी":
                    setLocale("mr");
                    break;
                case "বাংলা":
                    setLocale("bn-rIN");
                    break;
                case "Français":
                    setLocale("fr");
                    break;
                case "اردو":
                    setLocale("ur-rIN");
                    break;
                case "ગુજરાતી":
                    setLocale("gu");
                    break;
                case "italiana":
                    setLocale("it");
                    break;
                case "日本語":
                    setLocale("ja");
                    break;
                case "తెలుగు":
                    setLocale("te");
                    break;
            }
            startActivity(new Intent(LanguageChangeActivity.this, MainActivity.class));
        });
    }

    private void setLocale(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        recreate();
    }
}
