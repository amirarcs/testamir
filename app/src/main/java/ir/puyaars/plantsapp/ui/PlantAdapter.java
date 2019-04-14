package ir.puyaars.plantsapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ir.puyaars.plantsapp.R;
import ir.puyaars.plantsapp.repository.entities.PlantEntity;

import static ir.puyaars.plantsapp.repository.utils.Const.PLANT_ID_KEY;
import static ir.puyaars.plantsapp.repository.utils.Const.TAG;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantHolder> {


    private List<PlantEntity> mPlants;
    private Context mContext;

    PlantAdapter(Context mContext) {
        this.mContext = mContext;
        this.mPlants = new ArrayList<>();
    }

    public void setPlants(List<PlantEntity> mPlants) {
        this.mPlants.clear();
        this.mPlants.addAll(mPlants);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.plant_tile, parent, false);
        return new PlantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantHolder holder, int position) {
        holder.configureWith(mPlants.get(position), mContext);
    }

    @Override
    public int getItemCount() {

        Log.i(TAG, "getItemCount: " + mPlants.size());
        return mPlants.size();
    }

    class PlantHolder extends RecyclerView.ViewHolder {


        AppCompatImageView imageView;

        AppCompatTextView name;
        AppCompatTextView nameEn;
      //  AppCompatTextView identifity;

        public PlantHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.tileImage);
            name = itemView.findViewById(R.id.tileText);
            nameEn = itemView.findViewById(R.id.tileTextEn);
           // identifity = itemView.findViewById(R.id.identity);
        }

        public void configureWith(PlantEntity plantEntity, Context mContext) {
            // TODO Update ui
           if (plantEntity.getCommonName() != null) {
                if (!plantEntity.getCommonName().equals("")) {
                    imageView.setImageBitmap(getBitmapFromAssets(
                            plantEntity.getCommonName(), mContext
                    ));
                }
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
            Log.i("tag",plantEntity.getCommonName());
            name.setText(plantEntity.getName());
            nameEn.setText(plantEntity.getEnName());
          //  identifity.setText(plantEntity.getIdentity());

            this.itemView.setOnClickListener(v -> {
                Intent i = new Intent(mContext, PlantActivity.class);
                i.putExtra(PLANT_ID_KEY,plantEntity.getId());
                mContext.startActivity(i);
            });
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

}
