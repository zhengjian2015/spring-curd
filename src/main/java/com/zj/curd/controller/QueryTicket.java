package com.zj.curd.controller;

public class QueryTicket extends Thread {
	 
		// ��Ʊ������
		private String username;
	 
		//����Ĵ���
		private int loopstep=1;
		//ÿ�μ����ʱ��(��)
		private int looptime=1;
	 
		//������
		public QueryTicket(String username, int loopstep, int looptime) {
			super();
			this.username = username;
			this.loopstep = loopstep;
			this.looptime = looptime;
		}
	 
	 
		@Override
		public void run() {
			for (int i = 0; i < loopstep; i++) {
				//ִ���ĸ�����ĸ�����
				System.out.println("sql��ѯ����(ִ��3��ÿ�μ��5��)");
				System.out.println("_______����:"+username+"_��"+(i+1)+"����Ʊ״̬:δ����");
				try {
					Thread.sleep(looptime*1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		


}
