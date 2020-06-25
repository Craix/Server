package com.example.demo.rest;

import com.example.demo.DTO.*;
import com.example.demo.Storage.ShipStorage;
import com.example.demo.Storage.TTTStorage;
import com.example.demo.service.ShipService;
import com.example.demo.service.TTTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class Requests {

    //universla logger
    private static final Logger LOGGER = LoggerFactory.getLogger(Requests.class);

    //all ship game
    @Autowired
    private ShipService shipService;
    @Autowired
    private ShipStorage shipStorage;
    //all tt game
    @Autowired
    private TTTService tttService;
    @Autowired
    private TTTStorage tttStorage;

    //ship game - reset bot
    @RequestMapping(value = "/ship/reset", method = RequestMethod.POST)
    public ResponseEntity<Void> addComputerArray() {
        try {
            int map[][] = shipService.Generate();
            shipStorage.setComputerArray(map);

            LOGGER.info("Dodawania planszy bota - ship game");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ship game - bot move
    @RequestMapping(value = "/ship/bot", method = RequestMethod.GET)
    public ResponseEntity<Single2Int> computerMove() {
        try {

            Single2Int object = shipService.getBotTarget();
            LOGGER.info("Ruch bota - ship game");

            return new ResponseEntity<Single2Int>(object, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ship game - add palyer array to storage
    @RequestMapping(value = "/ship/array", method = RequestMethod.POST)
    public ResponseEntity<Void> addPlayerArray(@RequestBody Array2Int array2Int) {
        try {
            LOGGER.info("Dodawania planszy gracza - ship game");
            // get array from dto and save in srray storage
            shipStorage.setPlayerArray(array2Int.getArray());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ship game - player move
    @RequestMapping(value = "/ship/user", method = RequestMethod.POST)
    public ResponseEntity<SingeBoolean> playerMove(@RequestBody Single2Int object) {
        try {
            if (object.getX() >= 0 && object.getX() < 10 && object.getY() >= 0 && object.getY() < 10)
            {
                SingeBoolean singeBoolean = new SingeBoolean(shipStorage.getComputerArray()[object.getX()][object.getY()] == 1);
                LOGGER.info("Ruch gracza - ship game");
                return new ResponseEntity<SingeBoolean>(singeBoolean, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ttt game - get game result
    @RequestMapping(value = "/ttt/result", method = RequestMethod.GET)
    public ResponseEntity<Array2Int> getGameResult() {
        Array2Int array2Int = new Array2Int(tttService.getTab());
        LOGGER.info("Pobieranie planszy - ttt game");
        return new ResponseEntity<Array2Int>(array2Int, HttpStatus.OK);
    }

    //ttt game - reset game
    @RequestMapping(value = "/ttt/reset", method = RequestMethod.PUT)
    public ResponseEntity<Void> resetGame() {
        tttService.resetGameFunction();
        LOGGER.info("Resetowanie gry - ttt game");
        return new ResponseEntity(HttpStatus.OK);
    }

    //ttt game - user move
    @RequestMapping(value = "/ttt/user", method = RequestMethod.PUT)
    public ResponseEntity<Void> usersMove(@RequestBody Single3Int single3IntDto) {
        try {
            tttService.usersMoveFunction(single3IntDto);
            LOGGER.info("Ruch gracza - ttt game " + single3IntDto.getX() + " $ " +single3IntDto.getY());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ttt game - bot move
    @RequestMapping(value = "/ttt/bot", method = RequestMethod.PUT)
    public ResponseEntity<Void> botMove(@RequestBody Single1Int single1IntDto) {
        try {
            tttService.aiMoveFunction(single1IntDto);
            LOGGER.info("Ruch bota - ttt game");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
