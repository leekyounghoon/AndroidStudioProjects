package com.leekh13.exerciserecord;

import java.nio.ByteBuffer;
      
public class USER_INFO extends Obj { 
	 public long m_UserSN; 
	 public byte[] m_NickName = new byte[16]; 
	 public CHAR_INFO m_CharInfo = new CHAR_INFO(); 
	 public boolean putbuf( ByteBuffer buf){ 
		 buf.putLong( m_UserSN );
		 buf.put( m_NickName, 0, 16);
		 m_CharInfo.putbuf( buf );
		 return true; 
	 }  
	 public boolean getbuf(ByteBuffer buf){ 
		 m_UserSN = buf.getLong();
		 buf.get( m_NickName, 0, 16 );
		 m_CharInfo.getbuf( buf );
		 return true; 
	 }  
	 public void clear(){ 
		 m_UserSN = 0;
		 m_CharInfo.clear();
	 }  
};  
