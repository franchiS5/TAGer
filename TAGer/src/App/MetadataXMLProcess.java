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

	
public static IIOMetadataNode MetadataChange (IIOMetadataNode tiffRootNode, LinkedHashMap<Integer, String> tablaExif) throws IOException{
	
	imageMetadata=null;
	return metaexif(tiffRootNode, tablaExif);
}

static IIOMetadataNode metaexif (IIOMetadataNode tiffRootNode, LinkedHashMap<Integer, String> tablaExif) throws IOException{
	
	NodeList miLista=tiffRootNode.getElementsByTagName("TIFFField");
	    
	Iterator it=tablaExif.entrySet().iterator();
	while (it.hasNext()){
		int x=0;
		int numeroTag= Integer.parseInt(miLista.item(0).getAttributes().getNamedItem("number").getNodeValue());
		Map.Entry<Integer,String> e =(Map.Entry<Integer,String>) it.next();
		while(numeroTag< e.getKey() && x < miLista.getLength()){
			numeroTag= Integer.parseInt(miLista.item(x).getAttributes().getNamedItem("number").getNodeValue());
			if (numeroTag < e.getKey())
			{
				x++;	
			}
		}
		
		if (numeroTag == e.getKey())
		{
			//Cambiar valor
			miLista.item(x).getChildNodes().item(0).getChildNodes().item(0).getAttributes().item(0).setNodeValue(e.getValue());
		}else{
			IIOMetadataNode newChild= crearNodo(e.getKey(),e.getValue());
			if (x>=miLista.getLength())
			{
				//Añadir nodo
				tiffRootNode.getChildNodes().item(0).appendChild(newChild);
			}else{
				//Insertar nodo
				tiffRootNode.insertBefore(newChild, miLista.item(x)); 
			}
		}
	}
	return tiffRootNode;
}



static IIOMetadataNode crearNodo(int codigo,String valor)
{
	Etiquetas miEtq=new Etiquetas();
	IIOMetadataNode nuevo = new IIOMetadataNode("TIFFField");
	nuevo.setAttribute("name", miEtq.getNombre(codigo));
	nuevo.setAttribute("number", Integer.toString(codigo));
	String tipoString = miEtq.getTipo(codigo);
	IIOMetadataNode tipo = new IIOMetadataNode(tipoString);
	IIOMetadataNode subTipo = new IIOMetadataNode(miEtq.getSubTipo(tipoString));
	subTipo.setAttribute("value", valor);
	tipo.appendChild(subTipo);
	nuevo.appendChild(tipo);
	
	return nuevo;
}
}