package com.example.lab4_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lab4_2.DatabaseHandler.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        lvContact = findViewById(R.id.lv_contact);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        db.addContact(new Contact(1,"Ravi", "9100000000"));
        db.addContact(new Contact(2,"Srinivas", "9199999999"));
        db.addContact(new Contact(3,"Tommy", "9522222222"));
        db.addContact(new Contact(4,"Karthik", "9533333333"));

        // Reading all contacts
        List<Contact> contacts = db.getAllContacts();

        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, R.layout.custom_list_item, R.id.textName, contacts) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Get the data item for this position
                Contact contact = getItem(position);

                // Check if an existing view is being reused, otherwise inflate the view
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item, parent, false);
                }

                // Lookup view for data population
                TextView idtextview = convertView.findViewById(R.id.textID);
                TextView nameTextView = convertView.findViewById(R.id.textName);
                TextView phoneNumberTextView = convertView.findViewById(R.id.textPhoneNumber);

                // Populate the data into the template view using the data object
                idtextview.setText(String.valueOf(contact.getID())); // Convert ID to String
                nameTextView.setText(contact.getName());
                phoneNumberTextView.setText(contact.getPhoneNumber());

                // Return the completed view to render on screen
                return convertView;
            }
        };
        lvContact.setAdapter(adapter);
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = (Contact) parent.getItemAtPosition(position);

                db.deleteContact(selectedContact);

                adapter.remove(selectedContact);
                adapter.notifyDataSetChanged();

                return true;
            }
        });



    }
}
