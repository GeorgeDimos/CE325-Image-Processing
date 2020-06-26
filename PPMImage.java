/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.processing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author George
 */
public class PPMImage extends RGBImage{
    
    /*Δημιουργεί ένα αντικείμενο της κλάσης λαμβάνοντας 
    ως είσοδο το περιεχόμενο του αρχείου file . 
    Στην περίπτωση που το αρχείο file δεν υπάρχει 
    ή υπάρχει αλλά δεν μπορεί να το διαβάσει 
    παράγει ένα java.io.FileNotFoundException , 
    ενώ στην περίπτωση που το αρχείο file δεν είναι τύπου PPM 
    τότε παράγει ένα ce325.hw3.UnsupportedFileFormatException .*/
    public PPMImage(java.io.File file) throws java.io.FileNotFoundException, UnsupportedFileFormatException{
	super(0, 0, 0);
	if(!file.canRead()|| !file.exists()){
	    throw new java.io.FileNotFoundException();
	}
	
	Scanner sc = new Scanner(file);
	if(!sc.next().equals("P3")){
	    throw new UnsupportedFileFormatException();
	}
	int colordepth;
	width = sc.nextInt();
	height = sc.nextInt();
	colordepth = sc.nextInt();
	pixels = new RGBPixel[height][width];
	int red, green, blue;
	for(int i=0; i<height; i++){
	    for(int j=0; j<width; j++){
		red = sc.nextInt();
		green = sc.nextInt();
		blue = sc.nextInt();
		if(red>colordepth || green>colordepth || blue>colordepth ){
		    throw new UnsupportedFileFormatException();
		}
		pixels[i][j] = new RGBPixel((short)red, (short)green, (short)blue);
	    }
	}
    }

    
    public PPMImage(RGBImage img) {
	super(img);
    }
    
    /*Κατασκευάζει ένα PPMImage από ένα YUVImage.*/
    public PPMImage(YUVImage img){
	super(img.getWidth(), img.getHeight(), 255);
	for(int i=0; i<height; i++){
	    for(int j=0; j<width; j++){
		pixels[i][j] = new RGBPixel(img.getPixel(i, j));
	    }
	}
    }
    
    @Override
    public String toString(){
	StringBuilder str= new StringBuilder();
	str.append("P3\n").append(width).append(" ").append(height).append(" ").append("255\n");
	for(int i=0; i<height; i++){
	    for(int j=0; j<width; j++){
		str.append(this.getPixel(i, j).toString());
	    }
	    str.append("\n");
	}
	return str.toString();
    }
    
    public void toFile(java.io.File file){
	
	try (FileWriter fw = new FileWriter(file)) {
	    fw.append(this.toString());
	} catch (IOException ex) {
	    System.err.format("File \"%s\" not found", file.getName());
	}
    }
}
