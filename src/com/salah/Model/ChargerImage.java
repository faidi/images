package com.salah.Model;

import com.salah.Outils.GestionnaireSignature;
import com.salah.Outils.ObjetImage;
import com.salah.Outils.StaticValues;
import com.salah.Outils.Utiles;

 
public class ChargerImage {

	
	public ChargerImage(ObjetImage objetImage){
		super();
		chargerImageBD(objetImage);
	}
	
	public void chargerImageBD(ObjetImage objetImage) {
		
		String cheminDuDossier = objetImage.getPath();
		
		if (cheminDuDossier.endsWith(".jpg") == true  || cheminDuDossier.endsWith(".png") == true) {
			if (Utiles.copyFile(cheminDuDossier,StaticValues.BDD_IMAGES+objetImage.getFileName()+objetImage.getExtention())) {
				ObjetImage oi = new ObjetImage(cheminDuDossier);
				GestionnaireSignature gs = new GestionnaireSignature(oi);
			}
		}
	}
	

}
