package com.example.gestioncontact;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCreate,btnSignOut;
    ContactsDB db=new ContactsDB(MainActivity.this);

    ListView listview;

    ArrayList<Contact> Clist=new ArrayList<>();
    FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        refrech();
    }
    public void refrech(){
        List<Contact>Contacts =db.getAllContacts();
        Clist.removeAll(Clist);
        for (Contact cont :Contacts){
            Clist.add(cont);
        }
        ContactsAdapter adapter=new ContactsAdapter(this,Clist);
        listview.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        btnCreate=findViewById(R.id.btnCreate);
        btnSignOut=findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(this);

        List<Contact>Contacts=db.getAllContacts();
        for (Contact cont:Contacts){
            Clist.add(cont);
        }
        ContactsAdapter adapter=new ContactsAdapter(this,Clist);
        listview=findViewById(R.id.List1);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedItem=(Contact) parent.getItemAtPosition(position);
                Intent intent=new Intent(MainActivity.this,DetailsContact.class);
                intent.putExtra("id",String.valueOf(selectedItem._id));
                //intent.putExtra("name",String.valueOf(selectedItem._name));
                //intent.putExtra("phone",String.valueOf(selectedItem._phone_number));
                //intent.putExtra("email",String.valueOf(selectedItem._email_adress));
                //intent.putExtra("location",String.valueOf(selectedItem._location));
                //intent.putExtra("profession",String.valueOf(selectedItem._profession));
                startActivity(intent);
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddContact.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnSignOut){
            mAuth.signOut();
            Intent intent=new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
    }
}