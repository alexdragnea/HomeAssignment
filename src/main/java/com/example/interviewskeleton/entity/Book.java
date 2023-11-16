package com.example.interviewskeleton.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int year;
    private String seoName;
    private String productCode;

    public void generateSeoName() {
        this.seoName = String.format("%s - %s (%d)", author, title, year);

        /* OR
        this.seoName = author + " - " + title + " (" + year + ")"; */
    }

    public void generateProductCode() {
        if (seoName == null) {
            productCode = null;
            return;
        }

        StringBuilder codeBuilder = new StringBuilder();
        for (char ch : seoName.toCharArray()) {
            if (ch == '(' || ch == ')' || ch == ' ' || ch == '-') {
                codeBuilder.append('.');
            } else if ("AEIOUaeiou".contains(String.valueOf(ch))) {
                codeBuilder.append('1');
            } else if (Character.isAlphabetic(ch)) {
                codeBuilder.append('0');
            } else {
                codeBuilder.append(ch);
            }
        }
        this.productCode = codeBuilder.toString();
    }
}
