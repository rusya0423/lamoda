package kz.lamoda.lamoda.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private boolean active;

    @PrePersist
    public void prePersist(){
        this.active = true ;
    }
}
