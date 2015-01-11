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
	static HashMap<String, String> etiqueta;
	
	
	
public static HashMap <String, String> LeerExif (String filewcb) throws IOException{
		
		etiqueta=null;
		getExifFile(new File(filewcb));
		return etiqueta;
		
	}

static HashMap <String, String> getExifFile (File ficheroTiffWCB) throws IOException
{
	HashMap<String,String> hmtiff = new HashMap<String, String>();
	Metadata tiffMetadata = TiffMetadataReader.readMetadata(ficheroTiffWCB);							//Creamos objeto Metadata del fichero ficheroTiffWCB	
	if (ficheroTiffWCB.toString().endsWith(".tif"))													//Comprobamos que es un .tif
	{
		ExifIFD0Directory exifdirectory = tiffMetadata.getDirectory(ExifIFD0Directory.class);		//Creamos el objeto exifdirectory del objeto tiffMetadata
			if( exifdirectory != null )																//Comprobamos que no sea null
			{
				for(Iterator<Tag> i = exifdirectory.getTags().iterator(); i.hasNext(); )			//Iteramos exifdirectory
				{
				Tag tag = ( Tag )i.next();
            	//System.out.println( "\t" + tag.getTagTypeHex() + " = " + tag.getDescription());
            	hmtiff.put(tag.getTagTypeHex(),tag.getDescription());								//Vamos llenando el HashMap con el contenido de exifdirectory
            	}
			}
			else
			{
			System.out.println( "El fichero " + ficheroTiffWCB + " no contiene datos Exif" );		//Si estaba null avisamos
			}
	}else{
		System.out.println("El fichero "+ ficheroTiffWCB + " no es del tipo TIFF");					//Si no era .tif avisamos
		}
		etiqueta = new HashMap<String, String> (hmtiff);
		return etiqueta;
}
}
