package pooa20161.iff.edu.br.citizen.Models;

import com.orm.SugarRecord;

/**
 * Created by leandropiresdesouza on 9/19/16.
 */
public class Apoio extends SugarRecord {
    int idUsuario;
    int idCausa;

    public Apoio(){


    }

    public Apoio(int idUsuario, int idCausa){
        this.idCausa = idCausa;
        this.idUsuario = idUsuario;
    }

}
