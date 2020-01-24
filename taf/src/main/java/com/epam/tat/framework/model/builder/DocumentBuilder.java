package com.epam.tat.framework.model;

import java.util.Random;

public class DocumentBuilder {

    private Document document = new Document();

    private Random random = new Random();

    public DocumentBuilder setName(String name) {
        document.setName(name);
        return this;
    }

    public DocumentBuilder setText(String text) {
        document.setText(text);
        return this;
    }

    public DocumentBuilder setDefaultName() {
        document.setName(String.valueOf(random.nextInt(999999)));
        return this;
    }

    public DocumentBuilder setDefaultText() {
        document.setText("Hello World!");
        return this;
    }

    public Document build() {
        return document;
    }
}
