/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.processing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author George
 */
public class Histogram {
    int[] histogram;
    int[] equalized;
    int numOfPixels;
    
    /*Δημιουργεί το ιστόγραμμα μιας εικόνας YUV.*/
    public Histogram(YUVImage img){
	histogram = new int[255];
	numOfPixels = img.getHeight() * img.getWidth();
	for(int i=0;i<img.getHeight();i++){
	    for(int j=0; j< img.getWidth(); j++){
		histogram[img.getPixel(i, j).getY()]++;
	    }
	}
    }
    
    /*Εκτυπώνει το ιστόγραμμα σε ένα String ως εξής. Κάθε τιμή 
    φωτεινότητας καταλαμβάνει μία γραμμή. Η γραμμή περιέχει τα εξής: 
    a. την αριθμητική τιμή της φωτεινότητας και b. τόσους χαρακτήρες ‘*’ 
    όσους αντιστοιχούν (αναλογικά) στα pixels που έχουν τη συγκεκριμένη 
    τιμή φωτεινότητας. Επειδή κάποιες τιμές φωτεινότητας είναι πολύ υψηλές 
    και ξεφεύγουν το μέγιστο εύρος γραμμής, οι τιμές φωτεινότητας 
    κανονικοποιούνται ώστε η μέγιστη τιμή της φωτεινότητας για την εικόνα 
    να περιγράφεται από max 80 χαρακτήρες ‘*’.*/
    @Override
    public String toString(){
	StringBuilder str = new StringBuilder();
	int max;
	for(int i=0; i< histogram.length; i++){
	    max = histogram[i]>80 ? 80 : histogram[i];
	    for (int j=0; j< max; j++){
		str.append("*");
	    }
	    str.append("\n");
	}
	return str.toString();
    }
    
    /*εκτυπώνει το String της μεθόδου toString() σε ένα αρχείο.*/
    public void toFile(File file){
	
	try (FileWriter fw = new FileWriter(file)) {
	    fw.append(toString());
	} catch (IOException ex) {
	    System.err.format("File \"%s\" not found", file.getName());
	}
    }
    
    /*Η μέθοδος εξισορροπεί το ιστόγραμμα.*/
    public void equalize(){
	double[] px = new double[histogram.length];
	for(int i=0;i<histogram.length;i++){
	    px[i] = ((double)histogram[i])/numOfPixels;
	}
	double[] cdf = new double[histogram.length];
	cdf[0] = px[0];
	for(int i=1;i<histogram.length;i++){
	    cdf[i] = cdf[i-1]+px[i];
	}
	equalized = new int[histogram.length];
	for(int i=1;i<histogram.length;i++){
	    equalized[i]= (int) (cdf[i]*235);
	}
	
    }
    
    /*Επιστρέφει την εξισορροπημένη τιμή φωτεινότητας που αντιστοιχεί στην δοθείσα τιμή φωτεινότητας luminocity .*/
    public short getEqualizedLuminocity(int luminocity){
	return (short) equalized[luminocity];
    }
}
