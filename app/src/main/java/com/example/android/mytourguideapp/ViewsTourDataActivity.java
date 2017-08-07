package com.example.android.mytourguideapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

/**
 * Created by Admin on 7/31/2017.
 */

public class ViewsTourDataActivity extends AppCompatActivity {
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    private static final int RC_PHOTO_PICKER =  2;
    private EditText tHeadEditText;
    private EditText tDescriptionEditText;
    private Button tPhotoPickerButton;
    private Button tSendButton;
    private EditText tAddressEditText;
    private EditText tTelephoneEditText;
    private EditText tHoursTextEdit;
    DatabaseReference tTourDatabaseReference;
    private StorageReference tTourPhotosStorageReference;
    private ImageView PreviewImageView;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK ){
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImageUri);
                PreviewImageView.setImageBitmap(bm);

            } catch (IOException e) {
                e.printStackTrace();
            }
            StorageReference photoRef = tTourPhotosStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    Tours tours = new Tours(tHeadEditText.getText().toString(),tDescriptionEditText.getText().toString(),downloadUri.toString(),tAddressEditText.getText().toString(),tHoursTextEdit.getText().toString(),tTelephoneEditText.getText().toString());;
                    tTourDatabaseReference.push().setValue(tours);
                    tHeadEditText.setText("");
                    tDescriptionEditText.setText("");
                    tAddressEditText.setText("");
                    tTelephoneEditText.setText("");
                    tHoursTextEdit.setText("");
                    PreviewImageView.setVisibility(View.GONE);
                    Toast.makeText(ViewsTourDataActivity.this, "Information saved...",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_data);
        tTourDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Views");

        tTourPhotosStorageReference = FirebaseStorage.getInstance().getReference().child("photos_tour");

        tPhotoPickerButton = (Button) findViewById(R.id.photoPickerButton);
        tHeadEditText = (EditText) findViewById(R.id.headEditText);
        tDescriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        tSendButton = (Button) findViewById(R.id.sendButton);
        tAddressEditText = (EditText) findViewById(R.id.addressEditText);
        tAddressEditText = (EditText) findViewById(R.id.addressEditText);
        tTelephoneEditText = (EditText) findViewById(R.id.telephoneEditText);
        tHoursTextEdit = (EditText) findViewById(R.id.hoursEditText);
        tSendButton = (Button) findViewById(R.id.sendButton);
        PreviewImageView = (ImageView) findViewById(R.id.preview_image);

        tPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

            }
        });






        tSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tours tours = new Tours(tHeadEditText.getText().toString(),tDescriptionEditText.getText().toString(),null,tAddressEditText.getText().toString(),tHoursTextEdit.getText().toString(),tTelephoneEditText.getText().toString());
                tTourDatabaseReference.push().setValue(tours);
                tHeadEditText.setText("");
                tDescriptionEditText.setText("");
                tAddressEditText.setText("");
                tTelephoneEditText.setText("");
                tHoursTextEdit.setText("");
                Toast.makeText(ViewsTourDataActivity.this, "Information saved...",Toast.LENGTH_SHORT).show();
            }
        });






        tHeadEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tHeadEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});


        tDescriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tDescriptionEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        tAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tAddressEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        tTelephoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tTelephoneEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        tHoursTextEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tHoursTextEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.eat_admin_menu:
                Intent intentEat = new Intent(this,EatTourDataActivity.class);
                startActivity(intentEat);
                return true;
            case R.id.drink_admin_menu:
                Intent intentDrink  = new Intent(this,DrinkTourDataActivity.class);
                startActivity(intentDrink);
                return true;
            case R.id.stay_admin_menu:
                Intent intentStay = new Intent(this,StayTourDataActivity.class);
                startActivity(intentStay);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
