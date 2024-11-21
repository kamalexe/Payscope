package pay.scope.payscope.Fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.speech.RecognizerIntent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import pay.scope.payscope.Activity.ChoosePlanPostpaidActivity;
import pay.scope.payscope.Adapter.ContactPostpaidAdapter;
import pay.scope.payscope.Model.ContactPostpaidModel;
import pay.scope.payscope.R;


public class PostpaidRechargeFragment extends Fragment {
    Button RechargePostpaidBtn;
    private LinearLayout RechargePostpaidViewContact;
    private ListView RechargePostpaidListView;
    private SearchView RechargeSearchViewPostpaid;
    ImageView RechargePostpaidMic;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private List<ContactPostpaidModel> contactPostpaidModelList;
    private List<ContactPostpaidModel> filteredContactPostpaidModelList;
    private ContactPostpaidAdapter postpaidAdapter;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
    SharedPreferences sharedPreferences;
    private static final String KEY_FIRST_VISIT = "firstVisit";
    CircleImageView PostpaidUserContactImg;
    TextView PostpaidUserContactName, PostpaidUserContactNumber;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    private static ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_postpaid_recharge, container, false);

        RechargePostpaidBtn = view.findViewById(R.id.RechargePostpaidBtn);
        RechargePostpaidViewContact = view.findViewById(R.id.RechargePostpaidViewContact);
        RechargePostpaidListView = view.findViewById(R.id.RechargePostpaidListView);
        RechargeSearchViewPostpaid = view.findViewById(R.id.RechargeSearchViewPostpaid);
        RechargePostpaidMic = view.findViewById(R.id.RechargePostpaidMic);

        PostpaidUserContactImg = view.findViewById(R.id.PostpaidUserContactImg);
        PostpaidUserContactName = view.findViewById(R.id.PostpaidUserContactName);
        PostpaidUserContactNumber = view.findViewById(R.id.PostpaidUserContactNumber);

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        boolean isFirstVisit = sharedPreferences.getBoolean(KEY_FIRST_VISIT, true);


        progressDialog = new ProgressDialog(requireActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.dismiss();

        contactPostpaidModelList = new ArrayList<>();
        filteredContactPostpaidModelList = new ArrayList<>();
        postpaidAdapter = new ContactPostpaidAdapter(requireActivity(), filteredContactPostpaidModelList);
        RechargePostpaidListView.setAdapter(postpaidAdapter);

        customizeSearchView(RechargeSearchViewPostpaid);

        RechargePostpaidBtn.setOnClickListener(v -> startActivity(new Intent(requireActivity(), ChoosePlanPostpaidActivity.class)));

        RechargePostpaidViewContact.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                loadContacts();
                RechargePostpaidViewContact.setVisibility(View.GONE);
            }
        });

        RechargePostpaidMic.setOnClickListener(v -> {
            if (checkPermission()) {
                startSpeechRecognition();
            } else {
                requestRecordAudioPermission();
            }
        });

        RechargeSearchViewPostpaid.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterContacts(newText);
                return true;
            }
        });

        RechargePostpaidListView.setOnItemClickListener((parent, view1, position, id) -> {
            ContactPostpaidModel selectedContact = filteredContactPostpaidModelList.get(position);
            Intent intent = new Intent(requireActivity(), ChoosePlanPostpaidActivity.class);
            intent.putExtra("contactName", selectedContact.getPostpaidName());
            intent.putExtra("contactNumber", selectedContact.getPostpaidPhoneNumber());
            startActivity(intent);
        });

        if (isFirstVisit) {
            RechargePostpaidViewContact.setVisibility(View.VISIBLE);
            sharedPreferences.edit().putBoolean(KEY_FIRST_VISIT, false).apply();
        } else {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
            } else {
                RechargePostpaidViewContact.setVisibility(View.VISIBLE);
            }
        }

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
                RechargePostpaidViewContact.setVisibility(View.GONE);
            } else {
                RechargePostpaidViewContact.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startSpeechRecognition();
            }
        }
    }

    private void customizeSearchView(SearchView searchView) {
        try {
            Field queryTextViewField = SearchView.class.getDeclaredField("mSearchSrcTextView");
            queryTextViewField.setAccessible(true);
            TextView queryTextView = (TextView) queryTextViewField.get(searchView);

            if (queryTextView != null) {
                queryTextView.setTextSize(14);
                queryTextView.setHintTextColor(ContextCompat.getColor(requireActivity(), R.color.hint));
                queryTextView.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void filterContacts(String query) {
        filteredContactPostpaidModelList.clear();
        if (query.isEmpty()) {
            filteredContactPostpaidModelList.addAll(contactPostpaidModelList);
        } else {
            for (ContactPostpaidModel contact : contactPostpaidModelList) {
                if (contact.getPostpaidName().toLowerCase().contains(query.toLowerCase()) || contact.getPostpaidPhoneNumber().contains(query)) {
                    filteredContactPostpaidModelList.add(contact);
                }
            }
        }
        postpaidAdapter.notifyDataSetChanged();
        RechargePostpaidListView.setVisibility(View.VISIBLE);
    }

    @SuppressLint("StaticFieldLeak")
    private void loadContacts() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        progressDialog.show();

        new AsyncTask<Void, Void, List<ContactPostpaidModel>>() {
            @Override
            protected List<ContactPostpaidModel> doInBackground(Void... voids) {
                List<ContactPostpaidModel> contacts = new ArrayList<>();
                ContentResolver contentResolver = requireActivity().getContentResolver();
                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        @SuppressLint("Range") String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        @SuppressLint("Range") String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        @SuppressLint("Range") String imageUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));


                        Cursor phoneCursor = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{contactId},
                                null
                        );

                        if (phoneCursor != null) {
                            while (phoneCursor.moveToNext()) {
                                @SuppressLint("Range") String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                contacts.add(new ContactPostpaidModel(contactName, phoneNumber, imageUri));
                            }
                            phoneCursor.close();
                        }
                    }
                    cursor.close();
                }
                return contacts;
            }

            @Override
            protected void onPostExecute(List<ContactPostpaidModel> contacts) {
                super.onPostExecute(contacts);
                if (contacts == null || !isAdded()) {
                    return;
                }
                contactPostpaidModelList.clear();
                contactPostpaidModelList.addAll(contacts);
                filteredContactPostpaidModelList.clear();
                filteredContactPostpaidModelList.addAll(contactPostpaidModelList);
                postpaidAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }.execute();
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestRecordAudioPermission() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION_CODE);
    }

    private void startSpeechRecognition() {
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
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (results != null && !results.isEmpty()) {
                    RechargeSearchViewPostpaid.setQuery(results.get(0), false);
                }
            }
        }
    }
}




