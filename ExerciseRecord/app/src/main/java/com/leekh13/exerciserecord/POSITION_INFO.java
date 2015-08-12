package com.leekh13.exerciserecord;

import java.nio.ByteBuffer;
        
public class POSITION_INFO extends Obj { 
	 public int m_X; 
	 public int m_Y; 
	 public int m_Z; 
	 public int m_Dir; 
	 public boolean putbuf( ByteBuffer buf){ 
		 buf.putInt( m_X );
		 buf.putInt( m_Y );
		 buf.putInt( m_Z );
		 buf.putInt( m_Dir );
		 return true; 
	 }  
	 public boolean getbuf(ByteBuffer buf){ 
		 m_X = buf.getInt();
		 m_Y = buf.getInt();
		 m_Z = buf.getInt();
		 m_Dir = buf.getInt();
		 return true; 
	 }  
	 public void clear(){ 
		 m_X = 0;
		 m_Y = 0;
		 m_Z = 0;
		 m_Dir = 0;
	 }  
};  
