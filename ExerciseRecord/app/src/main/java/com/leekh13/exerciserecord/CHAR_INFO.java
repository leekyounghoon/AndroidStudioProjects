package com.leekh13.exerciserecord;

import java.nio.ByteBuffer;
    
public class CHAR_INFO extends Obj { 
	 public long m_CharSN; 
	 public int m_CharType; 
	 public boolean putbuf( ByteBuffer buf){ 
		 buf.putLong( m_CharSN );
		 buf.putInt( m_CharType );
		 return true; 
	 }  
	 public boolean getbuf(ByteBuffer buf){ 
		 m_CharSN = buf.getLong();
		 m_CharType = buf.getInt();
		 return true; 
	 }  
	 public void clear(){ 
		 m_CharSN = 0;
		 m_CharType = 0;
	 }  
};  
