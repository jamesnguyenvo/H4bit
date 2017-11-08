package h4bit.h4bit;

/**
 * Created by benhl on 2017-10-29.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * usernameText, passwordText, signupButton, crealogButton
 * These are the respective textboxes required to handle the information
 * we plan for this screen to have dual functionality, so the crealog button should
 * either show create or login depending on whether they are
 * creating an account or logging in with an existing account
 *
 * Should there be an offline mode button? Should it just skip
 * the login screen when someone is offline?
 */

public class CreateAccountActivity extends AppCompatActivity {

    private User user;
    // We will store the user locally
    private static final String FILENAME = "localsave.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Button signupButton = (Button) findViewById(R.id.signupButton);
        Button crealogButton = (Button) findViewById(R.id.crealogButton);

        // This will be the listener for the signup button
        signupButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                // Signup the user, put them into the system
                signup();
            }
        });

        crealogButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                // Log the user in
                login();
            }
        });

    }

    // This should log in the user and take us to the main todays habits screen screen
    public void login() {

        // Get the entered username and password text
        // Its probably good practice to compare the users pass with a stored
        // one-way hash for information security

        EditText usernameText = (EditText) findViewById(R.id.usernameText);
        EditText passwordText = (EditText) findViewById(R.id.passwordText);

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        if (username.isEmpty()) {
            Log.i("Login", "Please enter a username");
        } else if (username.matches("[a-zA-Z]+")){  // do we want the username to only include a-z/A-Z?
            // elastic search
        }

        // Attempt to login
        // Send user/pass keypair to elasticsearch
        // check if keypair matches a user account
        // elasticsearch returns user object

        // Right now this will just take us to the new screen

        Intent intent = new Intent(this, MainHabitActivity.class);
        startActivity(intent);
        finish();
    }



    public void signup(){

        EditText usernameText = (EditText) findViewById(R.id.usernameText);
        EditText passwordText = (EditText) findViewById(R.id.passwordText);
    }

    private void loadFromFile(String username) {
        try {
            FileInputStream fis = openFileInput(username+".sav");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-19
//            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            this.user = gson.fromJson(in, User.class);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            user = new User("test");
        }
    }

    // This is the code form the lonelyTwitter lab exercise
    private void saveInFile(String username) {
        try {
            FileOutputStream fos = openFileOutput(username+".sav",
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(this.user, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}

