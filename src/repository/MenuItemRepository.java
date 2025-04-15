package repository;

import model.*;

import java.util.*;

public class MenuItemRepository {

    private Map<Long, MenuItem> menuItemMap = new HashMap<>();
    private static long id = 1;

    public MenuItem save(MenuItem menuItem) {
        if(menuItem.getId() == 0) {
            menuItem.setId(id++);
        }
        menuItemMap.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    public Optional<MenuItem> findById(long id) {
        return Optional.ofNullable(menuItemMap.get(id));
    }
}
