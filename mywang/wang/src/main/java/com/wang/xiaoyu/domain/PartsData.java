package com.wang.xiaoyu.domain;

public class PartsData {

	public Data data;
	
	public class Data{
		public String stdPartName;
		public int retailPrice;
		public String imageUrl;
		
		@Override
		public String toString() {
			
			return "retailPrice="+retailPrice+"stdPartName"+stdPartName;
		}
	}
	
	@Override
	public String toString() {
		
		return "data="+data;
	}
}
