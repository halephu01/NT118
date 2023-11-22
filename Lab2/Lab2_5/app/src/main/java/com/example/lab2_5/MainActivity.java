package com.example.lab2_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spDish;
    ThumbnailAdapter thumbnailAdapter;
    Dish resDish;
    Button btAdd;
    GridView gvDish;
    EditText edName;
    ArrayList<Dish> arrDish;
    ArrayList<Dish> dishGv;
    DishAdapter adapter;
    CheckBox cbPromotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish);


        arrDish = new ArrayList<Dish>();
        arrDish.add(new Dish("Thumbnail 1", R.drawable.banhmi));
        arrDish.add(new Dish("Thumbnail 2", R.drawable.pho));
        arrDish.add(new Dish("Thumbnail 3", R.drawable.bunbo));
        arrDish.add(new Dish("Thumbnail 4", R.drawable.miquang));

        //final List<String> thumbnails = Arrays.asList("cake", "candy", "chocolate", "icecream", "omelette");
        spDish = (Spinner) findViewById(R.id.sp_thumbail);

        thumbnailAdapter = new ThumbnailAdapter(getApplicationContext(), R.layout.dropdown_item, arrDish);
        spDish.setAdapter(thumbnailAdapter);


        spDish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                resDish = arrDish.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        cbPromotion = (CheckBox) findViewById(R.id.cb_promotion) ;
        btAdd  = (Button) findViewById(R.id.bt_addDish) ;
        gvDish = (GridView) findViewById(R.id.gv_dish);

        edName = (EditText) findViewById(R.id.ed_name);
        dishGv = new ArrayList<Dish>();
        adapter = new DishAdapter(this, R.layout.item_dish,dishGv);
        gvDish.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                Dish dish = new Dish();
                dish.setMonAn(name);

                dish.setThumbnail(resDish.getThumbnail());

                if (cbPromotion.isChecked())
                {
                    dish.setPromotion(true);
                }
                else
                {
                    dish.setPromotion(false);
                }

                dishGv.add(dish);
                edName.setText("");
                spDish.setSelection(0);
                cbPromotion.setChecked(false);
                adapter.notifyDataSetChanged();

                edName.setText("");
                cbPromotion.setChecked(false);
            }
        });
    }
}