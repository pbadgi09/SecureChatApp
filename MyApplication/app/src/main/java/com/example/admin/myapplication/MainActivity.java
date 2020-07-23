package com.example.admin.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.admin.myapplication.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private static final int IMAGE_REQUEST_CODE = 100;
    private static final int VIDEO_REQUEST_CODE = 200;
    private Uri fileUri;
    private Button imageButton;
    private Button videoButton;
    private VideoView showVideo;
    private ImageView showImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton =(Button) findViewById(R.id.image_button);
        videoButton = (Button)findViewById(R.id.video_button);

        showImage = (ImageView) findViewById(R.id.image_view);
        showVideo =(VideoView) findViewById(R.id.video_view);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });

        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureVideo();
            }
        });


    }

    private void captureVideo() {

        Intent imageIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        fileUri = Utils.getOutpurMediaFile(Utils.TYPEVIDEO);

        imageIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        startActivityForResult(imageIntent, 200);

    }


    private void captureImage() {

        Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = Utils.getOutpurMediaFile(Utils.TYPEIMAGE);

        imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(imageIntent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                reviewCaptureImage();
            } else {

                if (resultCode == RESULT_CANCELED)
                    Toast.makeText(this, "Error In code", Toast.LENGTH_LONG).show();

                else {
                    Toast.makeText(this, "Can not find Image", Toast.LENGTH_LONG).show();
                }
            }
        } else if (resultCode == RESULT_OK) {
            reviewCaptureVideo();
        } else {

            if (resultCode == RESULT_CANCELED)
                Toast.makeText(this, "Error In code", Toast.LENGTH_LONG).show();

            else {
                Toast.makeText(this, "Can not find video", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void reviewCaptureVideo() {
        showVideo.setVisibility(View.VISIBLE);
        showImage.setVisibility(View.GONE);
        showVideo.setVideoPath(fileUri.getPath());
        showVideo.start();
    }

    private void reviewCaptureImage() {
        showVideo.setVisibility(View.GONE);
        showImage.setVisibility(View.VISIBLE);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
        showImage.setImageBitmap(bitmap);


    }


}
