package App;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;


public class MainWCBProcess extends SwingWorker<Void, Void> {																		// Clase para lanzar el corte de los WCB

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
	private JLabel labelTotalWCB;
	private JLabel labelTotalIMG;
	private boolean contados;
	private String EXIFCopyrightWCB;
	private String EXIFSoftwareWCB;
	private String EXIFFabricanteWCB;
	private String EXIFModeloWCB;
	
	
public MainWCBProcess(String ruta, String Destino, JProgressBar progreso, JTextArea etiqueta, boolean marco,  double valormarco, int totalimagewcb, JLabel labelTotalWCB, JLabel labelTotalIMG,
		String EXIFCopyrightWCB, String EXIFSoftwareWCB, String EXIFFabricanteWCB, String EXIFModeloWCB) {

	RutaOrigen = ruta;
	RutaDestino = Destino;
	this.progreso = progreso;
	this.etiqueta = etiqueta;
	this.marco = marco;
	this.totalimagewcb=totalimagewcb;
	this.valormarco=valormarco;
	this.labelTotalWCB = labelTotalWCB;
	this.labelTotalIMG = labelTotalIMG;
	this.EXIFCopyrightWCB = EXIFCopyrightWCB;
	this.EXIFSoftwareWCB = EXIFSoftwareWCB;
	this.EXIFFabricanteWCB = EXIFFabricanteWCB;
	this.EXIFModeloWCB = EXIFModeloWCB;
	contados = false;
		
}

private void recorreYcorta(File f) throws IOException, Exception {

		File[] ficheros = f.listFiles();
			int totficheroswcb = 0;
			
			for (File fichero : ficheros) { 																				// Recorremos el directorio mientras hayan ficheros
				for (int i=0; i<ficheros.length && contados == false;i++){													// Contamos los ficheros WCB del directorio
					if (ficheros[i].getName().endsWith(".wcb")){
						totficheroswcb ++;
					}
				}
				contados = true;
				labelTotalWCB.setText(Integer.toString(totficheroswcb));
				labelTotalIMG.setText(Integer.toString(WCBFilesCalculate.totalimagecount(RutaOrigen)));
				if (fichero.isFile()) {
					String nombre = fichero.getName().toLowerCase();
					if (nombre.endsWith(".wcb")) {																			// Controlamos que el fichero sea .WCB
						try {
							try {
								int imagenSalida = 1;
								FileReader fr = new FileReader(fichero);
								BufferedReader br = new BufferedReader(fr);
								RutaOrigen=br.readLine();																				// Saltamos la primera linea, no nos aporta nada que no haya en las siguientes
								RutaOrigen = RutaOrigen.replace("\\", "/");
								RutaDestino = RutaDestino.replace("\\", "/");
								String DestinoCalculado = WCBPath.CreaRuta(RutaOrigen, RutaDestino);
								
								DestinoCalculado = DestinoCalculado + "/";
								
									while ((linea = br.readLine()) != null) {												// Leemos linea a linea hasta el final del fichero
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
										//RutaOrigen = (coordenadas[9]).replace("\\", "/");
										//RutaDestino = RutaDestino.replace("\\", "/");
										RutaOrigen = RutaOrigen + "/";
										etiqueta.append("Procesando imagen: " +coordenadas[9]  + nombreimagenIN + "\n");
										
										
										File fileimagen = new File(RutaOrigen + nombreimagenIN);
										BufferedImage buffimage = ImageIO.read(fileimagen);
										
										
										//AQUI CREAMOS EL HASHMAP CON LOS VALORES DE LOS EXIF A MODIFICAR
										LinkedHashMap<Integer, String> tablaexif = new LinkedHashMap<Integer, String>();
										tablaexif.put(271, EXIFFabricanteWCB);
										tablaexif.put(272, EXIFModeloWCB);
										tablaexif.put(305, EXIFSoftwareWCB);
										tablaexif.put(33432, EXIFCopyrightWCB);
										
										
										IIOMetadata newIIOMetadata = MetadataXMLProcess.MetadataChange(fileimagen, tablaexif);
										
										
										
										
										//TIENE SOLAMENTE P1
										if (Integer.parseInt(P2x2) == 0 && Integer.parseInt(P2y2) == 0) {					
										TIFFCrop croptiffP1 = new TIFFCrop(fileimagen, buffimage, DestinoCalculado, P1x1, P1y1, P1x2, P1y2, imagenSalida, marco, valormarco, newIIOMetadata );		//CORTA PAG1
										croptiffP1.doInBackground();
										
										imagenSalida++;
										contador++;
										porcent=new Double(contador * 100 / totalimagewcb).intValue();
										progreso.setValue(porcent);
										}else{ // TIENE P1 Y P2
											TIFFCrop croptiffP1 = new TIFFCrop(fileimagen, buffimage, DestinoCalculado, P1x1, P1y1, P1x2, P1y2, imagenSalida, marco, valormarco, newIIOMetadata );		//CORTA PAG 1
											croptiffP1.doInBackground();
											imagenSalida++;
											contador++;																																				//SOLO SUMAMOS UNA VEZ A CONTADOR
											porcent=new Double(contador * 100 / totalimagewcb).intValue();
											progreso.setValue(porcent);
											
											TIFFCrop croptiffP2 = new TIFFCrop(fileimagen, buffimage, DestinoCalculado, P2x1, P2y1, P2x2, P2y2, imagenSalida, marco, valormarco, newIIOMetadata);		//CORTA PAG2
											croptiffP2.doInBackground();
											imagenSalida++;
											porcent=new Double(contador * 100 / totalimagewcb).intValue();
											progreso.setValue(porcent);
											
										}
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
			etiqueta.append("Ficheros TIFF procesados: " + totalimagewcb + "\n");
}

@Override
protected Void doInBackground() throws Exception {

	recorreYcorta(new File(RutaOrigen));
	etiqueta.append("PROCESO TERMINADO\n");
	return null;
}
}