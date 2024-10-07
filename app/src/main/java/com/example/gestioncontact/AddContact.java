package com.example.gestioncontact;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity implements View.OnClickListener {

    EditText name,phone,email,location,profession;
    Button add,cancel;
    ContactsDB db=new ContactsDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        name=findViewById(R.id.Name);
        phone=findViewById(R.id.Phone);
        email=findViewById(R.id.Web);
        location=findViewById(R.id.Location);
        profession=findViewById(R.id.Profession);
        add=findViewById(R.id.btnAdd);
        cancel=findViewById(R.id.btnCancel);
        add.setOnClickListener(this);
        cancel.setOnClickListener(this);


    }
    public void refrech(){

    }

    @Override
    public void onClick(View v) {
    if (v.getId()==R.id.btnAdd){
        Contact c1=new Contact(name.getText().toString(),phone.getText().toString(),email.getText().toString(),
                location.getText().toString(),profession.getText().toString());
    db.addContact(c1);
    this.finish();
    }
    if (v.getId()==R.id.btnCancel){
        this.finish();


    }

    }
}