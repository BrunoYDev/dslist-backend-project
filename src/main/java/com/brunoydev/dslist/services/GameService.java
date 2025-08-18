package com.brunoydev.dslist.services;

import com.brunoydev.dslist.dto.GameMinDTO;
import com.brunoydev.dslist.entities.Game;
import com.brunoydev.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll(){
        List<Game> result = gameRepository.findAll();

        // Can override lambda expression with GameMinDTO::new
        return result.stream().map(game -> new GameMinDTO(game)).toList();
    }
}
