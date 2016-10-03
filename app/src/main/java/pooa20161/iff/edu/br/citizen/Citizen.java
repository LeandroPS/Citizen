package pooa20161.iff.edu.br.citizen;

import android.app.Application;

import pooa20161.iff.edu.br.citizen.Models.Usuario;

/**
 * Created by leandropiresdesouza on 10/1/16.
 */
public class Citizen extends Application
{
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Usuario usuario;
}

