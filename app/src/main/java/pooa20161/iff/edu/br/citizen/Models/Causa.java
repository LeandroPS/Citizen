package pooa20161.iff.edu.br.citizen.Models;

import com.orm.SugarRecord;

/**
 * Created by leandropiresdesouza on 9/19/16.
 */
public class Causa extends SugarRecord {
    int idAutor;
    String titulo;
    String descricao;
    String coordenadas;
    int idCidade;

    public Causa(){

    }

    public Causa(int idAutor, String titulo, String descricao, String coordenadas, int idCidade){
        this.idAutor = idAutor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.coordenadas = coordenadas;
        this.idCidade = idCidade;

    }

}
