package core;

import com.sun.istack.Nullable;
import core.enumerator.Sex;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="Obsada")
public class Cast implements Serializable{
    
    private static final long serialVersionUID = 1L;
        
    @Column(name="Id_osoby", unique=true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    @Column(length = 255, name="Imie")
    private String name;
    
    @NonNull
    @Column(length = 255, name="Nazwisko")
    private String surname;

    @Column(name="Data_urodzenia")
    private Date birthDate;

    @Column(name="Data_smierci")
    private Date deathDate;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne(optional = false)
    @JoinColumn(name="country", nullable = false)
    private Countries country;

    @ManyToMany(mappedBy="actors")
    private List<Movies> movies;

    @Column(name="Opis")
    private String about;

    @Override
    public String toString() {
        return name + " " + surname;

}
}