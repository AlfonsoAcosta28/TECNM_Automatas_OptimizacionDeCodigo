package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class Optimizacion {

	private final List<Character> operadores = Arrays.asList('+', '-', '*', '/', '^');

	private List<KeyValue> mapear(String texto) {
		String[] lineas = texto.split("\\r?\\n");

		List<KeyValue> datos = new ArrayList<>();

		for (String linea : lineas) {
			
			KeyValue keyValue = new KeyValue();
			String clave = "";
			List<String> valor = new ArrayList<String>();
			boolean bandera = true;
			String variable = "";

			for (char c : linea.toCharArray()) {
				if (c == ' ') {
					continue;
				}
				if (c == '=') {
					keyValue.setKey(clave);
					bandera = false;
					continue;
				}
				if (bandera) {
					clave += c;
				}else {			
					if (operadores.contains(c)) {
						valor.add(variable);
						valor.add(c+"");
						variable = "";
						continue;
					}
					variable+=c;
				}				
			}

			valor.add(variable);			
			keyValue.setValue(valor);
			datos.add(keyValue);
		}
		return datos;
	}

	public List<KeyValue> optimizar(String texto) {
		var mapeo = mapear(texto);

		//		int primerCiclo = 0;
		////		PASO 1
		//		mapeo = subExpresionesComunes(mapeo);//1
		////		PASO 2
		////		mapeo = subExpresionesComunes(mapeo);//1
		//		mapeo = propagacionDeCopias(mapeo);//2
		////		PASO 3
		//		mapeo = subExpresionesComunes(mapeo);//1
		//		mapeo = propagacionDeCopias(mapeo);//2
		//		mapeo = eliminacionDeCodigoMuerto(mapeo);//3
		////		PASO 4
		//		mapeo = subExpresionesComunes(mapeo);//1
		//		mapeo = propagacionDeCopias(mapeo);//2
		//		mapeo = eliminacionDeCodigoMuerto(mapeo);//3
		//		mapeo = transformacionesAritmeticas(mapeo);//4
		////		PASO 5
		//		mapeo = subExpresionesComunes(mapeo);//1
		//		mapeo = propagacionDeCopias(mapeo);//2
		//		mapeo = eliminacionDeCodigoMuerto(mapeo);//3
		//		mapeo = transformacionesAritmeticas(mapeo);//4
		//		mapeo = transformacionesAlgebraicas(mapeo);//5
		////		PASO 6
		//		mapeo = subExpresionesComunes(mapeo);//1
		//		mapeo = propagacionDeCopias(mapeo);//2
		//		mapeo = eliminacionDeCodigoMuerto(mapeo);//3
		//		mapeo = transformacionesAritmeticas(mapeo);//4
		//		mapeo = transformacionesAlgebraicas(mapeo);//5
		//		mapeo = reduccionDeIntensidad(mapeo);//6

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j <= i; j++) {
				switch (j) {
				case 0:
					mapeo = subExpresionesComunes(mapeo);
					break;
				case 1:
					mapeo =  propagacionDeCopias(mapeo);
					break;
				case 2:
					mapeo = eliminacionDeCodigoMuerto(mapeo);
					break;
				case 3:
					mapeo = transformacionesAritmeticas(mapeo);
					break;
				case 4:
					mapeo = transformacionesAlgebraicas(mapeo);
					break;
				case 5:
					mapeo = reduccionDeIntensidad(mapeo);
					break;
				default:
					break;
				}
			}
			System.out.println("\n\n");
		}
		return mapeo;
	}


	public List<KeyValue> subExpresionesComunes(List<KeyValue> datos) {
		System.out.println("****SUBEXPRESIONES COMUNES****");
		List<KeyValue> clavesRemplazadas = new ArrayList<KeyValue>();

		for (int i = 0; i < datos.size(); i++) {
			KeyValue keyValue = datos.get(i);
			String dato = keyValue.getValueString();
			String key = keyValue.getKey();				
			for (int j = i+1; j < datos.size(); j++) {
				KeyValue keyValuej = datos.get(j);
				String datoJ = keyValuej.getValueString();

				if (keyValue.getValue().contains(keyValuej.getKey())) {
					break;
				}

				if (dato.equals(datoJ)) {

					if (clavesRemplazadas.stream().anyMatch(kv -> kv.getKey().equals(keyValue.getValueString()))) {
						break;
					}
					datos.get(j).setValue(Arrays.asList(key));
					if (!clavesRemplazadas.contains(keyValue)) {
						clavesRemplazadas.add(keyValue);
					}
				}
			}
		}

		datos.forEach(e -> System.out.println(e));

		return datos;
	}

	public List<KeyValue> propagacionDeCopias(List<KeyValue> datos) {
		System.out.println("****PROPAGACION DE COPIAS****");

		for (int i = 0; i < datos.size(); i++) {
			KeyValue keyValue = datos.get(i);
			if (keyValue.getValue().size() > 1) {
				continue;
			}
			String dato = keyValue.getValueString();
			String key = keyValue.getKey();				
			for (int j = i+1; j < datos.size(); j++) {
				KeyValue keyValuej = datos.get(j);

				if (keyValue.getKey().equals(keyValuej.getKey())) {
					break;
				}

				if (keyValuej.getValue().contains(key)) {
					for (int k = 0; k < keyValuej.getValue().size(); k++) {
						if (keyValuej.getValue().get(k).equals(key)) {
							keyValuej.getValue().set(k, dato);
							keyValuej.setValue(keyValuej.getValue());

						}
					}
				}

			}
		}
		datos.forEach(e -> System.out.println(e));
		return datos;
	}

	public List<KeyValue> eliminacionDeCodigoMuerto(List<KeyValue> datos) {
		System.out.println("****ELIMINACION DE CODIGO MUERTO****");

		boolean esCodigoMuerto = true;
		boolean hayCodigoMuerto = false;

		for (int i = 0; i < datos.size()-1; i++) {
			KeyValue keyValue = datos.get(i);
			String key = keyValue.getKey();			
			esCodigoMuerto = true;
			for (int j = i+1; j < datos.size(); j++) {
				KeyValue keyValuej = datos.get(j);

				if (keyValuej.getKey().equals(key)) {
					break;
				}
				if (keyValuej.getValue().contains(key)) {
					if (!keyValuej.getKey().equals(key)) {
						esCodigoMuerto = false;
					}
					break;						
				}

			}
			if (esCodigoMuerto) {
				datos.remove(i);
				hayCodigoMuerto = true;
			}
		}

		datos.forEach(e -> System.out.println(e));

		if (hayCodigoMuerto) {
			datos = eliminacionDeCodigoMuerto(datos);
		}

		return datos;
	}

	public List<KeyValue> transformacionesAritmeticas(List<KeyValue> datos) {
		System.out.println("****TRANSFORMACIONES ARITMETICAS****");

		for (int i = 0; i < datos.size(); i++) {
			KeyValue keyValue = datos.get(i);

			if (keyValue.getValue().size() == 1) {
				continue;
			}
			try {
				double numero1 = Double.parseDouble(keyValue.getValue().get(0));
				double numero2 = Double.parseDouble(keyValue.getValue().get(2));
				String operador = keyValue.getValue().get(1);

				double resultadoD = 0;
				switch (operador) {
				case "+":
					resultadoD = numero1 + numero2;
					break;
				case "-":
					resultadoD = numero1 - numero2;
					break;
				case "*":
					resultadoD = numero1 * numero2;
					break;
				case "/":
					resultadoD = (double) numero1 / numero2;
					break;
				default:
					break;
				}
				try {
					keyValue.setValue(Arrays.asList(convertIfEndsInZero(resultadoD)+""));
				} catch (Exception e) {
					// TODO: handle except
				}
					
				
			} catch (Exception e2) {
			}			
		}

		datos.forEach(e -> System.out.println(e));

		return datos;
	}
	public Number convertIfEndsInZero(double number) {
        if (number % 1 == 0) {
            return (int) number;
        } else {
            return number;
        }
    }


	public List<KeyValue> transformacionesAlgebraicas(List<KeyValue> datos) {
		System.out.println("****TRANSFORMACIONES ALGEBRAICAS****");

		for (int i = 0; i < datos.size(); i++) {
			KeyValue keyValue = datos.get(i);
			if (keyValue.getValue().size() == 1) {
				continue;
			}
			String operador = keyValue.getValue().get(1);
			String valorString1 = keyValue.getValue().get(0);
			String valorString2 = keyValue.getValue().get(2);
			if (valorString1.equals(valorString2) && operador.equals("/")) {
				keyValue.setValue(Arrays.asList("1"));
				continue;
			}
			Integer valor1 = null;
			Integer valor2 = null;
			try {
				valor1 = Integer.parseInt(valorString1);
			} catch (Exception e2) {
			}
			try {
				valor2 = Integer.parseInt(valorString2);
			} catch (Exception e2) {
			}
			if (valor1 != null) {
				switch (operador) {
				case "*":
					if (valor1 == 1) {
						keyValue.setValue(Arrays.asList(valorString2));
					}
					break;
				case "+":
					if (valor1 == 0) {
						keyValue.setValue(Arrays.asList(valorString2));
					}
					break;
				case "-":
					if (valor1 == 0) {
						keyValue.setValue(Arrays.asList(valorString2));
					}
					break;
				default:
					break;
				}
			}
			if (valor2 != null) {
				switch (operador) {
				case "*":
					if (valor2 == 1) {
						keyValue.setValue(Arrays.asList(valorString1));
					}
					break;
				case "/":
					if (valor2 == 1) {
						keyValue.setValue(Arrays.asList(valorString1));
					}
					break;
				case "+":
					if (valor2 == 0) {
						keyValue.setValue(Arrays.asList(valorString1));
					}
					break;
				case "-":
					if (valor2 == 0) {
						keyValue.setValue(Arrays.asList(valorString1));
					}
					break;
				default:
					break;
				}
			}

		}

		datos.forEach(System.out::println);
		return datos;
	}

	public List<KeyValue> reduccionDeIntensidad(List<KeyValue> datos) {
		System.out.println("****REDUCCION DE INTENSIDAD****");

		for (int i = 0; i < datos.size(); i++) {
			KeyValue keyValue = datos.get(i);
			if (keyValue.getValue().size() == 1) {
				continue;
			}

			String operador = keyValue.getValue().get(1);
			Integer valor1 = null;
			Integer valor2 = null;
			try {
				valor1 = Integer.parseInt(keyValue.getValue().get(0));
			} catch (Exception e2) {
			}
			try {
				valor2 = Integer.parseInt(keyValue.getValue().get(2));
			} catch (Exception e2) {
			}
			var dato1 = keyValue.getValue().get(0);
			var dato2 = keyValue.getValue().get(2);
			if (valor2 != null) {
				if (operador.equals("^") && valor2 == 2) {					
					keyValue.setValue(Arrays.asList(dato1,"*",dato1));
				}
				if (operador.equals("*") && valor2 == 2) {					
					keyValue.setValue(Arrays.asList(dato1,"+",dato1));
				}
			}
			if (valor1 != null) {
				if (operador.equals("*") && valor1 == 2) {					
					keyValue.setValue(Arrays.asList(dato2,"+",dato2));
				}
			}
		}

		datos.forEach(e -> System.out.println(e));
		return datos;
	}
}
