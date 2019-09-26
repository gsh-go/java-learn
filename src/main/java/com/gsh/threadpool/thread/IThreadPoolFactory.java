package com.gsh.threadpool.thread;

//线程池工厂接口，创建线程池对象
public interface IThreadPoolFactory {
	public IThreadPool createThreadPool(
			int maxTaskCount, 	//每个线程的任务队列能缓存的最大任务个数
			int threadCount, 		//线程池中的线程个数
			IThreadCallback tcb	//应用的任务处理回调对象，由应用实现，线程池回调
			);
}

//线程池接口使用示例：
//
//任务处理回调类
//public class threadCallback implements IThreadCallback{
//	public void doTask(threadTask task)
//	{
//		...
//	}

//线程池创建
//IThreadPool threads = threadPoolFactory.Instance().createThreadPool(10000, 10, new threadCallback());
//

//往线程池中添加任务
//threadTask task = new threadTask(type, o1, o2);
//threads.addTask(task);

