package edu.gatech.seclass.crypto6300;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.gatech.seclass.crypto6300.Model.AppDatabase;
import edu.gatech.seclass.crypto6300.Model.Cryptogram;

public class CreateCreptogramActivity extends AppCompatActivity {

    private EditText cryptogram;
    private EditText solution;
    private EditText easyAttempts;
    private EditText normalAttempts;
    private EditText hardAttempts;
    private Button createCryptogram;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_cryptogram);

        cryptogram = (EditText)findViewById(R.id.cryptogram);
        solution = (EditText)findViewById(R.id.cryptoSolution);
        easyAttempts = (EditText) findViewById(R.id.easyAttempts);
        normalAttempts = (EditText) findViewById(R.id.normalAttempts);
        hardAttempts = (EditText) findViewById(R.id.hardAttempts);
        createCryptogram = (Button) findViewById(R.id.create_cryptogram_bt);
        cancelBtn =(Button)findViewById(R.id.Cancel_button);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminPageActivity.class));
            }
        });
        createCryptogram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                AppDatabase cdb=AppDatabase.getDatabase(getApplicationContext());
                String input_crypt=cryptogram.getText().toString();
                String input_solution=solution.getText().toString();
                int input_easy = 0;
                int input_normal = 0;
                int input_hard = 0;
                boolean valid_create=true;
                try {
                    input_easy = Integer.valueOf(easyAttempts.getText().toString());
                } catch (Exception e){
                    easyAttempts.requestFocus();
                    easyAttempts.setError("Input a integer bigger than 0");
                    valid_create=false;

                }
                try {
                    input_normal = Integer.valueOf(normalAttempts.getText().toString());
                } catch (Exception e){
                    normalAttempts.requestFocus();
                    normalAttempts.setError("Input a integer bigger than 0");
                    valid_create=false;

                }

                try{
                    input_hard=Integer.valueOf(hardAttempts.getText().toString());
                } catch (Exception e){
                    hardAttempts.requestFocus();
                    hardAttempts.setError("Input a integer bigger than 0");
                    valid_create=false;
                }


                if (input_crypt.equals(""))
                {
                    cryptogram.requestFocus();
                    cryptogram.setError("Input a Cryptogram name");
                    valid_create=false;
                };
                if (cdb.cryptogramDao().findByName(input_crypt)!=null){
                    cryptogram.requestFocus();
                    cryptogram.setError("This name is used");
                    valid_create=false;
                };
                if (input_solution.equals("")) {
                    solution.requestFocus();
                    solution.setError("Input a string");
                    valid_create=false;
                };

                if (valid_create)
                {
                    Cryptogram newcrypt=new Cryptogram();
                    newcrypt.cname=input_crypt;
                    newcrypt.csolution=input_solution;
                    newcrypt.ceasy=input_easy;
                    newcrypt.cnormal=input_normal;
                    newcrypt.chard=input_hard;
                    cdb.cryptogramDao().insert(newcrypt);
                    startActivity(new Intent(getApplicationContext(),AdminPageActivity.class));
                };




            }
        });



    }
}
