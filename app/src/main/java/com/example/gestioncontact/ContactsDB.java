package com.example.gestioncontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactsDB extends SQLiteOpenHelper {
    private Context context;//zedtha njarreb biha

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contactsManager";
    private static final String TABLE_CONTACTS="contacts";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PHONE="phone_number";
    private static final String KEY_EMAIL="email_address";
    private static final String KEY_LOCATION="location";
    private static final String KEY_PROFESSION="profession";

    public ContactsDB(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_PHONE + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_LOCATION + " TEXT, "
                + KEY_PROFESSION + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS);
        onCreate(db);

    }
    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues values = new ContentValues() ;
        values.put(KEY_NAME,contact.get_name()) ; // Contact name
        values.put(KEY_PHONE,contact.get_phone_number()) ; //Contact phone
        values.put(KEY_EMAIL,contact.get_email_adress());
        values.put(KEY_LOCATION,contact.get_location());
        values.put(KEY_PROFESSION,contact.get_profession());

       long result= db.insert(TABLE_CONTACTS,null,values) ;
       if (result==-1){
           Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
       }

    }
    /*Contact getContact(String phone){
        SQLiteDatabase db = this .getReadableDatabase() ;
        Cursor cursor = db.query(TABLE_CONTACTS,new String[]{KEY_ID,KEY_NAME,KEY_PHONE,KEY_EMAIL,KEY_LOCATION,KEY_PROFESSION},
                KEY_PHONE + "=?" ,
                new String[] {String.valueOf(phone)} , null,null,null,null ) ;
        if (cursor != null)
            cursor.moveToFirst() ;
        Contact contact = new Contact(cursor.getString(0),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)) ;
        // return contact
        return contact ;
    }*/
    /*Contact getContact(int id){
        SQLiteDatabase db = this .getReadableDatabase() ;
        Cursor cursor = db.query(TABLE_CONTACTS,new String[]{KEY_ID,KEY_NAME,KEY_PHONE},
                KEY_ID + "=?" ,
                new String[] {String.valueOf(id)} , null,null,null,null ) ;
        if (cursor != null)
            cursor.moveToFirst() ;
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2)) ;
        // return contact
        return contact ;
    }*/
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_LOCATION, KEY_PROFESSION},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Contact contact = new Contact();

        if (cursor != null && cursor.moveToFirst()) {
            contact.set_id(Integer.parseInt(cursor.getString(0)));
            contact.set_name(cursor.getString(1));
            contact.set_phone_number(cursor.getString(2));
            contact.set_email_adress(cursor.getString(3));
            contact.set_location(cursor.getString(4));
            contact.set_profession(cursor.getString(5));
            cursor.close();
        }

        return contact;
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList= new ArrayList<>();
        // select all query
        String selectQuery =" SELECT * FROM " +TABLE_CONTACTS ;
        SQLiteDatabase db = this.getReadableDatabase() ;
        Cursor cursor = db.rawQuery(selectQuery,null) ;

        if (cursor.getCount()==0){
            Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
        }

        //looping through all rows and adding to list
        if(cursor.moveToFirst()) {
            do {
                Contact contact = new Contact (Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)) ;
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));
                contact.set_phone_number(cursor.getString(2));
                contact.set_email_adress(cursor.getString(3));
                contact.set_location(cursor.getString(4));
                contact.set_profession(cursor.getString(5));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext()) ;
        }
        //return contact list
        return contactList ;
    }
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues values = new ContentValues() ;
        values.put(KEY_NAME,contact.get_name());
        values.put(KEY_PHONE,contact.get_phone_number()) ;
        values.put(KEY_EMAIL,contact.get_email_adress());
        values.put(KEY_LOCATION,contact.get_location());
        values.put(KEY_PROFESSION,contact.get_profession());

        //updating row
        return db.update(TABLE_CONTACTS,values,KEY_ID+ "= ?",
                new String[] {String.valueOf(contact.get_id())});
    }
    public void deleteContact(int contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS,KEY_ID+"=?",
                new String[]{String.valueOf(contact)});
        db.close();
    }

}
