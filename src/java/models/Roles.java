package models;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Roles {
    private int id;
    private String roleName;
    private Date createdAt;
    private Date updatedAt;

    public Roles() {
    }

    public Roles(int id, String roleName, Date createdAt, Date updatedAt) {
        this.id = id;
        this.roleName = roleName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Roles{" + "id=" + id + ", roleName=" + roleName + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
}
