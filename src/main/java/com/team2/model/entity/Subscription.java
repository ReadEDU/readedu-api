package com.team2.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.team2.model.enums.PaymentStatus;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String price;
    private String duration;
    private Boolean status;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_subscription_plan"))
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_subscription_reader"))
    private Reader reader;
}
