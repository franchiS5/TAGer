package App;

import java.io.File;


import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;



public class Recorre extends SwingWorker<Void, Void>{
	
	private Boolean ch1,ch2,ch3;
	private String exifcopyright,exifsoftware,exiffabricante,exifmodelo,iptcCop,iptcTit,iptcAutor,iptcAsunto;
	private JTextArea etiqueta;
	private String cmd2;
	private String rutaInicial;
	private final JProgressBar progreso;
	private int contador;
	private int total;
	private int porcent;
	
	private Boolean ch4;
	private int inicio;
	private int longitudtotal;
	
	public Recorre(Boolean ch1, Boolean ch2, Boolean ch3,String copyright, String software, String fabricante, String modelo, String iptcCop, String iptcTit, String iptcAutor, String iptcAsunto, String ruta, JTextArea etiqueta, JProgressBar progreso, int total, Boolean ch4)
	{
		this.ch1=ch1;
		this.ch2=ch2;
		this.ch3=ch3;
		this.exifcopyright=copyright;
		this.exifsoftware=software;
		this.exiffabricante=fabricante;
		this.exifmodelo=modelo;
		this.iptcCop=iptcCop;
		this.iptcTit=iptcTit;
		this.iptcAutor=iptcAutor;
		this.iptcAsunto=iptcAsunto;
		this.etiqueta=etiqueta;
		rutaInicial=ruta;
		this.progreso=progreso;
		this.total=total;
		
		this.ch4=ch4;
		contador=0;
		inicio=0;
		longitudtotal=0;
	}

	private void recorrer(File f){
        
		
		String comp= exifcopyright + exifsoftware + exiffabricante + exifmodelo + iptcCop + iptcTit + iptcAutor + iptcAsunto;
		
        if (!(comp.isEmpty()) || ch4){
        	File[] ficheros = f.listFiles();
            

            for (File fichero : ficheros) {
            	
                if (fichero.isFile()) {
                    
                    String nombre=fichero.getName().toLowerCase();
                    if ((nombre.endsWith("tif") && ch1)||(nombre.endsWith("jpg") && ch2)||(nombre.endsWith("pdf") && ch3)){
                        try {
                                            
                        
                        cmd2 = "exiv2";
                        
                        
                        if (ch4){
        					inicio = 0;
                        	// buscamos la ruta del fichero y nos quedamos con la �ltima parte donde aparece directorio y fichero
        					
        					inicio = fichero.getParent().toString().lastIndexOf("\\");
        					longitudtotal = fichero.toString().length();
        					iptcAsunto= fichero.toString().substring(inicio+1, longitudtotal);
        					
        					// limpiamos directorio y fichero para quedarnos solamente con directorio. Sabemos que a partir de la \ est� el fichero
        					// as� que buscamos su �ndice de aparici�n y cogemos desde el principio hasta ese punto.
        					
        					inicio = iptcAsunto.lastIndexOf("\\");
        					iptcAsunto = iptcAsunto.substring(0, inicio);
        					        					
        		        }
                        
                        
                        
                        if (!(iptcTit.isEmpty())){
                        	cmd2 += " -M\"" + "set Iptc.Application2.ObjectName " + iptcTit +"\"";
                        	
                        }
                        
                        if (!(iptcAutor.isEmpty())){
                        	cmd2 += " -M\"" + "set Iptc.Application2.BylineTitle " + iptcAutor +"\"";
                        	
                        }
                        
                        
                        if (!(iptcAsunto.isEmpty())){
                        	cmd2 += " -M\"" + "set Iptc.Application2.Caption " + iptcAsunto +"\"";
                        	
                        }
                        
                        
                        if (!(iptcCop.isEmpty())){
                        	cmd2 += " -M\"" + "set Iptc.Application2.Copyright " + iptcCop +"\"";
                        	
                        }
                        
                        if (!(exifcopyright.isEmpty())){
                        	cmd2 += " -M\"" + "set Exif.Image.Copyright " + exifcopyright +"\"";
                        	
                        	                   
                        }
                        if (!(exifsoftware.isEmpty())){
                        	cmd2 += " -M\"" + "set Exif.Image.Software " + exifsoftware +"\"";
                        	
                        	
                        }
                        
                        if (!(exiffabricante.isEmpty())){
                        	cmd2 += " -M\"" + "set Exif.Image.Make " + exiffabricante +"\"";
                        	
                        	
                        }
                        
                        if (!(exifmodelo.isEmpty())){
                        	cmd2 += " -M\"" + "set Exif.Image.Model " + exifmodelo +"\"";
                        	
                        	
                        }
                                            
                        
                        cmd2 += " \"" + fichero.toString() + "\"";	
  
                        etiqueta.append(fichero.toString() + "\n");
                        
                        contador++;
                        porcent=new Double(contador * 100 / total).intValue();
                        progreso.setValue(porcent);
                        
                        Process proceso1=Runtime.getRuntime().exec(cmd2);
                		try{
                        	proceso1.waitFor();
                        	
                        	
                        	}catch(InterruptedException ex){
                        	System.out.println(ex.toString());
                        	}
                		
                		
                    }catch (Exception e) {
                        System.out.println (e);
                    }
                    
                    }  
                } else {
                    recorrer(fichero);
                }
                  
            }
        }else{
        	
        	  JOptionPane.showMessageDialog(null,"Debe seleccionar al menos una opci�n");
        }
		
        
     }
	
	@Override
	protected Void doInBackground() throws Exception{
		
		
		recorrer(new File(rutaInicial));
		
		etiqueta.append("Proceso Terminado\n");
		
		
		return null;
	}
	
	@Override
	protected void done(){
		
		
	}
	
	
}
