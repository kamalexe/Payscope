package pay.scope.payscope.Helper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import pay.scope.payscope.Fragment.PostpaidRechargeFragment;
import pay.scope.payscope.Fragment.PripaidRechargeFragment;


public class MobileRechargeViewPager extends FragmentStateAdapter {
    public MobileRechargeViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new PostpaidRechargeFragment();
        }
        return new PripaidRechargeFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
