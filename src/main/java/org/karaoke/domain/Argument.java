package org.karaoke.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Argument {
    private Company company;
    private Category category;
    private String word;
    private int page;

    public String getCompany() {
        return company.toString();
    }

    public Argument setCompany(Company company) {
        this.company = company;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Argument setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getWord() {
        return word;
    }

    public Argument setWord(String word) {
        this.word = word;
        return this;
    }

    public Integer getPage() {
        return page == 0 ? 1 : page;
    }

    public Argument setPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public String toString() {
        return company + ":" + category + ":" + word + ":" + page;
    }
}
