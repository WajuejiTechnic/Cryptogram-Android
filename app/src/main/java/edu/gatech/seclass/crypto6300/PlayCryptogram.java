package edu.gatech.seclass.crypto6300;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.gatech.seclass.crypto6300.Model.AppDatabase;
import edu.gatech.seclass.crypto6300.Model.PlayRef;
import edu.gatech.seclass.crypto6300.Model.User;
import edu.gatech.seclass.crypto6300.Model.Cryptogram;
import edu.gatech.seclass.crypto6300.Model.encryption;


public class PlayCryptogram extends AppCompatActivity {


    private static PlayRef Now_Play;
    private static User Now_User;
    private static Cryptogram Now_Crypt;
    private TextView cryptogramName;
    private TextView attemptsRemaining;
    private TextView cryptogramPhraseDisplay;
    private EditText encryptLetter;
    private EditText decryptLetter;
    private Button substituteLetter;
    private TextView currentPhrase;
    private Button submitSolution;
    private Button backButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_page);

        cryptogramName = (TextView)findViewById(R.id.cryptogramName);
        attemptsRemaining = (TextView)findViewById(R.id.attemptsRemaining);
        cryptogramPhraseDisplay = (TextView)findViewById(R.id.cryptogramPhraseDisplay);
        encryptLetter = (EditText)findViewById(R.id.encryptLetter);
        decryptLetter = (EditText)findViewById(R.id.decryptLetter);
        substituteLetter = (Button)findViewById(R.id.substituteLetter);
        currentPhrase = (TextView)findViewById(R.id.currentPhrase);
        submitSolution = (Button)findViewById(R.id.submitSolution);
        backButton = (Button)findViewById(R.id.backButton);

        AppDatabase cdb=AppDatabase.getDatabase(getApplicationContext());
         Now_Play=cdb.playrefDao().findByUserCrypt(Curr.username,Curr.playcrypt);
         Now_User = cdb.userDao().findByName(Curr.username);
         Now_Crypt = cdb.cryptogramDao().findByName(Curr.playcrypt);
        if (Now_Play==null) {

            PlayRef New_Play= new PlayRef();
            New_Play.pUname=Now_User.username;
            New_Play.pCname=Now_Crypt.cname;
            if (Now_User.dlevel.equals("easy")) New_Play.pattempts=Now_Crypt.ceasy;
            if (Now_User.dlevel.equals("normal")) New_Play.pattempts=Now_Crypt.cnormal;
            if (Now_User.dlevel.equals("hard")) New_Play.pattempts=Now_Crypt.chard;
            String new_e= encryption.process(Now_Crypt.csolution);
            New_Play.ephrase=new_e;
            New_Play.pphrase=New_Play.ephrase;
            New_Play.pstatus="in_process";
            New_Play.pSolution=Now_Crypt.csolution;
            cdb.playrefDao().insert(New_Play);
            Now_Play=New_Play;
        };
        cryptogramName.setText(Now_Play.pCname);
        attemptsRemaining.setText(Integer.toString(Now_Play.pattempts));
        cryptogramPhraseDisplay.setText(Now_Play.ephrase);
        currentPhrase.setText(Now_Play.pphrase);

    }


