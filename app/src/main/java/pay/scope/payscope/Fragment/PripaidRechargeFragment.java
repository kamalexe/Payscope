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
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Activity.ChoosePlanActivity;
import pay.scope.payscope.Adapter.ContactAdapter;
import pay.scope.payscope.Model.ContactModel;
import pay.scope.payscope.R;

public class PripaidRechargeFragment extends Fragment {
    Button recharge;
    LinearLayout MobileRecharge_ViewContact;
    ListView MobileRechargeListView;
    SearchView MobileRechargeSearchView;
    ImageView MobileRechargeRechargeMic;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
    private static final int SPEECH_REQUEST_CODE = 0;
    private List<ContactModel> contactList;
    private List<ContactModel> filteredContactList;
    private ContactAdapter contactAdapter;
    SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_FIRST_VISIT = "firstVisit";
    private static ProgressDialog progressDialog;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pripaid_recharge, container, false);

        recharge = view.findViewById(R.id.recharge);
        MobileRecharge_ViewContact = view.findViewById(R.id.MobileRecharge_ViewContact);
        MobileRechargeListView = view.findViewById(R.id.MobileRechargeListView);
        MobileRechargeSearchView = view.findViewById(R.id.MobileRechargeSearchView);
        MobileRechargeRechargeMic = view.findViewById(R.id.MobileRechargeRechargeMic);

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isFirstVisit = sharedPreferences.getBoolean(KEY_FIRST_VISIT, true);

        progressDialog = new ProgressDialog(requireActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.dismiss();


        // Initialize contact list and adapter
        contactList = new ArrayList<>();
        filteredContactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(requireActivity(), filteredContactList);
        MobileRechargeListView.setAdapter(contactAdapter);

        // Customize SearchView
        customizeSearchView(MobileRechargeSearchView);

        // Set up onClick listeners
        recharge.setOnClickListener(v -> startActivity(new Intent(requireActivity(), ChoosePlanActivity.class)));

        MobileRecharge_ViewContact.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                loadContacts();
                MobileRecharge_ViewContact.setVisibility(View.GONE);
            }
        });

        MobileRechargeRechargeMic.setOnClickListener(v -> {
            if (checkPermission()) {
                displaySpeechRecognizer();
            } else {
                requestPermission();
            }
        });

        MobileRechargeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterContacts(newText);
                return false;
            }
        });

        MobileRechargeListView.setOnItemClickListener((parent, view1, position, id) -> {
            ContactModel selectedContact = filteredContactList.get(position);
            Intent intent = new Intent(requireActivity(), ChoosePlanActivity.class);
            intent.putExtra("contactName", selectedContact.getName());
            intent.putExtra("contactNumber", selectedContact.getPhoneNumber());
            startActivity(intent);
        });

        if (isFirstVisit) {
            MobileRecharge_ViewContact.setVisibility(View.VISIBLE);
            sharedPreferences.edit().putBoolean(KEY_FIRST_VISIT, false).apply();
        } else {
            MobileRecharge_ViewContact.setVisibility(View.GONE);
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
            } else {
                MobileRecharge_ViewContact.setVisibility(View.VISIBLE);
            }
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
                queryTextView.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void filterContacts(String query) {
        filteredContactList.clear();
        if (query.isEmpty()) {
            filteredContactList.addAll(contactList);
        } else {
            for (ContactModel contact : contactList) {
                if (contact.getName().toLowerCase().contains(query.toLowerCase()) || contact.getPhoneNumber().contains(query)) {
                    filteredContactList.add(contact);
                }
            }
        }
        contactAdapter.notifyDataSetChanged();
        MobileRechargeListView.setVisibility(View.VISIBLE);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION_CODE);
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
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (results != null && !results.isEmpty()) {
                MobileRechargeSearchView.setQuery(results.get(0), true);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
                MobileRecharge_ViewContact.setVisibility(View.GONE);
            } else {
                MobileRecharge_ViewContact.setVisibility(View.VISIBLE);
            }
        }

        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displaySpeechRecognizer();
            }
        }
    }

    private void loadContacts() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return; // Ensure permission is granted before loading contacts
        }

        new LoadContactsTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadContactsTask extends AsyncTask<Void, Void, List<ContactModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (!isAdded()) {
                cancel(true);
                return;
            }
            progressDialog.show();
        }

        @Override
        protected List<ContactModel> doInBackground(Void... voids) {
            if (!isAdded()) {
                return null;
            }

            List<ContactModel> contacts = new ArrayList<>();
            ContentResolver contentResolver = requireActivity().getContentResolver();

            try (Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)) {
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        @SuppressLint("Range") String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        @SuppressLint("Range") String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        @SuppressLint("Range") String imageUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));


                        try (Cursor phoneCursor = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{contactId},
                                null
                        )) {
                            if (phoneCursor != null) {
                                while (phoneCursor.moveToNext()) {
                                    @SuppressLint("Range") String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                    contacts.add(new ContactModel(contactName, phoneNumber, imageUri));
//                                    contacts.add(new ContactModel(contactName != null ? contactName : "Unknown", phoneNumber));
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("LoadContactsTask", "Error loading contacts", e);
            }
            return contacts;
        }

        @Override
        protected void onPostExecute(List<ContactModel> contacts) {
            super.onPostExecute(contacts);
            if (!isAdded() || contacts == null) {
                return;
            }
            contactList.clear();
            contactList.addAll(contacts);
            filteredContactList.clear();
            filteredContactList.addAll(contactList);
            contactAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
        }
    }
}


