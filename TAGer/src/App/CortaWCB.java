package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;


public class CortaWCB extends SwingWorker<Void, Void> {

	private String RutaOrigen;
	private String RutaDestino;
	private JTextArea etiqueta;
	private JProgressBar progreso;
	private boolean marco;
	private int totalimagewcb;
	private int contador;
	private int porcent;
	private String linea;
	private double valormarco;
	
	
	public CortaWCB(String ruta, String Destino, JProgressBar progreso,
			JTextArea etiqueta, boolean marco,  double valormarco, int totalimagewcb) {

		RutaOrigen = ruta;
		RutaDestino = Destino;
		this.progreso = progreso;
		this.etiqueta = etiqueta;
		this.marco = marco;
		this.totalimagewcb=totalimagewcb;
		this.valormarco=valormarco;
		
	}

private void recorreYcorta(File f) throws IOException, Exception {

		
		File[] ficheros = f.listFiles();

		for (File fichero : ficheros) { 										// Recorremos el directorio mientras hayan ficheros

			if (fichero.isFile()) {

				String nombre = fichero.getName().toLowerCase();
				if (nombre.endsWith(".wcb")) {									// Controlamos que el fichero sea .WCB
					try {
						try {
							
							int imagenSalida = 1;
							FileReader fr = new FileReader(fichero);
							BufferedReader br = new BufferedReader(fr);

							br.readLine();										// Saltamos la primera linea, no nos aporta nada que no haya en las siguientes

							while ((linea = br.readLine()) != null) {			// Leemos linea a linea hasta el final del fichero

							String[] coordenadas = linea.split("\t");
							String nombreimagenIN = coordenadas[0];
							String P1x1 = (coordenadas[1]);
							String P1y1 = (coordenadas[2]);
							String P1x2 = (coordenadas[3]);
							String P1y2 = (coordenadas[4]);
							String P2x1 = (coordenadas[5]);
							String P2y1 = (coordenadas[6]);
							String P2x2 = (coordenadas[7]);
							String P2y2 = (coordenadas[8]);
							RutaOrigen = (coordenadas[9]).replace("\\", "/");
								
								
							etiqueta.append(coordenadas[9] + nombreimagenIN + "\n");
							etiqueta.append(P1x1 + "\n");
							etiqueta.append(P1y1 + "\n");
							etiqueta.append(P1x2 + "\n");
							etiqueta.append(P1y2 + "\n");
							etiqueta.append(P2x1 + "\n");
							etiqueta.append(P2y1 + "\n");
							etiqueta.append(P2x2 + "\n");
							etiqueta.append(P2y2 + "\n");
							etiqueta.append("Procesando imagen" + "\n");
								
							System.out.println("WCB RutaOrigen: " + RutaOrigen + "\n");
							System.out.println("WCB nombreimagenIN: " + nombreimagenIN + "\n");
							System.out.println("WCB imagenSalida: " + imagenSalida + "\n");
								
								
								
								
							//if (Integer.parseInt(P2x1) == 0 || Integer.parseInt(P2y1) == 0 || Integer.parseInt(P2x2) == 0 || Integer.parseInt(P2y2) == 0) {
									
							CortaTiff croptiff = new CortaTiff(RutaOrigen, nombreimagenIN, P1x1, P1y1, P1x2, P1y2, imagenSalida );
							croptiff.doInBackground();
							imagenSalida++;
							//}
								
							contador++;
							porcent=new Double(contador * 100 / totalimagewcb).intValue();
	                        progreso.setValue(porcent);
							}
							
							br.close();
							fr.close();
							
						} catch (Exception e) {
							System.out.println("Excepcion leyendo fichero "	+ fichero + ": " + e);
						}

					} catch (Exception e) {
						System.out.println(e);
					}
				}

			} else {
				
				recorreYcorta(fichero);
			}
		}
	
		System.out.println("Images a tratar en los WCB: " + totalimagewcb);
	}

	@Override
	protected Void doInBackground() throws Exception {

		recorreYcorta(new File(RutaOrigen));
		etiqueta.append("Proceso Terminado\n");
		return null;
	}

}
