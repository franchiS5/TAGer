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
		nombres.put(305,"Software");
		nombres.put(271, "Make");
		nombres.put(272, "Model");
		nombres.put(306, "DateTime");
		tipos.put(33432, "TIFFAsciis");
		tipos.put(305, "TIFFAsciis");
		tipos.put(271, "TIFFAsciis");
		tipos.put(272, "TIFFAsciis");
		tipos.put(306,  "TIFFAsciis");
		
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