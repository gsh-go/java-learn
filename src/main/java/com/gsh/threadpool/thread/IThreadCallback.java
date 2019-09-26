package com.gsh.threadpool.thread;

//线程池任务处理回调类，由应用实现，由线程池回调
public interface IThreadCallback {
	public void doTask(threadTask task);
}