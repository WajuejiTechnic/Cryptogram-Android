//package edu.gatech.seclass.crypto6300.Local;
//
//import android.arch.persistence.room.Delete;
//import android.arch.persistence.room.Query;
//import android.arch.persistence.room.Update;
//
//import java.util.List;
//
//import edu.gatech.seclass.crypto6300.Model.User;
//import io.reactivex.Flowable;
//
//public interface UserDAO {
//    @Query("SELECT* FROM User WHERE id =:userId")
//    Flowable<User> getUserById(int userId);
//
//    @Query("SELECT * FROM User")
//    Flowable<List<User>> getAllUsers();
//
//    @Update
//    void updateUser(User... users);
//
//    @Delete
//    void deleteUser(User user);
//
//    @Query("DELETE FROM User")
//    void deleteAllUsers();
//}
