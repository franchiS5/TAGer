package App;

import java.io.File;

public class ContarFicheros {														// Esta clase nos devuelve total ficheros a los que metemos metadatos en  Tab EXIF//IPTC

	static int total;

public static int contar(String ruta, boolean tif, boolean jpeg, boolean pdf) {
	total = 0;
	recorrer(new File(ruta), tif, jpeg, pdf);
	return total;
}

static void recorrer(File fichero, boolean tif, boolean jpeg, boolean pdf) {

		File[] ficheros = fichero.listFiles();
		for (File f : ficheros) {
			if (!(f.isFile())) {
				recorrer(f, tif, jpeg, pdf);
			} else {
					if (tif == true && f.toString().endsWith(".tif")) {
						total++;
					}
					if (jpeg == true && f.toString().endsWith(".jpg")) {
							total++;
					}
					if (pdf == true && f.toString().endsWith(".pdf")) {
					total++;
					}
			}
		}
}
}