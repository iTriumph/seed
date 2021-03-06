package com.jadyer.seed.server.core;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * 组装服务端的编解码器的工厂
 * <p>
 *     暂不提供客户端编解码器，目前系统使用com.jadyer.seed.comm.util.MinaUtil应对客户端场景
 * </p>
 * ----------------------------------------------------------------------------------------------
 * 其内部维护了一个MessageDecoder数组，用于保存添加的所有解码器
 * 每次decode()的时候就调用每个MessageDecoder的decodable()逐个检查
 * 只要发现一个MessageDecoder不是对应的解码器，就从数组中移除，直到找到合适的MessageDecoder
 * 如果最后发现数组为空，就表示没有找到对应的MessageDecoder，最后抛出异常
 * ----------------------------------------------------------------------------------------------
 * Created by 玄玉<http://jadyer.cn/> on 2012/12/22 19:24.
 */
class ServerProtocolCodecFactory extends DemuxingProtocolCodecFactory {
    ServerProtocolCodecFactory(){
        super.addMessageEncoder(String.class, ServerProtocolEncoder.class);
        super.addMessageDecoder(ServerProtocolTCPDecoder.class);
        super.addMessageDecoder(ServerProtocolHTTPDecoder.class);
    }
}