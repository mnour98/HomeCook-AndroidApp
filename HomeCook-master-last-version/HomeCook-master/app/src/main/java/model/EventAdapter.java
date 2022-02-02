package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homecook.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Event> listOfEvent;


    public EventAdapter(Context context, ArrayList<Event> listOfEvent) {
        this.context = context;
        this.listOfEvent = listOfEvent;
    }

    @Override
    public int getCount() {
        return listOfEvent.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfEvent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.one_event, parent, false);
        }
        Event event = (Event)getItem(position);
        TextView tveventName = (TextView)
                convertView.findViewById(R.id.tvEventName);
        TextView tvDescription = (TextView)
                convertView.findViewById(R.id.tvDescription);
        ImageView imImage = (ImageView)
                convertView.findViewById(R.id.imgEvent);

        tveventName.setText(event.getEventName());
        tvDescription.setText(event.getDescription());
        String imgURL = event.getPhoto();
        Picasso.with(context).load(imgURL).placeholder(R.drawable.image_loading).into(imImage);




        return convertView;
    }
}
