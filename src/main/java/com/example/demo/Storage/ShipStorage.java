package com.example.demo.Storage;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipStorage {
    int[][] playerArray = new int[10][10];
    int[][] computerArray = new int[10][10];
}
