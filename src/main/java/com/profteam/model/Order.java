
package com.profteam.model;

import com.profteam.dao.AdminDAO;
import com.profteam.dao.UserDAO;
import com.profteam.helper.DateHelper;
import com.profteam.helper.SettingSave;

import java.sql.SQLException;
import java.util.Date;

public class Order {
    private int id;
    private int userId;
    private int adminId;
    private Date dateCreated;
    private String status;
    
    public Order() {
    
    }

    public Order(int id, int userId, int adminId, Date dateCreated, String status) {
        this.id = id;
        this.userId = userId;
        this.adminId = adminId;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getAdminId() {
        return adminId;
    }
    
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    
    public Date getDateCreated() {
        return dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearchString() {
        String usernameUser = "";
        String usernameAdmin = "";
        String createdDateStr = "";
        
        try {
            usernameUser = UserDAO.findByID(userId).getUsername();
            usernameAdmin = AdminDAO.findByID(adminId).getUsername();
            createdDateStr = DateHelper.dateToString(dateCreated, SettingSave.getSetting().getDateFormat());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return id + " " + usernameUser + " " + usernameAdmin + " " + createdDateStr + " ";
    }
}