//    public void handleClickSubstituteLetter(View view){
//
//        int messageError = 0;
//
//        String cryptogramValue = cryptogramPhraseDisplay.getText().toString();
//        String encryptValue = encryptLetter.getText().toString();
//        String decryptValue = decryptLetter.getText().toString();
//        String currentValue = currentPhrase.getText().toString();
//
//
//        // Write code to test that the input encryptLetter and decryptLetter are letters and not other symbols
//
//        currentPhrase.setError(null);
//        // If there are no errors we submit the solution to be validated
//        if (messageError == 0) {
//
//            currentPhrase.setText(substituteLetters(cryptogramValue, currentValue, encryptValue, decryptValue));
//
//
//        }
//
//
//    }

    public void handleClickSubstituteLetter(View view){

        int messageError = 0;

        String cryptogramValue = cryptogramPhraseDisplay.getText().toString();
        String encryptValue = encryptLetter.getText().toString();
        String decryptValue = decryptLetter.getText().toString();
        String currentValue = currentPhrase.getText().toString();


        // Write code to test that the input encryptLetter and decryptLetter are letters and not other symbols
        Pattern p = Pattern.compile("[^A-Za-z]");
        Matcher encryptMatch = p.matcher(encryptValue);
        Matcher decryptMatch = p.matcher(decryptValue);

        if(encryptMatch.find()){

            encryptLetter.requestFocus();
            encryptLetter.setError("Only Alphabetic Letters Allowed");
            messageError = 1;

        }

        if(decryptMatch.find()){
            decryptLetter.requestFocus();
            decryptLetter.setError("Only Alphabetic Letters Allowed");
            messageError = 1;

        }

        if(encryptValue.equals("")){
            encryptLetter.requestFocus();
            encryptLetter.setError("Alphabetic Letters Must Be Entered");
            messageError = 1;
        }


        if(decryptValue.equals("")){
            decryptLetter.requestFocus();
            decryptLetter.setError("Alphabetic Letters Must Be Entered");
            messageError = 1;
        }

        currentPhrase.setError(null);
        // If there are no errors we submit the solution to be validated
        if (messageError == 0) {

            currentPhrase.setText(substituteLetters(cryptogramValue, currentValue, encryptValue, decryptValue));


        }


    }

    public void handleClickSubmitSolution(View view){

        AppDatabase cdb=AppDatabase.getDatabase(getApplicationContext());
        int messageError = 0;

        String nameValue = cryptogramName.getText().toString();
        int attemptsValue = Integer.parseInt(attemptsRemaining.getText().toString());
        String cryptogramValue = cryptogramPhraseDisplay.getText().toString();
        String encryptValue = encryptLetter.getText().toString();
        String decryptValue = decryptLetter.getText().toString();
        String currentValue = currentPhrase.getText().toString();


        if (attemptsValue <= 0){
            attemptsRemaining.requestFocus();
            attemptsRemaining.setError(getString(R.string.attempts_limit));
            messageError = 1;
            cdb.playrefDao().updateAttemptNum(Curr.username,Curr.playcrypt,0);
            cdb.playrefDao().updateStatus(Curr.username,Curr.playcrypt,"lost");
        } else {
            attemptsRemaining.setError(null);
        }

        // Write code to test that the input encryptLetter and decryptLetter are letters and not other symbols


        // If there are no errors we submit the solution to be validated
        if (messageError == 0) {

            String correctPhrase =Now_Play.pSolution;
            if (currentValue.equals(correctPhrase)){

                currentPhrase.setText(substituteLetters(cryptogramValue, currentValue, encryptValue, decryptValue));
                cdb.playrefDao().updateStatus(Curr.username,Curr.playcrypt,"won");
                cdb.userDao().updateWon(Now_User.username,Now_User.won+1);
                startActivity(new Intent(getApplicationContext(), Success.class));
            } else {
                currentPhrase.requestFocus();
                currentPhrase.setError(getString(R.string.wrong_solution));
                cdb.playrefDao().updatePPhrase(Curr.username,Curr.playcrypt,currentValue);

                attemptsValue -= 1;
                cdb.playrefDao().updateAttemptNum(Curr.username,Curr.playcrypt,attemptsValue);
                if (attemptsValue==0){
                    cdb.playrefDao().updateStatus(Curr.username,Curr.playcrypt,"lost");
                    cdb.userDao().updateLost(Now_User.username,Now_User.lost+1);
                };

                attemptsRemaining.setText(Integer.toString(attemptsValue));

            }

        }

    }



    public void handleClickBack(View view){
        AppDatabase cdb=AppDatabase.getDatabase(getApplicationContext());
        String currentValue = currentPhrase.getText().toString();
        cdb.playrefDao().updatePPhrase(Curr.username,Curr.playcrypt,currentValue);
        startActivity(new Intent(getApplicationContext(), CryptogramListViewActivity.class));
    }







    public String substituteLetters(String inputPhrase, String currentValue, String encryptLetter, String decryptLetter){

        int i = 0;
        String currentChar = "";
        String changedString = "";
        for (i=0;i<inputPhrase.length(); i++){

            currentChar = Character.toString(inputPhrase.charAt(i));
            if (currentChar.equalsIgnoreCase(encryptLetter)){
                //System.out.println(currentChar);
                if (currentChar == currentChar.toLowerCase()){

                    changedString += decryptLetter.toLowerCase();
                } else {
                    changedString += decryptLetter.toUpperCase();
                }

            } else {
                changedString += Character.toString(currentValue.charAt(i));
            }

        }

        return(changedString);

    }


}
