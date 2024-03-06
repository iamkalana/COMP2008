package com.example.mad_prac07;

import static android.provider.ContactsContract.CommonDataKinds.*;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_THUMBNAIL = 1;
    private static final int REQUEST_CONTACT = 2;
    private static final int REQUEST_PHOTO = 3;
    private static final int REQUEST_READ_CONTACT_PERMISSION = 4;

    private Button callBtn, locateBtn, thumbBtn, pickContact, photoBtn, grayScleBtn;
    private EditText phoneNo, latitude, longitude;
    private ImageView thumbnail, originalPhoto;
    private TextView id, name, email, phone;
    private File photoFile;
    private int contactID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callBtn = findViewById(R.id.bCall);
        phoneNo = findViewById(R.id.eTPhone);
        locateBtn = findViewById(R.id.bLocate);
        latitude = findViewById(R.id.eTLatitude);
        longitude = findViewById(R.id.eTLongitude);
        thumbBtn = findViewById(R.id.bThumbPhoto);
        thumbnail = findViewById(R.id.thumbPhoto);
        pickContact = findViewById(R.id.bPickContact);
        id = findViewById(R.id.c_id);
        name = findViewById(R.id.c_name);
        email = findViewById(R.id.c_email);
        phone = findViewById(R.id.c_phone);
        photoBtn = findViewById(R.id.bPhoto);
        originalPhoto = findViewById(R.id.photo);
        grayScleBtn = findViewById(R.id.bGray);

        callBtn.setOnClickListener(view -> {
            makeCall(phoneNo.getText().toString());
        });

        locateBtn.setOnClickListener(view -> {

            locate(latitude.getText().toString(), longitude.getText().toString());
        });

        thumbBtn.setOnClickListener(view -> {
            takeThumb();
        });

        pickContact.setOnClickListener(view -> {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setData(ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, REQUEST_CONTACT);

            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACT_PERMISSION);
            }

        });

        photoBtn.setOnClickListener(view -> {
            takePhoto();
        });

        grayScleBtn.setOnClickListener(view -> {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            originalPhoto.setColorFilter(new ColorMatrixColorFilter(matrix));
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_THUMBNAIL) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            if (image != null) {
                thumbnail.setImageBitmap(image);
            }
        } else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_PHOTO) {
            Bitmap photo = BitmapFactory.decodeFile(photoFile.toString());
            originalPhoto.setImageBitmap(photo);

        } else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CONTACT) {
            Uri contactUri = data.getData();
            String[] queryFields = new String[]{
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME
            };
            Cursor c = getContentResolver().query(
                    contactUri, queryFields, null, null, null);
            try {
                if (c.getCount() > 0) {
                    c.moveToFirst();
                    this.contactID = c.getInt(0);
                    String contactName = c.getString(1);
                    id.setText(String.valueOf(contactID));
                    name.setText(contactName);
                }
            } finally {
                c.close();
            }
            getPhone();
            getEmail();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACT_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Contact Reading Permission Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makeCall(String number) {
        if (!number.isEmpty()) {
            int phoneNumber = Integer.parseInt(number);
            Uri uri = Uri.parse(String.format("tel:%d", phoneNumber));
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            intent.setData(uri);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Enter a phone number!", Toast.LENGTH_SHORT).show();
        }
    }

    private void locate(String latitude, String longitude) {
        if (!latitude.isEmpty() || !longitude.isEmpty()) {
            try {
                double ltVal = Double.parseDouble(latitude);
                double lgVal = Double.parseDouble(longitude);
                Uri uri = Uri.parse(String.format("geo:%f,%f", ltVal, lgVal));
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Invalid data!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Not enough data provided!", Toast.LENGTH_SHORT).show();
        }
    }

    private void takeThumb() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_THUMBNAIL);
    }

    private void getEmail() {
        String result = "";
        Uri emailUri = Email.CONTENT_URI;
        String[] queryFields = new String[]{
                Email.ADDRESS
        };

        String whereClause = Email.CONTACT_ID + "=?";
        String[] whereValues = new String[]{
                String.valueOf(this.contactID)
        };
        Cursor c = getContentResolver().query(
                emailUri, queryFields, whereClause, whereValues, null);
        try {
            c.moveToFirst();
            do {
                String emailAddress = c.getString(0);
                result = result + emailAddress + " ";
            }
            while (c.moveToNext());

        } finally {
            c.close();
        }

        email.setText(result);
    }

    private void getPhone() {
        String result = "";
        Uri phoneUri = Phone.CONTENT_URI;

        String[] queryFields = new String[]{
                Phone.NUMBER
        };
        String whereClause = Phone.CONTACT_ID + "=?";
        String[] whereValues = new String[]{
                String.valueOf(this.contactID)
        };
        Cursor c = getContentResolver().query(
                phoneUri, queryFields, whereClause, whereValues, null);
        try {
            c.moveToFirst();
            do {
                String phoneNumber = c.getString(0);
                result = result + phoneNumber + " ";
            }
            while (c.moveToNext());

        } finally {
            c.close();
        }

        phone.setText(result);
    }

    private void takePhoto() {
        photoFile = new File(getFilesDir(), "photo.jpg");
        Uri cameraUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", photoFile);
        Intent photoIntent = new Intent();
        photoIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);

        PackageManager pm = getPackageManager();
        for (ResolveInfo a : pm.queryIntentActivities(
                photoIntent, PackageManager.MATCH_DEFAULT_ONLY)) {

            grantUriPermission(a.activityInfo.packageName, cameraUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }

        startActivityForResult(photoIntent, REQUEST_PHOTO);
    }
}