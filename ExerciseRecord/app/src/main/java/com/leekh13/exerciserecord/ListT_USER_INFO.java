package com.leekh13.exerciserecord;

import java.nio.ByteBuffer;
 import java.util.LinkedList;

 	public class ListT_USER_INFO extends LinkedList<USER_INFO> {
 	public void clear(){this.clear();}
 	public boolean putbuf( ByteBuffer buf ){
 		buf.putInt( this.size() );
 		for( int i = 0; i < this.size() ; i++)
 		{
 			(this.get(i)).putbuf( buf );
 		}
 		return true;
 	}

 	public boolean getbuf(  ByteBuffer buf ){
 		int  count;
 		count = buf.getInt();
 		for( int i = 0; i < count ; i++)
 		{
 			USER_INFO Temp = new USER_INFO();
 			Temp.getbuf(buf);
 			push(Temp);
 		}
 		return true;
 	}
 }
