package com.example.gestioncontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsContact extends AppCompatActivity implements View.OnClickListener {
    TextView id;
    ImageView ivPhone,ivWeb,ivLocation;
    EditText name,phone,email,location,profession;
    Button update,delete;

    ContactsDB db=new ContactsDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_contact);
        ivPhone=findViewById(R.id.ivPhone);
        ivWeb=findViewById(R.id.ivWeb);
        ivLocation=findViewById(R.id.ivLocation);

        id=findViewById(R.id.tvID);
        id.setVisibility(View.GONE);
        name=findViewById(R.id.Name);
        phone=findViewById(R.id.Phone);
        email=findViewById(R.id.Web);
        location=findViewById(R.id.Location);
        profession=findViewById(R.id.Profession);
        delete=findViewById(R.id.btnDelete);
        update=findViewById(R.id.btnUpdate);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        ivPhone.setOnClickListener(this);
        ivWeb.setOnClickListener(this);
        ivLocation.setOnClickListener(this);

        Contact contact=db.getContact(Integer.valueOf(getIntent().getStringExtra("id")));
        id.setText(String.valueOf(contact.get_id()));
        name.setText(contact.get_name());
        phone.setText(contact.get_phone_number());
        email.setText(contact.get_email_adress());
        location.setText(contact.get_location());
        profession.setText(contact.get_profession());

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnDelete){
          db.deleteContact(Integer.valueOf(id.getText().toString()));
            Intent intent=new Intent(DetailsContact.this, MainActivity.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.btnUpdate){
            Contact contact=new Contact();
            contact.set_id(Integer.valueOf(contact.get_id()));
            contact.set_name(name.getText().toString());
            contact.set_phone_number(phone.getText().toString());
            contact.set_email_adress(email.getText().toString());
            contact.set_location(location.getText().toString());
            contact.set_profession(profession.getText().toString());
            db.updateContact(contact);
            Intent intent=new Intent(DetailsContact.this, MainActivity.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.ivPhone){
            Intent intent =new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone.getText().toString()));
            startActivity(intent);
        }
        if (v.getId()==R.id.ivWeb){
            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://"+email.getText().toString()));
            startActivity(intent);

        }
        if (v.getId()==R.id.ivLocation){
            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("geo: 0,0?q="+location.getText().toString()));
            startActivity(intent);

        }

    }
}