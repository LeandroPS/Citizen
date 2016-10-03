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
    Integer cidade;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getFoto() {
        return foto;
    }

    public Integer getCidadeId() {
        return cidade;
    }

    public Cidade getCidade() {
        Cidade cid = Cidade.findById(Cidade.class, Long.parseLong(getCidadeId().toString()));
        return cid;
    }

    public Usuario(){

    }

    public Usuario(String nome, String email, String senha, String foto, Integer cidade){

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.cidade = cidade;
    }
}
