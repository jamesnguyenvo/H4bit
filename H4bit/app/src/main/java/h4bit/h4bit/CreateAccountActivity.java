package h4bit.h4bit;

/**
 * Created by benhl on 2017-10-29.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        Button signupButton = (Button) findViewById(R.id.signupButton);
        Button crealogButton = (Button) findViewById(R.id.crealogButton);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

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
    public void login(){

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

    }

    public void signup(){

        EditText usernameText = (EditText) findViewById(R.id.usernameText);
        EditText passwordText = (EditText) findViewById(R.id.passwordText);
    }
}
