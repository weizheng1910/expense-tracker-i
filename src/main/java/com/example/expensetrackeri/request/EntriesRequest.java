package com.example.expensetrackeri.request;

import com.google.gson.annotations.SerializedName;

public class EntriesRequest {

	@SerializedName("activity")
	private String activity;
	@SerializedName("cost")
	private double cost;
	@SerializedName("date")
	private String date;

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
