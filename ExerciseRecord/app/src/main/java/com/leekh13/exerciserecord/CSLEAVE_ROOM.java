package com.leekh13.exerciserecord;

import java.nio.ByteBuffer;
  
public class CSLEAVE_ROOM extends Obj { 
	 public long m_UserSN; 
	 public boolean putbuf( ByteBuffer buf){ 
		 buf.putLong( m_UserSN );
		 return true; 
	 }  
	 public boolean getbuf(ByteBuffer buf){ 
		 m_UserSN = buf.getLong();
		 return true; 
	 }  
	 public void clear(){ 
		 m_UserSN = 0;
	 }  
};  
