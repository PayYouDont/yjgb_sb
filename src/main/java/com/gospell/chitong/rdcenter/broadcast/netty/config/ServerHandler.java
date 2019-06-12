/**   
* @Title: ServerHandler.java 
* @Package com.payudon.config 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年9月20日 下午4:33:55 
*/
package com.gospell.chitong.rdcenter.broadcast.netty.config;

import com.gospell.chitong.rdcenter.broadcast.netty.entity.ReturnData;
import com.gospell.chitong.rdcenter.broadcast.netty.util.Crc32Util;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**netty 接收二进制
* @ClassName: ServerHandler 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月20日 下午4:33:55 
*  
*/
public class ServerHandler extends SimpleChannelInboundHandler<Object>{

	/** 
	 * <p>Title: messageReceived</p> 
	 * <p>Description: </p> 
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 * @see SimpleChannelInboundHandler#messageReceived(ChannelHandlerContext, Object)
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月20日 下午4:41:32
	 */
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof byte[]){
            byte[] bytes = (byte[]) msg;
            if(Crc32Util.check (bytes)){
                ReturnData returnData = new ReturnData (bytes);
                returnData.save ();
            }
        }
        //initialize (ctx);
        super.channelActive(ctx);
	}
	@Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println (ctx);
    }
}
