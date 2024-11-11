package com.team2.model.entity;

import com.team2.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float total;

    @Enumerated(EnumType.STRING)
    private PaymentStatus payment_status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_pucharse_users"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "donation_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_pucharse_donations"))
    private Donation donation;
}