package edu.gatech.seclass.crypto6300.Model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface CryptogramDao {
    @Query("SELECT * FROM Cryptogram")
    List<Cryptogram> getAll();

    @Query("SELECT * FROM Cryptogram WHERE cryptogram_name  Like :input_Name")
    Cryptogram findByName(String input_Name);

    @Query("SELECT COUNT(*) FROM Cryptogram")
    int cryptogramCount();


    @Insert
    void insert(Cryptogram ci);

    @Insert
    void insertAll(Cryptogram... Cryptograms);


    @Delete
    void delete(Cryptogram cryptogram);

    @Query("DELETE FROM Cryptogram")
    public void nukeTable();
}
