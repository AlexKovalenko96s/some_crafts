package ua.kas.laba11;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
	
	@FXML TextField t1;
	@FXML TextField t2;
	@FXML TextField price1;
	@FXML TextField price2;	
	String itl_1;
	String itl_2;
	String ua_1;
	String ua_2;
	String ua_3;
	ArrayList<String> name = new ArrayList<String>(); 
	ArrayList<Integer> al_1 = new ArrayList<Integer>(); 
	ArrayList<Integer> al_2 = new ArrayList<Integer>();
	ArrayList<Integer> ua_11 = new ArrayList<Integer>(); 
	ArrayList<Integer> ua_22 = new ArrayList<Integer>();
	ArrayList<Integer> ua_33 = new ArrayList<Integer>();
	
	public void first_btn(ActionEvent e) throws SQLException{
		
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/laba11", "root", "root");
		Statement myStmt = myConn.createStatement();
		ResultSet myRs = myStmt.executeQuery("select * from wear");
		
		while (myRs.next()) {
			System.out.println(myRs.getString("name")+" "+myRs.getString("elemet1")+" "+myRs.getString("elemet2")+" "+myRs.getString("elemet3")+" "+myRs.getString("elemet4"));
		}
	}
	
	public void second_btn(ActionEvent e) throws SQLException{
		
		String tt1 = t1.getText();
		String tt2 = t2.getText();
		
		if((tt1.equals("1")||tt1.equals("2")||tt1.equals("3")||tt1.equals("4")) && (tt2.equals("1")||tt2.equals("2")||tt2.equals("3")||tt2.equals("4"))){
		
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/laba11", "root", "root");
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from wear");
			
			while (myRs.next()) {
				System.out.println(myRs.getString("name")+" "+myRs.getString("elemet"+tt1)+" "+myRs.getString("elemet"+tt2));
			}
		}
		else{
			System.err.println("enter correct parameters");
		}
	}
	
	public void third_btn(ActionEvent e) throws SQLException{
		
		String tt1 = price1.getText();
		String tt2 = price2.getText();
		
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/laba11", "root", "root");
		ResultSet myRs = null;
		java.sql.PreparedStatement myStmt;
		
		for(int i=Integer.parseInt(tt1); i<=Integer.parseInt(tt2); i++ ){
			myStmt = myConn.prepareStatement("select * from wear where elemet1=?");
			myStmt.setString(1, "element1 - "+i+"$");
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				System.out.println(myRs.getString("name")+" "+myRs.getString("elemet1"));
			}
			myStmt = myConn.prepareStatement("select * from wear where elemet2=?");
			myStmt.setString(1, "element2 - "+i+"$");
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				System.out.println(myRs.getString("name")+" "+myRs.getString("elemet2"));
			}
			myStmt = myConn.prepareStatement("select * from wear where elemet3=?");
			myStmt.setString(1, "element3 - "+i+"$");
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				System.out.println(myRs.getString("name")+" "+myRs.getString("elemet3"));
			}
			myStmt = myConn.prepareStatement("select * from wear where elemet4=?");
			myStmt.setString(1, "element4 - "+i+"$");
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				System.out.println(myRs.getString("name")+" "+myRs.getString("elemet4"));
			}
		}
	}
	
	public void fourth_btn(ActionEvent e) throws SQLException{
		al_1.clear();
		al_2.clear();
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/laba11", "root", "root");
		Statement myStmt = myConn.createStatement();
		ResultSet myRs = myStmt.executeQuery("select * from wear where country='itl'");
		
		int i=0;
		
		while (myRs.next()) {
			i++;
			
			if(i==1){
				itl_1 = myRs.getString("name")+"&"+" "+myRs.getString("elemet1")+" "+myRs.getString("elemet2")+" "+myRs.getString("elemet3")+" "+myRs.getString("elemet4");	
			}
			if(i==2){
				itl_2 = myRs.getString("name")+"&"+" "+myRs.getString("elemet1")+" "+myRs.getString("elemet2")+" "+myRs.getString("elemet3")+" "+myRs.getString("elemet4");	
			}		
		}
		
		
		String name1 = itl_1.substring(0,itl_1.indexOf("&"));
		String name2 = itl_2.substring(0,itl_2.indexOf("&"));
				
		al_1.add(Integer.parseInt(itl_1.substring(itl_1.indexOf("1 - ")+4, itl_1.indexOf("$ element2"))));
		al_1.add(Integer.parseInt(itl_1.substring(itl_1.indexOf("2 - ")+4, itl_1.indexOf("$ element3"))));
		al_1.add(Integer.parseInt(itl_1.substring(itl_1.indexOf("3 - ")+4, itl_1.indexOf("$ element4"))));
		al_1.add(Integer.parseInt(itl_1.substring(itl_1.indexOf("4 - ")+4, itl_1.lastIndexOf("$"))));
		
//		System.out.println(al_1);
		
		al_2.add(Integer.parseInt(itl_2.substring(itl_2.indexOf("1 - ")+4, itl_2.indexOf("$ element2"))));
		al_2.add(Integer.parseInt(itl_2.substring(itl_2.indexOf("2 - ")+4, itl_2.indexOf("$ element3"))));
		al_2.add(Integer.parseInt(itl_2.substring(itl_2.indexOf("3 - ")+4, itl_2.indexOf("$ element4"))));
		al_2.add(Integer.parseInt(itl_2.substring(itl_2.indexOf("4 - ")+4, itl_2.lastIndexOf("$"))));
		
//		System.out.println(al_2);
		
		al_1.sort(null);
		al_2.sort(null);
		
//		System.out.println(al_1);
//		System.out.println(al_2);
		
		int s1 = al_1.size()-1;
		int s2 = al_2.size()-1;
		
		if(al_1.get(s1)>al_2.get(s2)){
			System.err.println(name1);
		}
		else{
			System.err.println(name2);
		}
	}
	public void fifth_btn(ActionEvent e) throws SQLException{
		al_1.clear();
		al_2.clear();
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/laba11", "root", "root");
		Statement myStmt = myConn.createStatement();
		ResultSet myRs = myStmt.executeQuery("select * from wear where country='itl'");
		
		int i=0;
		
		while (myRs.next()) {
			i++;
			
			if(i==1){
				itl_1 = myRs.getString("name")+"&"+" "+myRs.getString("elemet1")+" "+myRs.getString("elemet2")+" "+myRs.getString("elemet3")+" "+myRs.getString("elemet4");	
			}
			if(i==2){
				itl_2 = myRs.getString("name")+"&"+" "+myRs.getString("elemet1")+" "+myRs.getString("elemet2")+" "+myRs.getString("elemet3")+" "+myRs.getString("elemet4");	
			}		
		}
		
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/laba11", "root", "root");
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select * from wear where country='ua'");
		
		int i_ua=0;
		
		while (myRs.next()) {
			i_ua++;
			
			if(i_ua==1){
				ua_1 = myRs.getString("name")+"&"+" "+myRs.getString("elemet1")+" "+myRs.getString("elemet2")+" "+myRs.getString("elemet3")+" "+myRs.getString("elemet4");	
			}
			if(i_ua==2){
				ua_2 = myRs.getString("name")+"&"+" "+myRs.getString("elemet1")+" "+myRs.getString("elemet2")+" "+myRs.getString("elemet3")+" "+myRs.getString("elemet4");	
			}
			if(i_ua==3){
				ua_3 = myRs.getString("name")+"&"+" "+myRs.getString("elemet1")+" "+myRs.getString("elemet2")+" "+myRs.getString("elemet3")+" "+myRs.getString("elemet4");	
			}	
		}
		
		String name3 = ua_1.substring(0,ua_1.indexOf("&"));
		String name4 = ua_2.substring(0,ua_2.indexOf("&"));
		String name5 = ua_3.substring(0,ua_3.indexOf("&"));
		
		
				
		al_1.add(Integer.parseInt(itl_1.substring(itl_1.indexOf("1 - ")+4, itl_1.indexOf("$ element2"))));
		al_1.add(Integer.parseInt(itl_1.substring(itl_1.indexOf("2 - ")+4, itl_1.indexOf("$ element3"))));
		al_1.add(Integer.parseInt(itl_1.substring(itl_1.indexOf("3 - ")+4, itl_1.indexOf("$ element4"))));
		al_1.add(Integer.parseInt(itl_1.substring(itl_1.indexOf("4 - ")+4, itl_1.lastIndexOf("$"))));

		al_2.add(Integer.parseInt(itl_2.substring(itl_2.indexOf("1 - ")+4, itl_2.indexOf("$ element2"))));
		al_2.add(Integer.parseInt(itl_2.substring(itl_2.indexOf("2 - ")+4, itl_2.indexOf("$ element3"))));
		al_2.add(Integer.parseInt(itl_2.substring(itl_2.indexOf("3 - ")+4, itl_2.indexOf("$ element4"))));
		al_2.add(Integer.parseInt(itl_2.substring(itl_2.indexOf("4 - ")+4, itl_2.lastIndexOf("$"))));
		
		ua_11.add(Integer.parseInt(ua_1.substring(ua_1.indexOf("1 - ")+4, ua_1.indexOf("$ element2"))));
		ua_11.add(Integer.parseInt(ua_1.substring(ua_1.indexOf("2 - ")+4, ua_1.indexOf("$ element3"))));
		ua_11.add(Integer.parseInt(ua_1.substring(ua_1.indexOf("3 - ")+4, ua_1.indexOf("$ element4"))));
		ua_11.add(Integer.parseInt(ua_1.substring(ua_1.indexOf("4 - ")+4, ua_1.lastIndexOf("$"))));

		ua_22.add(Integer.parseInt(ua_2.substring(ua_2.indexOf("1 - ")+4, ua_2.indexOf("$ element2"))));
		ua_22.add(Integer.parseInt(ua_2.substring(ua_2.indexOf("2 - ")+4, ua_2.indexOf("$ element3"))));
		ua_22.add(Integer.parseInt(ua_2.substring(ua_2.indexOf("3 - ")+4, ua_2.indexOf("$ element4"))));
		ua_22.add(Integer.parseInt(ua_2.substring(ua_2.indexOf("4 - ")+4, ua_2.lastIndexOf("$"))));
		
		ua_33.add(Integer.parseInt(ua_3.substring(ua_3.indexOf("1 - ")+4, ua_3.indexOf("$ element2"))));
		ua_33.add(Integer.parseInt(ua_3.substring(ua_3.indexOf("2 - ")+4, ua_3.indexOf("$ element3"))));
		ua_33.add(Integer.parseInt(ua_3.substring(ua_3.indexOf("3 - ")+4, ua_3.indexOf("$ element4"))));
		ua_33.add(Integer.parseInt(ua_3.substring(ua_3.indexOf("4 - ")+4, ua_3.lastIndexOf("$"))));
		
		al_1.sort(null);
		al_2.sort(null);
		ua_11.sort(null);
		ua_22.sort(null);
		ua_33.sort(null);
			
		if(al_1.get(0)>al_2.get(0)){
			if(al_1.get(0)>ua_11.get(0)){
				name.add(name3);
			}
			if(al_1.get(0)>ua_22.get(0)){
				name.add(name4);
			}
			if(al_1.get(0)>ua_33.get(0)){
				name.add(name5);
			}
		}
		else{
			if(al_1.get(0)>ua_11.get(0)){
				name.add(name3);
			}
			if(al_1.get(0)>ua_22.get(0)){
				name.add(name4);
			}
			if(al_1.get(0)>ua_33.get(0)){
				name.add(name5);
			}
		}
		name.sort(null);
		System.out.println(name);
	}

}
