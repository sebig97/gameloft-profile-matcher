package com.example.playerprofileservice.controller;


import com.example.playerprofileservice.dto.PlayerProfileDto;
import com.example.playerprofileservice.exception.ResourceNotFoundException;
import com.example.playerprofileservice.service.PlayerProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@AllArgsConstructor
@RequestMapping("/players")
public class PlayerProfileController {

    private final PlayerProfileService playerProfileService;

    @PostMapping("/save")
    public ResponseEntity<?> savePlayer(@Valid @RequestBody PlayerProfileDto playerProfileDto) {
        PlayerProfileDto savedPlayerDto = playerProfileService.savePlayer(playerProfileDto);
        return new ResponseEntity<>(savedPlayerDto, HttpStatus.CREATED);
    }

    @GetMapping("/get_client_config/{id}")
    public ResponseEntity<?> getClientConfigByI(@PathVariable UUID id) throws ResourceNotFoundException {
        PlayerProfileDto playerProfileDto = playerProfileService.findPlayerByUuid(id);
        return new ResponseEntity<>(playerProfileDto, HttpStatus.OK);
    }
}
