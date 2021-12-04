package com.example.farmconnection.database;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.farmconnection.R;


@Database(entities = Product.class, version = 3)
public abstract class ProductDatabase extends RoomDatabase {
    private static ProductDatabase instance;
    public abstract ProductDAO productDAO();


    public static synchronized ProductDatabase getInstance(Context context) {
        instance = instance == null ? Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class, "no_database")
                .fallbackToDestructiveMigration().addCallback(roomCallback).build() : instance;
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private ProductDAO productDAO;
        private PopulateDbAsyncTask(ProductDatabase db){
            productDAO = db.productDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // how to initialize with pictures.
            productDAO.Insert(new Product("1","1","1",1,new byte[1], R.drawable.bartlett_pear_4_fgt_650x));
            productDAO.Insert(new Product("2","2","1",1,new byte[1], R.drawable.bing_cherry_tree_3_fgt_650x));
            return null;
        }
    }

}



