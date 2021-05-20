package edu.gatech.seclass.crypto6300.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "User")
public class User{

//    public static final String TABLE_NAME="users";
//    public static final String COLUMN_NAME="user_name";
//    public static final String COLUMN_ID="user_id";

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "user_name")
    public String username;
    @ColumnInfo(name = "pass_word")
    public String password;
    @ColumnInfo(name = "user_type")
    public String usertype;

    @ColumnInfo(name = "first_name")
    public String firstname;
    @ColumnInfo(name = "last_name")
    public String lastname;
    @ColumnInfo(name = "difficulty_level")
    public String dlevel;
    @ColumnInfo(name = "num_won")
    public int won;
    @ColumnInfo(name = "num_lost")
    public int lost;

//    public void setUserName(String uName) {
//        this.username = uName;
//    }
}