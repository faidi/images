package Model;

import java.io.InputStream;

public class ImgToWeb {
private InputStream blob;
private String nom;


public ImgToWeb(InputStream blob,String nom){
	this.blob=blob;
	this.nom=nom;
}
public InputStream getBlob() {
	return blob;
}
public void setBlob(InputStream blob) {
	this.blob = blob;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
}
