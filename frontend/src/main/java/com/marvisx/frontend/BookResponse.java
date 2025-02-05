package com.marvisx.frontend;

import java.util.List;

public class BookResponse {
    private List<Book> content;
    private Page page;
    // Getter and Setter
    public List<Book> getContent() {
        return content;
    }

    public void setContent(List<Book> content) {
        this.content = content;
    }
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
}
