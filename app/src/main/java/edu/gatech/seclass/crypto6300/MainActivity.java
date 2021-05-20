package edu.gatech.seclass.crypto6300;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.gatech.seclass.crypto6300.Model.AppDatabase;
import edu.gatech.seclass.crypto6300.Model.Cryptogram;
import edu.gatech.seclass.crypto6300.Model.PlayRef;
import edu.gatech.seclass.crypto6300.Model.User;


public class MainActivity extends AppCompatActivity {

    private EditText entered_username;
    private EditText entered_password;
    private Button login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getApplicationContext().deleteDatabase("database-cryptogram");            //delete old database
         entered_username = (EditText) findViewById(R.id.username);
         entered_password = (EditText) findViewById(R.id.password);

         login_button = (Button) findViewById(R.id.login);

         login_button.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View v) {


                 AppDatabase cdb=AppDatabase.getDatabase(getApplicationContext());
                 User uAdmin= new User();
                  uAdmin.username="admin";
                  uAdmin.password="Test1";
                  uAdmin.usertype="admin";
                 if (cdb.userDao().findByName(uAdmin.username)==null) cdb.userDao().insert(uAdmin);
                 User uPlayer1=new User();
                  uPlayer1.username="player1";
                  uPlayer1.usertype="player";
                  uPlayer1.password="test2";
                  uPlayer1.firstname="Alan";
                  uPlayer1.lastname="Lee";
                  uPlayer1.dlevel="normal";
                  uPlayer1.lost=0;
                  uPlayer1.won=0;
                 if (cdb.userDao().findByName(uPlayer1.username)==null) cdb.userDao().insert(uPlayer1);
                 User uPlayer2=new User();
                 uPlayer2.username="player2";
                 uPlayer2.usertype="player";
                 uPlayer2.password="test3";
                 uPlayer2.firstname="Bob";
                 uPlayer2.lastname="White";
                 uPlayer2.dlevel="easy";
                 uPlayer2.lost=0;
                 uPlayer2.won=0;
                 if (cdb.userDao().findByName(uPlayer2.username)==null) cdb.userDao().insert(uPlayer2);
                 User uPlayer3=new User();
                 uPlayer3.username="player3";
                 uPlayer3.usertype="player";
                 uPlayer3.password="test4";
                 uPlayer3.firstname="David";
                 uPlayer3.lastname="Smith";
                 uPlayer3.dlevel="hard";
                 uPlayer3.lost=0;
                 uPlayer3.won=0;
                 if (cdb.userDao().findByName(uPlayer3.username)==null) cdb.userDao().insert(uPlayer3);
                 Cryptogram crypt1=new Cryptogram();
                  crypt1.cname="Fruits";
                  crypt1.csolution="Banana Apple Orange";
                  crypt1.ceasy=10;
                  crypt1.cnormal=8;
                  crypt1.chard=6;
                 if (cdb.cryptogramDao().findByName(crypt1.cname)==null) cdb.cryptogramDao().insert(crypt1);

                 Cryptogram crypt2=new Cryptogram();
                 crypt2.cname="Animals";
                 crypt2.csolution="Cat Rabbit Bat";
                 crypt2.ceasy=11;
                 crypt2.cnormal=9;
                 crypt2.chard=7;
                 if (cdb.cryptogramDao().findByName(crypt2.cname)==null) cdb.cryptogramDao().insert(crypt2);

                 Cryptogram crypt3=new Cryptogram();
                 crypt3.cname="Sports";
                 crypt3.csolution="Basketball Football";
                 crypt3.ceasy=9;
                 crypt3.cnormal=7;
                 crypt3.chard=5;
                 if (cdb.cryptogramDao().findByName(crypt3.cname)==null) cdb.cryptogramDao().insert(crypt3);

                 System.out.println("entered password: " + entered_password.getText().toString());
                 System.out.println("LOGIN BUTTON IS BEING CLICKED");
                 String username_input=entered_username.getText().toString();
                 String password_input=entered_password.getText().toString();
                 User user_try=cdb.userDao().findByName(username_input);
                 if (user_try==null)
                    {   entered_username.requestFocus();
                        entered_username.setError("User not found");}
                 else
                     if(!user_try.password.equals(password_input))
                 {entered_password.setError("Invalid password");}
                     else
                 {
                    if (user_try.usertype.equals("admin")) {
                        Curr.username=username_input;
                        startActivity(new Intent(getApplicationContext(),AdminPageActivity.class));
                    }
                    else if (user_try.usertype.equals("player")){
                        Curr.username=username_input;
                        startActivity(new Intent(getApplicationContext(),Player_view.class));
                    };
                 }

             }
         });



    }

}

