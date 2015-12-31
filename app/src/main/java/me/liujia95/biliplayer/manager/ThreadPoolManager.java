package me.liujia95.biliplayer.manager;

/**
 * @项目名: GooglePlay10
 * @包名: org.itheima.googleplay10.manager
 * @类名: ThreadPoolManager
 * @创建者: 肖琦
 * @创建时间: 2015-8-23 下午5:09:38
 * @描述: 线程池的管理者
 * 
 * @svn版本: $Rev: 49 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-08-27 11:35:40 +0800 (Thu, 27 Aug 2015) $
 * @更新描述: TODO
 */
public class ThreadPoolManager
{

	private static ThreadPoolProxy	mLongPool;						// 耗时操作的池子
	private static Object			mLongLock		= new Object();

	private static ThreadPoolProxy	mDownloadPool;					// 下载的池子
	private static Object			mDownloadLock	= new Object();

	/**
	 * 获得耗时操作的池子
	 * 
	 * @return
	 */
	public static ThreadPoolProxy getLongPool()
	{
		if (mLongPool == null)
		{
			synchronized (mLongLock)
			{
				if (mLongPool == null)
				{
					mLongPool = new ThreadPoolProxy(3, 3, 0L);
				}
			}
		}
		return mLongPool;
	}

	/**
	 * 获得下载的池子
	 * 
	 * @return
	 */
	public static ThreadPoolProxy getDownloadPool()
	{
		if (mDownloadPool == null)
		{
			synchronized (mDownloadLock)
			{
				if (mDownloadPool == null)
				{
					mDownloadPool = new ThreadPoolProxy(3, 3, 0L);
				}
			}
		}
		return mDownloadPool;
	}
}
