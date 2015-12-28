package gprosper.org.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import gprosper.org.gridview.model.Player;

/**
 * Created by a on 12/28/15.
 */
public class PlayerGridViewAdapter extends ArrayAdapter<Player> {

    public PlayerGridViewAdapter(Context context, ArrayList<Player> players){
        super(context,R.layout.grid_view_item,players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder = null;

        if (row == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.grid_view_item, parent, false);

            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) row.getTag();
        }

        Player player = getItem(position);

        viewHolder.playerPicture.setImageBitmap(player.getPicture());
        viewHolder.playerNumber.setText(String.valueOf(player.getJerseyNumber()));

        return row;
    }

    private static class ViewHolder{
        ImageView playerPicture;
        TextView playerNumber;

        public ViewHolder(View row){
            playerPicture = (ImageView) row.findViewById(R.id.playerPicture);
            playerNumber = (TextView) row.findViewById(R.id.playerNumber);
        }
    }
}
