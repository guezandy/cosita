package com.cositoycosita.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("AlbumItem")

public class AlbumItem extends ParseObject {
    //empty constructor required
    public AlbumItem() {

    }

    public String getCaption() {
        return this.getString("album_title");
    }

    public ParseFile getImage() {
        return this.getParseFile("cover_image");
    }
}