package com.example.budgettracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgettracking.datamodel.userHelper;
import com.example.budgettracking.datamodel.users;

import java.util.ArrayList;

public class login_screen extends AppCompatActivity {
    int count=0;
TextView emailinput;
    TextView passinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
emailinput =findViewById(R.id.signin_email_field);
        passinput=findViewById(R.id.signin_pass_field);

    }

    public void sign(View v){

        int flag=0;
        //initialize
        emailinput=findViewById(R.id.signin_email_field);
        passinput=findViewById(R.id.signin_pass_field);

       //gettingData
        String email=emailinput.getText().toString();
        String password=passinput.getText().toString();

        //validation
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            flag=1;
            emailinput.setError("please enter the correct email type");
        }
        if(password.length()<8){
            flag=1;
            passinput.setError("password should be minimum 8 characters");
        }

        if(flag==0){
//getting data from database
            userHelper dbhelper=new userHelper(this);
            ArrayList<users> alluser=dbhelper.getAllUser();

            for (users user:alluser ) {
                if(user.getEmail().equals(email)){
                    if(user.getPassword().equals(password)){
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        i.putExtra("name",user.getName());
                        i.putExtra("address",user.getPassword());
                        i.putExtra("email",user.getEmail());
                        startActivity(i);
                        return;
                    } else {
                        passinput.setError("password is wrong,type the correct password");
                        if(count==2){
                            Toast.makeText(this, "you reach maximum", Toast.LENGTH_SHORT).show();
                        }
                        count++;
                        return;
                    }
                }
            }
            emailinput.setError("this email not found");
        }









    }
    public void create_account(View v){
        Intent i=new Intent(getApplicationContext(),signup_screen.class);
        startActivity(i);
    }
}