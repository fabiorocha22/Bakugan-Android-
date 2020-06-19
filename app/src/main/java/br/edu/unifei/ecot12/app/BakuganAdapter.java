package br.edu.unifei.ecot12.app;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.edu.unifei.ecot12.bakugan.Bakugan;

public class BakuganAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bakugan> bakugans;

    public BakuganAdapter(Context c, List<Bakugan> h) {
        bakugans = h;
        mContext = c;
    }

    public int getCount() {
        int count = bakugans.size()*3;
        Log.i("Script", "count = " + count);
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
            textView.setText("" + bakugans.get((int) position/3).getId());
            return textView;
        } else if ((position+1) % 3 == 0) {
            TextView textView = new TextView(mContext);
            textView.setText("" + bakugans.get((int) position/3).getNome());
            return textView;
        } else {
            TextView textView = new TextView(mContext);
            textView.setText(bakugans.get((int) position/3).getAtributo().toString());
            return textView;
        }
    }
}
