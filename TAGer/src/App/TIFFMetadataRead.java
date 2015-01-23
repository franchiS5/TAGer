package App;

import java.io.File;
import java.io.IOException;
//import java.util.HashMap;
//import java.util.Iterator;

import com.drew.imaging.tiff.TiffMetadataReader;
import com.drew.metadata.Metadata;
//import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;


																									//Esta clase nos tiene que devolver un objeto HasMap con los Exif del fichero que le pasemos

public class TIFFMetadataRead {
	
static ExifIFD0Directory exifdirectory;
	
	
public static ExifIFD0Directory LeerExif (String filewcb) throws IOException{
		
		exifdirectory=null;
		getExifFile(new File(filewcb));
		return exifdirectory;
	}

static ExifIFD0Directory getExifFile (File ficheroTiffWCB) throws IOException
{
	
	Metadata tiffMetadata = TiffMetadataReader.readMetadata(ficheroTiffWCB);							//Creamos objeto Metadata del fichero ficheroTiffWCB	
	if (ficheroTiffWCB.toString().endsWith(".tif"))													//Comprobamos que es un .tif
	{
		ExifIFD0Directory exifdirectory = tiffMetadata.getDirectory(ExifIFD0Directory.class);		//Creamos el objeto exifdirectory del objeto tiffMetadata
			if( exifdirectory != null )																//Comprobamos que no sea null
			{
				
			} else{
				System.out.println( "El fichero " + ficheroTiffWCB + " no contiene datos Exif" );	
			}
	}else{
		System.out.println("El fichero "+ ficheroTiffWCB + " no es del tipo TIFF");					//Si no era .tif avisamos
		}
	return exifdirectory;
}
}
