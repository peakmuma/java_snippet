package me.peak;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue<T> {
	
	private Queue<T> queue = new LinkedList<T>();
	private final int capacity;
	
	public MyBlockingQueue(int capacity){
		this.capacity=capacity;
	}
	
	public synchronized T get() throws InterruptedException{
		while(queue.size()==0){
			wait();
		}
		notifyAll();
		return queue.poll();
	}
	
	public synchronized boolean put(T t) throws InterruptedException{
		while(queue.size()==capacity){
			wait();
		}
		notifyAll();
		return queue.add(t);
	}
}
