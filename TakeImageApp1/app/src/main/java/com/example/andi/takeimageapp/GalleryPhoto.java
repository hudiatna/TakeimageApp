package com.example.aji.takeimageapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class GalleryPhoto extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    ImageAdapter myImageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_photo);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        myImageAdapter = new ImageAdapter(this);
        gridView.setAdapter(myImageAdapter);
        gridView.setOnItemClickListener(this);

        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();
        String targetPath = ExternalStorageDirectoryPath + "/DCIM/Camera/";
        Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_SHORT).show();
        File targetDirector = new File(targetPath);
        File[] files = targetDirector.listFiles();
        //Log.d("Files", targetDirector.getAbsolutePath()); //String.valueOf(files.length));
        //Log.d("Files count", String.valueOf(files.length));
        for (File file : files){
            myImageAdapter.add(file.getAbsolutePath());
        }
        //myImageAdapter.notifyDataSetChanged();

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int positions, long id) {
        Toast.makeText(getApplicationContext(), "Gambar No "+ positions +"Gambar Dipilih" , Toast.LENGTH_SHORT).show();
        Intent is = new Intent(GalleryPhoto.this, SinglePhoto.class);
        is.putExtra("mypath", myImageAdapter.itemList.get(positions));
        startActivity(is);
    }
}
