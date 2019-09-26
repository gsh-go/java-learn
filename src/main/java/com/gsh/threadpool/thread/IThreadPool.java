package com.gsh.threadpool.thread;

//线程池对象的接口
public interface IThreadPool {
	public boolean addTask(threadTask task);
}