package core;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ManyToAny;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="Kraje")
public class Countries implements Serializable{
    
    private static final long serialVersionUID = -300025L;
    
    @Column(unique=true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NonNull
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
