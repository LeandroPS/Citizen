package pooa20161.iff.edu.br.citizen.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pooa20161.iff.edu.br.citizen.Models.Causa;
import pooa20161.iff.edu.br.citizen.R;

public class CausaAdapter extends ArrayAdapter<Causa> {

    private Context context = null;
    private ArrayList<Causa> causas = null;

    public CausaAdapter(Context context, ArrayList<Causa> causas){
        super(context, R.layout.causalayout, causas);
        this.context = context;
        this.causas = causas;
    }

    public View getView(int position, View convertView, ViewGroup    parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.causalayout, parent, false);

        TextView titulo = (TextView) rowView.findViewById(R.id.causa_titulo);
        TextView subtitulo = (TextView) rowView.findViewById(R.id.causa_subtitulo);
        ImageView icon = (ImageView) rowView.findViewById(R.id.causa_icon);

        titulo.setText(causas.get(position).getTitulo());
        subtitulo.setText(causas.get(position).getDescricao());
        //icon.setImageBitmap(BitmapFactory.decodeFile(causas.get(position).getPhoto()));

        return rowView;
    }
}