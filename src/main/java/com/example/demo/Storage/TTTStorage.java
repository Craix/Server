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
public class TTTStorage {
    int board[][];
}
