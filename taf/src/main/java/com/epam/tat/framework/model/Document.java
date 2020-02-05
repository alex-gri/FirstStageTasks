package com.epam.tat.framework.model;

import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;

public class Document {

    private String name;
    private String text;
    private YandexDiskTextDocumentPage documentPage;

    public Document() {
        this.documentPage = new YandexDiskTextDocumentPage();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public YandexDiskTextDocumentPage getDocumentPage() {
        return documentPage;
    }
}
