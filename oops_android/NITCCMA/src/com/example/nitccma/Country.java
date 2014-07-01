package com.example.nitccma;

public class Country {
	String name = null;
	 String roll = null;
	 boolean selected = false;
	  
	 public Country(String name, String roll, boolean selected) {
	  super();
	  this.roll = roll;
	  this.name = name;
	  this.selected = selected;
	 }
	  
	 public String getRoll() {
	  return roll;
	 }
	 public void setRoll(String roll) {
	  this.roll = roll;
	 }
	 public String getName() {
	  return name;
	 }
	 public void setName(String name) {
	  this.name = name;
	 }
	 
	 public boolean isSelected() {
	  return selected;
	 }
	 public void setSelected(boolean selected) {
	  this.selected = selected;
	 }

}
