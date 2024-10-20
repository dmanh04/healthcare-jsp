/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.criteria;

import dto.criteria.BaseCriteria;

/**
 *
 * @author Admin
 */
public class ServiceCriteria extends BaseCriteria {

    private String searchName;

    private ServiceCriteria(Builder builder) {
        this.searchName = builder.searchName;
        this.page = builder.page;
        this.limit = builder.limit;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ServiceCriteria{" + "searchName=" + searchName + ", page=" + page + ", limit=" + limit + '}';
    }

    public static class Builder {

        private String searchName;
        private int page;
        private int limit;

        public Builder() {
            this.page = 1;
            this.limit = 10;
        }

        public Builder searchName(String searchName) {
            this.searchName = searchName;
            return this;
        }

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }
        public ServiceCriteria build() {
            return new ServiceCriteria(this);
        }
    }

}
