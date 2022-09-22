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

package com.dcca.jane.mmy.mlkit.barcodescanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;
import com.dcca.jane.mmy.mlkit.graphic.GraphicOverlay;
import com.dcca.jane.mmy.mlkit.VisionProcessorBase;

import java.util.List;

/** Barcode Detector Demo. */
public class BarcodeScannerProcessor extends VisionProcessorBase<List<Barcode>> {

  private static final String TAG = "BarcodeProcessor";

  private final BarcodeScanner barcodeScanner;



  private static Context contxt;

  public BarcodeScannerProcessor(Context context) {
    super(context);
    // Note that if you know which format of barcode your app is dealing with, detection will be
    // faster to specify the supported barcode formats one by one, e.g.
    // new BarcodeScannerOptions.Builder()
    //     .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
    //     .build();

    contxt=context;

    barcodeScanner = BarcodeScanning.getClient();
  }

  @Override
  public void stop() {
    super.stop();
    barcodeScanner.close();
  }

  @Override
  protected Task<List<Barcode>> detectInImage(InputImage image) {
    return barcodeScanner.process(image);
  }

  @Override
  protected void onSuccess(
      @NonNull List<Barcode> barcodes, @NonNull GraphicOverlay graphicOverlay) {
    if (barcodes.isEmpty()) {
      Log.v(MANUAL_TESTING_LOG, "No barcode has been detected");
      Log.d(TAG, "No barcode has been detected");
    }
    for (int i = 0; i < barcodes.size(); ++i) {
      Barcode barcode = barcodes.get(i);
      graphicOverlay.add(new BarcodeGraphic(graphicOverlay, barcode));
      logExtrasForTesting(barcode);
    }
  }

  private static void logExtrasForTesting(Barcode barcode) {
    if (barcode != null) {
      if (barcode.getBoundingBox() != null) {
        Log.v(
            MANUAL_TESTING_LOG,
            String.format(
                "Detected barcode's bounding box: %s", barcode.getBoundingBox().flattenToString()));
        Log.d(TAG, String.format(
                "Detected barcode's bounding box: %s", barcode.getBoundingBox().flattenToString()));
      }
      if (barcode.getCornerPoints() != null) {
        Log.v(
            MANUAL_TESTING_LOG,
            String.format(
                "Expected corner point size is 4, get %d", barcode.getCornerPoints().length));
        Log.d(TAG, String.format(
                "Expected corner point size is 4, get %d", barcode.getCornerPoints().length));
      }
      for (Point point : barcode.getCornerPoints()) {
        Log.v(
            MANUAL_TESTING_LOG,
            String.format("Corner point is located at: x = %d, y = %d", point.x, point.y));
        Log.d(TAG, String.format(
                "Corner point is located at: x = %d, y = %d",  point.x, point.y));
      }

      Log.d(TAG, "barcode display value: " + barcode.getDisplayValue());
      Log.d(TAG, "barcode raw value: " + barcode.getRawValue());

      checkblogpost(barcode.getDisplayValue().toString());



      Barcode.DriverLicense dl = barcode.getDriverLicense();
      if (dl != null) {
        Log.d(TAG, "driver license city: " + dl.getAddressCity());
        Log.d(TAG, "driver license state: " + dl.getAddressState());
        Log.d(TAG, "driver license street: " + dl.getAddressStreet());
        Log.d(TAG, "driver license zip code: " + dl.getAddressZip());
        Log.d(TAG, "driver license birthday: " + dl.getBirthDate());
        Log.d(TAG, "driver license document type: " + dl.getDocumentType());
        Log.d(TAG, "driver license expiry date: " + dl.getExpiryDate());
        Log.d(TAG, "driver license first name: " + dl.getFirstName());
        Log.d(TAG, "driver license middle name: " + dl.getMiddleName());
        Log.d(TAG, "driver license last name: " + dl.getLastName());
        Log.d(TAG, "driver license gender: " + dl.getGender());
        Log.d(TAG, "driver license issue date: " + dl.getIssueDate());
        Log.d(TAG, "driver license issue country: " + dl.getIssuingCountry());
        Log.d(TAG, "driver license number: " + dl.getLicenseNumber());


      }
    }
  }

  private static boolean checkblogpost(String value) {

    String substring="";
    String company=value.substring(3,7);
    String product=value.substring(7,12);
    Log.d(TAG, "company : " + company);
    Log.d(TAG, "product : " + product);
    // GS water bottle
    if (company.equals("9482") &&(product.equals("50048"))) {
      Intent view = new Intent(Intent.ACTION_VIEW);
      view.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      view.setData(Uri.parse("http://www.seanlabcoding.com/mirae-yumang-jigeobgun/"));
      contxt.startActivity(view);
      //startActivity(view);
      return true;
    } else if (company.equals("1056") &&(product.equals("04988"))){
      Intent view = new Intent(Intent.ACTION_VIEW);
      view.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      view.setData(Uri.parse("http://www.seanlabcoding.com/ceogceogbagsa-keompyuteo/"));
      contxt.startActivity(view);
      //startActivity(view);
      return true;
    }
    else if (company.equals("1043") &&(product.equals("02278"))){
      Intent view = new Intent(Intent.ACTION_VIEW);
      view.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      view.setData(Uri.parse("http://www.seanlabcoding.com/segye-coegoyi-ingongjineung-daehageun/" +
              ""));
      contxt.startActivity(view);
      //startActivity(view);
      return true;
    }
    else if (company.equals("9055") &&(product.equals("54642")||(product.equals("54644")))){
      Intent view = new Intent(Intent.ACTION_VIEW);
      view.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      view.setData(Uri.parse("http://www.seanlabcoding.com/segye-coegoyi-ingongjineung-daehageun/" +
              ""));
      contxt.startActivity(view);
      //startActivity(view);
      return true;
    }
    return false;
  }



  @Override
  protected void onFailure(@NonNull Exception e) {

    Log.e(TAG, "Barcode detection failed " + e);

  }
}
