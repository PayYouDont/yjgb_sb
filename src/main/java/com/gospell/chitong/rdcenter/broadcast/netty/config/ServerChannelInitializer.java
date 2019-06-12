/**   
* @Title: ServerChannelInitializer.java 
* @Package com.payudon.config 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年9月20日 下午4:31:57 
*/
package com.gospell.chitong.rdcenter.broadcast.netty.config;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
* @ClassName: ServerChannelInitializer 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月20日 下午4:31:57 
*  
*/
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel>{

	/**
	 * <p>Title: initChannel</p> 
	 * <p>Description: </p> 
	 * @param socketChannel
	 * @throws Exception
	 * @see ChannelInitializer#initChannel(io.netty.channel.Channel)
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月20日 下午4:32:27
	 */
	@Override
	public void initChannel(SocketChannel socketChannel) throws Exception {
		// 解码编码
        socketChannel.pipeline().addLast(new ByteArrayDecoder ());
        socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
        socketChannel.pipeline().addLast(new ServerHandler());
	}

}
