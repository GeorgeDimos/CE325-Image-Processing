package ImageProcessing;

/**
 *
 * @author George
 */
public class RGBPixel {
	private int color;

	/* Δημιουργεί ένα pixel με βάση τις τιμές red, green, blue. */
	public RGBPixel(short red, short green, short blue) {
		color = red << 16;
		color += green << 8;
		color += blue;
	}

	public RGBPixel(int color) {
		this.color = color;
	}

	/* Δημιουργεί ένα αντικείμενο που αποτελεί ακριβές αντίγραφο του pixel. */
	public RGBPixel(RGBPixel pixel) {
		this.color = pixel.color;
	}

	/* Δημιουργεί ένα RGBPixel από ένα YUVPixel */
	public RGBPixel(YUVPixel pixel) {
		int C = pixel.getY() - 16;
		int D = pixel.getU() - 128;
		int E = pixel.getV() - 128;
		int red = clip((298 * C + 409 * E + 128) >> 8);
		int green = clip((298 * C - 100 * D - 208 * E + 128) >> 8);
		int blue = clip((298 * C + 516 * D + 128) >> 8);
		color = red << 16;
		color += green << 8;
		color += blue;
	}

	private int clip(int num) {
		if (num < 0) {
			return 0;
		}
		if (num > 255) {
			return 255;
		}
		return num;
	}

	/* επιστρέφει την τιμή του κόκκινου χρώματος. */
	public short getRed() {
		return (short) (color >>> 16);
	}

	/* επιστρέφει την τιμή του πράσινου χρώματος. */
	public short getGreen() {
		return (short) ((color & 0x00FF00) >>> 8);
	}

	/* επιστρέφει την τιμή του μπλε χρώματος. */
	public short getBlue() {
		return (short) (color & 0x0000FF);
	}

	/* θέτει την τιμή του κόκκινου χρώματος. */
	public void setRed(short red) {
		color = (color & 0x00FFFF) + (red << 16);
	}

	/* θέτει την τιμή του πράσινου χρώματος. */
	public void setGreen(short green) {
		color = (color & 0xFF00FF) + (green << 8);
	}

	/* θέτει την τιμή του μπλε χρώματος. */
	public void setBlue(short blue) {
		color = (color & 0xFFFF00) + blue;
	}

	/* Επιστρέφει έναν ακέραιο που περιέχει τα 3 RGB χρώματα. */
	public int getRGB() {
		return color;
	}

	/*
	 * Θέτει τις τιμές των τριών χρωμάτων με βάση τον ακέραιο value. Η ακέραια
	 * μεταβλητή value περιέχει τα 3 RGB χρώματα.
	 */
	public void setRGB(int value) {
		color = value;
	}

	/*
	 * Θέτει τις τιμές των τριών χρωμάτων με βάση τις τιμές των μεταβλητών red,
	 * green, blue .
	 */
	public final void setRGB(short red, short green, short blue) {
		this.setRed(red);
		this.setGreen(green);
		this.setBlue(blue);
	}

	/*
	 * Επιστρέφει ένα αλφαριθμητικό στην μορφή “ (R,G,B) ”, όπου R η τιμή του
	 * κόκκινου, G η τιμή του πράσινού και B η τιμή του μπλε χρώματος .
	 */
	@Override
	public String toString() {
		return String.valueOf(this.getRed()) + " " + String.valueOf(this.getGreen()) + " "
				+ String.valueOf(this.getBlue() + " ");
	}

}
