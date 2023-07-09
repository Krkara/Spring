package com.kristjan.cardgame;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class CardGameController {
    CardDeck cardDeck = new CardDeck();
    List<Card> cards = cardDeck.getDeck();
    Card baseCard = new Card();
    Random random = new Random();
    int points = 0;
    StringBuilder stringBuilder = new StringBuilder();

    @GetMapping("start")
    public String startRound() {
        int randomNumber = random.nextInt(52);
        baseCard = cards.get(randomNumber);

        stringBuilder.append(baseCard.toString()).append("<br>");
        stringBuilder.append("Is the next card higher, lower or equal?").append("<br><br>");

        return stringBuilder.toString();
    }

    @GetMapping("higher")
    public String higher() {
        return handleChoice("higher");
    }

    @GetMapping("lower")
    public String lower() {
        return handleChoice("lower");
    }

    @GetMapping("equal")
    public String equal() {
        return handleChoice("equal");
    }

    private String handleChoice(String choice) {
        int randomNumber = random.nextInt(52);
        Card resultCard = cards.get(randomNumber);
        stringBuilder.append(resultCard.toString()).append("<br>");

        if ((choice.equals("higher") && resultCard.getValue() > baseCard.getValue()) ||
                (choice.equals("lower") && resultCard.getValue() < baseCard.getValue()) ||
                        (choice.equals("equal") && resultCard.getValue() == baseCard.getValue())) {
            points++;
            stringBuilder.append("Correct choice, point added. Points: ").append(points).append("<br>");
        } else {
            stringBuilder.append("Wrong choice, no points added. Points: ").append(points).append("<br>");
        }
        stringBuilder.append("Is the next card higher, lower or equal?").append("<br><br>");
        baseCard = resultCard;
        return stringBuilder.toString();
    }
}