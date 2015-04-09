package servlets;


import java.security.AccessController;
import java.sql.*;
import java.util.ArrayList;
import java.io.*;

import com.salah.Outils.ObjetImage;

 
import com.salah.Outils.Utiles;

import Model.ImgToWeb;
import Model.Signatures;

public class  Database {
	private ArrayList<ImgToWeb> imageListName=new ArrayList<ImgToWeb>() ;
 
	public static String pathBase = "/Users/cash/Desktop/base/";

	public  Database() {
	}
/**
 * établire une connexion
 * @return
 * @throws SQLException
 */
	private Connection getConnection() throws SQLException {
		Connection connection = null;

		String connectionURL = "jdbc:mysql://localhost:8889/test";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			connection = DriverManager.getConnection(connectionURL, "root", "root");
			System.out.println("Connected to database");
		}

		catch (Exception ex) {
			System.out.println("Found some error : " + ex);
		}
		return connection;

	}

	public void saveImg(String img, String nom) throws SQLException {

		 
		PreparedStatement psmnt = null;
		FileInputStream fis;
		try {

			Connection conn = getConnection();
			File image = new File(img);
			ObjetImage imgr = new ObjetImage(img);

			 
			fis = new FileInputStream(image);

			psmnt = conn.prepareStatement("insert into image(name, img, sigrg, sigby, sigwb) "+ "values(?,?,?,?,?)");
			 
			psmnt.setString(1, nom);
 			psmnt.setBinaryStream(2, (InputStream) fis, (int) (image.length()));
  			psmnt.setString(3, imgr.getTabRgS());
			psmnt.setString(4, imgr.getTabWbS());
			psmnt.setString(5, imgr.getTabByS());
 
			int s = psmnt.executeUpdate();
			if (s > 0) {
				System.out.println("Uploaded successfully !");
				conn.close();
				psmnt.close();
			} else {
				System.out.println("unsucessfull to upload image.");
				conn.close();
				psmnt.close();
			}
		}
		// catch if found any exception during rum time.
		catch (Exception ex) {
			System.out.println("Found some error : " + ex);
		}
	}

	
	
	
	public void chargeIMGInFolder(String name, String location) throws Exception {

		FileOutputStream ostreamImage = new FileOutputStream(location + "/"
				+ name);

		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select img from image where name=?");

			try {
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();

				try {
					if (rs.next()) {
						
						InputStream istreamImage = rs.getBinaryStream("img");

						byte[] buffer = new byte[1024];
						int length = 0;

						while ((length = istreamImage.read(buffer)) != -1) {
							ostreamImage.write(buffer, 0, length);
						}

						 

					}
				} finally {
					rs.close();
				}
			} finally {
				ps.close();
			}
		} finally {
			ostreamImage.close();
		}

		System.out.println("download successfully !");
	}

	 

	public ArrayList trouverSimilaire(ObjetImage img) throws SQLException {

		 
		double dist = 0;
		 

		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("select name, sigrg, sigby, sigwb from image");

			try {

				ResultSet rs = ps.executeQuery();
				imageListName.clear();
				try { if(rs.next()){
					 
						do {

							Signatures sig = new Signatures(rs.getString(2), rs.getString(3), rs.getString(4));

							 dist = Utiles.calculerDistanceEuclidienne(img, sig);
							 System.out.println(dist);
							 
							 if (dist != 0) {

								try {
									//this.chargeIMGInFolder(rs.getString(1),
										//	"/Users/cash/Desktop/base");
									
									
									imageListName.add(imagesResultat( rs.getString(1))) ;
 
								 } catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}} 

							 
						} while (rs.next());

					 
				}
				} finally {
					rs.close();
				}
			} finally {
				ps.close();

				conn.close();

			}
		} finally {
		}
		return imageListName;
	}
	public ImgToWeb imagesResultat( String name) throws SQLException {
   ImgToWeb img = null;
		 
		 
		 

		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("select name,img from image where name=?");

			try {
				 
				  ps.setString(1, name);
				 
				ResultSet rs = ps.executeQuery();
				 
				try { if(rs.next()){
					    
							 img=new ImgToWeb(rs.getBinaryStream(2),rs.getString(1) );
							   
 
				}
				} finally {
					rs.close();
				}
			} finally {
				ps.close();

				conn.close();

			}
		} finally {
		}
		return img;
	}
}