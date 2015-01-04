package App;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.SwingWorker;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.media.jai.codec.TIFFField;


public class CortaTiff extends SwingWorker<Void,Void>{

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



public CortaTiff(String RutaOrigen, String nombreimagenIN,String P1x1, String P1y1, String P1x2, String P1y2,
				String P2x1, String P2y1, String P2x2, String P2y2, int imagenSalida){
	
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
	
}


private void cortatiff(File f) throws IOException, Exception{
	
	try{
		
		
	
	ImagePlus imp = IJ.openImage(RutaOrigen + nombreimagenIN);
	ImageProcessor ip = imp.getProcessor();
	

	ip.setRoi(Integer.parseInt(P1x1),Integer.parseInt(P1y1),Integer.parseInt(P1x2),Integer.parseInt(P1y2));
	ip = ip.crop();
	
	int width = ip.getWidth();
	int heigth = ip.getHeight();
	
	System.out.println("El ancho es:" + width);
	BufferedImage croppedimage = ip.getBufferedImage();
	
	System.out.println("CORTATIFF RutaOrigen: " + RutaOrigen + "\n");
	System.out.println("CORTATIFF nombreimagenIN: " + nombreimagenIN + "\n");
	System.out.println("CORTATIFF: " + P1x1 + "\n");
	System.out.println("CORTATIFF: " + P1y1 + "\n");
	System.out.println("CORTATIFF: " + P1x2 + "\n");
	System.out.println("CORTATIFF: " + P1y2 + "\n");
	System.out.println("CORTATIFF imagenSalida: " + imagenSalida + "\n");
	
	TIFFEncodeParam param = new TIFFEncodeParam();
    
    final int XRES_TAG = 282;
    final int YRES_TAG = 283;
    final int INCH_TAG = 296;
    final int COPYRIGHT_TAG = 33432;
    final int IMAGEWIDTH_TAG = 256;
    final int IMAGELENGTH_TAG = 257;
    final int NEWSUBFILETYPE_TAG = 254;
    final int FILLORDER_TAG = 266;
    final int ORIENTATION_TAG = 274;
    
    // Componemos la cabecera  usando los TAGS definidos en las variables anteriores
    
    
    param.setCompression(TIFFEncodeParam.COMPRESSION_NONE);
    TIFFField xRes = new TIFFField(XRES_TAG, TIFFField.TIFF_RATIONAL, 1, new long[][]{{ (long)300, 1 }});
	TIFFField yRes = new TIFFField(YRES_TAG, TIFFField.TIFF_RATIONAL, 1, new long[][]{{ (long)300, 1 }});
    TIFFField unit_Inch = new TIFFField(INCH_TAG, TIFFField.TIFF_SHORT, 1, (Object)new char [] {2});
    TIFFField copyright = new TIFFField(COPYRIGHT_TAG, TIFFField.TIFF_ASCII,1, (Object)new String [] {"vinfra"});
    TIFFField imagewidth = new TIFFField(IMAGEWIDTH_TAG, TIFFField.TIFF_LONG,1, (Object) new long [] {width});
    TIFFField imagelength = new TIFFField(IMAGELENGTH_TAG, TIFFField.TIFF_LONG,1, (Object) new long [] {heigth});
    TIFFField subfiletype = new TIFFField(NEWSUBFILETYPE_TAG,TIFFField.TIFF_SHORT,1,(Object) new char [] {0});
    TIFFField fillorder = new TIFFField(FILLORDER_TAG,TIFFField.TIFF_SHORT,1,(Object) new char [] {1});
    TIFFField orientation = new TIFFField(ORIENTATION_TAG,TIFFField.TIFF_SHORT,1,(Object) new char [] {1}); 
    
    param.setExtraFields(new TIFFField [] {xRes,yRes,unit_Inch,copyright,imagewidth,imagelength,subfiletype,fillorder,orientation});
    
    
    
    	
    	// Creamos la imagen de salida
		
		FileOutputStream tiffsalida = new FileOutputStream(RutaOrigen + Integer.toString(imagenSalida) + ".tif");
		ImageEncoder encoder = ImageCodec.createImageEncoder("TIFF", tiffsalida, param);
		encoder.encode(croppedimage);
        tiffsalida.close();
        
        
        
        
        
        
        	
	
	
	
	}catch (Exception e){
		System.out.println(e);
		
	}
}


protected Void doInBackground() throws Exception {
	

	cortatiff(new File(RutaOrigen));
	notify();
	System.out.println("Proceso TERMINADO");
	return null;
	
	}

}


