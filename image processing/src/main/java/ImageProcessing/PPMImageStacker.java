package ImageProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author George
 */
public class PPMImageStacker {
	List<PPMImage> lst;
	PPMImage result;

	/*
	 * Λαμβάνει ως είσοδο ένα αρχείο το οποίο πρέπει να είναι κατάλογος. Εάν το
	 * αρχείο δεν υπάρχει εμφανίζει το μήνυμα “ [ERROR] Directory <dirname> does not
	 * exist! ”, ενώ εάν το αρχείο υπάρχει αλλά δεν είναι κατάλογος εμφανίζει το
	 * μήνυμα “[ ERROR] <dirname> is not a directory! ”.
	 */
	public PPMImageStacker(java.io.File dir) throws FileNotFoundException {

		if (!dir.exists()) {
			System.out.format(" [ERROR] Directory %s does not exist! \n", dir.getName());
			throw new FileNotFoundException();
		}
		if (!dir.isDirectory()) {
			System.out.format(" [ ERROR] %s is not a directory! \n", dir.getName());
			throw new FileNotFoundException();
		}
		lst = new ArrayList<>();

		for (File f : dir.listFiles()) {
			try {
				lst.add(new PPMImage(f));
				System.out.format("success adding %s\n", f.getName());
			} catch (UnsupportedFileFormatException ex) {
				System.out.format("File %s not a PPM image", f.getName());
			}
		}
	}

	/* Εφαρμόζει την μέθοδο stacking για τις εικόνες που διάβασε. */
	void stack() {
		Iterator<PPMImage> iter = lst.iterator();
		PPMImage current;
		int size = lst.size();
		int red = 0, green = 0, blue = 0;

		if (!iter.hasNext()) {
			return;
		}
		result = new PPMImage((PPMImage) iter.next());

		iter = lst.iterator();
		for (int i = 0; i < result.getHeight(); i++) {
			for (int j = 0; j < result.getWidth(); j++) {
				while (iter.hasNext()) {
					current = (PPMImage) iter.next();
					red += current.getPixel(i, j).getRed();
					green += current.getPixel(i, j).getGreen();
					blue += current.getPixel(i, j).getBlue();
				}
				iter = lst.iterator();
				result.setPixel(i, j,
						new RGBPixel((short) (red / size), (short) (green / size), (short) (blue / size)));
				red = 0;
				green = 0;
				blue = 0;
			}
		}
	}

	/* Επιστρέφει την εικόνα που προέκυψε από την διαδικασία του stacking. */
	public PPMImage getStackedImage() {
		stack();
		return result;
	}

}
