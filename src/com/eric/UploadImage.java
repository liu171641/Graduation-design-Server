package com.eric;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class UploadImage {

	 public static void convertStringtoImage(String encodedImageStr, String fileName) {

	  try {
	   // Base64½âÂëÍ¼Æ¬
	   byte[] imageByteArray = Base64.decode(encodedImageStr);

	   //
	   FileOutputStream imageOutFile = new FileOutputStream("C:/MyEclipseWork/.metadata/.me_tcat7/webapps/admintes/images/" + fileName+".jpg");
	   imageOutFile.write(imageByteArray);

	   imageOutFile.close();

	   System.out.println("Image Successfully Stored");
	  } catch (FileNotFoundException fnfe) {
	   System.out.println("Image Path not found" + fnfe);
	  } catch (IOException ioe) {
	   System.out.println("Exception while converting the Image " + ioe);
	  }
	 }
	}