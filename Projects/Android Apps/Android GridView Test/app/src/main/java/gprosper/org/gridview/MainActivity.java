package gprosper.org.gridview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import gprosper.org.gridview.model.Player;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    GridView gridView;

    ArrayList<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPlayers();

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new PlayerGridViewAdapter(this, players));
        gridView.setOnItemClickListener(this);
    }

    private void getPlayers() {
        Bitmap player1Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.dwayne_wade)).getBitmap();
        Player player1 = new Player("Dwayne", "Wade", 3, Player.GUARD, player1Pic);

        Bitmap player2Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.amare_stoudemire)).getBitmap();
        Player player2 = new Player("Amare", "Stoudemire", 5, Player.FORWARD_CENTER, player2Pic);

        Bitmap player3Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.beno_udrih)).getBitmap();
        Player player3 = new Player("Beno", "Udrih", 19, Player.GUARD, player3Pic);

        Bitmap player4Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.chris_andersen)).getBitmap();
        Player player4 = new Player("Chris", "Andersen", 11, Player.FORWARD_CENTER, player4Pic);

        Bitmap player5Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.chris_bosh)).getBitmap();
        Player player5 = new Player("Chris", "Bosh", 1, Player.FORWARD, player5Pic);

        Bitmap player6Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.gerald_green)).getBitmap();
        Player player6 = new Player("Gerald", "Green", 14, Player.FORWARD, player6Pic);

        Bitmap player7Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.goran_dragic)).getBitmap();
        Player player7 = new Player("Goran", "Dragic", 7, Player.GUARD, player7Pic);

        Bitmap player8Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.hassan_whiteside)).getBitmap();
        Player player8 = new Player("Hassan", "Whiteside", 21, Player.CENTER, player8Pic);

        Bitmap player9Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.jarnell_stokes)).getBitmap();
        Player player9 = new Player("Jarnell", "Stokes", 12, Player.FORWARD_CENTER, player9Pic);

        Bitmap player10Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.josh_mcroberts)).getBitmap();
        Player player10 = new Player("Josh", "McRoberts", 4, Player.FORWARD, player10Pic);

        Bitmap player11Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.josh_richardson)).getBitmap();
        Player player11 = new Player("Josh", "Richardson", 0, Player.GUARD, player11Pic);

        Bitmap player12Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.justise_winslow)).getBitmap();
        Player player12 = new Player("Justise", "Winslow", 20, Player.FORWARD, player12Pic);

        Bitmap player13Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.luol_deng)).getBitmap();
        Player player13 = new Player("Luol", "Deng", 9, Player.FORWARD, player13Pic);

        Bitmap player14Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.tyler_johnson)).getBitmap();
        Player player14 = new Player("Tyler", "Johnson", 8, Player.GUARD, player14Pic);

        Bitmap player15Pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.udonis_haslem)).getBitmap();
        Player player15 = new Player("Udonis", "Haslem", 40, Player.FORWARD, player15Pic);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);
        players.add(player7);
        players.add(player8);
        players.add(player9);
        players.add(player10);
        players.add(player11);
        players.add(player12);
        players.add(player13);
        players.add(player14);
        players.add(player15);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Player player = (Player) parent.getItemAtPosition(position);
        Intent dialog = new Intent(this, PlayerDialog.class);

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        player.getPicture().compress(Bitmap.CompressFormat.JPEG,100,bs);

        dialog.putExtra("playerName", player.getFirstName() + " " + player.getLastname());
        dialog.putExtra("playerPosition", player.getPostition());
        dialog.putExtra("playerNumber", player.getJerseyNumber());
        dialog.putExtra("playerPicture", bs.toByteArray());

        startActivity(dialog);
    }
}
