package vendedoras;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		Premio p = null;
		try {
			p = new Premio("compMinima2Vendedoras.in"); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		p.resolver();
		
	}

}
