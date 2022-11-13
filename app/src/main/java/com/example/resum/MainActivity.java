package com.example.resum;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), e -> {
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set image resource
        ImageView profileImageView = findViewById(R.id.profile_image);
        profileImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.profile));
        profileImageView.setLayoutParams(new LinearLayout.LayoutParams(600, 600));
    }

    public void callMe(View view) {
        if (ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED) {
            // call permission is granted
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel: 09101983117"));
            startActivity(intent);
        } else {
            // request call permission
            requestPermissionLauncher.launch(
                    Manifest.permission.CALL_PHONE);
        }
    }
}