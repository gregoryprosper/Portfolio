package gprosper.org.invertimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ProgressBar progressBar;

    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Get Image from drawable file
        Drawable drawableImage = getResources().getDrawable(R.drawable.image);
        image = ((BitmapDrawable) drawableImage).getBitmap();

        imageView.setImageBitmap(image);
    }

    public void invertButtonClicked(View view) {
        progressBar.setVisibility(View.VISIBLE);

        Thread invertImageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                image = invertImage(image);
                //Set ImageView image on ui thread
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(image);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
        invertImageThread.start();
    }

    public void resetButtonClicked(View view) {
        Drawable drawableImage = getResources().getDrawable(R.drawable.image);
        image = ((BitmapDrawable) drawableImage).getBitmap();

        imageView.setImageBitmap(image);
    }

    public void filterButtonClicked(View view) {
        //Create LayerDrawable
        Drawable layers[] = new Drawable[2];
        layers[0] = new BitmapDrawable(getResources(), image);
        layers[1] = getResources().getDrawable(R.drawable.filter);
        LayerDrawable layerDrawable = new LayerDrawable(layers);

        //Create fresh bitmap to draw on
        image = Bitmap.createBitmap(image.getWidth(),image.getHeight(),image.getConfig());

        //Draw LayerDrawable to bitmap
        layerDrawable.setBounds(0,0,image.getWidth(),image.getHeight());
        layerDrawable.draw(new Canvas(image));

        imageView.setImageBitmap(image);
    }

    public void saveImage(View view) {
        Thread saveImageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                MediaStore.Images.Media.insertImage(getContentResolver(),image,"yourFilteredImage","Your modified Image");
            }
        });
        saveImageThread.start();
    }

    public void send(View view) {
        //Get image file from app pictures dir
        File imageFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"image.jpg");
        Uri imageUri = null;

        //Save image to file and create image Uri
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(imageFile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, out);
        }
        catch (FileNotFoundException ex){
            Toast.makeText(this, "Error Happened: " + ex.getMessage(), Toast.LENGTH_LONG ).show();
            return;
        }
        finally {
            imageUri = Uri.fromFile(imageFile);
        }


        Intent intent = new Intent(Intent.ACTION_SEND);

        //Set Up Intent
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.putExtra(Intent.EXTRA_TEXT, "Attached Image");

        Intent chooser = Intent.createChooser(intent,"Send Picture");
        startActivity(chooser);
    }

    private static Bitmap invertImage(Bitmap originalImage) {
        Bitmap finalImage = Bitmap.createBitmap(originalImage.getWidth(),originalImage.getHeight(),originalImage.getConfig());

        int A,R,G,B;
        int pixelColor;

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        //Loop through every pixel and invert them
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                pixelColor = originalImage.getPixel(x,y);

                A = Color.alpha(pixelColor);
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);

                finalImage.setPixel(x,y,Color.argb(A,R,G,B));
            }
        }
        return finalImage;
    }
}
