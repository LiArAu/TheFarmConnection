package com.example.farmconnection.database;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository repo;
    private LiveData<List<Product>> products;

    public ProductViewModel(@NonNull Application app) {
        super(app);
        repo = new ProductRepository(app);
        products = repo.getAllProducts();
    }

    public void insert(Product prod) { repo.insert(prod); }
    public void update(Product prod){
        repo.update(prod);
    }
    public void delete(Product prod){
        repo.delete(prod);
    }

    public LiveData<List<Product>> getAllProducts(){
        return products;
    }
}