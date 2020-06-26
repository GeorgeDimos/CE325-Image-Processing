/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.processing;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author George
 */
public class YUVImage {
    private int width, height;
    private YUVPixel[][] pixels;
    
    /*Δημιουργεί ένα αντικείμενο τύπου YUVImage με διαστάσεις width x height . 
    Οι τιμές για τις παραμέτρους Y,U,V ορίζονται ως εξής: Υ=16, U=128, V=128 .*/
    public YUVImage(int width, int height){
	this.width = width;
	this.height = height;
	pixels = new YUVPixel[height][width];
	for(int i=0; i< height; i++){
	    for (int j=0; j< width; j++){
		pixels[i][j] = new YUVPixel((short)16, (short)128, (short)128);
	    }
	}
    }
    
    /*Δημιουργεί ένα αντικείμενο τύπου YUVImage από ένα άλλο αντικείμενο τύπου 
    YUVImage. Η νέα εικόνα είναι αντίγραφο της αρχικής (copy constructor).*/
    public YUVImage(YUVImage copyImg){
	height = copyImg.height;
	width = copyImg.width;
	pixels = copyImg.pixels.clone();
    }
    
    /*Δημιουργεί ένα αντικείμενο τύπου YUVImage από ένα αντικείμενο τύπου RGBImage.*/
    public YUVImage(RGBImage RGBImg){
	height = RGBImg.getHeight();
	width = RGBImg.getWidth();
	pixels = new YUVPixel[height][width];
	for(int i=0; i<RGBImg.getHeight(); i++){
	    for(int j=0; j< RGBImg.getWidth(); j++){
		pixels[i][j] = new YUVPixel(RGBImg.getPixel(i, j));
	    }
	}
    }
    
    /*Δημιουργεί ένα αντικείμενο τύπου YUVImage την πληροφορία του οποίου 
    διαβάζει από το αρχείο file . Η κωδικοποίηση του αρχείου είναι YUV.
    Στην περίπτωση που a. το αρχείο file δεν υπάρχει παράγει ένα 
    java.io.FileNotFoundException . b. το αρχείο file δεν είναι τύπου YUV 
    τότε παράγει ένα ce325.hw3.UnsupportedFileFormatException . */
    public YUVImage(java.io.File file) throws UnsupportedFileFormatException, FileNotFoundException{
	if(!file.exists() || !file.canRead()){
	    throw new FileNotFoundException();
	}
	
	Scanner sc = new Scanner(file);
	if(!sc.hasNext() || !sc.next().equals("YUV3")){
	    throw new UnsupportedFileFormatException();
	}
	
	width = sc.nextInt();
	height = sc.nextInt();
	pixels = new YUVPixel[height][width];
	
	for (int i=0; i<height; i++){
	    for (int j=0; j< width; j++){
		pixels[i][j] = new YUVPixel((short)sc.nextInt(), (short)sc.nextInt(), (short)sc.nextInt());
	    }
	}
    }
    
    public int getHeight(){
	return height;
    }
    
    public int getWidth(){
	return width;
    }
    
    public YUVPixel getPixel(int height,int width){
	return pixels[height][width];
    }
    
    /*επιστρέφει ένα java.lang.String που περιέχει τα περιεχόμενα του αρχείου σε format YUV.*/
    @Override
    public String toString(){
	StringBuilder str = new StringBuilder();
	str.append("YUV3\n").append(width).append(" ").append(height).append("\n");
	for (int i=0; i<height; i++){
	    for (int j=0; j< width; j++){
		str.append(pixels[i][j].getY()).append(" ").append(pixels[i][j].getU()).append(" ").append(pixels[i][j].getV()).append("\n");
	    }
	}
	return str.toString();
    }

    /*Γράφει την εικόνα σε μορφή YUV μέσα στο αρχείο file . 
    Εάν το αρχείο υπάρχει ήδη, διαγράφει το υφιστάμενο περιεχόμενο.*/
    public void toFile(java.io.File file){
	
	try (FileWriter fw = new FileWriter(file)) {
	    fw.append(toString());
	} catch (IOException ex) {
	    System.err.format("File \"%s\" not found", file.getName());
	}
    }
    
    /*Εξισορροπεί την εικόνα χρησιμοποιώντας τον αλγόριθμο εξισορρόπησης 
    ιστογράμματος που αναφέρεται παρακάτω.*/
    public void equalize(){
	Histogram h = new Histogram(this);
	h.equalize();
	for(int i=0; i< height; i++){
	    for (int j=0; j< width; j++){
		pixels[i][j].setY(h.getEqualizedLuminocity(pixels[i][j].getY()));
	    }
	}
    }
    
}
