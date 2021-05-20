package edu.gatech.seclass.crypto6300;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.gatech.seclass.crypto6300.Model.AppDatabase;
import edu.gatech.seclass.crypto6300.Model.User;

public class CreatePlayerActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private EditText difficulty;
    private Button createPlayer;
    private Button cancelBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player);
        username = (EditText)findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);
        firstName = (EditText)findViewById(R.id.fistName);
        lastName = (EditText)findViewById(R.id.lastName);
        difficulty = (EditText)findViewById(R.id.difficulty_level);
        createPlayer = (Button)findViewById(R.id.create_player_bt);
        cancelBtn=(Button)findViewById(R.id.Cancel_button);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminPageActivity.class));
            }
        });

        createPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase cdb=AppDatabase.getDatabase(getApplicationContext());
                String input_username=username.getText().toString();
                String input_password=password.getText().toString();
                String input_firstname=firstName.getText().toString();
                String input_lastname=lastName.getText().toString();
                String input_dlevel=difficulty.getText().toString();
            boolean valid_create=true;
            if (input_username.equals(""))
            {
                username.setError("Input a username");
                valid_create=false;
            };
            if (input_firstname.equals(""))
                {
                    firstName.setError("Input a first name");
                    valid_create=false;
                };
            if (input_firstname.equals(""))
                {
                    lastName.setError("Input a last name");
                    valid_create=false;
                };

            if (cdb.userDao().findByName(input_username)!=null){
                username.requestFocus();
                username.setError("This name is used");
                valid_create=false;
            }
            if (input_password.equals("")) {
                password.setError("Input a password");
                valid_create=false;
            }
            if(!(input_dlevel.equals("normal")||input_dlevel.equals("easy")||input_dlevel.equals("hard"))){
                difficulty.requestFocus();
                difficulty.setError("Invalid difficulty level");
                valid_create=false;
            }

            if (valid_create)
            {
                User newuser=new User();
                newuser.username=input_username;
                newuser.usertype="player";
                newuser.password=input_password;
                newuser.won=0;
                newuser.lost=0;
                newuser.firstname=input_firstname;
                newuser.lastname=input_lastname;
                newuser.dlevel=input_dlevel;
                cdb.userDao().insert(newuser);
                startActivity(new Intent(getApplicationContext(), AdminPageActivity.class));
            };


            }
        });

    }
}
