package edu.gatech.seclass.crypto6300.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "PlayRef")
public class PlayRef {
    @PrimaryKey(autoGenerate = true)
    public int pid;
    @ColumnInfo(name="play_crypt_name")
    public String pCname;
    @ColumnInfo(name="play_user_name")
    public String pUname;
    @ColumnInfo(name = "previous_phrase")
    public String pphrase;
    @ColumnInfo(name = "encrypted_phrase")
    public String ephrase;
    @ColumnInfo(name = "play_solution")
    public String pSolution;
    @ColumnInfo(name = "attempt_times")
    public int pattempts;
    @ColumnInfo(name = "play_status")
    public String pstatus;


//    public void setUserName(String uName) {
//        this.username = uName;
//    }
}