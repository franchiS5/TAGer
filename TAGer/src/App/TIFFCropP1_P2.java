package App;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.SwingWorker;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.imgscalr.Scalr;

import com.sun.media.imageio.plugins.tiff.TIFFImageWriteParam;
import com.sun.media.imageioimpl.plugins.tiff.TIFFImageWriter;
import com.sun.media.imageioimpl.plugins.tiff.TIFFImageWriterSpi;

public class TIFFCropP1_P2 extends SwingWorker<Void, Void> {

	private String RutaOrigen;
	private String nombreimagenIN;
	private String P1x1;
	private String P1y1;
	private String P1x2;
	private String P1y2;
	private String P2x1;
	private String P2y1;
	private String P2x2;
	private String P2y2;
	private int imagenSalida;
	private boolean marco;
	private double valormarco;
	
	

public TIFFCropP1_P2(String RutaOrigen, String nombreimagenIN, String P1x1,String P1y1, String P1x2, String P1y2,String P2x1, String P2y1, String P2x2, String P2y2, int imagenSalida, boolean marco, double valormarco){

	this.RutaOrigen = RutaOrigen;
	this.P1x1 = P1x1;
	this.P1y1 = P1y1;
	this.P1x2 = P1x2;
	this.P1y2 = P1y2;
	this.P2x1 = P2x1;
	this.P2y1 = P2y1;
	this.P2x2 = P2x2;
	this.P2y2 = P2y2;
	this.imagenSalida = imagenSalida;
	this.nombreimagenIN = nombreimagenIN;
	this.marco = marco;
	this.valormarco = valormarco;
	
}

private void cortatiff(File f) throws IOException, Exception {

	 int porcentmarco = 0;
	
	
	try {

			BufferedImage buffimage = ImageIO.read(f);
			
			final BufferedImage croppedimage = buffimage.getSubimage(Integer.parseInt(P1x1), Integer.parseInt(P1y1),Integer.parseInt(P1x2), Integer.parseInt(P1y2));
			int width = croppedimage.getWidth();											//Obtenemos las dimensiones X e Y de la imagen
			int heigth = croppedimage.getHeight();
			BufferedImage croppedandborderedimage = null;
			
			if (marco == true){
				if (width > heigth ){
					porcentmarco = (int) ((width * valormarco) /100);
					System.out.println(porcentmarco);
					croppedandborderedimage = Scalr.pad(croppedimage, porcentmarco);
				}else{
					porcentmarco = (int) ((heigth * valormarco) /100);
					System.out.println(porcentmarco);
					croppedandborderedimage = Scalr.pad(croppedimage, porcentmarco);
				}
			}
			
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
	        
	        String formatonombres = imageMetadata.getNativeMetadataFormatName();
	        IIOMetadataNode tiffRootNode = (IIOMetadataNode) imageMetadata.getAsTree(formatonombres);
	        
	        // AQUI DEBEMOS MODIFICAR LOS METADATOS DEL NODO QUE HEMOS EXTRAIDO DE LA IMAGEN
	        
	        
			
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(tiffRootNode);
			StreamResult console = new StreamResult(new File(RutaOrigen + imagenSalida + ".xml"));
			transformer.transform(source, console);
			 
			System.out.println("\nXML DOM Created Successfully..");
			
			
	        // AQUI EMPEZAMOS A GUARDAR LA IMAGEN DE SALIDA
			
			
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
			if (marco == true){
				tiffwriter.write(null, new IIOImage(croppedandborderedimage, null, imageMetadata), tifparam);	
			}else{
				tiffwriter.write(null, new IIOImage(croppedimage, null, imageMetadata), tifparam);
			}
			
			ios.flush();
			tiffwriter.dispose();
			ios.close();
			
			
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