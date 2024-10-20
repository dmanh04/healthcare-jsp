package dto.response;

import java.util.List;

public class PageableResponse<T> {
    private int totalPage;
    private int page;
    private int size;
    private List<T> data;

    private PageableResponse(Builder<T> builder) {
        this.totalPage = builder.totalPage;
        this.page = builder.page;
        this.size = builder.size;
        this.data = builder.data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageableResponse{" + "totalPage=" + totalPage + ", page=" + page + ", size=" + size + ", data=" + data + '}';
    }

    public static class Builder<T> {
        private int totalPage;
        private int page;
        private int size;
        private List<T> data;

        public Builder<T> totalPage(int totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public Builder<T> page(int page) {
            this.page = page;
            return this;
        }

        public Builder<T> size(int size) {
            this.size = size;
            return this;
        }

        public Builder<T> data(List<T> data) {
            this.data = data;
            return this;
        }

        public PageableResponse<T> build() {
            return new PageableResponse<>(this);
        }
    }
}
