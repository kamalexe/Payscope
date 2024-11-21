package pay.scope.payscope.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FiltersViewPager extends RecyclerView.Adapter<FiltersViewPager.ViewHolder> {
    private final List<Integer> layouts;
   Context context;

    public FiltersViewPager(List<Integer> layouts, Context context) {
        this.layouts = layouts;
        this.context = context;
    }

    @NonNull
    @Override
    public FiltersViewPager.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layouts.get(viewType), parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FiltersViewPager.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return layouts.size();
    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
