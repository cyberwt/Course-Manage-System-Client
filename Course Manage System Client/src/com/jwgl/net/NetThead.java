package com.jwgl.net;

import android.os.Handler;

/**
 * ´¦ÀíÍøÂçÇëÇó
 * @author Administrator
 *
 */
public class NetThead extends Thread {
	protected NetDeal NetThead;
	protected Handler handler;
	public NetThead(NetDeal netDeal,Handler handler){
		this.NetThead = netDeal;
		this.handler = handler;
	}

}
