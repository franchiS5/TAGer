package App;

import java.util.HashMap;

public class Etiquetas {
	
	private HashMap<Integer,String> nombres;
	private HashMap<Integer,String> tipos;
	private HashMap<String,String> subtipos;
	
	public Etiquetas()
	{
		nombres=new HashMap<Integer,String>();
		tipos=new HashMap<Integer,String>();
		subtipos=new HashMap<String,String>();
		subtipos.put("TIFFAsciis", "TIFFAscii");
		subtipos.put("TIFFShorts", "TIFFShort");
		subtipos.put("TIFFLongs", "TIFFLong");
		nombres.put(33432, "Copyright");
		tipos.put(33432, "TIFFAsciis");
	}

	
	public String getNombre(int codigo)
	{
		return nombres.get(codigo);
	}
	
	
	public String getTipo(int codigo)
	{
		return tipos.get(codigo);
	}
	
	
	public String getSubTipo(String tipo)
	{
		return subtipos.get(tipo);
	}
}