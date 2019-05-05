package com.example.pranav234.hackathonapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class tender_recycler extends AppCompatActivity {
RecyclerView recyclerView;
adapter adapter;
ArrayList<Details> arrayList;
ImageView Add_note;
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    private FirebaseVisionText text;
    private String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender_recycler);
        recyclerView = findViewById(R.id.RecyclerView);
        Add_note = findViewById(R.id.add_noteplus);
        Add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photo));
                imageUri = Uri.fromFile(photo);
                startActivityForResult(intent, TAKE_PICTURE);
            }
        });
        arrayList = new ArrayList<>();
        arrayList.add(new Details("Global Tender", "Steve A.Limited", "Lucid Parks,San Francisco", "29th June, 2019", "100 $", "Require textiles/fabrics.", "4.2K people interested"));
        arrayList.add(new Details("Private Tender", "Sitaram Caterers", "Okhla Apartments,New Delhi", "31st May,2019", "3 Lakhs", "Require steele and copper utensils", "6"));
        arrayList.add(new Details("Government Tender", "Bharat Petroleum", "Dusshera Ground,sector-31,Gurgaon", "30th September", "", "Require 16 Wheel Trucks", "7"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new adapter(arrayList);
        recyclerView.setAdapter(adapter);

    }




    private void gettheResult(FirebaseVisionText firebaseVisionText) {

        List<FirebaseVisionText.TextBlock> textBlocks=firebaseVisionText.getTextBlocks();
        if (textBlocks.size() == 0) {
            Toast.makeText(this, "nothing found", Toast.LENGTH_SHORT).show();
            return;}
        for (int i = 0; i < textBlocks.size(); i++) {
            List<FirebaseVisionText.Line> lines = textBlocks.get(i).getLines();

            for (int j = 0; j < lines.size(); j++) {
                List<FirebaseVisionText.Element> elements = lines.get(i).getElements();
                for (int k = 0; k < elements.size(); k++) {

                    s1=s1+elements.get(k).getText();
                }
            }
        }
    Log.i("String abc",s1);
    }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case TAKE_PICTURE:
                    if (resultCode == Activity.RESULT_OK) {
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        //ImageView imageView = (ImageView) findViewById(R.id.ImageView);
                        ContentResolver cr = getContentResolver();
                        Bitmap bitmap;
                        try {
                            bitmap = android.provider.MediaStore.Images.Media
                                    .getBitmap(cr, selectedImage);
                            FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
                           // imageView.setImageBitmap(bitmap);
                            final FirebaseVisionImage firebaseVisionImage= FirebaseVisionImage.fromBitmap(bitmap);
                            FirebaseVisionTextRecognizer recognizer= FirebaseVision.getInstance().getOnDeviceTextRecognizer();
                            recognizer.processImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                                @Override
                                public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                    text=firebaseVisionText;

                                    Log.d("text recognixer","Success");
                                    gettheResult(firebaseVisionText);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("Text Recognizer","fsiled");
                                    e.printStackTrace();
                                }
                            });

                            Toast.makeText(this, selectedImage.toString(),
                                    Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                                    .show();
                            Log.e("Camera", e.toString());
                        }
                    }
            }
        }


}
