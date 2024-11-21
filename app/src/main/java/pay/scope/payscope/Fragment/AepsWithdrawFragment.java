package pay.scope.payscope.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Arrays;

import pay.scope.payscope.Activity.PAepsActivity;
import pay.scope.payscope.Activity.PAepsTransactionActivity;
import pay.scope.payscope.Helper.SpinnerUtils;
import pay.scope.payscope.R;

public class AepsWithdrawFragment extends Fragment {

    private final String[] PAepsTransaction = {"Select", "transaction_string", "Aratek", "transaction_string L1", "Evolute"};
    private final String[] PAepsDevice = {"Select", "Morpho", "Mantra", "Mantra L1", "Aratek", "Evolute"};
    private final String[] PAepsBank = {"Select", "Airtel Payment Bank", "Airtel Payment Bank", "Airtel Payment Bank", "Airtel Payment Bank", "Airtel Payment Bank"};
    Spinner PAeps_transaction_spinner, PAepsDevice_spinner, PAepsBank_spinner;
    Button PAeps_SuccessfulBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aeps_withdraw, container, false);

        PAeps_transaction_spinner = view.findViewById(R.id.PAeps_transaction_spinner);
        PAepsDevice_spinner = view.findViewById(R.id.PAepsDevice_spinner);
        PAepsBank_spinner = view.findViewById(R.id.PAepsBank_spinner);
        PAeps_SuccessfulBtn = view.findViewById(R.id.PAeps_SuccessfulBtn);

        SpinnerUtils.setUpSpinner(requireActivity(), PAeps_transaction_spinner, Arrays.asList(PAepsTransaction));
        SpinnerUtils.setUpSpinner(requireActivity(), PAepsDevice_spinner, Arrays.asList(PAepsDevice));
        SpinnerUtils.setUpSpinner(requireActivity(), PAepsBank_spinner, Arrays.asList(PAepsBank));

        PAeps_SuccessfulBtn.setOnClickListener(v -> startActivity(new Intent(requireActivity(), PAepsTransactionActivity.class)));

        return view;
    }
}