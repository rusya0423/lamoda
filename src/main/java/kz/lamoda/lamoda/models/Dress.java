package kz.lamoda.lamoda.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="dresses")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class Dress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private boolean active;

    public Dress(Dress dress) {
    }


    @PrePersist
    public void prePersist(){
        if(!this.active){
            this.active = true;
        }
    }
    private String fileName;
}
