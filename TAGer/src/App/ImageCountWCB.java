package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ImageCountWCB { // Esta clase nos devolvera el total de ficheros a
								// cortar dentro de
								// todos los .WCB de la ruta entregada.

	static int totalimagewcb;

	public static int totalimagecount(String ruta) { // Metodo

		totalimagewcb = 0;
		imagecount(new File(ruta));
		return totalimagewcb;

	}

	static void imagecount(File ficheroswcb) { // Metodo

		File[] ficheros = ficheroswcb.listFiles();

		for (File fichero : ficheros) {

			if (!(fichero.isFile())) {

				imagecount(fichero);
				
			} else {
				
				String nombre = fichero.getName().toLowerCase();
				if (nombre.endsWith(".wcb")) {
					try {
						try {

							FileReader frwcb = new FileReader(fichero);
							BufferedReader brwcb = new BufferedReader(frwcb);
							brwcb.readLine();
							while ((brwcb.readLine()) != null) {
								// Saltamos una linea para no contar la primera
								// que es la ruta
								// brwcb.readLine();
								totalimagewcb++;

							}
							frwcb.close();

						} catch (Exception e) {
							System.out.println("Excepcion leyendo fichero "
									+ ficheroswcb + ": " + e);
						}

					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}

		}

	}

}
