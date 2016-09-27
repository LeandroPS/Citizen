package pooa20161.iff.edu.br.citizen.Models;

import com.orm.SugarRecord;

/**
 * Created by leandropiresdesouza on 9/19/16.
 */
public class Cidade extends SugarRecord {

    String estado;
    String pais;

    public Cidade(){


    }

    public Cidade(String estado, String pais){
        this.estado = estado;
        this.pais = pais;

    }


}
