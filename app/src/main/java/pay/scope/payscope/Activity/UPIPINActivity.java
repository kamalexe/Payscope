package pay.scope.payscope.Activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class UPIPINActivity extends AppCompatActivity {
    MaterialToolbar UpiPin_toolbar;
    Button UpiPinBtn;
    EditText UpiPinEditText1,UpiPinEditText2,UpiPinEditText3,UpiPinEditText4;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upipinactivity);

        UpiPin_toolbar = findViewById(R.id.UpiPin_toolbar);
        UpiPinBtn = findViewById(R.id.UpiPinBtn);

        UpiPinEditText1 = findViewById(R.id.UpiPinEditText1);
        UpiPinEditText2 = findViewById(R.id.UpiPinEditText2);
        UpiPinEditText3 = findViewById(R.id.UpiPinEditText3);
        UpiPinEditText4 = findViewById(R.id.UpiPinEditText4);

        UpiPin_toolbar.setNavigationOnClickListener(v -> finish());



        UpiPinEditText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    UpiPinEditText2.requestFocus();
                }
            }
        });

        UpiPinEditText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    UpiPinEditText3.requestFocus();
                }
            }
        });

        UpiPinEditText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    UpiPinEditText4.requestFocus();
                }
            }
        });



        UpiPinBtn.setOnClickListener(v -> showAlertDialogTransferSuccessfull());

    }

    private void showAlertDialogTransferCancel() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.transfercancle_layout, null);
        builder.setView(dialogView);

//        builder.setTitle("Alert");
        builder.setCancelable(false);

//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                dialog.dismiss();
//            }
//        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button canclebtn = dialogView.findViewById(R.id.canclebtn);


        canclebtn.setOnClickListener(v -> alertDialog.dismiss());
    }

    private void showAlertDialogTransferSuccessfull() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View transfersuccessful = inflater.inflate(R.layout.transfersuccessful_layout, null);
        builder.setView(transfersuccessful);
        builder.setCancelable(false);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button conitnuebtn = transfersuccessful.findViewById(R.id.conitnuebtn);


        conitnuebtn.setOnClickListener(v -> alertDialog.dismiss());
    }
}