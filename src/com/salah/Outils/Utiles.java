package com.salah.Outils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

 

 
import Model.Signatures;


public class Utiles {
	
	public static int deffinirIntervale(int intervaleTotale, int nbrDeDivision, int nombre){
		int j = 0;
		int inter = intervaleTotale*2/nbrDeDivision;
		for(int i = -intervaleTotale; i <= intervaleTotale; i+= inter){
			if(nombre >= i && nombre <= i+inter){
				i = intervaleTotale;
			}else{
				i++;
				j++;
			}
		}
		return j;
	}

	
	public static int getMin(int num1, int num2){
		if(num1<num2){
			return num1;
		}else{
			return num2;
		}
	}
	
	public static String getFileName(String filePath) {
		String fileName = "";
		for (int i = filePath.length(); i > 0; i--) {

			if (filePath.charAt(i - 1) == '\\') {
				System.out.println("oui");
				i = 0;
			}
			if (i != 0) {
				fileName = filePath.charAt(i - 1) + fileName;
			}
		}
		System.out.println(fileName);
		return fileName;
	}
	
	public static void listerRepertoire(File repertoire) {

		String[] listefichiers;

		int i;
		listefichiers = repertoire.list();
		for (i = 0; i < listefichiers.length; i++) {
			if (listefichiers[i].endsWith(".jpg") == true) {
				System.out.println(listefichiers[i].substring(0,listefichiers[i].length() - 4));
			}
		}
	}
	
	/** copie le fichier source dans le fichier resultat
	 * retourne vrai si cela r�ussit
	 */
	public static boolean copyFile(String sourcePath, String destPath){

		FileChannel in = null; // canal d'entr�e
		FileChannel out = null; // canal de sortie
		 
		try {
		  // Init
		  in = new FileInputStream(sourcePath).getChannel();
		  out = new FileOutputStream(destPath).getChannel();
		 
		  // Copie depuis le in vers le out
		  
		  in.transferTo(0, in.size(), out);
		} catch (Exception e) {
		  e.printStackTrace(); // n'importe quelle exception
		} finally { // finalement on ferme
		  if(in != null) {
		  	try {
			  in.close();
			} catch (IOException e) {}
		  }
		  if(out != null) {
		  	try {
			  out.close();
			} catch (IOException e) {
				return false;
			}
		  }
		}
		return true;
	}
/**
 * cette fonction supprime les virgules et les crochés
 * @param line
 * @return String
 */
	public static String supprimerChar(String line)  {
		 
		 
		String a=line.replace( ',' ,' ' );
		String b=a.replace('[', ' ');
		String c=b.replace(']', ' ');
		 return c;
	}
	/**
	 * 
	 * cette fonction transforme une String vers un tableau d'entiers
	 * @param lin
	 * @return
	 */

	public static int[] tointTabWb(String lin){
 		int[] b = new int[16];
		int j = 0;
		String aux = "";
		String li=supprimerChar(lin);
		for (int i = 0; i < li.length(); i++) {
 			if (li.charAt(i) != ' ' && li.charAt(i) != ','  ) {
 				aux += li.charAt(i);

			} else {
 				if (aux != "") {
					b[j] = (Integer.parseInt(aux));
					j++;
 					aux = "";
				}
			}

		}

		if (aux != "") {
			b[16] = (Integer.parseInt(aux));

			aux = "";
		}
 		return b;
	}
	/**
	 * 
	 * cette fonction transforme une String vers un tableau d'entiers
	 * @param lin
	 * @return
	 */
	public static int[] tointTabRg(String lin){
 		int[] b = new int[8];
		int j = 0;
		String aux = "";
		String li=supprimerChar(lin);
		for (int i = 0; i < li.length(); i++) {
 			if (li.charAt(i) != ' ' && li.charAt(i) != ','  ) {
 				aux += li.charAt(i);

			} else {
 				if (aux != "") {
					b[j] = (Integer.parseInt(aux));
					j++;
 					aux = "";
				}
			}

		}

		if (aux != "") {
			b[7] = (Integer.parseInt(aux));

			aux = "";
		}
 		return b;
	
}
	 /**
	  * cette fonction transforme  
	  */


	public static float calculerDistanceIntersection(ObjetImage img,
			Signatures sig) {
		 
		return 0;
	}


	 /////
	// calcule de la distance eucludienne entre les deux valeurs
		public static double calculerDistanceEuclidienne(ObjetImage oi1, Signatures sig) {
			double chi2Res = 0;
			float chi2ResRg = 0;
			float chi2ResBy = 0;
			float chi2ResWb = 0;
			 
			double ros = 0, ros2 = 0, ros3 = 0;
			double rosp = 0,rosp2=0,rosp3=0; 
			for (int i = 0; i < 8; i++) {

				chi2ResRg += calculerchiRg(oi1, sig, i);

			}

			for (int i = 0; i < 16; i++) {

				chi2ResBy += calculerchiBy(oi1, sig, i);
			}

			for (int i = 0; i < 16; i++) {

				chi2ResWb += calculerchiWb(oi1, sig, i);
			}

			ros = (Math.sqrt(chi2ResRg))  ;
			ros2 = (Math.sqrt(chi2ResBy))  ;
			 
			ros3 = (Math.sqrt(chi2ResWb))  ;

			 
					rosp= 1 / (1 + ros);
					rosp2= 1 / (1 + ros2);
					rosp3= 1 / (1 + ros3);
			 

			//chi2Res = (rosp + rosp2 + rosp3) / 3;
			chi2Res = (ros + ros2 + ros3) / 3;
			return chi2Res;
		}

		 

		public static double calculerchiRg(ObjetImage oi1, Signatures sig,
				int elementNum) {

			double soust = 0;

			soust = Math.pow(
					oi1.getTabRgElement(elementNum)
							- sig.getRg()[elementNum] , 2);

			return soust;
		}

		public static double calculerchiBy(ObjetImage oi1, Signatures sig,
				int elementNum) {

			double soust = 0;
			soust = Math.pow(
					oi1.getTabByElement(elementNum)
							- sig.getBy()[elementNum], 2);

			return soust;
		}

		private static double calculerchiWb(ObjetImage oi1, Signatures sig,
				int elementNum) {
			// TODO Auto-generated method stub
			double soust = 0;
			soust = Math.pow(
					oi1.getTabWbElement(elementNum)
							- sig.getWb()[elementNum], 2);

			return soust;
		}

		 

}