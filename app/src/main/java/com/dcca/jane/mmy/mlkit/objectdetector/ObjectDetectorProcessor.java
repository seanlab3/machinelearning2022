/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dcca.jane.mmy.mlkit.objectdetector;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.dcca.jane.mmy.mlkit.graphic.GraphicOverlay;
import com.dcca.jane.mmy.mlkit.VisionProcessorBase;
import com.google.mlkit.vision.objects.DetectedObject;
import com.google.mlkit.vision.objects.ObjectDetection;
import com.google.mlkit.vision.objects.ObjectDetector;
import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase;

import java.util.List;
import java.util.Locale;

/** A processor to run object detector. */
public class ObjectDetectorProcessor extends VisionProcessorBase<List<DetectedObject>> {

  private static final String TAG = "ObjectDetectorProcessor";

  private final ObjectDetector detector;

  private static Context contxt;

  public ObjectDetectorProcessor(Context context, ObjectDetectorOptionsBase options) {
    super(context);
    detector = ObjectDetection.getClient(options);

    contxt=context;
  }

  @Override
  public void stop() {
    super.stop();
    detector.close();
  }

  @Override
  protected Task<List<DetectedObject>> detectInImage(InputImage image) {
    return detector.process(image);
  }

  @Override
  protected void onSuccess(
      @NonNull List<DetectedObject> results, @NonNull GraphicOverlay graphicOverlay) {
    for (DetectedObject object : results) {


      graphicOverlay.add(new ObjectGraphic(graphicOverlay, object));

      Log.d(TAG, "object TrackingId: " + object.getTrackingId());
      Log.d(TAG, "object Labels: " + object.getLabels());
      for (DetectedObject.Label label : object.getLabels()) {
        Log.d(TAG, "object Labels: " + label.getText());
        Log.d(TAG, "object Labels: " + label.getConfidence());
        checkblogpost(label.getText().toString());
      }

    }
  }

  private static boolean checkblogpost(String value) {


    Log.d(TAG, "value : " + value);

    // GS water bottle
    if (value.contains("Mouse")) {
      Intent view = new Intent(Intent.ACTION_VIEW);
      view.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      view.setData(Uri.parse("http://www.seanlabcoding.com/mirae-yumang-jigeobgun/"));
      contxt.startActivity(view);
      //startActivity(view);
      return true;
    } else if (value.contains("ball")){
      Intent view = new Intent(Intent.ACTION_VIEW);
      view.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      view.setData(Uri.parse("http://www.seanlabcoding.com/ceogceogbagsa-keompyuteo/"));
      contxt.startActivity(view);
      //startActivity(view);
      return true;
    }

    return false;
  }



  @Override
  protected void onFailure(@NonNull Exception e) {
    Log.e(TAG, "Object detection failed!", e);
  }
}
