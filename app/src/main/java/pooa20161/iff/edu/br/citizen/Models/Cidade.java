package pooa20161.iff.edu.br.citizen.Models;

import com.orm.SugarRecord;

/**
 * Created by leandropiresdesouza on 9/19/16.
 */
public class Cidade extends SugarRecord {

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    String nome;
    String estado;

    public Cidade(){


    }

    public Cidade(String nome, String estado){
        this.nome = nome;
        this.estado = estado;
    }


}
