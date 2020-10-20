package ImageProcessing;

/**
 *
 * @author George
 */
public class RGBImage implements Image {
	protected RGBPixel[][] pixels;
	protected int width, height;
	public final static int MAX_COLORDEPTH = 255;

	/*
	 * Δημιουργεί μία RGB εικόνα με διαστάσεις πλάτος width και ύψος height και
	 * μέγιστη τιμή φωτεινότητας colordepth .
	 */
	public RGBImage(int width, int height, int colordepth) {
		pixels = new RGBPixel[height][width];
		this.width = width;
		this.height = height;
	}

	/*
	 * Δημιουργεί μία εικόνα RGB από μία άλλη εικόνα RGB. Η νέα εικόνα είναι
	 * αντίγραφο της αρχικής (copy constructor).
	 */
	public RGBImage(RGBImage copyImg) {
		pixels = copyImg.pixels.clone();
		width = copyImg.width;
		height = copyImg.height;
	}

	/* Επιστρέφει έναν ακέραιο που αντιστοιχεί στην τιμή του πλάτους της εικόνας. */
	public int getWidth() {
		return width;
	}

	/* Επιστρέφει έναν ακέραιο που αντιστοιχεί στην τιμή του ύψους της εικόνας. */
	public int getHeight() {
		return height;
	}

	/*
	 * Επιστρέφει έναν ακέραιο που αντιστοιχεί στην τιμή του βάθους χρώματος της
	 * εικόνας.
	 */
	public int getColorDepth() {
		return MAX_COLORDEPTH;
	}

	/*
	 * επιστρέφει το αντικείμενο τύπου Pixel που αντιστοιχεί στη γραμμή row, col του
	 * πίνακα της εικόνας.
	 */
	public RGBPixel getPixel(int row, int col) {
		return pixels[row][col];
	}

	/* θέτει το αντικείμενο pixel στη γραμμή row, col του πίνακα της εικόνας. */
	public void setPixel(int row, int col, RGBPixel pixel) {
		pixels[row][col] = pixel;
	}

	/* Δημιουργεί μία εικόνα RGB από μία εικόνα YUV. */
	public RGBImage(YUVImage YUVImg) {
		height = YUVImg.getHeight();
		width = YUVImg.getWidth();
		pixels = new RGBPixel[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pixels[i][j] = new RGBPixel(YUVImg.getPixel(i, j));
			}
		}
	}

	@Override
	public void grayscale() {
		double red, green, blue;
		short grey;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				red = pixels[i][j].getRed() * 0.3;
				green = pixels[i][j].getGreen() * 0.59;
				blue = pixels[i][j].getBlue() * 0.11;
				grey = (short) (red + green + blue);
				pixels[i][j].setRGB(grey, grey, grey);
			}
		}
	}

	@Override
	public void doublesize() {
		RGBPixel[][] doublePixels = new RGBPixel[2 * height][2 * width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				doublePixels[2 * i][2 * j] = new RGBPixel(pixels[i][j]);
				doublePixels[2 * i + 1][2 * j] = new RGBPixel(pixels[i][j]);
				doublePixels[2 * i][2 * j + 1] = new RGBPixel(pixels[i][j]);
				doublePixels[2 * i + 1][2 * j + 1] = new RGBPixel(pixels[i][j]);
			}
		}
		pixels = doublePixels;
		width = 2 * width;
		height = 2 * height;
	}

	@Override
	public void halfsize() {
		RGBPixel[][] halfPixels = new RGBPixel[height / 2][width / 2];
		short red, green, blue;
		for (int i = 0; i < height / 2; i++) {
			for (int j = 0; j < width / 2; j++) {
				red = (short) ((pixels[2 * i][2 * j].getRed() + pixels[2 * i + 1][2 * j].getRed()
						+ pixels[2 * i][2 * j + 1].getRed() + pixels[2 * i + 1][2 * j + 1].getRed()) / 4);
				green = (short) ((pixels[2 * i][2 * j].getGreen() + pixels[2 * i + 1][2 * j].getGreen()
						+ pixels[2 * i][2 * j + 1].getGreen() + pixels[2 * i + 1][2 * j + 1].getGreen()) / 4);
				blue = (short) ((pixels[2 * i][2 * j].getBlue() + pixels[2 * i + 1][2 * j].getBlue()
						+ pixels[2 * i][2 * j + 1].getBlue() + pixels[2 * i + 1][2 * j + 1].getBlue()) / 4);
				halfPixels[i][j] = new RGBPixel(red, green, blue);
			}
		}
		pixels = halfPixels;
		width = width / 2;
		height = height / 2;
	}

	@Override
	public void rotateClockwise() {
		RGBPixel[][] rotatePixels = new RGBPixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				rotatePixels[i][j] = new RGBPixel(pixels[(height - 1) - j][i]);
			}
		}
		pixels = rotatePixels;
		int swap = width;
		width = height;
		height = swap;
	}

}
