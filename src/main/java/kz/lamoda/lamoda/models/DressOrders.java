package kz.lamoda.lamoda.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="order_dresses")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class DressOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dress_id")
    private Dress dress;
    private double price;
    private int quantity;

}
