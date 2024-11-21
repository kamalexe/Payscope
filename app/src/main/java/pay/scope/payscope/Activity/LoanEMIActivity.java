package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import com.google.android.material.appbar.MaterialToolbar;
import pay.scope.payscope.R;

public class LoanEMIActivity extends AppCompatActivity {
    MaterialToolbar LoanEMI_toolbar;
    Button loanBtn;
    CardView loan_Home, loan_Personal;
    CardView loan_Medical, loan_Car, loan_Education;
    private int selectedCardIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_emiactivity);

        LoanEMI_toolbar = findViewById(R.id.LoanEMI_toolbar);
        loanBtn = findViewById(R.id.loanBtn);
        loan_Home = findViewById(R.id.loan_Home);
        loan_Personal = findViewById(R.id.loan_Personal);
        loan_Medical = findViewById(R.id.loan_Medical);
        loan_Car = findViewById(R.id.loan_Car);
        loan_Education = findViewById(R.id.loan_Education);

        selectedCardIndex = 0;
        updateBackgroundColor();


        LoanEMI_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(LoanEMIActivity.this, QuickLoanActivity.class));
            finish();

        });

        loanBtn.setOnClickListener(v -> startActivity(new Intent(LoanEMIActivity.this, StatisticsActivity.class)));

        loan_Home.setOnClickListener(v -> {
            selectedCardIndex = 0;
            updateBackgroundColor();
        });

        loan_Personal.setOnClickListener(v -> {
            selectedCardIndex = 1;
            updateBackgroundColor();
        });

        loan_Medical.setOnClickListener(v -> {
            selectedCardIndex = 2;
            updateBackgroundColor();
        });

        loan_Car.setOnClickListener(v -> {
            selectedCardIndex = 3;
            updateBackgroundColor();
        });

        loan_Education.setOnClickListener(v -> {
            selectedCardIndex = 4;
            updateBackgroundColor();
        });

    }


    private void updateBackgroundColor() {
        loan_Home.setCardBackgroundColor(getResources().getColor(R.color.cardchange));
        loan_Personal.setCardBackgroundColor(getResources().getColor(R.color.cardchange));
        loan_Medical.setCardBackgroundColor(getResources().getColor(R.color.cardchange));
        loan_Car.setCardBackgroundColor(getResources().getColor(R.color.cardchange));
        loan_Education.setCardBackgroundColor(getResources().getColor(R.color.cardchange));
//        Ac_OnlyText.setTextColor(ContextCompat.getColor(IRCTCActivity.this, R.color.black));

        switch (selectedCardIndex) {
            case 0:
                loan_Home.setCardBackgroundColor(ContextCompat.getColor(LoanEMIActivity.this, R.color.editbox));
                break;
            case 1:
                loan_Personal.setCardBackgroundColor(ContextCompat.getColor(LoanEMIActivity.this, R.color.editbox));
                break;

            case 2:
                loan_Medical.setCardBackgroundColor(ContextCompat.getColor(LoanEMIActivity.this, R.color.editbox));
                break;
            case 3:
                loan_Car.setCardBackgroundColor(ContextCompat.getColor(LoanEMIActivity.this, R.color.editbox));
                break;
            case 4:
                loan_Education.setCardBackgroundColor(ContextCompat.getColor(LoanEMIActivity.this, R.color.editbox));
                break;
            default:
                break;
        }


    }


}