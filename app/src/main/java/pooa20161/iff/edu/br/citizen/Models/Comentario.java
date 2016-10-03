package pooa20161.iff.edu.br.citizen.Models;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by leandropiresdesouza on 9/19/16.
 */
public class Comentario extends SugarRecord {
    int idAutor;
    int idCausa;

    public int getIdAutor() {
        return idAutor;
    }

    public int getIdCausa() {
        return idCausa;
    }

    public String getTexto() {
        return texto;
    }

    public Date getData() {
        return data;
    }

    String texto;
    Date data;

    public Comentario(){

    }

    public Comentario(int idAutor, int idCausa, String texto, Date data){
        this.idAutor = idAutor;
        this.idCausa = idCausa;
        this.texto = texto;
        this.data = data;

    }
}
