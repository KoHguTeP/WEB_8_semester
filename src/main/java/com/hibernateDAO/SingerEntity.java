package com.hibernateDAO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "singer", schema = "public", catalog = "MusicShop")
public class SingerEntity {
    private int idSinger;
    private String name;


    @Id
    @Column(name = "id_singer", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return idSinger;
    }

    public void setId(int id) {
        this.idSinger = id;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true)
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingerEntity that = (SingerEntity) o;

        if (idSinger != that.idSinger) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSinger;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
