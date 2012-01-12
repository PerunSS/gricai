package com.cerspri.bitchScheduler.model;

import java.io.Serializable;
import java.util.Date;

public class Bitch implements Serializable,Comparable<Bitch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	public enum State {C_PERIOD, B_AROUND_PERIOD, A_NO_PERIOD};
	
	private int id;
	private String name;
	private Date periodStart;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public State getBitchState() {
		Date curDate = new Date();
		long diff = Math.abs(curDate.getTime() - periodStart.getTime());
		diff /=1000; //milisecconds;
		diff /=60; //seconds;
		diff /=60; //hours;
		diff /=24; //days;
		if((diff+1)%28<5)
			return State.C_PERIOD;
		else if((diff+1)%28<7)
			return State.B_AROUND_PERIOD;
		else if((diff+1)%28<26)
			return State.A_NO_PERIOD;
		else 
			return State.B_AROUND_PERIOD;
	}
	public void setPeriodStart(Date periodStart) {
		this.periodStart = periodStart;
	}
	@Override
	public int compareTo(Bitch another) {
		return getBitchState().compareTo(another.getBitchState());
	}
	public Date getPeriodStart() {
		return periodStart;
	}
	
	
}
