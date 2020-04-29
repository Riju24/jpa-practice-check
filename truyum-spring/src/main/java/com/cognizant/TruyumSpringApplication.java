package com.cognizant;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.dao.MenuItemDaoCollectionImpl;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;
import com.cognizant.truyum.util.DateUtil;

@SpringBootApplication
public class TruyumSpringApplication {

	

	private static MenuItemService menuItemService;
	
	@Autowired
	public void setMenuItemService(MenuItemService menuItemService)
	{
		this.menuItemService = menuItemService;
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TruyumSpringApplication.class);
	
	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(TruyumSpringApplication.class, args);
		
		
		LOGGER.info("Inside main");
		testMenuItemListAdmin();
		testMenuItemListCustomer();
		testGetMenuItem();
		testEditMenu();
		
	
	
	}
		
	// Testing GetMenuItemListAdmin
	public static void testMenuItemListAdmin()
	{
			LOGGER.info("Start");
			Set<MenuItem> list = menuItemService.getMenuItemListAdmin();
			LOGGER.debug("Admin list{}",list);
			LOGGER.info("End");
	}
		
	// Testing GetMenuItemListCustomer
	public static void testMenuItemListCustomer()
	{
		LOGGER.info("Start");
		Set<MenuItem> cusList = menuItemService.getMenuItemListCustomer();
		LOGGER.debug("Customer list{}",cusList);
		LOGGER.info("End");
	}
	
	
	// Testing GetMenuItem
	public static void testGetMenuItem()
	{
		LOGGER.info("Start");
		MenuItem item = menuItemService.getMenuItem();
		LOGGER.debug("Item{}",item);
		LOGGER.info("End");
	}

	// Testing ModifyMenuItem
	public static void testEditMenu()
	{
		LOGGER.info("start");
		MenuItem menuItem = new MenuItem(5, "Chocolate Muffins", 30.00f, true, DateUtil.convertToDate("12/12/2012"), "Starter",
				true);
		menuItemService.editMenuItem(menuItem);
		Set<MenuItem> items = menuItemService.getMenuItemListAdmin();
		LOGGER.debug("Items{}", items);
		LOGGER.info("end");
	}
		
	

}
