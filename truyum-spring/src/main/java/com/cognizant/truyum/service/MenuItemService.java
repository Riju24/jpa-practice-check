package com.cognizant.truyum.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.MenuItemRepository;
import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.model.*;


@Service
public class MenuItemService {

	@Autowired
	private MenuItemDao menuItemDao;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Transactional
	public Set<MenuItem> getMenuItemListAdmin()
	{
		
		//List<MenuItem> list = menuItemDao.getMenuItemListAdmin();
		return new HashSet(menuItemRepository.findAll());
	
	}
	
	@Transactional
	public Set<MenuItem> getMenuItemListCustomer()
	{
		//List<MenuItem> list = menuItemDao.getMenuItemListCustomer();		
		
		List<MenuItem> menuItems= menuItemRepository.findByActiveAndDateOfLaunchBefore(true,new Date());
		Set<MenuItem> items = new HashSet(menuItems);
		return items;
	}
	
	
	@Transactional
	public MenuItem getMenuItem()
	{
		MenuItem m = null;
		try {
		m =  menuItemRepository.getOne(1);
		}
		catch(Exception e)
		{
			
		}
		
		return m;
	}
	
	@Transactional
	public void editMenuItem(MenuItem menuItem)
	{
		Optional<MenuItem> m =  menuItemRepository.findById((int) menuItem.getId());
		MenuItem item = m.get();
		
		item.setName(menuItem.getName());
		item.setPrice(menuItem.getPrice());
		item.setActive(menuItem.isActive());
		item.setCategory(menuItem.getCategory());
		item.setDateOfLaunch(menuItem.getDateOfLaunch());
		item.setFreeDelivery(menuItem.isFreeDelivery());
		
		menuItemRepository.save(item);
		
	}
	
	
	@Transactional
	public void addItems()
	{
		
		List<MenuItem> list = menuItemDao.getMenuItemListAdmin();
		for(MenuItem m: list)
		{
			menuItemRepository.save(m);
		}
		
	}
}
