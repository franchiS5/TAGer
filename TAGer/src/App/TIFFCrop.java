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
import java.util.LinkedHashMap;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.media.imageio.plugins.tiff.TIFFImageWriteParam;
import com.sun.media.imageioimpl.plugins.tiff.TIFFImageWriter;
import com.sun.media.imageioimpl.plugins.tiff.TIFFImageWriterSpi;



public class TIFFCrop extends SwingWorker<Void, Void> {

	
	private String RutaDestino;
	private String x;
	private String y;
	private String xsize;
	private String ysize;
	private String imagenSalida;
	private boolean marco;
	private double valormarco;
	private BufferedImage buffimage;
	private File f;
	//private IIOMetadata imageMetadata;
	private LinkedHashMap <Integer,String> tablaexif;
	private boolean Mantenernombre;
	
	

public TIFFCrop(File f, BufferedImage buffimage, String RutaDestino, String x,String y, String xsize, String ysize, String imagenSalida,
		boolean marco, double valormarco, LinkedHashMap <Integer,String> tablaexif, boolean Mantenernombre) {

	
	this.RutaDestino = RutaDestino;
	this.x = x;
	this.y = y;
	this.xsize = xsize;
	this.ysize = ysize;
	this.imagenSalida = imagenSalida;
	this.marco = marco;
	this.valormarco = valormarco;
	this.buffimage = buffimage;
	this.f = f;
	this.tablaexif=tablaexif;
	//this.imageMetadata = imageMetadata;
	this.Mantenernombre= Mantenernombre;
	
}

private void cortatiff(BufferedImage buffimage) throws IOException, Exception {
	
	int porcentmarco = 0;
	String newXsize = null;
	String newYsize = null;
	
	if(Integer.parseInt(x) < 0) {
		x= "0";
	}
	if(Integer.parseInt(y) < 0) {
		y= "0";
	}
	
	if(Integer.parseInt(xsize) > buffimage.getWidth()){
		newXsize = Integer.toString(buffimage.getWidth());
		xsize = newXsize;
	}
	if(Integer.parseInt(ysize) > buffimage.getHeight()){
		newYsize = Integer.toString(buffimage.getHeight());
		ysize = newYsize;
	}
	if(Integer.parseInt(x) + Integer.parseInt(xsize) > buffimage.getWidth() ){
		newXsize = Integer.toString(buffimage.getWidth() - Integer.parseInt(x));
		xsize = newXsize;
	}
	if(Integer.parseInt(y) + Integer.parseInt(ysize) > buffimage.getHeight()){
		newYsize = Integer.toString(buffimage.getHeight() - Integer.parseInt(y));
		ysize = newYsize;
	}
	
	try {
		
			final BufferedImage croppedimage = buffimage.getSubimage(Integer.parseInt(x), Integer.parseInt(y),Integer.parseInt(xsize), Integer.parseInt(ysize));
			int width = croppedimage.getWidth();
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
	        String formatonombres = imageMetadata.getNativeMetadataFormatName();
	        IIOMetadataNode tiffRootNode = (IIOMetadataNode) imageMetadata.getAsTree(formatonombres);
	        
	        
	        // LLAMADA A LA CLASE QUE MODIFICA EL tiffRootNode de la imagen
	          
	        tiffRootNode=MetadataXMLProcess.metaexif(tiffRootNode, tablaexif);
	         
	        
	        // AQUI EMPEZAMOS A GUARDAR LA IMAGEN DE SALIDA
			
			
			ImageWriterSpi tiffspi = new TIFFImageWriterSpi();							
			
			TIFFImageWriter tiffwriter = (TIFFImageWriter) tiffspi.createWriterInstance();
			TIFFImageWriteParam tifparam = new TIFFImageWriteParam(Locale.US);
			tifparam.setCompressionMode(TIFFImageWriteParam.MODE_DISABLED);
			tifparam.setDestinationOffset((new Point(0, 0)) );
			tifparam.setTilingMode(TIFFImageWriteParam.MODE_DISABLED);
			tifparam.setSourceSubsampling(1, 1, 0, 0);
			
			if(Mantenernombre != true){			//DETERMINAMOS SI DEBEMOS MANTENER EL NOMBRE ORIGINAL DE LA IMAGEN PARA NO AÑADIR CEROS DELANTE
				
			File fOutputFile = new File(RutaDestino); // + imagenSalida + ".tif");
			
			if (!fOutputFile.exists()){
				
				fOutputFile.mkdirs();
				fOutputFile = new File (RutaDestino + String.format("%04d", Integer.parseInt(imagenSalida)) + ".tif");
			}else{
				fOutputFile = new File (RutaDestino + String.format("%04d", Integer.parseInt(imagenSalida)) + ".tif");
			}
			
			
			OutputStream fos = new BufferedOutputStream(new FileOutputStream(fOutputFile));
			ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
			ios.setBitOffset(0);
			ios.setByteOrder(ByteOrder.LITTLE_ENDIAN);
			tiffwriter.setOutput(ios);
			imageMetadata.setFromTree(formatonombres, tiffRootNode);
			
			if (marco == true){
				tiffwriter.write(null, new IIOImage(croppedandborderedimage, null, imageMetadata), tifparam);
				croppedandborderedimage.flush();
			}else{
				tiffwriter.write(null, new IIOImage(croppedimage, null, imageMetadata), tifparam);
				croppedimage.flush();
				}
			
			
			ios.flush();
			tiffwriter.dispose();
			reader.dispose();
			ios.close();
			fos.close();
			
			} else {	//NO AÑADIMOS CEROS DELANTE DEL NOMBRE DE LA IMAGEN YA QUE VA A SER LA ORIGINAL
				
				File fOutputFile = new File(RutaDestino); // + imagenSalida + ".tif");
				
				if (!fOutputFile.exists()){
					
					fOutputFile.mkdirs();
					fOutputFile = new File (RutaDestino + imagenSalida);
				}else{
					fOutputFile = new File (RutaDestino + imagenSalida);
				}
				
				
				
				OutputStream fos = new BufferedOutputStream(new FileOutputStream(fOutputFile));
				ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
				ios.setBitOffset(0);
				ios.setByteOrder(ByteOrder.LITTLE_ENDIAN);
				tiffwriter.setOutput(ios);
				imageMetadata.setFromTree(formatonombres, tiffRootNode);
				
				if (marco == true){
					tiffwriter.write(null, new IIOImage(croppedandborderedimage, null, imageMetadata), tifparam);
					croppedandborderedimage.flush();
				}else{
					tiffwriter.write(null, new IIOImage(croppedimage, null, imageMetadata), tifparam);
					croppedimage.flush();
					}
				
				
				ios.flush();
				tiffwriter.dispose();
				reader.dispose();
				ios.close();
				fos.close();
				
			}
			
			
			
			
} catch (Exception e) {
				System.out.println(e);
				
			} finally {

			}
}

public IIOMetadataNode getNodoCopy(String copyright)
{
	IIOMetadataNode NodoCopyright = new IIOMetadataNode("TIFFField");
	NodoCopyright.setAttribute("name", "Copyright");
	NodoCopyright.setAttribute("number", "33432");
	IIOMetadataNode nodoAsciis = new IIOMetadataNode("TIFFAsciis");
	IIOMetadataNode nodoAscii = new IIOMetadataNode("TIFFAscii");
	nodoAscii.setAttribute("value", copyright);
	nodoAsciis.appendChild(nodoAscii);
	NodoCopyright.appendChild(nodoAsciis);
	
	return NodoCopyright;
}

protected Void doInBackground() throws Exception {

	cortatiff(buffimage);
	return null;

}
}