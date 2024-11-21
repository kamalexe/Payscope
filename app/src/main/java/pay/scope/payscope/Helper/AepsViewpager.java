package pay.scope.payscope.Helper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import pay.scope.payscope.Fragment.AadharPaymentFragment;
import pay.scope.payscope.Fragment.AepsDepositFragment;
import pay.scope.payscope.Fragment.AepsStatementFragment;
import pay.scope.payscope.Fragment.AepsWithdrawFragment;
import pay.scope.payscope.Fragment.BalanceEnquiryFragment;

public class AepsViewpager extends FragmentStateAdapter {
    private static final int NUM_PAGES = 5;
    public AepsViewpager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AepsWithdrawFragment();
            case 1:
                return new AepsStatementFragment();
            case 2:
                return new BalanceEnquiryFragment();
            case 3:
                return new AadharPaymentFragment();
            case 4:
                return new AepsDepositFragment();
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}

