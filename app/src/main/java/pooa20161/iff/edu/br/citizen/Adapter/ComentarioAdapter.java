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
import pooa20161.iff.edu.br.citizen.Models.Comentario;
import pooa20161.iff.edu.br.citizen.Models.Usuario;
import pooa20161.iff.edu.br.citizen.R;

/**
 * Created by leandropiresdesouza on 10/3/16.
 */
public class ComentarioAdapter extends ArrayAdapter<Comentario> {

    private Context context = null;
    private ArrayList<Comentario> comentarios = null;

    public ComentarioAdapter(Context context, ArrayList<Comentario> comentarios){
        super(context, R.layout.causalayout, comentarios);
        this.context = context;
        this.comentarios = comentarios;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.causalayout, parent, false);

        TextView titulo = (TextView) rowView.findViewById(R.id.causa_titulo);
        TextView subtitulo = (TextView) rowView.findViewById(R.id.causa_subtitulo);
        ImageView icon = (ImageView) rowView.findViewById(R.id.causa_icon);

        String username = (Usuario.findById(Usuario.class, Long.parseLong(""+comentarios.get(position).getIdAutor()))).getNome();

        titulo.setText(username);
        subtitulo.setText(comentarios.get(position).getTexto());
        //icon.setImageBitmap(BitmapFactory.decodeFile(comentarios.get(position).getPhoto()));

        return rowView;
    }
}