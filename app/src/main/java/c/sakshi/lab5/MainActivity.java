package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String usernameKey = "username";


    public void clickFunction(View view){

        //1. Get username and password via EditText view
        EditText myTextField =  findViewById(R.id.editText);
        String str = myTextField.getText().toString();
        //2. Add username to SharedPreferences object
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username",str).apply();
        //3. Other Work
        //Toast.makeText(MainActivity.this, myTextField.getText().toString(), Toast.LENGTH_LONG).show();

        goToActivity2(str);
    }

    public void goToActivity2(String s){
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("message",s);
        startActivity(intent);
    }


    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if(!sharedPreferences.getString(usernameKey,"").equals("")){
            Intent intent = new Intent(this,Main2Activity.class);
            String s = sharedPreferences.getString("username","");
            intent.putExtra("message",s);
            startActivity(intent);
        }else{
            setContentView(R.layout.activity_main);
        }
    }
}

