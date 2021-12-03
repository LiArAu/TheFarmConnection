package com.example.farmconnection;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {
    private ProductDAO productDAO;
    private LiveData<List<Product>> products;

    public ProductRepository(Application app){
        ProductDatabase db = ProductDatabase.getInstance(app);
        //we can't call abstract class, but here we have db and room.
        productDAO = db.productDAO();
        products = productDAO.getAllProducts();
    }

    public void insert(Product prod){
        new InsertNoteAsyncTask(productDAO).execute(prod);
    }

    public void update(Product prod){
        new UpdateNoteAsyncTask(productDAO).execute(prod);
    }

    public void delete(Product prod){
        new DeleteNoteAsyncTask(productDAO).execute(prod);
    }

    public LiveData<List<Product>> getAllProducts(){
        return products;
    }

    //async task
    private static class InsertNoteAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDAO productDAO;

        private InsertNoteAsyncTask(ProductDAO productDAO){
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.Insert(products[0]);
            return null;
        }
    }

    //async task
    private static class UpdateNoteAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDAO productDAO;

        private UpdateNoteAsyncTask(ProductDAO productDAO){
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.Update(products[0]);
            return null;
        }
    }

    //async task
    private static class DeleteNoteAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDAO productDAO;

        private DeleteNoteAsyncTask(ProductDAO productDAO){
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.Delete(products[0]);
            return null;
        }
    }

}

