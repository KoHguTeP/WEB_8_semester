package com.hibernateDAO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "composition", schema = "public", catalog = "MusicShop")
public class CompositionEntity {
    private int idComposition;
    private String name;
    private Date duration;
    private int idAlbum;


    @Id
    @Column(name = "id_composition", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return idComposition;
    }

    public void setId(int id) {
        this.idComposition = id;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true)
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "duration", nullable = false, insertable = true, updatable = true)
    public Date getDuration(){
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "id_album", nullable = false, insertable = true, updatable = true)
    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int id) {
        this.idAlbum = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionEntity that = (CompositionEntity) o;

        if (idComposition != that.idComposition) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (idAlbum != that.idAlbum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idComposition;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + idAlbum;
        return result;
    }
}
