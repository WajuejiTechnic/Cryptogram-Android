package edu.gatech.seclass.crypto6300;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import android.widget.Button;

import edu.gatech.seclass.crypto6300.Model.AppDatabase;
import edu.gatech.seclass.crypto6300.Model.Cryptogram;
import edu.gatech.seclass.crypto6300.Model.PlayRef;

public class CryptogramListViewActivity extends AppCompatActivity {

    private ListView listView;
    private Button Back_button;

    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cryptogram_list_view);
        Back_button =(Button)findViewById(R.id.Back_button);
        Back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Player_view.class));
            }
        });
        AppDatabase cdb=AppDatabase.getDatabase(getApplicationContext());
        List<Cryptogram> data =  cdb.cryptogramDao().getAll();
        final String[] list_name=new String[data.size()];
        final String[] list_status=new String[data.size()];;
        String[] list_print=new String[data.size()];
        int num_crypt=data.size();
        for (int i=0; i<num_crypt;i++){
        list_name[i]=data.get(i).cname;
        list_status[i]="unstarted";
        PlayRef temp_play=cdb.playrefDao().findByUserCrypt(Curr.username,list_name[i]);
        if (temp_play!=null) list_status[i]=temp_play.pstatus;
        list_print[i]=String.format("%-20s %s",list_name[i],list_status[i]);
        };
        int num_won_lost=0;
        for (int i=0;i<num_crypt;i++)
            if ((list_status[i].equals("won"))||(list_status[i].equals("lost"))) num_won_lost++;
        int num_left;
        num_left=num_crypt-num_won_lost;
        final String[] final_name=new String[num_left];
        final String[] final_status=new String[num_left];;
        String[] final_print=new String[num_left];
        int j=-1;
        for (int i=0;i<num_crypt;i++)
            if ((list_status[i].equals("unstarted"))||(list_status[i].equals("in_process"))){
                j++;
                final_name[j]=list_name[i];
                final_status[j]=list_status[i];
                final_print[j]=list_print[i];
            };



        listView = (ListView)findViewById(R.id.cryptograms_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mytextview,final_print);///////////////////
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                //intent.putExtra("Key",list_name[position]);
                Curr.playcrypt=final_name[position];
                //intent.setClass(CryptogramListViewActivity.this,PlayCryptogram.class);
                //if (!(list_status[position].equals("won")||list_status[position].equals("lost")))
                startActivity(new Intent(getApplicationContext(),PlayCryptogram.class));
            }
        });

    }
}
