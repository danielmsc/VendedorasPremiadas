package vendedoras;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * Datos para la complejidad:
 * n = cantidad de vendedoras (peor caso = 100)
 * m = cantidad de ventas (peor caso = 1000) 
 *
 */

public class Premio {
	private Vendedora[] vendedoras;
	private int n;
	
	public Premio(String in) throws FileNotFoundException {					// O(n*m)
		Scanner sc = new Scanner(new File(in));
		
		this.vendedoras = new Vendedora[sc.nextInt()];
		
		for(int i = 0; i < this.vendedoras.length; i++) {
			int cantVentas = sc.nextInt();
			this.vendedoras[i] = new Vendedora(i+1, cantVentas);
			for(int j = 0; j < cantVentas; j++) 
				this.vendedoras[i].addVenta(j, sc.nextInt());
		}
		
		this.n = sc.nextInt();
		
		sc.close();
	}
	
	public void resolver() {												// O(m*n)
		int ganadora = -1;
		boolean[] compiten = new boolean[vendedoras.length];
		boolean empate = false;
		
		Arrays.fill(compiten, true);								//compiten todas las vendedoras al inicio
		
		while(n < 1001) {
			int max = 0;
			int sinCantN = 0;
			
			//System.out.println(n);
			for(int i = 0; i < compiten.length; i++) {				//itero por cada vendedora O(n)
				if(compiten[i] == true) {							//si la vendedora compite, la analizo
					if(vendedoras[i].getCantVentas() >= n) {		//si la vendedora tiene igual o más ventas que n, analizo
						int totalConsecutivo = vendedoras[i].totalNVentasConsecutivas(n);
						if(totalConsecutivo > max) {
							max = totalConsecutivo;
							ganadora = vendedoras[i].getId();
							Arrays.fill(compiten, 0, i, false);		//si vendio mas que el maximo registrado, todas las anteriores analizadas dejan de competir
							compiten[i] = true;
							empate = false;
						} else if(totalConsecutivo == max) {
							empate = true;
							ganadora = -1;
						} else
							compiten[i] = false;
					} else 											//si no tiene las ventas necesarias para analizarla, lo cuento dentro de las que no tienen ventas necesarias
						sinCantN++;
				}
			}
			
			if(ganadora != -1) {
				System.out.println(ganadora);
				System.out.println(n + " " + vendedoras[ganadora-1].getTotalVentasN());
				return;
			}
			
			int cantCompiten = 0;
			for(boolean c : compiten)
				if(c == true) cantCompiten++;
			
			if(sinCantN < cantCompiten && empate == true)				//quedan mas ventas por analizar en las vendedoras que siguen compitiendo, incremento n y sigo
				n++;
			else {			
				//System.out.println(sinCantN + " " + cantCompiten + " " + empate + " " + n);
				if(sinCantN == cantCompiten && empate == true)			//todas las vendedoras restantes no tienen mas ventas para analizar (ventas < n) y hay empate
					System.out.println("No se puede desempatar");				
				else if(sinCantN == cantCompiten && empate == false) 	//ninguna de las vendedoras tuvo las ventas necesarias para competir
					System.out.println("No hay ganadoras");
				return;
			}
		}
		
		if(n >= 1000 && empate == true)
			System.out.println("No se puede desempatar");
		else if(n >= 1000 && empate == false)
			System.out.println("No hay ganadoras");
	}
}
