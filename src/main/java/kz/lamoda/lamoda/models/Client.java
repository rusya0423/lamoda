package kz.lamoda.lamoda.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="clients")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
    private boolean active;

    @PrePersist
    public void prePersist(){
        if(!this.active){
            this.active = true;
        }
    }
}
