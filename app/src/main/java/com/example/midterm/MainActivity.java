package com.example.midterm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddYearAndModelDialog.saveFragment{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView myImage;
    Car myCar;
    ArrayList<Car> carsArrayList = new ArrayList<Car>(0);
    EditText myName;
    Bitmap photo;
    Bundle saveBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImage = (ImageView) findViewById(R.id.personalImageID);
        myName = (EditText) findViewById(R.id.studentName);
        myCar = new Car();

       /* if(saveBundle != null){
            carsArrayList = savedInstanceState.getParcelableArrayList("carList");
        } */
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void uploade(View view) {
        //open camera app and take a photo of the car. (use an emulator fixed camera instead if you don't have physical device)
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            if(data != null){
                photo = (Bitmap) data.getExtras().get("data");
                myImage.setImageBitmap(photo);

                Log.d("camera ---- > ", "" + data.getExtras().get("data")); //log the extra
            }
        }
    }

    public void addNewCarModelAndYear(View view) {
        //open dialog fragment to get the car model and year from the owner
        FragmentManager fr = getFragmentManager();
        AddYearAndModelDialog dialog = new AddYearAndModelDialog();
        Bundle bundle = new Bundle();
        //bundle.putString("owner", myName.getText().toString());

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        myCar.setImageData(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
        myCar.setOwnerName(myName.getText().toString());

        bundle.putParcelable("car", myCar);

        dialog.setArguments(bundle);

        FragmentTransaction tr = fr.beginTransaction();
        dialog.show(tr, "fragment");
    }


    public void saveCar(View view) {
        //Save car object and navigate to report activity to show the list of all cars
        Intent intent = new Intent(this, ReportActivity.class);
        if(myCar != null && myCar.getModel() != null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytes = stream.toByteArray();
            if(myCar.getImageData() == null && myCar.getOwnerName() == null) {
                myCar.setImageData(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                myCar.setOwnerName(myName.getText().toString());
            }

            carsArrayList.add(myCar);
            myCar = new Car(); //so the previous data doesn't get overrite
            intent.putParcelableArrayListExtra("cars", carsArrayList);

            startActivity(intent);
        }else{
            Toast.makeText(this, "Please fill out the model and year", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void dataFromFragment(Car car){
        myCar = car;
    }

}