package com.digger.util;

import java.io.UnsupportedEncodingException;

public class ConvertCharacter{
	  public String Convert(String string){
	  String result = null;
	  byte[] temp ;
	  try {
		temp = string.getBytes("iso-8859-1");
		result = new String(temp,"utf-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  return result;
	  }
}


