package com.k1.pack.statesFree;



public class State{
	String name;
	int ID;
	public State(int i,String n) {
		setName(n);
		setID(i);
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
}
