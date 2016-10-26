package com.jwgl.net;

import com.jwgl.util.Constant;

import android.os.Handler;
import android.os.Message;

public class NetLogin extends NetThead {
	
	public NetLogin(NetDeal netDeal,Handler handler) {
		super(netDeal,handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		boolean success=super.NetThead.login();
		if(success){
			Message msg = new Message();
			msg.what = Constant.LOGIN_SUCCESS;
			handler.sendMessage(msg);
		}else{
			Message msg = new Message();
			msg.what = Constant.LOGIN_FAIL;
			handler.sendMessage(msg);
		}
		super.run();
	}

}
