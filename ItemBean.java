package com.stationery.beans;

import com.stationery.dao.CategoryDAO;
import com.stationery.dao.StationeryItemDAO;
import com.stationery.model.Category;
import com.stationery.model.StationeryItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

@ManagedBean(name = "itemBean")
@ViewScoped
public class ItemBean implements Serializable {

    private List<StationeryItem> itemList;
    private StationeryItem currentItem = new StationeryItem();
    private String searchKeyword = "";
    private int selectedCategoryFilter = 0;

    private final StationeryItemDAO itemDAO = new StationeryItemDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();

    public List<StationeryItem> getItemList() {
        if (itemList == null) itemList = itemDAO.getAllItems();
        return itemList;
    }

    public void searchItems(AjaxBehaviorEvent event) {
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            itemList = itemDAO.getAllItems();
        } else {
            itemList = itemDAO.searchItems(searchKeyword.trim());
        }
    }

    public void filterByCategory(AjaxBehaviorEvent event) {
        if (selectedCategoryFilter == 0) {
            itemList = itemDAO.getAllItems();
        } else {
            itemList = itemDAO.getItemsByCategory(selectedCategoryFilter);
        }
    }

    public String loadItemForEdit(int itemId) {
        currentItem = itemDAO.getItemById(itemId);
        return "editItem?faces-redirect=true";
    }

    public String addItem() {
        itemDAO.addItem(currentItem);
        currentItem = new StationeryItem();
        itemList = null;
        return "viewItems?faces-redirect=true";
    }

    public String updateItem() {
        itemDAO.updateItem(currentItem);
        currentItem = new StationeryItem();
        itemList = null;
        return "viewItems?faces-redirect=true";
    }

    public String deleteItem(int itemId) {
        itemDAO.deleteItem(itemId);
        itemList = null;
        return "viewItems?faces-redirect=true";
    }

    public List<SelectItem> getCategorySelectItems() {
        List<SelectItem> items = new ArrayList<>();
        items.add(new SelectItem(0, "-- Select Category --"));
        for (Category c : categoryDAO.getAllCategories()) {
            items.add(new SelectItem(c.getCategoryId(), c.getCategoryName()));
        }
        return items;
    }

    public List<SelectItem> getCategoryFilterItems() {
        List<SelectItem> items = new ArrayList<>();
        items.add(new SelectItem(0, "-- All Categories --"));
        for (Category c : categoryDAO.getAllCategories()) {
            items.add(new SelectItem(c.getCategoryId(), c.getCategoryName()));
        }
        return items;
    }

    public StationeryItem getCurrentItem() { return currentItem; }
    public void setCurrentItem(StationeryItem currentItem) { this.currentItem = currentItem; }
    public String getSearchKeyword() { return searchKeyword; }
    public void setSearchKeyword(String searchKeyword) { this.searchKeyword = searchKeyword; }
    public int getSelectedCategoryFilter() { return selectedCategoryFilter; }
    public void setSelectedCategoryFilter(int selectedCategoryFilter) { this.selectedCategoryFilter = selectedCategoryFilter; }
}
