package pay.scope.payscope.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import pay.scope.payscope.Model.ContactPostpaidModel;
import pay.scope.payscope.R;

public class ContactPostpaidAdapter extends BaseAdapter {

    private final Context context;
    private final List<ContactPostpaidModel> contactPostpaidModelList;

    public ContactPostpaidAdapter(Context context, List<ContactPostpaidModel> contactPostpaidModelList) {
        this.context = context;
        this.contactPostpaidModelList = contactPostpaidModelList;
    }

    @Override
    public int getCount() {
        return contactPostpaidModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactPostpaidModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contactpostpaid_item, parent, false);
        }

        ContactPostpaidModel postpaidModel = contactPostpaidModelList.get(position);

        TextView PostpaidContact_name = convertView.findViewById(R.id.PostpaidContact_name);
        TextView PostpaidContact_phone = convertView.findViewById(R.id.PostpaidContact_phone);
        CircleImageView contactImage = convertView.findViewById(R.id.contactImage);

        PostpaidContact_name.setText(postpaidModel.getPostpaidName());
        PostpaidContact_phone.setText(postpaidModel.getPostpaidPhoneNumber());


        if (postpaidModel.getImage() != null) {
            contactImage.setImageURI(Uri.parse(postpaidModel.getImage()));
        } else {
            contactImage.setImageResource(R.drawable.baseline_person_24); // Default image if no photo
        }

        return convertView;
    }
}
