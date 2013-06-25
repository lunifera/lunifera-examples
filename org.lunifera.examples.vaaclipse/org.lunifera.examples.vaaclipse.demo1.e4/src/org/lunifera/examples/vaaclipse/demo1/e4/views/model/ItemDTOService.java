package org.lunifera.examples.vaaclipse.demo1.e4.views.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemDTOService {

	private List<ItemDTO> result = new ArrayList<ItemDTO>();

	public ItemDTOService() {
		result.add(new ItemDTO("112233", "Food", 10.23));
		result.add(new ItemDTO("99875", "Drinks", 3.77));
		result.add(new ItemDTO("36547", "Steel", 100.00));
	}

	public ItemDTO createNew() {
		return new ItemDTO("", "", 0.0);
	}

	public void save(ItemDTO item) {
		if (!result.contains(item)) {
			result.add(item);
		}
	}

	public Collection<? extends ItemDTO> getAll() {
		return result;
	}

}