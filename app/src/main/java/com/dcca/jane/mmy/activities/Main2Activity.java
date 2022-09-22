/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dcca.jane.mmy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.dcca.jane.mmy.R;

import com.dcca.jane.mmy.tensorflow.FileSelection;


/** Entry activity to select the detection mode. */
public class Main2Activity extends AppCompatActivity {

  private static final String TAG = "Main2Activity";

  private  TextView titleView;
  //
  private ImageButton Item1Button;
  private ImageButton Item2Button;
  private ImageButton Item3Button;
  private ImageButton Item4Button;
  private ImageButton Item5Button;
  private ImageButton Item6Button;



  private  TextView item1title;
  private  TextView item2title;
  private  TextView item3title;
  private  TextView item4title;
  private  TextView item5title;
  private  TextView item6title;






  @Override
  protected void onCreate(@Nullable Bundle bundle) {
    super.onCreate(bundle);

    int REQ_CAMERA_PERMISSION = 1001;

    //sean_0909 AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    setContentView(R.layout.activity_main2);


    titleView =(TextView)findViewById(R.id.title1);
    /*
    int hasCameraPermission;
    if (Build.VERSION.SDK_INT >= 23) {
      hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA);
    } else {
      hasCameraPermission = CameraPermissionCheckUtils.checkCameraPermission(this) ?
              PackageManager.PERMISSION_GRANTED : PackageManager.PERMISSION_DENIED;
    }
    Toast.makeText(this, "camera granted : " + (hasCameraPermission == PackageManager.PERMISSION_GRANTED),
            Toast.LENGTH_SHORT).show();

     */



    Item1Button = (ImageButton) findViewById(R.id.btnitem1);
    Item2Button = (ImageButton) findViewById(R.id.btnitem2);
    Item3Button = (ImageButton) findViewById(R.id.btnitem3);
    Item4Button = (ImageButton) findViewById(R.id.btnitem4);
    Item5Button = (ImageButton) findViewById(R.id.btnitem5);
    Item6Button = (ImageButton) findViewById(R.id.btnitem6);

    item1title = (TextView)findViewById(R.id.itemtitle1);
    item2title = (TextView)findViewById(R.id.itemtitle2);
    item3title = (TextView)findViewById(R.id.itemtitle3);
    item4title = (TextView)findViewById(R.id.itemtitle4);
    item5title = (TextView)findViewById(R.id.itemtitle5);
    item6title = (TextView)findViewById(R.id.itemtitle6);





    Item1Button.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        // TODO : click event
        Activity activity = Main2Activity.this;

        activity.startActivity(new Intent(activity, com.dcca.jane.mmy.activities.CameraBarCodeActivity.class));
      }
    });
    Item2Button.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        // TODO : click event
        Activity activity = Main2Activity.this;

        //activity.startActivity(new Intent(activity, com.dcca.jane.mmy.activities.MainActivity.class));
        //activity.startActivity(new Intent(activity, com.dcca.jane.mmy.tensorflow.FileSelection.class));
        activity.startActivity(new Intent(activity, com.dcca.jane.mmy.tensorflow.ClassifierActivity.class));
      }
    });
    Item3Button.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        // TODO : click event
        Activity activity = Main2Activity.this;
        activity.startActivity(new Intent(activity, com.dcca.jane.mmy.activities.CustomObjectDetectionActivity.class));
      }
    });
    Item4Button.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        // TODO : click event
        Activity activity = Main2Activity.this;
        activity.startActivity(new Intent(activity, com.dcca.jane.mmy.activities.TextRecognitionActivity.class));
      }
    });

    Item5Button.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        // TODO : click event
        Activity activity = Main2Activity.this;
        activity.startActivity(new Intent(activity, com.dcca.jane.mmy.activities.CustomImageLabelingActivity.class));
      }
    });

    Item6Button.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View view) {
        // TODO : click event
        Activity activity = Main2Activity.this;

        activity.startActivity(new Intent(activity, com.dcca.jane.mmy.activities.CustomAutoMLImageLabelingActivity.class));
      }
    });

  }

  @Override
  protected void onResume() {
    super.onResume();

/* sean_0909
    if (!Utils.allPermissionsGranted(this)) {
      Utils.requestRuntimePermissions(this);
    }

 */
  }




}
