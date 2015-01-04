package App;


public class ReturnType {
	
	// El método getType debe devolvernos un String con el Tipo de Fichero TIFF, JPEG, PDF, XML
	// Debe recibir un tipo File del fichero que vamos a analizar
	
	public ReturnType (String ruta){
	}
	
	
	static String getType(String ruta){
		
		String nombrefichero = ruta.toLowerCase();
		String tipofichero;
		
		
		int posicionslash = nombrefichero.lastIndexOf("\\");
		int longitudruta = nombrefichero.length();
		String signatura = nombrefichero.substring(posicionslash, (longitudruta +1));
		System.out.println(signatura);
		
		if (nombrefichero.endsWith(".tif")){
			
			tipofichero = "TIFF";
		} else if (nombrefichero.endsWith(".jpg")){
			tipofichero = "JPEG";
			
		} else if (nombrefichero.endsWith(".pdf")){
			tipofichero = "PDF";
		} else if (nombrefichero.endsWith(".xml")){
			tipofichero = "XML";
		} else {
			
			tipofichero = "Tipo de fichero no esperado";
		}
	
		return signatura;
		
		
	}

}
