package pay.scope.payscope.Helper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import pay.scope.payscope.Fragment.ManualRequestFragment;
import pay.scope.payscope.Fragment.PhysicalCardFragment;
import pay.scope.payscope.Fragment.VirtualCardFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new VirtualCardFragment();
        }else if (position == 2) {
            return new PhysicalCardFragment();
        }
        return new ManualRequestFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

