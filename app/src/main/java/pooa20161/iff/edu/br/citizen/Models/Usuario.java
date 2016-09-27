package pooa20161.iff.edu.br.citizen.Models;

import android.content.Context;

import com.orm.SugarRecord;

/**
 * Created by leandropiresdesouza on 9/19/16.
 */
public class Usuario extends SugarRecord {

    String nome;
    String email;
    String senha;
    String foto;

    public Usuario(){

    }

    public Usuario(String nome, String email, String senha, String foto){

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }
}
