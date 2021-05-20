package edu.gatech.seclass.crypto6300.Model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE user_type Like :U_type ORDER BY num_won DESC")
    List<User> getAll(String U_type);

    @Query("SELECT * FROM User WHERE user_name Like :U_Name")
    User findByName(String U_Name);

    @Query("SELECT COUNT(*) FROM User")
    int userCount();

    @Query("UPDATE User SET num_won= :won_num WHERE user_name Like :U_Name")
    void updateWon(String U_Name, int won_num);

    @Query("UPDATE User SET num_lost= :won_lost WHERE user_name Like :U_Name")
    void updateLost(String U_Name, int won_lost);

    @Insert
    void insert(User ui);

    @Insert
    void insertAll(User... Users);


    @Delete
    void delete(User user);

    @Query("DELETE FROM User")
    public void nukeTable();
}
