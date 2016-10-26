package com.jwgl.net;

import com.jwgl.util.Constant;

import android.os.Handler;
import android.os.Message;

public class GetTable extends NetThead {

	public GetTable(NetDeal netDeal,Handler handler) {
		super(netDeal,handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		String Table=super.NetThead.getTable();
		
		Message msg = new Message();
		msg.what = Constant.GET_TABLE_SUCCESS;
		msg.obj=Table;
		handler.sendMessage(msg);
		super.run();
	}

}
