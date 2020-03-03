package br.com.jgm.main;

import br.com.jgm.web.NavNFS;
import br.com.jgm.web.NavNFTS;

public class Start {

	public static void main(String[] args) throws InterruptedException {
		
//		NavNFTS nav = new NavNFTS();		
//		nav.navegar();
		
//		NavGUIA nav = new NavGUIA();
//		nav.navegar();

		NavNFS nav = new NavNFS();
		nav.navegar();
		
	}

}
