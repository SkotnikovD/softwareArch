/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Part;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author HOME
 */
public class Converter {
    public static String getStringCategory(int category){
	switch(category){
	    case 15: {
		return("Other");
	    }case 1: {
		return("Interier");
	    }case 2: {
		return("Wheels and rims");
	    }case 3: {
		return("Air conditioner");
	    }case 4: {
		return("Body");
	    }case 5: {
		return("Suspension");
	    }case 6: {
		return("Steering");
	    }case 7: {
		return("Exhaust");
	    }case 8: {
		return("Coolant");
	    }case 9: {
		return("Fuel system");
	    }case 10: {
		return("Breaks");
	    }case 11: {
		return("Transmission");
	    }case 12: {
		return("Changes");
	    }case 13: {
		return("Electro");
	    }case 14: {
		return("Bears and seals");
	    }default: {
		return ("Unknown category");
	    }
	}
    }
    //SUV, CABRI, SEDAN, HATCH, COUP, TRUCK
    public static int getFactorByString(String ff){
	switch(ff){
	    case "Sedan": {
		return(1);
	    }
	    case "Hatchback": {
		return(2);
	    }
	    case "Coupe": {
		return(3);
	    }
	    case "SUV": {
		return(4);
	    }
	    case "Universal": {
		return(5);
	    }
	    default: {
		return(0);
	    }
	}
    }
    
    public static String[] getFactorList(){
	String[] a = {"Sedan", "Hatchback", "Coupe", "SUV", "Universal"};
	return a;
    }
    
    public static String[] getCategoryList(){
	String[] a = {"Interier", "Wheels and rims","Air conditioner", 
	"Body", "Suspension","Steering", "Exhaust","Coolant", "Fuel system",
	"Breaks","Transmission","Changes" ,"Electro" ,"Bears and seals"};
	return a;
    }
    
    public static String getStringFactor(int ff){
	switch(ff){
	    case 1: {
		return("Sedan");
	    }
	    case 2: {
		return("Hatchback");
	    }
	    case 3: {
		return("Coupe");
	    }
	    case 4: {
		return("SUV");
	    }
	    case 5: {
		return("Universal");
	    }
	    default: {
		return("Unknown formfactor");
	    }
	}
    }
    
    public static int getCategoryByString(String category){
	switch(category){
	    case "Other": {
		return(15);
	    }case "Interier": {
		return(1);
	    }case "Wheels and rims": {
		return(2);
	    }case "Air conditioner": {
		return(3);
	    }case "Body": {
		return(4);
	    }case "Suspension": {
		return(5);
	    }case "Steering": {
		return(6);
	    }case "Exhaust": {
		return(7);
	    }case "Coolant": {
		return(8);
	    }case "Fuel system": {
		return(9);
	    }case "Breaks": {
		return(10);
	    }case "Transmission": {
		return(11);
	    }case "Changes": {
		return(12);
	    }case "Electro": {
		return(13);
	    }case "Bears and seals": {
		return(14);
	    }default: {
		return (0);
	    }
	}
    }
}
