package App;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.SwingWorker;

import com.sun.media.imageio.plugins.tiff.TIFFImageWriteParam;
import com.sun.media.imageioimpl.plugins.tiff.TIFFImageWriter;
import com.sun.media.imageioimpl.plugins.tiff.TIFFImageWriterSpi;




public class CortaTiff extends SwingWorker<Void, Void> {

	private String RutaOrigen;
	private String nombreimagenIN;
	private String P1x1;
	private String P1y1;
	private String P1x2;
	private String P1y2;
	private int imagenSalida;
	

public CortaTiff(String RutaOrigen, String nombreimagenIN, String P1x1,String P1y1, String P1x2, String P1y2, int imagenSalida){

	this.RutaOrigen = RutaOrigen;
	this.P1x1 = P1x1;
	this.P1y1 = P1y1;
	this.P1x2 = P1x2;
	this.P1y2 = P1y2;
	this.imagenSalida = imagenSalida;
	this.nombreimagenIN = nombreimagenIN;
}

private void cortatiff(File f) throws IOException, Exception {

	
	
	try {

			//Falta depurar si el fichero de entrada es TIFF o JPEG
			//Falta depurar si el fichero tiene que ser cortado como P1 y P2 o solamente P1
			//Falta crear the method para recorrer Directory y extraer los metadatos al vuelo para optimizar rendimiento
			
			
			BufferedImage buffimage = ImageIO.read(f);
			
			final BufferedImage croppedimage = buffimage.getSubimage(Integer.parseInt(P1x1), Integer.parseInt(P1y1),Integer.parseInt(P1x2), Integer.parseInt(P1y2));
			
			int width = croppedimage.getWidth();											//Obtenemos las dimensiones X e Y de la imagen
			int heigth = croppedimage.getHeight();
			
			
			
			/*
			long fechams = f.lastModified();												//Obtenemos la fecha de creacion de la imagen de entrada
			Date d = new Date(fechams);
			Calendar c = new GregorianCalendar(); 
			c.setTime(d);
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString(c.get(Calendar.MONTH));
			String annio = Integer.toString(c.get(Calendar.YEAR));
			String hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
			String minuto = Integer.toString(c.get(Calendar.MINUTE));
			String segundo = Integer.toString(c.get(Calendar.SECOND));
			
			if (dia.length()<2){															// Corregimos los valores que no tengan dos digitos
				dia = "0"+dia;
			}
			if (mes.length()<2){
				mes = "0"+mes;
			}
			if (hora.length()<2){
				hora = "0"+hora;
			}
			if (minuto.length()<2){
				minuto = "0"+minuto;
			}
			if (segundo.length()<2){
				segundo = "0"+segundo;
			}
			
			
			String FECHACREACION_VAR = (annio + ":" + mes + ":" + dia + " " + hora + ":" + minuto + ":" + segundo);
			
			
			final int NEWSUBFILETYPE_TAG = 254;
			final int IMAGEWIDTH_TAG = 256;
			final int IMAGELENGTH_TAG = 257;
			final int FILLORDER_TAG = 266;
			final int IMAGE_DESCRIPTION = 270;
			final int MAKE = 271;
			final int MODEL = 272;
			final int STRIPOFFSETS = 273;
			final int ORIENTATION_TAG = 274;
			final int ROWSPERSTRIP = 278;
			final int STRIPSBYTECOUNTS = 279;
			final int XRES_TAG = 282;
			final int YRES_TAG = 283;
			final int PLANARCONFIGURATION = 284;
			final int INCH_TAG = 296;
			final int SOFTWARE = 305;
			final int FECHACREACION = 306;
			final int ARTIST = 315;
			final int COPYRIGHT_TAG = 33432;
			
			
			String DESCRIPTION_VAR = "Descripcion de la imagen tal cual aparece";
						String MAKE_VAR = "Descripcion de la imagen tal cual aparece";
						String MODEL_VAR = "Pues es mio";
						String SOFTWARE_VAR = "Descripcion de la imagen tal cual aparece";
						String ARTIST_VAR = "Tu si que eres un artista";
						String COPYRIGHT_VAR = "Pues es mio";
			
			*/
			
			// PRUEBAS LEER METADATOS EXIF DEL FICHERO DE ENTRADA USANDO LA CLASE TIFFMETADATAREAD.JAVA DEBERIA DEVOLVERNOS UN HASHMAP
			
			/* HashMap <String, String> hashmeta = TiffMetadataRead.LeerExif(f.getAbsolutePath().toString());

			String DESCRIPTION_VAR = hashmeta.get("0x010E");
			if (DESCRIPTION_VAR == null){
				DESCRIPTION_VAR = " ";
			}
			if (DESCRIPTION_VAR.trim().length()%2 !=0){
				//DESCRIPTION_VAR+="";
				
			}int cuentadigitos = DESCRIPTION_VAR.length();
			String MAKE_VAR = hashmeta.get("0x010f");
			
			if (MAKE_VAR == null){
				MAKE_VAR = " ";
			}
			MAKE_VAR+=" ";
			
			
			String MODEL_VAR = hashmeta.get("0x0110");
			if (MODEL_VAR == null){
				MODEL_VAR = " ";
			}
			
			
			
			String SOFTWARE_VAR = hashmeta.get("0x0131");
			if (SOFTWARE_VAR == null){
				SOFTWARE_VAR = " ";
			}
			if (SOFTWARE_VAR.trim().length()%2 !=0){
				SOFTWARE_VAR += " ";
			}cuentadigitos += SOFTWARE_VAR.length();
			
			String ARTIST_VAR = hashmeta.get("0x013B");
			if (ARTIST_VAR == null){
				ARTIST_VAR = " ";
			}
			
			
			String []COPYRIGHT_VAR = {hashmeta.get("0x8298")};
			if (COPYRIGHT_VAR[0] == null){
				COPYRIGHT_VAR[0] = " ";
			}
			
			
			System.out.println(cuentadigitos);
			if (cuentadigitos%2 != 0){
				
				MODEL_VAR += " " + "\0";
				
			}
			
			*/
			
            
			
			
			/*
            BaselineTIFFTagSet base = BaselineTIFFTagSet.getInstance();
     		TIFFTag tagNewSubfileType = base.getTag(BaselineTIFFTagSet.NEW_SUBFILE_TYPE_SINGLE_PAGE);
     		TIFFTag tagMake = base.getTag(BaselineTIFFTagSet.TAG_MAKE);
     		
     		
     		TIFFField make = new TIFFField (tagMake, TIFFTag.TIFF_ASCII,1, (Object) new String[] { MODEL_VAR });
			*/
			
			
			//IIORegistry registry = IIORegistry.getDefaultInstance();
			 
	        
	        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("tif");
	        ImageReader reader = null;
	        while (readers.hasNext())
	        {
	            reader = readers.next();
	            
	        }
	 
	       ImageInputStream stream = null;
	        stream = ImageIO.createImageInputStream(f);
	        reader.setInput(stream);
			
	        IIOMetadata imageMetadata = reader.getImageMetadata(0);
	        //IIOMetadata streamMetadata = reader.getStreamMetadata();
	        
	        
			ImageWriterSpi tiffspi = new TIFFImageWriterSpi();							
			
			TIFFImageWriter tiffwriter = (TIFFImageWriter) tiffspi.createWriterInstance();
			
			TIFFImageWriteParam tifparam = new TIFFImageWriteParam(Locale.US);
			
			tifparam.setCompressionMode(TIFFImageWriteParam.MODE_DISABLED);
			tifparam.setDestinationOffset((new Point(0, 0)) );
			tifparam.setTilingMode(TIFFImageWriteParam.MODE_DISABLED);
			tifparam.setSourceSubsampling(1, 1, 0, 0);
			
			
			File fOutputFile = new File(RutaOrigen + imagenSalida + ".tif");
			OutputStream fos = new BufferedOutputStream(new FileOutputStream(fOutputFile));
			ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
			ios.setBitOffset(0);
			ios.setByteOrder(ByteOrder.LITTLE_ENDIAN);
			
			
			tiffwriter.setOutput(ios);
			tiffwriter.write(null, new IIOImage(croppedimage, null, imageMetadata), tifparam);
			ios.flush();
			tiffwriter.dispose();
			ios.close();
			
			

			/* TIFFEncodeParam param = new TIFFEncodeParam();
			
			param.setCompression(TIFFEncodeParam.COMPRESSION_NONE);						// Componemos la cabecera usando los TAGS definidos en las variables anteriores
			param.setLittleEndian(true);
			param.setWriteTiled(false);
			param.setTileSize(0, 0);
			
			
			TIFFField newsubfiletype = new TIFFField (NEWSUBFILETYPE_TAG, TIFFTag.TIFF_LONG,1, (Object) new long[] { 0 });
			TIFFField imagewidth = new TIFFField(IMAGEWIDTH_TAG,TIFFField.TIFF_LONG, 1, (Object) new long[] { width });
			TIFFField imagelength = new TIFFField(IMAGELENGTH_TAG,TIFFField.TIFF_LONG, 1, (Object) new long[] { heigth });
			TIFFField fillorder = new TIFFField(FILLORDER_TAG,TIFFField.TIFF_SHORT, 1, (Object) new char[] { 1 });
			TIFFField image_description = new TIFFField(IMAGE_DESCRIPTION,TIFFField.TIFF_ASCII, 1, (Object) new String[] {DESCRIPTION_VAR});
			TIFFField make = new TIFFField(MAKE,TIFFField.TIFF_ASCII, 1, (Object) new String[] {MAKE_VAR});
			TIFFField model = new TIFFField(MODEL,TIFFField.TIFF_ASCII, 1, (Object) new String[] {MODEL_VAR});
			TIFFField stripoffsets = new TIFFField(STRIPOFFSETS, TIFFField.TIFF_LONG, 1, (Object) new long[] {});
			TIFFField orientation = new TIFFField(ORIENTATION_TAG,TIFFField.TIFF_SHORT, 1, (Object) new char[] { 1 });
			TIFFField rowsperstrip = new TIFFField(ROWSPERSTRIP, TIFFField.TIFF_LONG,1,(Object) new long[]{heigth});
			TIFFField stripsbytecounts = new TIFFField(STRIPSBYTECOUNTS, TIFFField.TIFF_LONG,1,(Object) new long[] {});
			TIFFField xRes = new TIFFField(XRES_TAG, TIFFField.TIFF_RATIONAL,1, new long[][] { { (long) 300, 1 } });
			TIFFField yRes = new TIFFField(YRES_TAG, TIFFField.TIFF_RATIONAL,1, new long[][] { { (long) 300, 1 } });
			TIFFField planarconfiguration = new TIFFField(PLANARCONFIGURATION,TIFFField.TIFF_SHORT, 1, (Object) new char[] { 1 });
			TIFFField unit_Inch = new TIFFField(INCH_TAG, TIFFField.TIFF_SHORT,1, (Object) new char[] { 2 });
			TIFFField software = new TIFFField(SOFTWARE,TIFFField.TIFF_ASCII, 1, (Object) new String[] {SOFTWARE_VAR});
			TIFFField fecha = new TIFFField(FECHACREACION, TIFFField.TIFF_ASCII, 1, (Object) new String[] {FECHACREACION_VAR});
			TIFFField artist = new TIFFField(ARTIST,TIFFField.TIFF_ASCII, 1, (Object) new String[] {ARTIST_VAR});
			TIFFField copyright = new TIFFField(COPYRIGHT_TAG,TIFFField.TIFF_ASCII, 1, (Object) new String[] {COPYRIGHT_VAR[0]});
			
			param.setExtraFields(new TIFFField[] {newsubfiletype, imagewidth, imagelength, fillorder, image_description, make, model, orientation, xRes, yRes, planarconfiguration
					,unit_Inch, software, fecha, artist, copyright});
			
			
			File finishFile = new File(RutaOrigen + imagenSalida + "_"+  ".tif");				// Escribimos la cabecera del TIFF con los EXIF creados anteriormente
			OutputStream os = new FileOutputStream(finishFile);
			
			BufferedImage iim = ImageIO.read(new File(RutaOrigen + imagenSalida + ".tif"));
			
			
			//out = new SeekableOutputStream(new RandomAccessFile(finishFile, "rw"));
			
			
			//ImageEncoder encoder = ImageCodec.createImageEncoder("tiff", out, param);
			TIFFImageEncoder encod = new TIFFImageEncoder(os,param);
			
			encod.encode(iim);
			
			//encoder.encode(iim);
			os.flush();
			os.close();																// for security reasons null the output stream and call the garbage // collector; otherwise the JVM could still hold some open file locks
			os = null;
			System.gc();
			
			*/
			
		} catch (Exception e) {
				System.out.println(e);
			} finally {

			}
}

protected Void doInBackground() throws Exception {

	cortatiff(new File(RutaOrigen + nombreimagenIN));
	return null;

}
}