**ThreadPoolExecutor**
     线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的处理方式让写的同学更加明确线程池的运行规则，避免资源耗尽的风险。
     说明： Executors返回的线程池对象返回的线程池对象的弊端如下 ：
    1、FixedThreadPool 和 SingleThreadPooll: 允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM（）。
    2、CachedThreadPool 和 ScheduledThreadPool  : 允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。

**采用ThreadPoolExecutor方式的优点**
    1、可以实时获取线程池内线程的各种状态
    2、可以动态调整线程池大小

**ThreadPoolExecutor线程池工作原理**
    1、如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
    2、如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
    3、如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
    4、如果线程池中的线程数量大于corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。
