package com.t3hh4xx0r.opennestsdk.sdk.objects;

public class TempChange {
	public final boolean target_change_pending = true;
	public double target_temperature;

	public TempChange( double target_temperature) {
		this.target_temperature = target_temperature;
	}
}