//package edu.gatech.seclass.crypto6300.Local;
//
//import android.arch.persistence.room.Database;
//import android.arch.persistence.room.Room;
//import android.arch.persistence.room.RoomDatabase;
//import android.content.Context;
//
//import edu.gatech.seclass.crypto6300.Model.User;
//
//import static edu.gatech.seclass.crypto6300.Local.CryptogramDatabase.DATABASE_VERSION;
//
//@Database(entities = User.class,version = DATABASE_VERSION)
//public abstract class CryptogramDatabase extends RoomDatabase {
//    public static final int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "CS6300_Cryptogram";
//
//    public abstract UserDAO userDAO();
//
//    public static CryptogramDatabase mInstance;
//
//    public static CryptogramDatabase getInstance(Context context)
//    {
//        if(mInstance==null)
//        {
//            mInstance = Room.databaseBuilder(context,CryptogramDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration().build();
//        }
//        return mInstance;
//    }
//
//}
