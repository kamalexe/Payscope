package pay.scope.payscope.Fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import static pay.scope.payscope.Activity.LoginActivity3.KEY_USER_TOKEN;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.lang.reflect.Field;
import java.util.List;

import pay.scope.payscope.Activity.DownloadStatementActivity;
import pay.scope.payscope.Activity.FiltersActivity;
import pay.scope.payscope.Adapter.TransactionAdapter;

import pay.scope.payscope.ApiService.LoginService;

import pay.scope.payscope.Model.TransactionModel;
import pay.scope.payscope.R;
import pay.scope.payscope.RetrofitClient.RetrofitLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionFragment extends Fragment {
    RecyclerView trans_recycler;
    SearchView TransactionFrag_SearchView;
    ImageView TransactionFragMic;
    MaterialCardView TransactionFrag_Filter, TransactionFrag_DownloadStatement;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
    public static SharedPreferences preferences;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    private String ACCESS_TOKEN;
    LoginService apiService;
    TransactionAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        TransactionFrag_SearchView = view.findViewById(R.id.TransactionFrag_SearchView);
        TransactionFrag_Filter = view.findViewById(R.id.TransactionFrag_Filter);
        TransactionFragMic = view.findViewById(R.id.TransactionFragMic);
        TransactionFrag_DownloadStatement = view.findViewById(R.id.TransactionFrag_DownloadStatement);
        trans_recycler = view.findViewById(R.id.trans_recycler);

        preferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        ACCESS_TOKEN = preferences.getString(KEY_USER_TOKEN, "");
        Log.d("ApiCall", "token: " + ACCESS_TOKEN);
        FragmentTransaction();

        TransactionFrag_Filter.setOnClickListener(v -> startActivity(new Intent(getActivity(), FiltersActivity.class)));

        TransactionFrag_DownloadStatement.setOnClickListener(v -> startActivity(new Intent(getActivity(), DownloadStatementActivity.class)));

        customizeSearchView(TransactionFrag_SearchView);

        TransactionFragMic.setOnClickListener(v -> {
            if (checkPermission()) {
                displaySpeechRecognizer();
            } else {
                requestPermission();
            }
        });


        return view;
    }


    private void customizeSearchView(SearchView searchView) {
        try {
            Field queryTextViewField = SearchView.class.getDeclaredField("mSearchSrcTextView");
            queryTextViewField.setAccessible(true);
            TextView queryTextView = (TextView) queryTextViewField.get(searchView);
            assert queryTextView != null;
            queryTextView.setTextSize(12);
            queryTextView.setHintTextColor(ContextCompat.getColor(requireActivity(), R.color.hint));
            queryTextView.setTextSize(14);
            queryTextView.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Set listener for search view text changes
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // This is called when the user presses the submit button
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // This is called when the user is typing
                if (adapter != null) {
                    adapter.filter(newText); // Apply the filter in the adapter
                }
                return false;
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displaySpeechRecognizer();
            }
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions((Activity) requireContext(), new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION_CODE);
    }

    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now");
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPEECH_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (results != null && !results.isEmpty()) {
                    // Or clear the searchView and set the query
                    TransactionFrag_SearchView.setQuery(results.get(0), false);
                }
            }
        }
    }

    private void FragmentTransaction() {
        String BASE_URL = "https://stage.payscope.in";
        apiService = RetrofitLogin.getClient(BASE_URL).create(LoginService.class);
        Call<TransactionModel> call = apiService.historyModel("Bearer " + ACCESS_TOKEN);

        call.enqueue(new Callback<TransactionModel>() {
            @Override
            public void onResponse(@NonNull Call<TransactionModel> call, @NonNull Response<TransactionModel> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<TransactionModel.Data> transactionModel = response.body().getData();

                    Log.d("ApiCall", "isSuccessful: " + response.isSuccessful());
                    Log.d("ApiCall", "body: " + response.body());

                    for (TransactionModel.Data data : transactionModel) {
                        Log.d("ApiCall", "id: " + data.getId());
                    }

                    trans_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    adapter = new TransactionAdapter(requireActivity(), transactionModel);
                    trans_recycler.setAdapter(adapter);

                    Log.d("ApiCall", "Response  successful");
                } else {
                    Log.d("ApiCall", "Response not successful");
                }


            }

            @Override
            public void onFailure(@NonNull Call<TransactionModel> call, @NonNull Throwable t) {
                Log.e("ApiCall", "Error: " + t.getMessage());
            }
        });
    }
}




