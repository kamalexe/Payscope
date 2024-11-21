package pay.scope.payscope.Fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static pay.scope.payscope.Activity.LoginActivity3.KEY_USER_TOKEN;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pay.scope.payscope.ApiService.LoginService;
import pay.scope.payscope.Helper.CustomSpinnerAdapter;

import pay.scope.payscope.Model.ManualRequest;
import pay.scope.payscope.Model.ManualRequestResponse;
import pay.scope.payscope.Model.PaymentModesModel;
import pay.scope.payscope.Model.SelectedBankModel;
import pay.scope.payscope.R;
import pay.scope.payscope.RetrofitClient.RetrofitLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ManualRequestFragment extends Fragment {
    private Spinner mr_selectBank, mr_paymentMode;
    private TextInputEditText mr_amount, mr_date, mr_referenceNo, mr_remark;
    LinearLayout mr_selectImg;
    private ImageView mr_Img;
    private TextView Choose_file;
    private LoginService apiService;
    Retrofit retrofit;
    Button mr_button;
    private static final int SELECT_PICTURE = 1;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    private static String ACCESS_TOKEN;
    private static int selectedBank;
    private static int paymentModesId;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manual_request, container, false);

        // Initialize views
        mr_selectBank = view.findViewById(R.id.manualRequest_selectBank);
        mr_paymentMode = view.findViewById(R.id.manualRequest_paymentMode);
        mr_amount = view.findViewById(R.id.manualRequest_amount);
        mr_date = view.findViewById(R.id.manualRequest_date);
        mr_referenceNo = view.findViewById(R.id.manualRequest_referenceNo);
        mr_remark = view.findViewById(R.id.manualRequest_remark);
        mr_selectImg = view.findViewById(R.id.manualRequest_selectImg);
        mr_Img = view.findViewById(R.id.manualRequest_Img);
        mr_button = view.findViewById(R.id.manualRequest_button);
        Choose_file = view.findViewById(R.id.Choose_file);

        retrofit = RetrofitLogin.getClient("https://stage.payscope.in");
        apiService = retrofit.create(LoginService.class);
        SharedPreferences preferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        ACCESS_TOKEN = preferences.getString(KEY_USER_TOKEN, "");
        Log.d("ManualRequest", "token: " + ACCESS_TOKEN);

        selectBankList();
        selectPaymentModes();

        mr_date.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(), (view1, year1, monthOfYear, dayOfMonth) -> {
                @SuppressLint("DefaultLocale") String formattedDate = String.format("%04d-%02d-%02d", year1, monthOfYear + 1, dayOfMonth);
                mr_date.setText(formattedDate);
            }, year, month, day);
            datePickerDialog.show();
        });

        mr_selectImg.setOnClickListener(arg0 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
        });


        mr_button.setOnClickListener(v -> {
            final String amountText = mr_amount.getText().toString().trim();
            final String dateText = mr_date.getText().toString().trim();
            final String referenceNo = mr_referenceNo.getText().toString().trim();
            final String remark = mr_remark.getText().toString().trim();

            if (amountText.isEmpty() || dateText.isEmpty() || referenceNo.isEmpty() || remark.isEmpty()) {
                Toast.makeText(requireActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                final float amount = Float.parseFloat(amountText);

                if (amount <= 0) {
                    Toast.makeText(requireActivity(), "Amount should be greater than zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                createManualRequest(selectedBank, amount, paymentModesId, dateText, referenceNo, remark, mr_Img);
            }
        });
        return view;
    }

    public void createManualRequest(int bank, float amount, int payment_mode, String pay_date, String reference_number, String remark, ImageView img) {

        String imageData = convertImageViewToBase64(img);
        ManualRequest manualRequest = new ManualRequest(bank, amount, payment_mode, pay_date, reference_number, remark, imageData);
        Call<ManualRequestResponse> call = apiService.createManualResponse(manualRequest, "Bearer " + ACCESS_TOKEN);
        call.enqueue(new Callback<ManualRequestResponse>() {
            @Override
            public void onResponse(@NonNull Call<ManualRequestResponse> call, @NonNull Response<ManualRequestResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("ManualRequest", "Manual Funds created successfully: " + response.body().getMessage());
                    Toast.makeText(requireActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("ManualRequest", "isSuccessful bank : " + bank + " amount: " + amount + " payment_mode: " + payment_mode + " pay_date: " + pay_date + " reference_number: " + reference_number + " remark: " + remark + " imageData: " + imageData);
                    mr_selectBank.setSelection(0);
                    mr_amount.setText("");
                    mr_paymentMode.setSelection(0);
                    mr_date.setText("");
                    mr_referenceNo.setText("");
                    mr_remark.setText("");
                    mr_Img.setImageDrawable(null);

                } else {
                    Log.d("ManualRequest", "Request failed: " + response.errorBody());
                    Toast.makeText(requireActivity(), "Request failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ManualRequestResponse> call, @NonNull Throwable t) {
                Log.d("ManualRequest", "Error: " + t.getMessage());
                Toast.makeText(requireActivity(), "An error occurred. Please check your network connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                String selectedImagePath = getPath(selectedImageUri);
                Log.d("ManualRequest", "Image Path : " + selectedImagePath);
                mr_Img.setImageURI(selectedImageUri);
                Choose_file.setVisibility(View.GONE);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        try (Cursor cursor = requireActivity().getContentResolver().query(uri, projection, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                return cursor.getString(column_index);
            }
        }
        return null;
    }

    private String convertImageViewToBase64(ImageView imageView) {
        if (imageView.getDrawable() == null) {
            return "";
        }
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

/*
    public void selectBankList() {
        Call<List<SelectedBankModel>> call = apiService.selectedBankModel("Bearer " + ACCESS_TOKEN);
        call.enqueue(new Callback<List<SelectedBankModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<SelectedBankModel>> call, @NonNull Response<List<SelectedBankModel>> response) {
                List<SelectedBankModel> bankModels = response.body();
                if (bankModels != null && bankModels.size() > 0) {
                    String[] Categorys = new String[bankModels.size()];
                    for (int i = 0; i < bankModels.size(); i++) {
                        Categorys[i] = response.body().get(i).getName();
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, Categorys);
                        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mr_selectBank.setAdapter(spinnerAdapter);


                        mr_selectBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Selection = bankModels.get(position).getId();
                                Log.d("BankList", "selectNameUtils: " + Selection);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<SelectedBankModel>> call, @NonNull Throwable t) {
                Log.d("BankList", "onFailure: " + t.getMessage());
            }
        });
    }
 */
    public void selectBankList() {
        Call<List<SelectedBankModel>> call = apiService.selectedBankModel("Bearer " + ACCESS_TOKEN);
        call.enqueue(new Callback<List<SelectedBankModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<SelectedBankModel>> call, @NonNull Response<List<SelectedBankModel>> response) {
                List<SelectedBankModel> bankModels = response.body();
                if (bankModels != null && bankModels.size() > 0) {
                    List<String> categories = new ArrayList<>();
                    for (SelectedBankModel model : bankModels) {
                        categories.add(model.getName());
                    }    // Use the custom adapter
                    CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(getContext(), categories);
                    mr_selectBank.setAdapter(spinnerAdapter);

                    mr_selectBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedBank = bankModels.get(position).getId();
                            Log.d("BankList", "selectedBank: " + selectedBank);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Do nothing
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<SelectedBankModel>> call, @NonNull Throwable t) {
                Log.d("BankList", "onFailure: " + t.getMessage());
            }
        });
    }

    public void selectPaymentModes() {
        Call<List<PaymentModesModel>> call = apiService.paymentModesModel("Bearer " + ACCESS_TOKEN);
        call.enqueue(new Callback<List<PaymentModesModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PaymentModesModel>> call, @NonNull Response<List<PaymentModesModel>> response) {
                List<PaymentModesModel> paymentModesModelList = response.body();
                if (paymentModesModelList != null && paymentModesModelList.size() > 0) {
                    List<String> payModes = new ArrayList<>();
                    for (PaymentModesModel modesModel : paymentModesModelList) {
                        payModes.add(modesModel.getName());
                    }
                    CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(getContext(), payModes);
                    mr_paymentMode.setAdapter(spinnerAdapter);

                    mr_paymentMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            paymentModesId = paymentModesModelList.get(position).getId();
                            Log.d("payMode", "selectedMode: " + paymentModesId);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Do nothing
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PaymentModesModel>> call, @NonNull Throwable t) {
                Log.d("payMode", "onFailure: " + t.getMessage());
            }
        });
    }

}
