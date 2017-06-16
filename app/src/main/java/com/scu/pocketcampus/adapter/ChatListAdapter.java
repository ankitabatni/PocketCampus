package com.scu.pocketcampus.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.scu.pocketcampus.ChatMessage;
import com.scu.pocketcampus.R;

import java.util.ArrayList;

/**
 * Created by avinash.kashyap on 6/15/17.
 */

public class ChatListAdapter extends ArrayAdapter<ChatMessage> {


    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public ChatListAdapter(Context context, ArrayList<ChatMessage> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ChatMessage chat = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_message_item, parent, false);
        }

        // Lookup view for data population
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        LinearLayout chatMessageInnerLayout = (LinearLayout) convertView.findViewById(R.id.chatMessageInnerLayout);
        LinearLayout chatMessageOuterLayout = (LinearLayout) convertView.findViewById(R.id.chatMessageOuterLayout);

        // Populate the data into the template view using the data object
        messageTextView.setText(chat.getMsg());
        nameTextView.setText(chat.getName());

        chatMessageInnerLayout.setBackgroundResource(R.drawable.chat_message_shape_other);

        if(mAuth.getCurrentUser().getUid().toString().equals(chat.getUserId())) {
            chatMessageOuterLayout.setGravity(Gravity.RIGHT | Gravity.END);
            chatMessageInnerLayout.setGravity(Gravity.RIGHT | Gravity.END);
            chatMessageInnerLayout.setBackgroundResource(R.drawable.chat_message_shape_user);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
