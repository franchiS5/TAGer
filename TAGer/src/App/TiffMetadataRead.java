package App;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import com.drew.imaging.tiff.TiffMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;


																									//Esta clase nos tiene que devolver un objeto HasMap con los Exif del fichero que le pasemos

public class TiffMetadataRead {
	static String etiqueta;
	private static String tagsolicitado;
	
public String LeerExif (String filewcb, String tagsolicitado) throws IOException{
		
		etiqueta=null;
		TiffMetadataRead.tagsolicitado = tagsolicitado;
		getExifFile(new File(filewcb));
		return etiqueta;
		
	}

static String getExifFile (File ficheroTiffWCB) throws IOException
{
Metadata tiffMetadata = TiffMetadataReader.readMetadata(ficheroTiffWCB);							//Creamos objeto Metadata del fichero ficheroTiffWCB	
	if (ficheroTiffWCB.toString().endsWith(".tif"))													//Comprobamos que es un .tif
	{
		ExifIFD0Directory exifdirectory = tiffMetadata.getDirectory(ExifIFD0Directory.class);		//Creamos el objeto exifdirectory del objeto tiffMetadata
			if( exifdirectory != null )																//Comprobamos que no sea null
			{
				for(Iterator<Tag> i = exifdirectory.getTags().iterator(); i.hasNext(); )			//Iteramos exifdirectory
				{
				HashMap<String,String> hmtiff = new HashMap<String, String>();
				Tag tag = ( Tag )i.next();
            	//System.out.println( "\t" + tag.getTagTypeHex() + " = " + tag.getDescription());
            	hmtiff.put(tag.getTagTypeHex(),tag.getDescription());								//Vamos llenando el HashMap con el contenido de exifdirectory
            		if (hmtiff.get(tagsolicitado) != null){
            		etiqueta = hmtiff.get(tagsolicitado);
            		}
            	}
			}
			else
			{
			System.out.println( "El fichero " + ficheroTiffWCB + " no contiene datos Exif" );		//Si estaba null avisamos
			}
	}else{
		System.out.println("El fichero "+ ficheroTiffWCB + " no es del tipo TIFF");					//Si no era .tif avisamos
		}
		return etiqueta;
}
}
