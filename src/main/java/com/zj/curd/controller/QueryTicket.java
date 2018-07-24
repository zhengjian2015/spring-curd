package com.zj.curd.controller;

public class QueryTicket extends Thread {
	 
		// 抢票人姓名
		private String username;
	 
		//轮序的次数
		private int loopstep=1;
		//每次间隔的时间(秒)
		private int looptime=1;
	 
		//构造器
		public QueryTicket(String username, int loopstep, int looptime) {
			super();
			this.username = username;
			this.loopstep = loopstep;
			this.looptime = looptime;
		}
	 
	 
		@Override
		public void run() {
			for (int i = 0; i < loopstep; i++) {
				//执行哪个类的哪个方法
				System.out.println("sql查询策略(执行3次每次间隔5秒)");
				System.out.println("_______姓名:"+username+"_第"+(i+1)+"次抢票状态:未抢到");
				try {
					Thread.sleep(looptime*1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		


}
