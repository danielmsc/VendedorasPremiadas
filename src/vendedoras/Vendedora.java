package vendedoras;

/**
 * 
 * Datos para la complejidad:
 * n = cantidad de vendedoras (peor caso = 100)
 * m = cantidad de ventas (peor caso = 1000) 
 *
 */

public class Vendedora {
	private int id;
	private int[] ventas;
	private int totalVentasN;
	
	public Vendedora(int id, int cantVentas) {				// O(1)
		this.id = id;
		this.ventas = new int[cantVentas];
		this.totalVentasN = 0;
	}
	
	public void addVenta(int nroVenta, int valor) {			// O(1)
		ventas[nroVenta] = valor;
	}
	
	public int totalNVentasConsecutivas(int n) {			// O(m)
		int indexIni = 0;
		int indexFin = n;
		int max = 0;
		
		while(indexFin <= ventas.length) {
			int total = 0;
			
			for(int i = indexIni; i < indexFin; i++) 
				total += ventas[i];
			
			if(total > max)
				max = total;
			
			indexIni++;
			indexFin++;
		}
		totalVentasN = max;
		
		return max;
	}
	
	public int getCantVentas() {							// O(1)
		return ventas.length;
	}
	
	public int getId() {									// O(1)
		return id;
	}
	
	public int getTotalVentasN() {							// O(1)
		return totalVentasN;
	}
}
