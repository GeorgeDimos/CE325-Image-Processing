/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.processing;

/**
 *
 * @author George
 */
public interface Image {
    
    /*μετατρέπει την εικόνα σε ασπρόμαυρη.*/
    public void grayscale();
    
    /*διπλασιάζει το μέγεθος της εικόνας.*/
    public void doublesize();
    
    /*υποδιπλασιάζει το μέγεθος της εικόνας.*/
    public void halfsize();
    
    /*περιστρέφει την εικόνα κατά 90 μοίρες δεξιόστροφα.*/
    public void rotateClockwise();

}
