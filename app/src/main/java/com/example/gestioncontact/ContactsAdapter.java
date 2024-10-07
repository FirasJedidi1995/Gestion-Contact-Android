package com.example.gestioncontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter<Contact> {
    public ContactsAdapter(@NonNull Context context, ArrayList<Contact>Contacts) {
        super(context, 0,Contacts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_contact,parent,false);
        }
        //Get the data item for this position
        Contact contact=getItem(position);
        //Lookup view for data population
        TextView id =convertView.findViewById(R.id.id1);
        TextView name =convertView.findViewById(R.id.name1);
        TextView phone =convertView.findViewById(R.id.phone1);
        //Populate the data into the template view using the data object
        id.setText(String.valueOf(contact._id));
        name.setText(contact._name);
        phone.setText(contact._phone_number);

        //Return the completed view to render on screen
        return convertView;
        }
    }

