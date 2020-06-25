package com.example.demo.service;

import com.example.demo.DTO.Single1Int;
import com.example.demo.DTO.Single3Int;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TTTService {

    int[][] tab = new int[3][3];

    public void resetGameFunction()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j <3; j++)
            {
                tab[i][j] = 0;
            }
        }
    }

    public void usersMoveFunction(Single3Int move) throws Exception
    {
        int x = move.getX();
        int y = move.getY();

        if(x >= 0 && x < 3 && y >= 0 && y < 3)
        {
            if(tab[x][y] == 0)
            {
                tab[x][y] = move.getMark();
            }
        }
        else
        {
            throw new Exception("Wrong position");
        }
    }

    public void aiMoveFunction(Single1Int move) throws Exception
    {
        int count = 0;

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(tab[i][j] == 0)
                {
                    count++;
                }
            }
        }

        if(count == 0)
        {
            throw new Exception("Full array");
        }

        Random rand = new Random();

        while(true)
        {
            int x = rand.nextInt(3);
            int y = rand.nextInt(3);

            if (tab[x][y] == 0) {
                tab[x][y] = move.getMark();
                return;
            }
        }
    }

}