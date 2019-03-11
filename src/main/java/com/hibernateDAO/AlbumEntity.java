package com.hibernateDAO;

import javax.persistence.*;

@Entity
@Table(name = "album", schema = "public", catalog = "MusicShop")
public class AlbumEntity {
    private int idAlbum;
    private String name;
    private String genre;
    private int idSinger;

    @Id
    @Column(name = "id_album", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return idAlbum;
    }

    public void setId(int id) {
        this.idAlbum = id;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true)
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "genre", nullable = false, insertable = true, updatable = true)
    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "id_singer", nullable = false, insertable = true, updatable = true)
    public int getIdSinger() {
        return idSinger;
    }

    public void setIdSinger(int id) {
        this.idSinger = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumEntity that = (AlbumEntity) o;

        if (idAlbum != that.idAlbum) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;
        if (idSinger != that.idSinger) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAlbum;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + idSinger;
        return result;
    }

}
