package App;

public class WCBPath {
	
static String RutaOrigen;
static String RutaDestino;
	
public static void RutaDestino(String RutaOrigen,  String RutaDestino) {
	
	CreaRuta(RutaOrigen, RutaDestino);
	//return RutaDestino;
	
}

static String CreaRuta (String RutaOrigen, String RutaDestino){
	
	String[] arrayOrigen = RutaOrigen.split("/");
	String[] arrayDestino = RutaDestino.split("/");
	
	for (int i=1; i<arrayDestino.length; i++){
		if (arrayOrigen[i].toString().matches(arrayDestino[i].toString())){
			//RutaDestino += "/" + arrayDestino[i];
			System.out.println("Son iguales");
		}else{
			
			for (int i2= i+1; i2<arrayOrigen.length;i2++){
				RutaDestino+= "/" + arrayOrigen[i2];
				
			}
		}
		
	}
	return RutaDestino;
	
	
	
}

}