/*
public class PostpaidRechargeFragment extends Fragment {

    Button RechargePostpaidBtn;
    private LinearLayout PostpaidLoadingOverlay;
    private LinearLayout RechargePostpaidViewContact;
    private ListView RechargePostpaidListView;
    private SearchView RechargeSearchViewPostpaid;
    ImageView RechargePostpaidMic;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private List<ContactPostpaidModel> contactPostpaidModelList;
    private List<ContactPostpaidModel> filteredContactPostpaidModelList;
    private ContactPostpaidAdapter postpaidAdapter;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;

     SharedPreferences sharedPreferences;
    private static final String KEY_FIRST_VISIT = "firstVisit";
    CircleImageView PostpaidUserContactImg;
    TextView PostpaidUserContactName, PostpaidUserContactNumber;
    private static final String SHARED_PREF_NAME = "MyPrefs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_postpaid_recharge, container, false);

        RechargePostpaidBtn = view.findViewById(R.id.RechargePostpaidBtn);
        PostpaidLoadingOverlay = view.findViewById(R.id.PostpaidLoadingOverlay);
        RechargePostpaidViewContact = view.findViewById(R.id.RechargePostpaidViewContact);
        RechargePostpaidListView = view.findViewById(R.id.RechargePostpaidListView);
        RechargeSearchViewPostpaid = view.findViewById(R.id.RechargeSearchViewPostpaid);
        RechargePostpaidMic = view.findViewById(R.id.RechargePostpaidMic);

        PostpaidUserContactImg = view.findViewById(R.id.PostpaidUserContactImg);
        PostpaidUserContactName = view.findViewById(R.id.PostpaidUserContactName);
        PostpaidUserContactNumber = view.findViewById(R.id.PostpaidUserContactNumber);

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        boolean isFirstVisit = sharedPreferences.getBoolean(KEY_FIRST_VISIT, true);

        contactPostpaidModelList = new ArrayList<>();
        filteredContactPostpaidModelList = new ArrayList<>();
        postpaidAdapter = new ContactPostpaidAdapter(requireActivity(), filteredContactPostpaidModelList);
        RechargePostpaidListView.setAdapter(postpaidAdapter);

        customizeSearchView(RechargeSearchViewPostpaid);

        RechargePostpaidBtn.setOnClickListener(v -> startActivity(new Intent(requireActivity(), ChoosePlanPostpaidActivity.class)));

        RechargePostpaidViewContact.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                loadContacts();
                RechargePostpaidViewContact.setVisibility(View.GONE);
            }
        });

        RechargePostpaidMic.setOnClickListener(v -> {
            if (checkPermission()) {
                startSpeechRecognition();
            } else {
                requestRecordAudioPermission();
            }
        });

        RechargeSearchViewPostpaid.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterContacts(newText);
                return true;
            }
        });

        RechargePostpaidListView.setOnItemClickListener((parent, view1, position, id) -> {
            ContactPostpaidModel selectedContact = filteredContactPostpaidModelList.get(position);
            Intent intent = new Intent(requireActivity(), ChoosePlanPostpaidActivity.class);
            intent.putExtra("contactName", selectedContact.getPostpaidName());
            intent.putExtra("contactNumber", selectedContact.getPostpaidPhoneNumber());
            startActivity(intent);
        });

        if (isFirstVisit) {
            RechargePostpaidViewContact.setVisibility(View.VISIBLE);
            sharedPreferences.edit().putBoolean(KEY_FIRST_VISIT, false).apply();
        } else {
            RechargePostpaidViewContact.setVisibility(View.GONE);
            loadContacts();
        }

        return view;
    }


    private void customizeSearchView(SearchView searchView) {
        try {
            Field queryTextViewField = SearchView.class.getDeclaredField("mSearchSrcTextView");
            queryTextViewField.setAccessible(true);
            TextView queryTextView = (TextView) queryTextViewField.get(searchView);

            if (queryTextView != null) {
                queryTextView.setTextSize(14);
                queryTextView.setHintTextColor(ContextCompat.getColor(requireActivity(), R.color.hint));
                queryTextView.setTextSize(14);
                queryTextView.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void filterContacts(String query) {
        filteredContactPostpaidModelList.clear();
        if (query.isEmpty()) {
            filteredContactPostpaidModelList.addAll(contactPostpaidModelList);
        } else {
            for (ContactPostpaidModel contact : contactPostpaidModelList) {
                if (contact.getPostpaidName().toLowerCase().contains(query.toLowerCase()) || contact.getPostpaidPhoneNumber().contains(query)) {
                    filteredContactPostpaidModelList.add(contact);
                }
            }
        }
        postpaidAdapter.notifyDataSetChanged();
        RechargePostpaidListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
                RechargePostpaidViewContact.setVisibility(View.GONE);
            } else {
                RechargePostpaidViewContact.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startSpeechRecognition();
            }
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestRecordAudioPermission() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION_CODE);
    }

    private void startSpeechRecognition() {
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
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (results != null && !results.isEmpty()) {
                    RechargeSearchViewPostpaid.setQuery(results.get(0), false); // Update the search view with the recognized text
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void loadContacts() {
        PostpaidLoadingOverlay.setVisibility(View.VISIBLE);

        new AsyncTask<Void, Void, List<ContactPostpaidModel>>() {
            @Override
            protected List<ContactPostpaidModel> doInBackground(Void... voids) {
                if (!isAdded()) {
                    return null;
                }

                List<ContactPostpaidModel> contacts = new ArrayList<>();
                ContentResolver contentResolver = requireActivity().getContentResolver();
                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        @SuppressLint("Range") String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        @SuppressLint("Range") String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                        Cursor phoneCursor = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{contactId},
                                null
                        );

                        if (phoneCursor != null) {
                            while (phoneCursor.moveToNext()) {
                                @SuppressLint("Range") String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                contacts.add(new ContactPostpaidModel(contactName, phoneNumber));
                                Log.d("Contact", "Name: " + contactName + ", Phone: " + phoneNumber);
                            }
                            phoneCursor.close();
                        }
                    }
                    cursor.close();
                }
                return contacts;
            }

            @Override
            protected void onPostExecute(List<ContactPostpaidModel> contacts) {
                super.onPostExecute(contacts);
                if (contacts == null || !isAdded()) {
                    return;
                }
                contactPostpaidModelList.clear();
                contactPostpaidModelList.addAll(contacts);
                filteredContactPostpaidModelList.clear();
                filteredContactPostpaidModelList.addAll(contactPostpaidModelList);
                postpaidAdapter.notifyDataSetChanged();
                PostpaidLoadingOverlay.setVisibility(View.GONE);
            }
        }.execute();
    }
}

 */
