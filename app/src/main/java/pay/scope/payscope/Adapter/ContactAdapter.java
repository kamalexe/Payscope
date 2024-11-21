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
import pay.scope.payscope.Model.ContactModel;
import pay.scope.payscope.R;

public class ContactAdapter extends BaseAdapter {
    private final Context context;
    private final List<ContactModel> contacts;

    public ContactAdapter(Context context, List<ContactModel> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        }

        ContactModel contact = contacts.get(position);

        TextView contactName = convertView.findViewById(R.id.contact_name);
        TextView contactPhone = convertView.findViewById(R.id.contact_phone);
        CircleImageView contact_image = convertView.findViewById(R.id.contact_image);

        contactName.setText(contact.getName());
        contactPhone.setText(contact.getPhoneNumber());

        if (contact.getImageUri() != null) {
            contact_image.setImageURI(Uri.parse(contact.getImageUri()));
        } else {
            contact_image.setImageResource(R.drawable.baseline_person_24); // Default image if no photo
        }

        return convertView;
    }
}
