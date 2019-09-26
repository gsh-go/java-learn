package com.gsh.threadpool.thread;

//线程池任务类
public class threadTask {
	public threadTask(int t, Object o1, Object o2)
	{
		type = t;
		wparam = o1;
		lparam = o2;
	}
		
	public int getType()
	{
		return type;
	}
	
	public Object getWparam()
	{
		return wparam;
	}
	
	public Object getLparam()
	{
		return lparam;
	}
	
	private int type;		//应用自定义任务类别
	private Object wparam; //应用自定义任务参数1
	private Object lparam; //应用自定义任务参数2
}