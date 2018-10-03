package com.wang.xiaoyu.domain;

import java.util.ArrayList;

public class HomePagerListData {

	public Data data;
	
	public class Data{
		
		public ArrayList<HomePagerListBaner> appHomeBaner;
		public ArrayList<HomePagerListService> appHomeService;
		@Override
		public String toString() {
			
			return "appHomeBaner"+appHomeBaner+"appHomeService"+appHomeService;
		}
	}
	
	public class HomePagerListBaner{
		public String photo;
		public String url;
		public String id;
		
		@Override
		public String toString() {
			
			return "id"+photo;
		}
	}
	
	public class HomePagerListService{
		public String name;
		public String imageUrl;
		
		@Override
		public String toString() {
			
			return "name"+imageUrl;
		}
	}
	
	@Override
	public String toString() {
		
		return "data"+data;
	}
}
