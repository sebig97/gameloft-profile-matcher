package com.example.playerprofileservice.repository;

import com.example.playerprofileservice.dto.PlayerProfileDto;
import com.example.playerprofileservice.entity.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, UUID> {

    Optional<PlayerProfile> findByCredential(String credential);
}
