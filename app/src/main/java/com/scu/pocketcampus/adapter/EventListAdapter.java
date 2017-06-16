package com.scu.pocketcampus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.scu.pocketcampus.R;
import com.scu.pocketcampus.model.PocketCampusEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by avinash.kashyap on 6/12/17.
 */

public class EventListAdapter extends ArrayAdapter<PocketCampusEvent> {
    public EventListAdapter(Context context, ArrayList<PocketCampusEvent> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PocketCampusEvent event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_list_item, parent, false);
        }

        // Lookup view for data population
        TextView eventName = (TextView) convertView.findViewById(R.id.eventName);
        TextView eventDescription = (TextView) convertView.findViewById(R.id.eventDescription);
        TextView eventDate = (TextView) convertView.findViewById(R.id.eventDate);
        TextView eventTime = (TextView) convertView.findViewById(R.id.eventTime);
        // Populate the data into the template view using the data object
        eventName.setText(event.getName());
        eventDescription.setText(event.getDescription());
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        try {
            eventDate.setText(formatter.format(new SimpleDateFormat("MM/dd/yy").parse(event.getDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eventTime.setText(getTwelveHourTime(event.getStartHour(),event.getStartMinute()) + " - " + getTwelveHourTime(event.getEndHour(), event.getEndMinute()));
        // Return the completed view to render on screen
        return convertView;
    }

    public String getTwelveHourTime(String TwentyFourHrTime, String minutes){
        String timeText = "";
        int startHourInt = Integer.parseInt(TwentyFourHrTime);
        if(startHourInt > 12){
            timeText +=  String.valueOf(startHourInt - 12) + ":" + minutes +  " PM";
        }else if(startHourInt == 12){
            timeText +=  String.valueOf(startHourInt) + ":" + minutes + " PM";
        }else{
            timeText +=  String.valueOf(startHourInt) + ":" + minutes + " AM";
        }

        return timeText;
    }


}
