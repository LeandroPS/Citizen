package pooa20161.iff.edu.br.citizen.Models;

import com.orm.SugarRecord;

/**
 * Created by leandropiresdesouza on 9/19/16.
 */
public class Causa extends SugarRecord {
    int idAutor;
    String titulo;
    String descricao;

    String latitude;
    String longitude;
    String local;

    String photo;
    int idCidade;

    public Causa(){

    }

    public Causa(int idAutor, String titulo, String descricao, String latitude, String longitude, String local, String photo, int idCidade){
        this.idAutor = idAutor;
        this.titulo = titulo;
        this.descricao = descricao;

        this.latitude = latitude;
        this.longitude = longitude;

        this.local = local;

        this.idCidade = idCidade;
        this.photo = photo;

    }

    public int getIdAutor() {
        return idAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhoto() {
        return photo;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

}
