package com.kristjan.cardgame;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@RestController
public class CardGameController {
    CardDeck cardDeck = new CardDeck();
    List<Card> cards = cardDeck.getDeck();
    Card baseCard = new Card();
    Random random = new Random();
    int points;
    StringBuilder stringBuilder;
    Timer timer;
    long startTime;

    @GetMapping("start")
    public String startRound() {
        stringBuilder = new StringBuilder();

        int randomNumber = random.nextInt(52);
        baseCard = cards.get(randomNumber);
        stringBuilder.append(baseCard.toString()).append("<br>");
        stringBuilder.append("Is the next card higher, lower or equal?").append("<br><br>");

        points = 0;
        startTime = System.currentTimeMillis();
        startTimer();
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
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime >= 10000) {
            stringBuilder.append("TIME_OUT");
            return stringBuilder.toString();
        }

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

        startTime = System.currentTimeMillis();
        startTimer();
        return stringBuilder.toString();
    }

    private void startTimer() {
        cancelTimer();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            }
        }, 10000);
    }

    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
