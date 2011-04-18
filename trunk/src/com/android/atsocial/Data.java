package com.android.atsocial;

public class Data {
	
	private static String URI;
	private static int userID;
	private static String type;
	private static String suffix;
	
	public Data() {
		// TODO Auto-generated constructor stub
	}
	
	public Data(String URI, int userID, String type, String suffix){
		this.URI = URI;
		this.userID = userID;
		this.type = type;
		this.suffix = suffix;		
	}
	
	public static String getSuffix() {
		return suffix;
	}
	
	public static String getType() {
		return type;
	}
	
	public static String getURI() {
		return URI;
	}
	
	public static int getUserID() {
		return userID;
	}
	

}
