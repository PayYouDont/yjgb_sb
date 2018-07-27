package com.gospell.chitong.rdcenter.broadcast.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;

public class SortUtil{

	//菜单根据number排序
	public static void MenuListSort(List<Menu> menus) {
		Collections.sort(menus,new Comparator<Menu>() {

			@Override
			public int compare(Menu o1, Menu o2) {
				return o1.getNumber()-o2.getNumber();
			}

		});
	}
}