/*
public class TransactionFragment extends Fragment {
    RecyclerView trans_recycler;
    SearchView TransactionFrag_SearchView;
    ImageView TransactionFragMic;
    MaterialCardView TransactionFrag_Filter, TransactionFrag_DownloadStatement;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
    public static SharedPreferences preferences;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    private String ACCESS_TOKEN;
    LoginService apiService;
    TransactionAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        TransactionFrag_SearchView = view.findViewById(R.id.TransactionFrag_SearchView);
        TransactionFrag_Filter = view.findViewById(R.id.TransactionFrag_Filter);
        TransactionFragMic = view.findViewById(R.id.TransactionFragMic);
        TransactionFrag_DownloadStatement = view.findViewById(R.id.TransactionFrag_DownloadStatement);
        trans_recycler = view.findViewById(R.id.trans_recycler);

        preferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        ACCESS_TOKEN = preferences.getString(KEY_USER_TOKEN, "");
        Log.d("ApiCall", "token: " + ACCESS_TOKEN);
        FragmentTransaction();

        TransactionFrag_Filter.setOnClickListener(v -> startActivity(new Intent(getActivity(), FiltersActivity.class)));

        TransactionFrag_DownloadStatement.setOnClickListener(v -> startActivity(new Intent(getActivity(), DownloadStatementActivity.class)));

        customizeSearchView(TransactionFrag_SearchView);

        TransactionFragMic.setOnClickListener(v -> {
            if (checkPermission()) {
                displaySpeechRecognizer();
            } else {
                requestPermission();
            }
        });


        return view;
    }


    private void customizeSearchView(SearchView searchView) {
        try {
            Field queryTextViewField = SearchView.class.getDeclaredField("mSearchSrcTextView");
            queryTextViewField.setAccessible(true);
            TextView queryTextView = (TextView) queryTextViewField.get(searchView);
            assert queryTextView != null;
            queryTextView.setTextSize(12);
            queryTextView.setHintTextColor(ContextCompat.getColor(requireActivity(), R.color.hint));
            queryTextView.setTextSize(14);
            queryTextView.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Set listener for search view text changes
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // This is called when the user presses the submit button
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // This is called when the user is typing
                if (adapter != null) {
                    adapter.filter(newText); // Apply the filter in the adapter
                }
                return false;
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displaySpeechRecognizer();
            }
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions((Activity) requireContext(), new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION_CODE);
    }

    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now");
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPEECH_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (results != null && !results.isEmpty()) {
                    // Or clear the searchView and set the query
                    TransactionFrag_SearchView.setQuery(results.get(0), false);
                }
            }
        }
    }

    private void FragmentTransaction() {
        String BASE_URL = "https://stage.payscope.in";
        apiService = RetrofitLogin.getClient(BASE_URL).create(LoginService.class);
        Call<TransactionModel> call = apiService.historyModel("Bearer " + ACCESS_TOKEN);

        call.enqueue(new Callback<TransactionModel>() {
            @Override
            public void onResponse(@NonNull Call<TransactionModel> call, @NonNull Response<TransactionModel> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<TransactionModel.Data> transactionModel = response.body().getData();

                    Log.d("ApiCall", "isSuccessful: " + response.isSuccessful());
                    Log.d("ApiCall", "body: " + response.body());

                    for (TransactionModel.Data data : transactionModel) {
                        Log.d("ApiCall", "id: " + data.getId());
                    }

                    trans_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    adapter = new TransactionAdapter(requireActivity(), transactionModel);
                    trans_recycler.setAdapter(adapter);

                    Log.d("ApiCall", "Response  successful");
                } else {
                    Log.d("ApiCall", "Response not successful");
                }


            }

            @Override
            public void onFailure(@NonNull Call<TransactionModel> call, @NonNull Throwable t) {
                Log.e("ApiCall", "Error: " + t.getMessage());
            }
        });
    }
}
 */