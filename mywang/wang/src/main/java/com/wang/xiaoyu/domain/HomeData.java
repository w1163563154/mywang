package com.wang.xiaoyu.domain;

import java.util.ArrayList;

public class HomeData {

	public data data;
	
	public class data{
    
	public ArrayList<appHomeActivity> appHomeActivity;
		
	@Override
		public String toString() {
			
			return "appHomeActivity"+appHomeActivity;
		}

	}
   public class appHomeActivity{
		
		public String stdPartName;
		public int id;
		public int retailPrice;
		public String imageUrl;
		
		
		@Override
		public String toString() {
			return "appHomeActivity [stdPartName" +stdPartName+",id"+id;
		}
		
	}

	
	@Override
	public String toString() {
		
		return "data"+data;
	}
	
	
}
