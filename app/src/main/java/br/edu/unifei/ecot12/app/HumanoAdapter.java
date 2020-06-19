package br.edu.unifei.ecot12.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.unifei.ecot12.bakugan.Humano;

public class HumanoAdapter extends BaseAdapter {
    private Context mContext;
    private List<Humano> humanos;

    public HumanoAdapter(Context c, List<Humano> h) {
        humanos = h;
        mContext = c;
    }

    public int getCount() {
        int count = humanos.size()*3;
        return count;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == 0 || position % 3 == 0) {
            TextView textView = new TextView(mContext);
            textView.setText("" + humanos.get((int) position/3).getId());
            return textView;
        } else if ((position+1) % 3 == 0) {
            ImageView imageView = new ImageView(mContext);
            // if it's not recycled, initialize some attributes
            imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(mContext.getResources().getIdentifier(
                    humanos.get((int) position/3).getImagem(),"drawable",
                    mContext.getPackageName()));

            return imageView;
        } else {
            TextView textView = new TextView(mContext);
            textView.setText(humanos.get((int) position/3).getNome());
            return textView;
        }
    }
}
