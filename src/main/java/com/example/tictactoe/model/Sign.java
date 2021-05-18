package com.example.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Sign {
    X(1),O(2);

    private Integer value;
}
