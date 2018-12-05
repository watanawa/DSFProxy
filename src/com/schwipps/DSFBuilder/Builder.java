package com.schwipps.DSFBuilder;

import com.schwipps.DSFBuilder.enums.DebugDataReadRequestCommand;
import com.schwipps.DSFBuilder.enums.MessageType;
import com.schwipps.DSFBuilder.enums.TargetAgentMode;
import com.schwipps.DSFBuilder.enums.TargetAgentRequestCommand;

public class  Builder {


    public static DSFMessage buildTargetAgentRequestMessage(int instance, int targetAgentId,TargetAgentRequestCommand targetAgentRequestCommand){
        DSFHeader   header      = new DSFHeader(instance, MessageType.TARGET_AGENT_REQUEST_MESSAGE,2,false,2, 0);
        DSFBody     body        = new DSFBodyTargetAgentRequestMessage(targetAgentId, targetAgentRequestCommand);
        DSFMessage  dsfMessage  = new DSFMessage(header,body,null);
        return      dsfMessage;
    }
    //Unused, will only be received
    public static DSFMessage buildTargetAgentDataMessage(int instanceId, long timeStamp, int targetAgentId, byte[] checksum, TargetAgentMode mode, int maxUtilization, int lostClientRequestMessages, int rxBufferSize, int txBufferSize, int maxSamplingFrequency){
        DSFBody body = new DSFBodyTargetAgentDataMessage(   timeStamp,
                                                            targetAgentId,
                                                            checksum,
                                                            mode,
                                                            maxUtilization,
                                                            lostClientRequestMessages,
                                                            rxBufferSize,
                                                            txBufferSize,
                                                            maxSamplingFrequency);

        DSFHeader header = new DSFHeader(instanceId, MessageType.TARGET_AGENT_DATA_MESSAGE, body.getByte().length, false, 2, 0);
        return new DSFMessage(header,body ,null );
    }
    //Unusedm will only be received
    public static DSFMessage buildDebugDataMessage(int instanceId, int timeStamp,int targetAgentId, DSFDebugDataItem[] dsfDebugDataItems){
        DSFBody body = new DSFBodyDebugDataMessage(timeStamp, targetAgentId, dsfDebugDataItems);
        DSFHeader header = new DSFHeader(instanceId, MessageType.DEBUG_DATA_MESSAGE, body.getByte().length, false, 2, 0);

        return new DSFMessage(header, body, null);
    }

    private static int instanceNo = 1;
    public static DSFMessage buildDebugDataWriteRequest(int targetAgentId, DSFDebugDataItem[] dsfDebugDataItems){
        DSFBody     body        = new DSFBodyDebugDataWriteRequest(targetAgentId, dsfDebugDataItems );
        DSFHeader   header      = new DSFHeader(instanceNo++, MessageType.DEBUG_DATA_WRITE_REQUEST_MESSAGE,body.getByte().length,false,2, 0);
        //DSFFooter   footer      = new DSFFooter(body.getChecksum()+header.getChecksum(), 2);
        DSFMessage  dsfMessage  = new DSFMessage(header,body,null);
        return      dsfMessage;
    }

    public static DSFMessage buildDebugDataReadRequest(int targetAgentId, DebugDataReadRequestCommand command, DSFDebugDataItem[] dsfDebugDataItems){
        DSFBody     body        = new DSFBodyDebugDataReadRequest(targetAgentId, command, dsfDebugDataItems);
        DSFHeader   header       = new DSFHeader(instanceNo++, MessageType.DEBUG_DATA_READ_REQUEST_MESSAGE,body.getByte().length,false,2, 0);
        //DSFFooter   footer      = new DSFFooter(body.getChecksum()+header.getChecksum(), 2);
        DSFMessage  dsfMessage  = new DSFMessage(header,body,null);
        return      dsfMessage;
    }


}
