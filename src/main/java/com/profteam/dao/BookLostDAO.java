package com.profteam.dao;

import com.profteam.helper.JDBCHelper;
import com.profteam.model.BookLost;
import com.profteam.model.BookLostDetail;
import com.profteam.model.BookProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookLostDAO 
{
    public static ArrayList<BookLost> getAll() throws SQLException
    {
        ArrayList<BookLost> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM BOOK_LOST");

        while(rs.next())
        {
        	BookLost lostBook = readFromResultSet(rs);
        	list.add(lostBook);
        }
        return list;
    }
    
    public static boolean insert(BookLost e) throws SQLException
    {
        String sql = "INSERT INTO BOOK_LOST Values(?, ?, ?, ?)";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        										e.getRentbookId(), 
        										e.getAdminId(), 
        										e.getCostLost(),
        										e.getCreatedDate());
        int count = pre.executeUpdate();
        
        return count > 0;
    }
    
    public static boolean insert(BookLost bookLost, List<BookProduct> products) throws SQLException
    {
        String sql = "INSERT INTO BOOK_LOST Values(?, ?, ?, ?)";
        
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        										bookLost.getRentbookId(), 
        										bookLost.getAdminId(), 
        										bookLost.getCostLost(),
        										bookLost.getCreatedDate());
        int count = pre.executeUpdate();
        if (count > 0)
        {
        	for (BookProduct p : products)
        	{
        		BookLostDetail detail = new BookLostDetail(bookLost.getRentbookId(), p.getBook().getId(), p.getAmount(), p.getPrice());
        		BookLostDetailDAO.insert(detail);
        	}
        	return true;
        }

        return false;
    }
    
    public static boolean update(BookLost bookLost, int rentbook_id) throws SQLException
    {
        String sql = "UPDATE BOOK_LOST SET rentbook_id = ?, "
        							+ "admin_id = ?, "
        							+ "cost_lost = ?, "
        							+ "created_date = ? "
        							+ "WHERE rentbook_id = ?";

        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql,
        											bookLost.getRentbookId(),
        											bookLost.getAdminId(),
        											bookLost.getCostLost(),
        											bookLost.getCreatedDate(),
        											rentbook_id);
        int count = pre.executeUpdate();
        return count > 0;

    }
    
    public static boolean update(BookLost bookLost, List<BookProduct> products) throws SQLException
    {
        if (findByID(bookLost.getRentbookId()) != null)
        {
        	//Xóa toàn bộ chi tiết cũ đi, sau đó cập nhật lại order, và thêm lại order chi tiết
        	BookLostDetailDAO.delete(bookLost.getRentbookId());
        	update(bookLost, bookLost.getRentbookId());
        	for (BookProduct p : products)
        	{
        		BookLostDetail detail = new BookLostDetail(bookLost.getRentbookId(), p.getBook().getId(), p.getAmount(), p.getPrice());
        		BookLostDetailDAO.insert(detail);
        	}
        	return true;
        }
        
        return false;
    }
    
    public static boolean delete(int rentbook_id) throws SQLException
    {
        String sql = "DELETE FROM BOOK_LOST Where rentbook_id = ?";
        PreparedStatement pre = JDBCHelper.createPreparedStatement(sql, rentbook_id);
        int count = pre.executeUpdate();
        return count > 0;
    }
    
    public static BookLost findByID(int rentbook_id) throws SQLException
    {
        ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM BOOK_LOST Where rentbook_id =?", rentbook_id);
        
        if (rs.next())
        {
        	return readFromResultSet(rs);
        }
        
        return null;
    }
    
    public static BookLost readFromResultSet(ResultSet rs) throws SQLException
    {
    	int rentbookId = rs.getInt(1);
        int adminId = rs.getInt(2);
        double costLost = rs.getDouble(3);
        Date dateCreated = rs.getDate(4);
        
        return new BookLost(rentbookId, adminId, costLost, dateCreated);
    }
    
//    public static Date convertStringTimeToDate(String stringTime) throws ParseException
//    {
//    	String StringDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
//    	SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//    	
//    	
//    	return formater.parse(StringDate + " " + stringTime);
//    	
//    }
    


}










