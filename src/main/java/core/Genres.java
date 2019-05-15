package core;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.sun.istack.Nullable;
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
@Table(name="Gatunki")
public class Genres implements Serializable{
    
    private static final long serialVersionUID = -300025L;
    
    @Column(unique=true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NonNull
    private String name;

    @ManyToMany(mappedBy="genreList")
    private List<Movies> movieList;

    @Override
    public String toString() {
        return name;
    }
}
