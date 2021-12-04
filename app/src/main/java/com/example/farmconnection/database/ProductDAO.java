package com.example.farmconnection.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void Insert(Product prod);
    @Update
    void Update(Product prod);
    @Delete
    void Delete(Product prod);

    @Query("select * from product_table order by prod_id asc")
    LiveData<List<Product>> getAllProducts(); // Live display change if there's any update.
}
