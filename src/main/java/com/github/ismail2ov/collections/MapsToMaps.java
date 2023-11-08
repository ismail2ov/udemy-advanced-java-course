package com.github.ismail2ov.collections;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class MapsToMaps {
    public static void main(String[] args) {
        mapsToMaps();
    }

    public static void mapsToMaps() {
        Map<String, Integer> channelToSubscribers = new TreeMap<>(); // channel, numSubscribers
        Map<String, String> channelToPublisher = new TreeMap<>(); // channel, publisher
        Map<String, Integer> publisherToSubscribers = new TreeMap<>(); // publisher, numSubscribers

        // channel -> number of subscribers
        // K -> V1
        channelToSubscribers.put("JustForLaughs", 120_000);
        channelToSubscribers.put("JustForGags", 10_000);
        channelToSubscribers.put("ContemplationTechniques", 10_000);
        channelToSubscribers.put("A New Earth", 20_000);

        // channel -> publisher
        // K -> V2
        channelToPublisher.put("JustForLaughs", "Charlie Chaplin");
        channelToPublisher.put("JustForGags", "Charlie Chaplin");
        channelToPublisher.put("ContemplationTechniques", "Echhart Tolle");
        channelToPublisher.put("A New Earth", "Echhart Tolle");

        // 1. Setup "publisherToSubscribers"
        // publisher -> number of subscribers (total)
        // V2 -> V1
        channelToSubscribers.forEach((channel, numSubscribers) -> {
            Optional<String> publisherOpt = Optional.ofNullable(channelToPublisher.get(channel));
            if (publisherOpt.isPresent()) {
                String publisher = publisherOpt.get();
                if (publisherToSubscribers.containsKey(publisher)) {
                    int currentSubscribers = publisherToSubscribers.get(publisher);
                    int newVal = currentSubscribers + numSubscribers;
                    publisherToSubscribers.put(publisher, newVal);
                } else {
                    publisherToSubscribers.put(publisher, numSubscribers);
                }
            }
        });


        // 2. Output "publisherToSubscribers"
        publisherToSubscribers.forEach((publisher, numSubscribers) -> System.out.println("publisher: " + publisher + "; numSubscribers:" + numSubscribers));


        // 3. Who has the most/least subscribers?
        Optional<Map.Entry<String, Integer>> minSubscribers = publisherToSubscribers.entrySet().stream()
                .min(Map.Entry.comparingByValue());

        Optional<Map.Entry<String, Integer>> maxSubscribers = publisherToSubscribers.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        maxSubscribers.ifPresent(max -> System.out.println("Publisher with most subscribers: " + max.getKey() + " " + max.getValue()));

        minSubscribers.ifPresent(min -> System.out.println("Publisher with fewest subscribers: " + min.getKey() + " " + min.getValue()));

    }
}