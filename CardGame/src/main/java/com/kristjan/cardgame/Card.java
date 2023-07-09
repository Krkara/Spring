package com.kristjan.cardgame;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Card
{
    private String suit;
    private String rank;
    private int value;
}
