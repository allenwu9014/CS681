package edu.umb.cs681.hw04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoCardsComparerTest {
    VideoCard[] videoCardsList = {
            new VideoCard("MSI GF RTX 3090 24GB", "GF3090", "MSI", 1749.99f),
            new VideoCard("EVGA GF RTX 3080 FTW3", "GF3080", "EVGA", 809.99f),
            new VideoCard("GIGABYTE Radeon RX 5700 XT", "RX 5700", "GIGABYTE", 409.99f)
    };
    List<VideoCard> videoCards = new ArrayList<>(Arrays.asList(videoCardsList));

    @Test
    public void maxPriceTest(){
        String expected = "1749.99";
        float price = videoCards.stream()
                .map((VideoCard videoCard) -> videoCard.getPrice())
                .reduce(0.0f, (result, videoCardsPrice) -> {
                    if (result == 0) return videoCardsPrice;
                    else if (videoCardsPrice > result) return videoCardsPrice;
                    else return result;
                });
        String actual = String.valueOf(price);
        assertEquals(expected, actual);
    }
}
