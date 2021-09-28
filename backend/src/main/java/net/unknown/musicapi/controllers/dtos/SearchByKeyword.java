package net.unknown.musicapi.controllers.dtos;

public class SearchByKeyword {

    private String keyword;

    public SearchByKeyword(String keyword) {
        this.keyword = keyword;
    }

    public SearchByKeyword() {

    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
