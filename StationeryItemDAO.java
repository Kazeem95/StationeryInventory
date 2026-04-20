package com.stationery.dao;

import com.stationery.model.StationeryItem;
import com.stationery.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationeryItemDAO {

    private StationeryItem mapRow(ResultSet rs) throws SQLException {
        StationeryItem item = new StationeryItem();
        item.setItemId(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setCategoryId(rs.getInt("category_id"));
        item.setCategoryName(rs.getString("category_name"));
        item.setBrand(rs.getString("brand"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setQuantityInStock(rs.getInt("quantity_in_stock"));
        item.setDescription(rs.getString("description"));
        item.setDateAdded(rs.getDate("date_added"));
        return item;
    }

    public List<StationeryItem> getAllItems() {
        List<StationeryItem> list = new ArrayList<>();
        String sql = "SELECT s.*, c.category_name FROM StationeryItem s " +
                     "JOIN Category c ON s.category_id = c.category_id ORDER BY s.name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<StationeryItem> searchItems(String keyword) {
        List<StationeryItem> list = new ArrayList<>();
        String sql = "SELECT s.*, c.category_name FROM StationeryItem s " +
                     "JOIN Category c ON s.category_id = c.category_id " +
                     "WHERE s.name LIKE ? OR s.brand LIKE ? ORDER BY s.name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String k = "%" + keyword + "%";
            ps.setString(1, k);
            ps.setString(2, k);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<StationeryItem> getItemsByCategory(int categoryId) {
        List<StationeryItem> list = new ArrayList<>();
        String sql = "SELECT s.*, c.category_name FROM StationeryItem s " +
                     "JOIN Category c ON s.category_id = c.category_id " +
                     "WHERE s.category_id = ? ORDER BY s.name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public StationeryItem getItemById(int id) {
        StationeryItem item = null;
        String sql = "SELECT s.*, c.category_name FROM StationeryItem s " +
                     "JOIN Category c ON s.category_id = c.category_id WHERE s.item_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) item = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public boolean addItem(StationeryItem item) {
        String sql = "INSERT INTO StationeryItem (name, category_id, brand, price, " +
                     "quantity_in_stock, description, date_added) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, item.getName());
            ps.setInt(2, item.getCategoryId());
            ps.setString(3, item.getBrand());
            ps.setBigDecimal(4, item.getPrice());
            ps.setInt(5, item.getQuantityInStock());
            ps.setString(6, item.getDescription());
            ps.setDate(7, new java.sql.Date(item.getDateAdded().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateItem(StationeryItem item) {
        String sql = "UPDATE StationeryItem SET name=?, category_id=?, brand=?, price=?, " +
                     "quantity_in_stock=?, description=?, date_added=? WHERE item_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, item.getName());
            ps.setInt(2, item.getCategoryId());
            ps.setString(3, item.getBrand());
            ps.setBigDecimal(4, item.getPrice());
            ps.setInt(5, item.getQuantityInStock());
            ps.setString(6, item.getDescription());
            ps.setDate(7, new java.sql.Date(item.getDateAdded().getTime()));
            ps.setInt(8, item.getItemId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteItem(int id) {
        String sql = "DELETE FROM StationeryItem WHERE item_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
