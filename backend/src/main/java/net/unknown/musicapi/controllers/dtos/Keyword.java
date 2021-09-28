package net.unknown.musicapi.controllers.dtos;

public class Keyword {

    private String keyword;

    public Keyword(String keyword) {
        this.keyword = keyword;
    }

    public Keyword() {

    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
