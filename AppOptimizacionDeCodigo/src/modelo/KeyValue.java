package modelo;

import java.util.Iterator;
import java.util.List;

public class KeyValue {
	private String key;
    private List<String> value;
    private String valueString;

    public KeyValue() {
    	
    }

	public KeyValue(String key2, List<String> value2) {
		setKey(key2);
		setValue(value2);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		String aux = ""; 
		for (String string : value) {
			aux += string;
		}
		this.valueString = aux;
		this.value = value;
	}

	

	@Override
	public String toString() {
		String salida = "";
		for (String string : value) {
			salida += string+" ";
		}
		return key + " = " + salida;
	}

	public String getValueString() {
		return valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}
    
}
