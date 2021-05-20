package edu.gatech.seclass.crypto6300.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Cryptogram")
public class Cryptogram{


    @PrimaryKey(autoGenerate = true)
    public int cid;
    @ColumnInfo(name = "cryptogram_name")
    public String cname;
    @ColumnInfo(name = "cryptogram_solution")
    public String csolution;
    @ColumnInfo(name = "attempt_easy")
    public int ceasy;
    @ColumnInfo(name = "attempt_normal")
    public int cnormal;
    @ColumnInfo(name = "attempt_hard")
    public int chard;

//    public void setUserName(String uName) {
//        this.username = uName;
//    }
}