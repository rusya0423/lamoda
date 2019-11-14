package kz.lamoda.lamoda.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="orders")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private boolean orderStatus;
    private boolean active;
    private Date createdAt;

    @PrePersist
    public void prePersist(){
        if(!this.active){
            this.active = true;
        }
    }

}
