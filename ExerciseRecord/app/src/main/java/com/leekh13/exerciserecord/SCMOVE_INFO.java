package com.leekh13.exerciserecord;

import java.nio.ByteBuffer;
    
public class SCMOVE_INFO extends Obj { 
	 public long m_AppID; 
	 public POSITION_INFO m_Position = new POSITION_INFO(); 
	 public boolean putbuf( ByteBuffer buf){ 
		 buf.putLong( m_AppID );
		 m_Position.putbuf( buf );
		 return true; 
	 }  
	 public boolean getbuf(ByteBuffer buf){ 
		 m_AppID = buf.getLong();
		 m_Position.getbuf( buf );
		 return true; 
	 }  
	 public void clear(){ 
		 m_AppID = 0;
		 m_Position.clear();
	 }  
};  
