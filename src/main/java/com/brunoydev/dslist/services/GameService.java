package com.brunoydev.dslist.services;

import com.brunoydev.dslist.dto.GameDTO;
import com.brunoydev.dslist.dto.GameMinDTO;
import com.brunoydev.dslist.entities.Game;
import com.brunoydev.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
        List<Game> result = gameRepository.findAll();

        // Can override lambda expression with GameMinDTO::new
        return result.stream().map(game -> new GameMinDTO(game)).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long gameId){
        Game result = gameRepository.findById(gameId).get();

        return new GameDTO(result);
    }
}
