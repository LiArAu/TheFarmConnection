package com.example.farmconnection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SelectNewPlants_database_version extends AppCompatActivity {
    private ProductViewModel pvm;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_new_plants);

        RecyclerView rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter ra = new RecyclerAdapter();

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(SelectNewPlants_database_version.this,2));
        rv.setAdapter(ra);

        pvm = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ProductViewModel.class);
        pvm.getAllProducts().observe(SelectNewPlants_database_version.this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                // update Recycler View
                ra.setProducts(products);
            }
        });

        // activate slide left/right to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override // move
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //get the position we want to delete
                pvm.delete(ra.getProducts(viewHolder.getAdapterPosition()));
                Toast.makeText(SelectNewPlants_database_version.this, "Plant has been deleted!", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(rv);

        ra.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent2 = new Intent(SelectNewPlants_database_version.this, ProductDetailModel.class);
                intent2.putExtra("title",product.getTitle());
                intent2.putExtra("content",product.getContent());
                intent2.putExtra("provider",product.getProvider());
                intent2.putExtra("price",product.getPrice());
                intent2.putExtra("image",product.getImage());
                startActivity(intent2);
            }

        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectNewPlants_database_version.this, UploadNewPlant.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("content");
            String provider = data.getStringExtra("provider");
            int price = Integer.valueOf(data.getStringExtra("price"));
            byte[] image = data.getByteArrayExtra("image");
            int image_id = data.getIntExtra("picid", -1);
            Product product = new Product(title, description, provider, price, image, image_id);
            pvm.insert(product);
        }
    }
}
//        } else if (requestCode == 2 && resultCode == RESULT_OK) {
//            int id = data.getIntExtra("id", -1);
//            String title = data.getStringExtra("newtitle");
//            String description = data.getStringExtra("newcontent");
//            String provider = data.getStringExtra("newprovider");
//            int price = data.getIntExtra("newprice", -1);
//            byte[] image = data.getByteArrayExtra("newpic");
//            int image_id = data.getIntExtra("newpicid", -1);
//
//            Product product = new Product(title, description, provider, price, image, image_id);
//            product.setId(id);
//            pvm.update(product);
//        }

