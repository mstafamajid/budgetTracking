package com.example.budgettracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.budgettracking.datamodel.userHelper;
import com.example.budgettracking.datamodel.users;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
ListView lv;
ArrayList<String> name=new ArrayList<String>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
TextView name=findViewById(R.id.txt1);
        TextView email=findViewById(R.id.txt2);
        TextView address=findViewById(R.id.txt3);
Bundle data=getIntent().getExtras();
if(data!=null){
    address.setText(data.getString("address"));
    email.setText(data.getString("email"));
    name.setText(data.getString("name"));

}

    }

    public void log(View v){
        Intent i=new Intent(this,login_screen.class);
        startActivity(i);
    }
}