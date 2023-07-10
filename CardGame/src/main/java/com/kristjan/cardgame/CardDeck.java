package com.kristjan.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardDeck {
    private List<Card> deck;

    public CardDeck() {
        deck = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                int value;
                if (rank.equals("Ace") || rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
                    value = 10;
                } else {
                    value = Integer.parseInt(rank);
                }

                Card card = new Card(rank, suit, value);
                deck.add(card);
            }
        }
    }

    public List<Card> getDeck() {
        return deck;
    }
}
