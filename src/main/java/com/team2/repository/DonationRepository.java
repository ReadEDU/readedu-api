package com.team2.repository;

import com.team2.model.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {
    //Esta consulta obtiene el total de donaciones realizadas por un usuario especifico
    @Query("SELECT COALESCE(SUM(donation.amount), 0) FROM Donation donation WHERE donation.user.id = :userId")
    Float getTotalDonationsByUserId(@Param("userId") Integer userId);

    //Esta consulta obtiene todas las donaciones hechas por un usuario especifico.
    @Query("SELECT donation FROM Donation donation WHERE donation.user.id = :userId")
    List<Donation> findDonationsByUserId(@Param("userId") Integer userId);
}