package com.leekh13.exerciserecord;

import java.nio.ByteBuffer;
  
public class SCROOMINFO extends Obj { 
	 public int m_RoomNumber; 
	 public boolean putbuf( ByteBuffer buf){ 
		 buf.putInt( m_RoomNumber );
		 return true; 
	 }  
	 public boolean getbuf(ByteBuffer buf){ 
		 m_RoomNumber = buf.getInt();
		 return true; 
	 }  
	 public void clear(){ 
		 m_RoomNumber = 0;
	 }  
};  
