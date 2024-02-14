package com.example.playerprofileservice.controller;


import com.example.playerprofileservice.dto.PlayerProfileDto;
import com.example.playerprofileservice.service.PlayerProfileService;
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

    @GetMapping(path = "/message")
    public String getMessage() {
        return "Salut, Sebi";
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePlayer(@RequestBody PlayerProfileDto playerProfileDto) {
        PlayerProfileDto savedPlayerDto = playerProfileService.savePlayer(playerProfileDto);
        return new ResponseEntity<>(savedPlayerDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerProfileById(@PathVariable UUID id) {
        PlayerProfileDto playerProfileDto = playerProfileService.findPlayerByUuid(id);
        return new ResponseEntity<>(playerProfileDto, HttpStatus.OK);
    }
}
