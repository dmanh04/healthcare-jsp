
package dto.criteria;

public class UserCriteria extends BaseCriteria {

    private final Integer roleId;
    private final String username;
    private final String fullname;

    private UserCriteria(Builder builder) {
        this.roleId = builder.roleId;
        this.username = builder.username;
        this.fullname = builder.fullname;
        this.page = builder.page;  
        this.limit = builder.limit;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }

    
    
    @Override
    public String toString() {
        return "UserCriteria{"
                + "roleId=" + roleId
                + ", username='" + username + '\''
                + ", fullname='" + fullname + '\''
                + ", page=" + page
                + ", limit=" + limit
                + '}';
    }

    public static class Builder {

        private Integer roleId;
        private String username;
        private String fullname;
        private int page;
        private int limit;

        public Builder() {
            this.page = 1;  
            this.limit = 10; 
        }

        public Builder roleId(Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder fullname(String fullname) {
            this.fullname = fullname;
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

        public UserCriteria build() {
            return new UserCriteria(this);
        }
    }
}
