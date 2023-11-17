package com.example.budgettracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import com.example.budgettracking.datamodel.userHelper;
import com.example.budgettracking.datamodel.users;

public class signup_screen extends AppCompatActivity {

    TextView name;
    TextView email;
    TextView pass;
    TextView confirm_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);
    }

    public void signup(View v){

        int flag=0; // for validation
        // intialize krdn
        name=findViewById(R.id.signup_name_field);
        pass=findViewById(R.id.signup_pass_field);
        email=findViewById(R.id.signup_email_field);


        //getting data
        String fullname=name.getText().toString();
        String password=pass.getText().toString();

        String Email=email.getText().toString();

        //validations
        if(fullname.isEmpty())
        {
            name.setError("please enter your fullname");
            flag=1;
        }
        if(password.length()<8) {
            pass.setError("please enter minimum 8 character");
            flag=1;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("please enter the email correctly");
            flag=1;
        }

        if(flag==0){
            userHelper db=new userHelper(getApplicationContext());
            users new_user=new users();
            new_user.setPassword(password);
            new_user.setEmail(Email);
            new_user.setName(fullname);
db.getWritableDatabase();
            db.insert_user(new_user);
            Intent intent=new Intent(this,login_screen.class);
            startActivity(intent);
        }

    }
}