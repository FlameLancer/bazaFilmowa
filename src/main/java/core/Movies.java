package core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.transaction.Transactional;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="Filmy")
public class Movies implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column(name="Id_filmu", unique=true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NonNull
    @Column(name="Tytul_oryginalny")
    private String orgTitle;

    @Column(name="Tytul_polski")
    private String polishTitle;

    @ManyToOne(optional = false)
    @JoinColumn(name="Id_rezysera",nullable = false)
    private Cast director;

    @Column(name="Data_premiery")
    private Date premiere;

    @ManyToOne(optional = false)
    @JoinColumn(name="Id_jezyka",nullable = false)
    private Languages language;

    @Column(name="Dlugosc_filmu")
    private Integer length;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Genres> genreList;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Cast> actors;

    @Column(name="Opis")
    private String about;

    @Override
    public String toString() {
        return orgTitle;
    }
}

