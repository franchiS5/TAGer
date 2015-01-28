package App;

import java.io.File;

import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
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

	
	
public static IIOMetadata metadatachange (IIOMetadata imageMetadata){
	
	metaexif(imageMetadata);
	return imageMetadata;
	
}

static void metaexif (IIOMetadata imageMetadata){
	
	String formatonombres = imageMetadata.getNativeMetadataFormatName();
    IIOMetadataNode tiffRootNode = (IIOMetadataNode) imageMetadata.getAsTree(formatonombres);
    
    // AQUI DEBEMOS MODIFICAR LOS METADATOS DEL NODO QUE HEMOS EXTRAIDO DE LA IMAGEN
    
    NodeList miLista=tiffRootNode.getElementsByTagName("TIFFField");
    
   for(int i=0;i<miLista.getLength();i++){	    	   
	   if (miLista.item(i).getAttributes().getNamedItem("number").getNodeValue().equals("272")){
		   
		   miLista.item(i).getChildNodes().item(0).getChildNodes().item(0).getAttributes().item(0).setNodeValue("Maquinita");
		   
	   }else{
		   System.out.println("+++" + miLista.item(i).getAttributes().getNamedItem("number").getNodeValue());
	   }
   }
    
	
    Transformer transformer = null;
	try {
		transformer = TransformerFactory.newInstance().newTransformer();
	} catch (TransformerConfigurationException e1) {
		// TODO Bloque catch generado automáticamente
		e1.printStackTrace();
	} catch (TransformerFactoryConfigurationError e1) {
		// TODO Bloque catch generado automáticamente
		e1.printStackTrace();
	}
	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	DOMSource source = new DOMSource(tiffRootNode);
	StreamResult console = new StreamResult(new File("salida.xml"));
	try {
		transformer.transform(source, console);
	} catch (TransformerException e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}
	 
	System.out.println("\nXML DOM Created Successfully..");
	
	
}
}