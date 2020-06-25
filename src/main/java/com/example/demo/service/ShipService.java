package com.example.demo.service;

import com.example.demo.DTO.Single2Int;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipService {

    int selectedPoitn[][] = new int[10][10];
    Random rand = new Random();
    int array[][] = new int[10][10];

    //call to get free point
    public Single2Int getBotTarget()
    {
        while(true) {
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);

            if(selectedPoitn[x][y] == 0)
            {
                selectedPoitn[x][y] = 1;
                return new Single2Int(x, y);
            }
        }
    }

    //clear exist array
    public void clearArray()
    {
        for( int i = 0; i < 10; i++)
        {
            for( int j = 0; j < 10; j++)
            {
                array[i][j] = 0;
                selectedPoitn[i][j] = 0;
            }
        }
    }

    //to generate new map for boot
    public int[][] Generate ()
    {
        //clear exist array
        clearArray();

        int s = 0;
        while(s != 6)
        {
            int type = rand.nextInt(2);
            int x = rand.nextInt(8) + 1;
            int y = rand.nextInt(8) + 1;

            if(type == 0)
            {
                if(x > 1 && x < 9) {
                    if (array[x - 1][y] == 0 && array[x][y] == 0 && array[x + 1][y] == 0) {
                        array[x - 1][y] = 1;
                        array[x][y] = 1;
                        array[x + 1][y] = 1;
                        System.out.println("Wygenerowana pozycja dla bota: " + type +  " # " + x + " # " + y + " % " + s);
                        s++;
                    }
                }
            }
            else if(type == 1)
            {
                if(y > 1 && y < 9)
                {
                    if(array[x][y-1] == 0 && array[x][y] == 0 && array[x][y+1] == 0)
                    {
                        array[x][y-1] = 1;
                        array[x][y] = 1;
                        array[x][y+1] = 1;
                        System.out.println("Wygenerowana pozycja dla bota: " + type +  " # " + x + " # " + y + " % " + s);
                        s++;
                    }
                }
            }
        }

        return array;
    }

}
