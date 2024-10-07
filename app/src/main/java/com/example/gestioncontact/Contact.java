package com.example.gestioncontact;

public class Contact {
    int _id;
    String _name;
    String _phone_number;
    String _email_adress;
    String _location;
    String _profession;

    public Contact(int i, String string, String cursorString, String s, String string1, String cursorString1){};

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_phone_number() {
        return _phone_number;
    }

    public String get_email_adress() {
        return _email_adress;
    }

    public String get_location() {
        return _location;
    }

    public String get_profession() {
        return _profession;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }

    public void set_email_adress(String _email_adress) {
        this._email_adress = _email_adress;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public void set_profession(String _profession) {
        this._profession = _profession;
    }

    public Contact() {
        this._id = _id;
        this._name = _name;
        this._phone_number = _phone_number;
        this._email_adress = _email_adress;
        this._location = _location;
        this._profession = _profession;
    }


    public Contact(String _name, String _phone_number, String _email_adress, String _location, String _profession) {
        this._name = _name;
        this._phone_number = _phone_number;
        this._email_adress = _email_adress;
        this._location = _location;
        this._profession = _profession;
    }


}
