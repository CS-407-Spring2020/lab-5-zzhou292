package c.sakshi.lab5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {
    String usernameKey = "username";

    int noteid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        //1.get edittext view
        EditText noteText =  findViewById(R.id.editText3);
        //2. get intent.
        Intent intent = getIntent();
        //3. get the value of integer "noteid" from intent
        noteid = intent.getIntExtra("noteid", -1);
        //4. initialize class variable "noteid" with the value from intent
        if(noteid!=-1){
            //display content of note by retrieving "notes" ArrayList in SecondActivity.
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
            //Use editText.setText() to display the contents of this note on screen.
            noteText.setText(noteContent);
        }
    }

    public void clickFunction(View view){
        saveMethod(view);
    }

    public void saveMethod(View view){
        String str = "";
        String info = "";
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        str = sharedPreferences.getString(usernameKey,"");
        //get edittext view and the content that user enetered.
        EditText noteText =  findViewById(R.id.editText3);
        info = noteText.getText().toString();
        //Initializa SQLiteDatabase instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        //initialize dbhelper class
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        //set username in the following variable by fetching from sharedpreferences
        String username = str;
        //save information to databzse
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1){
            title = "NOTE_"+ (Main2Activity.notes.size()+1);
            dbHelper.saveNotes(username,title,info,date);
        }else{
            title = "NOTE_"+(noteid+1);
            dbHelper.updateNote(title,date,info,username);
        }

        //go to second activity using intents
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("message",str);
        startActivity(intent);
    }

}
