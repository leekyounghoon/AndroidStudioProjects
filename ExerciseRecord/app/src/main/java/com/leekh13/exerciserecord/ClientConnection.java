package com.leekh13.exerciserecord;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

/**
 * Created by Lee on 2015-08-12.
 */
public class ClientConnection extends Thread {

    private Socket msocket;

    // MEMBER
    private boolean mEnd = true;
    private SocketChannel mSC = null;
    public int mSleep = 1;

    public int m_UserSN = 0;
    public int m_GameDBSN = 1;
    public long m_AppID = 0;
    public int m_UserState = 0;
    public int m_Result = 0;
    public int m_GameServerPort = 0;
    public String m_GameServerIP;

    public boolean m_Connection = false;
    public int m_Level = 0;

    public ClientConnection() { }


    public int Begin(String ip, int port, long AppID, int UserSN) {
        try {
               //InetSocketAddress address = new InetSocketAddress(ip, port);
                //mSC = SocketChannel.open(address);
            mSC = SocketChannel.open( new InetSocketAddress(ip, port) );
            //mSC = SocketChannel.open(new InetSocketAddress(ip, port));



            //InetAddress serverAddr = InetAddress.getByName(ip);
            //Socket socket = new Socket(serverAddr, port);


                m_Connection = true;
                m_AppID = AppID;
                m_UserSN = UserSN;
                m_Result = 1;
            } catch (Exception e) {
                m_Connection = false;
                m_Result = 0;
                return m_Result;
            }

            start();

            return m_Result;
        }

    public void End() {
        mEnd = true;
        try {
            mSC.finishConnect();
            mSC.close();
            m_Connection = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Send(ByteBuffer data) {
        try {
            int s = mSC.write(data);
            // System.out.println(BaseUtil.GetTime() + " " + s);
        } catch (Exception e) {
            e.printStackTrace();
            this.End();
            System.out
                    .println("out!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }


    public void makePacket_Send(short PacketNum, Obj object) {
        // buffer loading
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        buf.putShort((short) 0);
        buf.putShort(PacketNum);
        buf.putInt(0); // serial
        // Context
        object.putbuf(buf);
        short len = (short) (buf.position());
        buf.putShort(0, len);
        buf.flip();

        Send(buf);
    }

    // Run
    public void run() {
        // System.out.println("ClientConnection::run()");
        mEnd = false;
        ByteBuffer bb = ByteBuffer.allocate(4096);

        byte arr[] = new byte[4096];

        bb.order(ByteOrder.LITTLE_ENDIAN);

        while (!mEnd) {
            try {
                if (mSC.read(bb) == -1) {
                    // System.out.println("Err ClientConnection::run() read -1");
                    break;
                }

                CheckPacket(mSC, bb);

                Thread.sleep(1);
            }
            catch ( SocketTimeoutException ste) {
                if( Thread.currentThread().isInterrupted() == true )
                    mEnd = true;
                break;
            }
            catch (Exception e ) {
                // System.out.println("Err ClientConnection::run() read exception");
                e.printStackTrace();
                break;
            }

        }

        try {
            mSC.close();
        }
        catch (Exception e) {
            ;
        }

        bb.clear();
    }

    public void CheckPacket(SocketChannel sc, ByteBuffer bb) {
        bb.flip();
        while (bb.remaining() > 2) {
            bb.mark();
            short len = bb.getShort();
            bb.reset();

            if (bb.remaining() < len) {
                break;
            }

            byte[] data = new byte[len];
            bb.get(data);

            OnRecv(sc, data);
        }
        bb.compact();
    }

    //
    public void OnRecv(SocketChannel sc, byte[] data) {
        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.LITTLE_ENDIAN);

        short len = bb.getShort();
        short msg = bb.getShort();
        int Serial = bb.getInt();

        switch (msg) {
            // case TestProtocol.GC_LOGIN:
            case Protocol.SC_LOGIN:
                Recv_MsgLogin(sc, bb);
                break;
            case Protocol.SC_MAKE_ROOM:
                Recv_MsgMakeRoom( sc, bb);
                break;
            case Protocol.SC_JOIN_ROOM:
                Recv_MsgJoinRoom( sc, bb);
                break;
            case Protocol.SC_LEAVE_ROOM:
                Recv_MsgLeaveRoom( sc, bb);
                break;

            default:
                break;
        }



    }

    public void Recv_MsgLogin(SocketChannel sc, ByteBuffer bb){

        SCLOGIN recvMsg = new SCLOGIN();

        recvMsg.getbuf(bb);

        System.out.printf("[Recv_MsgLogin] Result Code:%d \n", recvMsg.m_ResultCode);

        return;
    }

    public void Recv_MsgJoinRoom(SocketChannel sc, ByteBuffer bb){
        SCJOIN_ROOM msg = new SCJOIN_ROOM();
        msg.getbuf(bb);

        System.out.printf("[Recv_MsgJoinRoom] Result Code:%d \n", msg.m_ResultCode);


    }

    public void Recv_MsgMakeRoom(SocketChannel sc, ByteBuffer bb){
        SCMAKE_ROOM msg = new SCMAKE_ROOM();
        msg.getbuf(bb);

        System.out.printf("[Recv_MsgMakeRoom] Result Code:%d \n", msg.m_ResultCode );

    }

    public void Recv_MsgLeaveRoom(SocketChannel sc, ByteBuffer bb){
        SCLEAVE_ROOM msg = new SCLEAVE_ROOM();

        msg.getbuf(bb);


        System.out.printf("[Recv_MsgLeaveRoom] Result Code:%d UserSN:%d \n",
                msg.m_ResultCode, msg.m_UserSN );

    }

    public void sendLoginRelayServer() {
        CSLOGIN sendmsg = new CSLOGIN();
        sendmsg.m_UserInfo.m_UserSN = m_AppID;
        System.arraycopy("leekh".getBytes(), 0, sendmsg.m_UserInfo.m_NickName, 0, "leekh".length() );

        // sendmsg
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.order(ByteOrder.LITTLE_ENDIAN);

        makePacket_Send( Protocol.CS_LOGIN, sendmsg );

    }

    public void sendMakeRoom(){
        CSMAKE_ROOM sendMsg = new CSMAKE_ROOM();
        sendMsg.m_RoomType =1;

        // sendmsg
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.order(ByteOrder.LITTLE_ENDIAN);

        makePacket_Send( Protocol.CS_MAKE_ROOM, sendMsg );

    }
    public void sendJoinRoom(){
        CSJOIN_ROOM sendMsg = new CSJOIN_ROOM();
        sendMsg.m_RoomType =1;
        sendMsg.m_RoomNumber = 1;

        // sendmsg
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.order(ByteOrder.LITTLE_ENDIAN);

        makePacket_Send( Protocol.CS_JOIN_ROOM, sendMsg );

    }

    public void sendLeaveRoom(){
        CSLEAVE_ROOM sendMsg = new CSLEAVE_ROOM();

        // sendmsg
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.order(ByteOrder.LITTLE_ENDIAN);

        makePacket_Send( Protocol.CS_LEAVE_ROOM, sendMsg );

    }


}
