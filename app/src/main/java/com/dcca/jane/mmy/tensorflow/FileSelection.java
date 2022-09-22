package com.dcca.jane.mmy.tensorflow;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dcca.jane.mmy.R;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.regex.Pattern;

public class FileSelection extends AppCompatActivity {


    CardView modelpath;
    CardView labelpath;
    CardView next_button;
    CardView Select_tflite;
    CardView Select_label;
    private String tflitePath="";
    private String labelPath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_selection);

        modelpath=findViewById(R.id.tflite);
        labelpath=findViewById(R.id.labels);
        next_button=findViewById(R.id.start);

        Select_tflite=findViewById(R.id.tflite);
        Select_label=findViewById(R.id.labels);

        if(ContextCompat.checkSelfPermission(FileSelection.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        if(ContextCompat.checkSelfPermission(FileSelection.this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2);
        }

        modelpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(FileSelection.this)
                        .withRequestCode(1)
                        .withFilter(Pattern.compile(".*\\.tflite$")) // Filtering files and directories by file name using regexp
                        .start();
            }

        });

        labelpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(FileSelection.this)
                        .withRequestCode(2)
                        .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
                        .start();
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(labelPath.equals("")){
                    Toast.makeText(FileSelection.this,"Label File not selected",Toast.LENGTH_SHORT).show();
                }
                if(tflitePath.equals("")){
                    Toast.makeText(FileSelection.this,".tflite File not selected",Toast.LENGTH_SHORT).show();
                }
                if(!(labelPath.equals("") || tflitePath.equals(""))){
                    Intent next = new Intent(FileSelection.this, ClassifierActivity.class);
                    next.putExtra("TflitePath", tflitePath);
                    next.putExtra("LabelPath", labelPath);
                    startActivity(next);
                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            tflitePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            Toast.makeText(FileSelection.this,tflitePath,Toast.LENGTH_SHORT).show();
            // Do anything with file
            if(!tflitePath.equals("")){
                Select_tflite.setBackground(ContextCompat.getDrawable(this, R.color.done));
            }
        }

        if (requestCode == 2 && resultCode == RESULT_OK) {
            labelPath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            Toast.makeText(FileSelection.this,labelPath,Toast.LENGTH_SHORT).show();
            // Do anything with file
            if(!labelpath.equals("")){
                Select_label.setBackground(ContextCompat.getDrawable(this, R.color.done));
            }
        }
    }
}
