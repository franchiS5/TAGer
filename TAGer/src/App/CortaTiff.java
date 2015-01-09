package App;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.SwingWorker;

import com.sun.media.imageio.plugins.tiff.TIFFImageWriteParam;
import com.sun.media.imageioimpl.plugins.tiff.TIFFImageWriter;
import com.sun.media.imageioimpl.plugins.tiff.TIFFImageWriterSpi;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.SeekableOutputStream;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.media.jai.codec.TIFFField;

public class CortaTiff extends SwingWorker<Void, Void> {

	private String RutaOrigen;
	private String nombreimagenIN;
	private String P1x1;
	private String P1y1;
	private String P1x2;
	private String P1y2;
	private int imagenSalida;

	public CortaTiff(String RutaOrigen, String nombreimagenIN, String P1x1,
			String P1y1, String P1x2, String P1y2, int imagenSalida) {

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
			
			
			//Obtenemos las dimensiones X e Y de la imagen
			int width = croppedimage.getWidth();
			int heigth = croppedimage.getHeight();
			
			//Falta obtener el COLOR SPACE de la imagen de entrada
			
			//Obtenemos la fecha de creacion de la imagen de entrada
			long fechams = f.lastModified();
			Date d = new Date(fechams);
			Calendar c = new GregorianCalendar(); 
			c.setTime(d);
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString(c.get(Calendar.MONTH));
			String annio = Integer.toString(c.get(Calendar.YEAR));
			String hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
			String minuto = Integer.toString(c.get(Calendar.MINUTE));
			String segundo = Integer.toString(c.get(Calendar.SECOND));
			
			// Corregimos los valores que no tengan dos digitos
			
			if (dia.length()<2){
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
			

			TIFFEncodeParam param = new TIFFEncodeParam();
			
			TiffMetadataRead readermeta = new TiffMetadataRead();
			
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
			String MODEL_VAR = "Descripcion de la imagen tal cual aparece";
			String SOFTWARE_VAR = "Descripcion de la imagen tal cual aparece";
			
			/*
			 * 
			 * String MAKE_VAR = readermeta.LeerExif(f.getAbsolutePath().toString(), "0x010f");
			 * String MODEL_VAR = readermeta.LeerExif(f.getAbsolutePath().toString(), "0x0110");
			 * String SOFTWARE_VAR = readermeta.LeerExif(f.getAbsolutePath().toString(), "0x0131");
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			String ARTIST_VAR = "Tu si que eres un artista";
			String COPYRIGHT_VAR = "Pues es mio";
			
			
			
			
			// Componemos la cabecera usando los TAGS definidos en las variables
			// anteriores
			
		
			param.setCompression(TIFFEncodeParam.COMPRESSION_NONE);
			param.setLittleEndian(true);
			param.setWriteTiled(false);
			param.setTileSize(0, 0);
			
			
			TIFFField newsubfiletype = new TIFFField(NEWSUBFILETYPE_TAG,TIFFField.TIFF_LONG, 1, (Object) new long[] { 0 });
			TIFFField imagewidth = new TIFFField(IMAGEWIDTH_TAG,TIFFField.TIFF_LONG, 1, (Object) new long[] { width });
			TIFFField imagelength = new TIFFField(IMAGELENGTH_TAG,TIFFField.TIFF_LONG, 1, (Object) new long[] { heigth });
			TIFFField fillorder = new TIFFField(FILLORDER_TAG,TIFFField.TIFF_SHORT, 1, (Object) new char[] { 1 });
			TIFFField image_description = new TIFFField(IMAGE_DESCRIPTION,TIFFField.TIFF_ASCII, 1, (Object) new String[] {DESCRIPTION_VAR});
			TIFFField make = new TIFFField(MAKE,TIFFField.TIFF_ASCII, 1, (Object) new String[] {MAKE_VAR});
			TIFFField model = new TIFFField(MODEL,TIFFField.TIFF_ASCII, 1, (Object) new String[] {MODEL_VAR});
			TIFFField stripoffsets = new TIFFField(STRIPOFFSETS, TIFFField.TIFF_LONG, 1, (Object) new long[] {});
			TIFFField orientation = new TIFFField(ORIENTATION_TAG,TIFFField.TIFF_SHORT, 1, (Object) new char[] { 1 });
			TIFFField rowsperstrip = new TIFFField(ROWSPERSTRIP, TIFFField.TIFF_LONG,1,(Object) new long[]{});
			TIFFField stripsbytecounts = new TIFFField(STRIPSBYTECOUNTS, TIFFField.TIFF_LONG,1,(Object) new long[] {});
			TIFFField xRes = new TIFFField(XRES_TAG, TIFFField.TIFF_RATIONAL,1, new long[][] { { (long) 300, 1 } });
			TIFFField yRes = new TIFFField(YRES_TAG, TIFFField.TIFF_RATIONAL,1, new long[][] { { (long) 300, 1 } });
			TIFFField planarconfiguration = new TIFFField(PLANARCONFIGURATION,TIFFField.TIFF_SHORT, 1, (Object) new char[] { 1 });
			TIFFField unit_Inch = new TIFFField(INCH_TAG, TIFFField.TIFF_SHORT,1, (Object) new char[] { 2 });
			TIFFField software = new TIFFField(SOFTWARE,TIFFField.TIFF_ASCII, 1, (Object) new String[] {SOFTWARE_VAR});
			TIFFField fecha = new TIFFField(FECHACREACION, TIFFField.TIFF_ASCII, 1, (Object) new String[] {FECHACREACION_VAR});
			TIFFField artist = new TIFFField(ARTIST,TIFFField.TIFF_ASCII, 1, (Object) new String[] {ARTIST_VAR});
			TIFFField copyright = new TIFFField(COPYRIGHT_TAG,TIFFField.TIFF_ASCII, 1, (Object) new String[] {COPYRIGHT_VAR});
			
			
			param.setExtraFields(new TIFFField[] { newsubfiletype, imagewidth, imagelength, fillorder, image_description, make, model, stripoffsets, orientation,
					  rowsperstrip, stripsbytecounts, xRes, yRes,  planarconfiguration, unit_Inch, software, fecha, artist, copyright});

			

			
			/*ImageWriter tiffWriter = (ImageWriter) ImageIO.getImageWritersByMIMEType("image/tiff").next();
			TIFFImageWriteParam writeParam = (TIFFImageWriteParam)tiffWriter.getDefaultWriteParam();
			TIFFT6Compressor compressor = new TIFFT6Compressor();
            writeParam.setCompressionMode(TIFFImageWriteParam.MODE_EXPLICIT);
            writeParam.setCompressionType(compressor.getCompressionType());
            writeParam.setTIFFCompressor(compressor);
            writeParam.setCompressionQuality(Float.parseFloat("1"));
            
         // get the metaData
            IIOMetadata imageMetadata = null;
            IIOImage testImage = null;

            
            */
            
            
            
            
			ImageWriterSpi tiffspi = new TIFFImageWriterSpi();
			TIFFImageWriter writer = (TIFFImageWriter) tiffspi.createWriterInstance();

			TIFFImageWriteParam tifparam = new TIFFImageWriteParam(Locale.US);
			tifparam.setCompressionMode(1);
			
	
			
			
			
			
			/*PRUEBA LECTURA DE CABECERA
			
			ImageInputStream inputFile = ImageIO.createImageInputStream(new File(RutaOrigen + nombreimagenIN));
			
			ImageReadParam readParam =null;

			//ImageReader tiffReader = null;
			//ImageReader tiffReader = new ImageReader;
			

			// Read primary image and IFD.
			BufferedImage image = tiffReader.read(0, readParam);
			IIOMetadata primaryIFD = tiffReader.getImageMetadata(0);
			System.out.println(primaryIFD.getExtraMetadataFormatNames());
			
			// Read thumbnail and IFD if present.
			BufferedImage thumbnail = null;
			IIOMetadata thumbnailIFD = null;
			if(tiffReader.getNumImages(true) > 1) {
			    thumbnail = tiffReader.read(1, readParam);
			    thumbnailIFD = tiffReader.getImageMetadata(1);
			}
			
			
			
			*/
			

			File fOutputFile = new File(RutaOrigen + imagenSalida + ".tif");
			OutputStream fos = new BufferedOutputStream(new FileOutputStream(fOutputFile));
			ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
			writer.setOutput(ios);
			writer.write(null, new IIOImage(croppedimage, null, null), tifparam);

			ios.flush();
			writer.dispose();
			ios.close();

			//
			File finishFile = new File(RutaOrigen + imagenSalida + ".tif");
			OutputStream out = null;
			out = new SeekableOutputStream(new RandomAccessFile(finishFile, "rw"));
			BufferedImage iim = ImageIO.read(new File(RutaOrigen + imagenSalida + ".tif"));
			ImageEncoder encoder = ImageCodec.createImageEncoder("tiff", out, param);
			
			encoder.encode(iim);
			out.flush();
			out.close(); // for security reasons null the output stream and call the garbage // collector; otherwise the JVM could still hold some open file locks
			out = null;
			System.gc();
			
			
			
			
			// Creamos la imagen de salida. Metodo antiguo.
			/*
			 * tiffsalida = new FileOutputStream(RutaOrigen + Integer.toString(imagenSalida) + ".tif");
			 * encoder = ImageCodec.createImageEncoder("TIFF", tiffsalida, param);
			 * encoder.encode(croppedimage);
			 * tiffsalida.flush();
			 */

		} catch (Exception e) {
			System.out.println(e);
		} finally {

		}
	}

	protected Void doInBackground() throws Exception {

		cortatiff(new File(RutaOrigen + nombreimagenIN));
		System.out.println("Proceso TERMINADO");
		return null;

	}
}