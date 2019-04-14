package ir.puyaars.plantsapp.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import androidx.lifecycle.ViewModelProviders;
import ir.puyaars.plantsapp.R;
import ir.puyaars.plantsapp.repository.entities.PlantEntity;
import ir.puyaars.plantsapp.repository.entities.Spec;

import static ir.puyaars.plantsapp.repository.utils.Const.PLANT_ID_KEY;

public class PlantActivity extends AppCompatActivity {

    private PlantViewModel presenter;
    private PlantEntity mPlant;
    com.google.android.material.appbar.AppBarLayout appBarLayout;
    TextView txt;
    TextView s0t;
    TextView s0d;
    TextView s1t;
    TextView s1d;
    TextView s2t;
    TextView s2d;
    TextView s3t;
    TextView s3d;
    TextView s4t;
    TextView s4d;
    TextView s5t;
    TextView s5d;
    TextView s6t;
    TextView s6d;
    TextView s7t;
    TextView s7d;
    TextView s8t;
    TextView s8d;
    String labal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initPresenter();
        setTitle(labal);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(view -> {
//            // TODO favorite the plant
//            if (mPlant != null) {
//                mPlant.setFavourite(!mPlant.isFavourite());
//                presenter.savePlant(mPlant);
//            }
//        });
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        appBarLayout = (com.google.android.material.appbar.AppBarLayout)findViewById(R.id.app_bar);
        txt=(TextView)findViewById(R.id.txtContent);
        s0t=(TextView)findViewById(R.id.s0t);
        s0d=(TextView)findViewById(R.id.s0d);
        s1t=(TextView)findViewById(R.id.s1t);
        s1d=(TextView)findViewById(R.id.s1d);
        s2t=(TextView)findViewById(R.id.s2t);
        s2d=(TextView)findViewById(R.id.s2d);
        s3t=(TextView)findViewById(R.id.s3t);
        s3d=(TextView)findViewById(R.id.s3d);
        s4t=(TextView)findViewById(R.id.s4t);
        s4d=(TextView)findViewById(R.id.s4d);
        s5t=(TextView)findViewById(R.id.s5t);
        s5d=(TextView)findViewById(R.id.s5d);
        s6t=(TextView)findViewById(R.id.s6t);
        s6d=(TextView)findViewById(R.id.s6d);
        s7t=(TextView)findViewById(R.id.s7t);
        s7d=(TextView)findViewById(R.id.s7d);
        s8t=(TextView)findViewById(R.id.s8t);
        s8d=(TextView)findViewById(R.id.s8d);
        }

    private void initPresenter() {
        presenter = ViewModelProviders.of(this).get(PlantViewModel.class);
        presenter.mPlant.observe(this,plantEntity -> {

            // TODO update UI
            mPlant = plantEntity;
            labal=mPlant.getName();
            String nameImgAfter = mPlant.getCommonName();
            String nameImg = nameImgAfter.replace(".jpg","");
            Log.i("tag",nameImg);
            switch (nameImg){
                case "barganjiri":
                    appBarLayout.setBackgroundResource(R.drawable.barganjiri);
                    break;
                case "shamdoni":
                    appBarLayout.setBackgroundResource(R.drawable.shamdoni);
                    break;
}
            txt.setText(mPlant.getIdentity());
            List<Spec> list =mPlant.getSpecs();
            Log.i("TTTTAAAAAGGGG",""+list.size());

int a = 0;
        if(a<list.size()){
            Spec sp= list.get(0);
            s0t.setText(sp.getTitle());
            s0d.setText(sp.getDesc());
        a++;
        }
        if(a<list.size()){
            Spec sp= list.get(1);
            s1t.setText(sp.getTitle());
            s1d.setText(sp.getDesc());
        a++;
        }
            if(a<list.size()){
            Spec sp= list.get(2);
            s2t.setText(sp.getTitle());
            s2d.setText(sp.getDesc());
            a++;
        }
            if(a<list.size()){
            Spec sp= list.get(3);
            s3t.setText(sp.getTitle());
            s3d.setText(sp.getDesc());
                a++;
        }
            if(a<list.size()){
            Spec sp= list.get(4);
            s4t.setText(sp.getTitle());
            s4d.setText(sp.getDesc());
                a++;
        }
            if(a<list.size()){
            Spec sp= list.get(5);
            s5t.setText(sp.getTitle());
            s5d.setText(sp.getDesc());
                a++;
        }
            if(a<list.size()){
            Spec sp= list.get(6);
            s6t.setText(sp.getTitle());
            s6d.setText(sp.getDesc());
                a++;
        }
            if(a<list.size()){
            Spec sp= list.get(7);
            s7t.setText(sp.getTitle());
            s7d.setText(sp.getDesc());
                a++;
        }
            if(a<list.size()){
            Spec sp= list.get(8);
            s8t.setText(sp.getTitle());
            s8d.setText(sp.getDesc());
                a++;
        }

        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            // no Id so get out soon
            finish();
        } else {
            int plantId = extras.getInt(PLANT_ID_KEY);
            presenter.loadData(plantId);
        }

    }
    private Bitmap getBitmapFromAssets(String fileName, Context context) {

        AssetManager am = context.getAssets();
        InputStream is = null;
        try {
            is = am.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(is);
    }
}
