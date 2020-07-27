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
