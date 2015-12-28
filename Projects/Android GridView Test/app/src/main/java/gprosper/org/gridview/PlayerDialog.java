package gprosper.org.gridview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayerDialog extends Activity {
    ImageView playerPicture;
    TextView playerNameTextView;
    TextView playerPositionTextView;
    TextView playerNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_dialog);

        setUpUI();

        Intent data = getIntent();
        if (data != null) {
            playerNameTextView.setText(data.getStringExtra("playerName"));
            playerPositionTextView.setText(data.getStringExtra("playerPosition"));
            playerNumberTextView.setText("#" + data.getIntExtra("playerNumber",0));

            byte[] picData = data.getByteArrayExtra("playerPicture");
            Bitmap picture = BitmapFactory.decodeByteArray(picData,0,picData.length);
            playerPicture.setImageBitmap(picture);
        }
    }

    private void setUpUI() {
        playerPicture = (ImageView) findViewById(R.id.playerPicture);
        playerNameTextView = (TextView) findViewById(R.id.playerName);
        playerPositionTextView = (TextView) findViewById(R.id.playerPosition);
        playerNumberTextView = (TextView) findViewById(R.id.playerNumber);
    }
}
