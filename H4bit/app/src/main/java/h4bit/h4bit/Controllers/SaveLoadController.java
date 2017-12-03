package h4bit.h4bit.Controllers;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

import h4bit.h4bit.Models.User;

/**
 * Created by benhl on 2017-11-20.
 *
 * This code was taken from the lonely twitter lab exercise
 */

public class SaveLoadController {

    private String savefile;
    private Context context;

    /**
     * This initializes the SaveLoadController object
     * @param savefile This is the String username that it will save/load to
     * @param context This is the context of the current activity
     */
    public SaveLoadController(String savefile, Context context){
        this.savefile = savefile;
        this.context = context;
        // Must load the user object from the filename
        // But then this user object wont be the same as the one outside
        // Solution: Make load return a user object
    }

    /**
     * This will save the user object locally, with the file being the name of the username
     * @param user This should be a user object
     */
    public void save(User user){
        try {
            user.setLastModified(new Date());
            FileOutputStream fos = context.openFileOutput(savefile, Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(user, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    /**
     * This will return a user object with the savefile name passed in on initialization
     * @return will return a user object
     */
    public User load(){
        User user;

        try {
            FileInputStream fis = context.openFileInput(savefile);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-19
//            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            user = gson.fromJson(in, User.class);

        } catch (FileNotFoundException e) {
            user = new User("test");
        }
        return user;
    }
}
