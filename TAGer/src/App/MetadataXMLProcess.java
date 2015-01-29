package App;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.NodeList;




public class MetadataXMLProcess {

private static IIOMetadata imageMetadata;

	
public static IIOMetadata MetadataChange (File fileimagen, LinkedHashMap<Integer, String> tablaExif) throws IOException{
	
	imageMetadata=null;
	metaexif(fileimagen, tablaExif);
	return imageMetadata;
}

static IIOMetadata metaexif (File fileimagen, LinkedHashMap<Integer, String> tablaExif) throws IOException{
	
	
	Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("tif");
    ImageReader reader = null;
    
    while (readers.hasNext())
    {
        reader = readers.next();
    }
	ImageInputStream stream = null;
	
	
	try {
    	stream = ImageIO.createImageInputStream(fileimagen);
    	reader.setInput(stream);
    	IIOMetadata imageMetadata=reader.getImageMetadata(0);	
		
    	String formatonombres = imageMetadata.getNativeMetadataFormatName();
    	IIOMetadataNode tiffRootNode = (IIOMetadataNode) imageMetadata.getAsTree(formatonombres);
		    
		    // AQUI DEBEMOS MODIFICAR LOS METADATOS DEL NODO QUE HEMOS EXTRAIDO DE LA IMAGEN
		    
    	NodeList miLista=tiffRootNode.getElementsByTagName("TIFFField");
		    
    	Iterator it=tablaExif.entrySet().iterator();
    	while (it.hasNext()){
    		int x=0;
    		int numeroTag= Integer.parseInt(miLista.item(0).getAttributes().getNamedItem("number").getNodeValue());
    		Map.Entry<Integer,String> e =(Map.Entry<Integer,String>) it.next();
    		while(numeroTag< e.getKey() && x < miLista.getLength()-1){
    			x++;
    			numeroTag= Integer.parseInt(miLista.item(x).getAttributes().getNamedItem("number").getNodeValue());
    		}
    		System.out.println("Probadolio");
    	}
		   
		   
		    
		    /*for(int i=0;i<miLista.getLength();i++){	    	   
			   if (miLista.item(i).getAttributes().getNamedItem("number").getNodeValue().equals("272")){
				   miLista.item(i).getChildNodes().item(0).getChildNodes().item(0).getAttributes().item(0).setNodeValue("Maquinita");
				}else{
				   System.out.println("+++" + miLista.item(i).getAttributes().getNamedItem("number").getNodeValue());
			   }
		   }
		   
		   */
		   Transformer transformer = null;
		   
		   try {
				transformer = TransformerFactory.newInstance().newTransformer();
		   } catch (TransformerConfigurationException e1) {
				// TODO Bloque catch generado automaticamente
			e1.printStackTrace();
		   } catch (TransformerFactoryConfigurationError e1) {
				// TODO Bloque catch generado automaticamente
				e1.printStackTrace();
		   }
		   
		   transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		   DOMSource source = new DOMSource(tiffRootNode);
		   StreamResult console = new StreamResult(new File("D:/PRUEBA/salida.xml"));
		   
		   try {
			   transformer.transform(source, console);
		   } catch (TransformerException e) {
				// TODO Bloque catch generado automáticamente	
			   e.printStackTrace();
		   }
			 
		   System.out.println("\nXML DOM Created Successfully..");
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	
return imageMetadata;
}
}