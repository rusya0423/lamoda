package kz.lamoda.lamoda.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.*;

@Entity
@Table(name="images")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String path;
    private boolean main;
    @ManyToOne
    @JoinColumn(name = "dress_id")
    private Dress dress;
}
