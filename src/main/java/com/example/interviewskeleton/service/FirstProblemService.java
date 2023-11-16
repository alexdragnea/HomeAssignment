package com.example.interviewskeleton.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class FirstProblemService {
    private static final String FORBIDDEN_CHARACTER = "a";

    public CompletableFuture<Void> saveWords(List<String> words) {

        return CompletableFuture.allOf(words.stream()
                .map(word -> CompletableFuture.runAsync(() -> processWord(word)))
                .toArray(CompletableFuture[]::new));
    }

    private void processWord(String word) {
        try {
            if (word.contains(FORBIDDEN_CHARACTER)) {
                throw new UnsupportedOperationException("This word is not valid: " + word);
            }

            saveWordToExternalApi(word);
        } catch (Exception e) {
            throw e;
        }
    }

    private void saveWordToExternalApi(String word) {
        try {
            // Here we call external API. We use a sleep of 1s to simulate that it takes a lot of time, and we have no control over it.
            Thread.sleep(1000);
        } catch (Throwable anyException) {
            log.info("[BEST EFFORT] Saving word '{}' failed: {}", word, anyException.getMessage());
        }
    }
}
