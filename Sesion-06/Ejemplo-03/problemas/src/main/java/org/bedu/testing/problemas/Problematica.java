package org.bedu.testing.problemas;


public class Problematica {

	private static final int LIMITE_SOLICITUDES = 10;
	
	public void variableInutil() {
		int a = 0;
		for (int i = 0; i < 10; i++);
	}

	public int retornarValor() {
		int promedio = 10 / 10;
		return promedio;
	}


	public String incorrecto() {
		String resultado = "";
		for (int i = 0; i < 10000; i++) {
			resultado += String.valueOf(i);
			resultado += ", ";
		}
		return resultado;
	}

	public String correcto() {
		StringBuilder resultado = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			resultado.append(i);
			resultado.append(", ");
		}
		return resultado.toString();
	}

	public void metodo(int variable1, int v2, int variableCreadaParaDemostrarQueLosNombresLargosSonIgualDeMalosQueLosMuyCortos) {
		int calculo = variable1 / v2;
		calculo += variableCreadaParaDemostrarQueLosNombresLargosSonIgualDeMalosQueLosMuyCortos / 2;
	}

	public boolean incorrecto(String str1, String str2) {
		return str1 == str2;
	}

	public boolean correcto(String str1, String str2) {
		return str1.equals(str2);
	}

	public void verificarValoresMal(int valor) {
		if (valor == 10) {
			for (int i = 0; i < 10; i++);
		}
	}

	public void verificarValoresBien(int valor) {
		if (valor == LIMITE_SOLICITUDES) {
			for (int i = 0; i < 10; i++);
		}
	}

	public boolean compararMal(boolean bandera) {
		if (bandera == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean compararBien(boolean bandera) {
		return bandera;
	}

	public String saludarMal(int x) {
		if (x > 0) {
			return "hey";
		}
		return "hi";
	}

	public String saludarBien(int x) {
		String saludo = "hi";
		if (x > 0) {
			saludo = "hey";
		}
		return saludo;
	}
}
