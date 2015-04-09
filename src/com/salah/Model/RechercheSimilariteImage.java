package com.salah.Model;

import java.io.File;

import com.salah.Outils.CalculSimilariteObd;
import com.salah.Outils.LectureSignature;
import com.salah.Outils.ObjetImage;
import com.salah.Outils.StaticValues;
 
public class RechercheSimilariteImage {
	private ObjetImage objetImage;
	private float tauxDeSimilarite;
	public RechercheSimilariteImage(ObjetImage objetImage,float tauxDeSimilarite){
		super();
		this.objetImage = objetImage;
		this.tauxDeSimilarite = tauxDeSimilarite;
	}
	
	public void calculerSimilarite(){
		
	}
	
	public void listerRepertoire() {
		File repertoire = new File(StaticValues.BDD_SIGANTURE);
		String[] listefichiers;

		int i;
		listefichiers = repertoire.list();
		for (i = 0; i < listefichiers.length; i++) {
			if (listefichiers[i].endsWith(".txt") == true) {
				LectureSignature lectureSignature = new LectureSignature(StaticValues.BDD_SIGANTURE+listefichiers[i]);
				CalculSimilariteObd calculSimilariteObd = new CalculSimilariteObd(objetImage, lectureSignature);
				if(calculSimilariteObd.getTauxSimlarite() >= this.tauxDeSimilarite){
					System.out.println(listefichiers[i]+" avec un taux de similarit� de: "+calculSimilariteObd.getTauxSimlarite());
					
				}
			}
		}

}
}