package com.cvm.service;

public interface ILinkedList<T> {
	public void add(T t);
	public LinkedList sort();
	public Object get(int i);
	public void display();
	public T[] enlarge();
}
