package com.scu.pocketcampus;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scu.pocketcampus.model.PocketCampusEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class PostEvent extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();
    private DatabaseReference mDatabase;
    private int selectedStartHour = 0;
    private int selectedStartMinute = 0;
    private int selectedEndHour = 0;
    private int selectedEndMinute = 0;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, EventScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);

        final Spinner eventType = (Spinner)findViewById(R.id.spinnerEvent);
        ArrayList<String> eventTypeList = new ArrayList<String>();
        eventTypeList.add("Academic School Events");
        eventTypeList.add("Community Events");
        eventTypeList.add("Free Food Events");
        eventTypeList.add("Individual Events");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,eventTypeList
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        eventType.setAdapter(spinnerArrayAdapter);

        final EditText eventDate = (EditText)findViewById(R.id.eventDate);
        final EditText eventStartTime = (EditText)findViewById(R.id.eventStartTime);
        final EditText eventEndTime = (EditText)findViewById(R.id.eventEndTime);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("event date","Inside the event Date click");
                new DatePickerDialog(PostEvent.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        eventStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PostEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        PostEvent.this.selectedStartHour = selectedHour;
                        PostEvent.this.selectedStartMinute = selectedMinute;
                        eventStartTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//No 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        eventEndTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PostEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        PostEvent.this.selectedEndHour = selectedHour;
                        PostEvent.this.selectedEndMinute = selectedMinute;
                        eventEndTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//No 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        EditText eventDate = (EditText)findViewById(R.id.eventDate);
        eventDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void createEvent(){
        final EditText eventName = (EditText)findViewById(R.id.eventName);
        final EditText eventDate = (EditText)findViewById(R.id.eventDate);
        final EditText eventDetails = (EditText)findViewById(R.id.eventDetails);
        final Spinner eventType = (Spinner)findViewById(R.id.spinnerEvent);

        PocketCampusEvent event = new PocketCampusEvent();
        event.setType(EventScreen.eventNameMap.get(eventType.getSelectedItem().toString()));
        event.setName(eventName.getText().toString().trim());
        event.setDate(eventDate.getText().toString().trim());
        event.setStartHour(String.valueOf(this.selectedStartHour));
        event.setStartMinute(String.valueOf(this.selectedStartMinute));
        event.setEndHour(String.valueOf(this.selectedEndHour));
        event.setEndMinute(String.valueOf(this.selectedEndMinute));
        event.setDescription(eventDetails.getText().toString().trim());
        mDatabase.child("events").child(UUID.randomUUID().toString()).setValue(event);
    }

    public void onClick(View v){
        Log.d("post event click","Inside post event Click");
        if (v.getId() == R.id.postEventButton) {
            final Spinner eventType = (Spinner)findViewById(R.id.spinnerEvent);

            String selectedEventType = EventScreen.eventNameMap.get(eventType.getSelectedItem().toString());

            if(selectedEventType!=null && selectedEventType!= ""){
                createEvent();


                if(selectedEventType == "community_event") {
                    Intent intent=new Intent(this,SchoolEvent.class);
                    intent.putExtra("eventName", "community_event");
                    startActivity(intent);
                }


                if(selectedEventType == "free_food_event") {
                    Intent i = new Intent(this, SchoolEvent.class);
                    i.putExtra("eventName", "free_food_event");
                    startActivity(i);
                }

                if(selectedEventType == "school_event") {
                    Intent i = new Intent(this, SchoolEvent.class);
                    i.putExtra("eventName", "school_event");
                    startActivity(i);
                }

                if(selectedEventType == "individual_event") {
                    Intent i = new Intent(this, SchoolEvent.class);
                    i.putExtra("eventName", "individual_event");
                    startActivity(i);
                }
            }else{
                Toast.makeText(getApplicationContext(), "Please select an event type", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
