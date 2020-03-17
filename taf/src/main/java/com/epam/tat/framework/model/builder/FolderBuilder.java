package com.epam.tat.framework.model.builder;

import com.epam.tat.framework.model.Folder;
import com.epam.tat.test.Constants;

import java.util.Random;

public class FolderBuilder {

    private Folder folder = new Folder();

    private Random random = new Random();

    public FolderBuilder setDefaultName() {
        folder.setName(String.valueOf(random.nextInt(Constants.FOLDER_NAME_NUMERIC_TILL)));
        return this;
    }

    public FolderBuilder setName(String name) {
        folder.setName(name);
        return this;
    }

    public Folder build() {
        return folder;
    }
}
