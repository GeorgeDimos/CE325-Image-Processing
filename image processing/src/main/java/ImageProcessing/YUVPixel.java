package ImageProcessing;

/**
 *
 * @author George
 */
public class YUVPixel {
	private int color;

	/* Δημιουργεί ένα αντικείμενο της κλάση YUVPixel με βάση τις τιμές Y, U, V. */
	public YUVPixel(short Y, short U, short V) {
		color = Y << 16;
		color += U << 8;
		color += V;
	}

	/* Δημιουργεί ένα αντικείμενο που αποτελεί ακριβές αντίγραφο του pixel. */
	public YUVPixel(YUVPixel pixel) {
		color = pixel.color;
	}

	/* Δημιουργεί ένα αντικείμενο YUVPixel από ένα αντικείμενο RGBPixel. */
	public YUVPixel(RGBPixel pixel) {
		short Y = (short) (((66 * pixel.getRed() + 129 * pixel.getGreen() + 25 * pixel.getBlue() + 128) >> 8) + 16);
		short U = (short) (((-38 * pixel.getRed() - 74 * pixel.getGreen() + 112 * pixel.getBlue() + 128) >> 8) + 128);
		short V = (short) (((112 * pixel.getRed() - 94 * pixel.getGreen() - 18 * pixel.getBlue() + 128) >> 8) + 128);
		color = Y << 16;
		color += U << 8;
		color += V;
	}

	/* επιστρέφει την τιμή της παραμέτρου Y. */
	short getY() {
		return (short) ((color & 0xFF0000) >>> 16);
	}

	/* επιστρέφει την τιμή της παραμέτρου U. */
	short getU() {
		return (short) ((color & 0x00FF00) >>> 8);
	}

	/* επιστρέφει την τιμή της παραμέτρου V. */
	short getV() {
		return (short) (color & 0x0000FF);
	}

	/* θέτει την τιμή της παραμέτρου Y. */
	void setY(short Y) {
		color = (color & 0x00FFFF) + (Y << 16);
	}

	/* θέτει την τιμή της παραμέτρου U. */
	void setU(short U) {
		color = (color & 0xFF00FF) + (U << 8);
	}

	/* θέτει την τιμή της παραμέτρου V. */
	void setV(short V) {
		color = (color & 0xFFFF00) + V;
	}

}
