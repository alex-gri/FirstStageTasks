package com.epam.tat.framework.model;

import java.util.Random;

public class FolderBuilder {

    private Folder folder = new Folder();

    private Random random = new Random();

    public FolderBuilder setDefaultName() {
        folder.setName(String.valueOf(random.nextInt(999999999)));
        return this;
    }

    public FolderBuilder setCustomName(String name) {
        folder.setName(name);
        return this;
    }

    public Folder build() {
        return folder;
    }
}
