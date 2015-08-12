package com.leekh13.exerciserecord;

import java.nio.ByteBuffer;
    
public class SCLEAVE_ROOM extends Obj { 
	 public short m_ResultCode; 
	 public long m_UserSN; 
	 public boolean putbuf( ByteBuffer buf){ 
		 buf.putShort( m_ResultCode );
		 buf.putLong( m_UserSN );
		 return true; 
	 }  
	 public boolean getbuf(ByteBuffer buf){ 
		 m_ResultCode = buf.getShort();
		 m_UserSN = buf.getLong();
		 return true; 
	 }  
	 public void clear(){ 
		 m_ResultCode = 0;
		 m_UserSN = 0;
	 }  
};  
