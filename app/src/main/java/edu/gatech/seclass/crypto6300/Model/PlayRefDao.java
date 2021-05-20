package edu.gatech.seclass.crypto6300.Model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PlayRefDao {
    @Query("SELECT * FROM PlayRef WHERE play_user_name LIKE :U_name")
    List<PlayRef> getAllByUser(String U_name);

    @Query("SELECT * FROM PlayRef WHERE play_user_name  LIKE :U_Name AND play_crypt_name LIKE :C_Name")
    PlayRef findByUserCrypt(String U_Name, String C_Name);

    @Query("SELECT COUNT(*) FROM PlayRef")
    int playrefCount();

    @Insert
    void insert(PlayRef pi);

    @Insert
    void insertAll(PlayRef... playrefs);

    @Query("UPDATE PlayRef SET previous_phrase= :new_phrase WHERE play_user_name Like :U_name AND play_crypt_name LIKE :C_name") //Update previous phrase
    void updatePPhrase(String U_name, String C_name, String new_phrase);

    @Query("UPDATE PlayRef SET play_status= :new_status WHERE play_user_name Like :U_name AND play_crypt_name LIKE :C_name") //Update previous phrase
    void updateStatus(String U_name, String C_name, String new_status);

    @Query("UPDATE PlayRef SET attempt_times= :new_attempt WHERE play_user_name Like :U_name AND play_crypt_name LIKE :C_name") //Update Attempted Times
    void updateAttemptNum(String U_name, String C_name, int new_attempt);

    @Delete
    void delete(PlayRef playref);

    @Query("DELETE FROM PlayRef")
    public void nukeTable();


}
