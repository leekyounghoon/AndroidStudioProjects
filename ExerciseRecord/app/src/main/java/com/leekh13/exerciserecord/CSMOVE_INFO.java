package com.leekh13.exerciserecord;

import java.nio.ByteBuffer;
  
public class CSMOVE_INFO extends Obj { 
	 public long m_AppID; 
	 public boolean putbuf( ByteBuffer buf){ 
		 buf.putLong( m_AppID );
		 return true; 
	 }  
	 public boolean getbuf(ByteBuffer buf){ 
		 m_AppID = buf.getLong();
		 return true; 
	 }  
	 public void clear(){ 
		 m_AppID = 0;
	 }  
};  
