package com.brunoydev.dslist.services;

import com.brunoydev.dslist.dto.GameListDTO;
import com.brunoydev.dslist.entities.GameList;
import com.brunoydev.dslist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> result = gameListRepository.findAll();

        // Can override lambda expression with GameListDTO::new
        return result.stream().map(game -> new GameListDTO(game)).toList();
    }

}
