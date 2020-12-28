package com.packtpub.chapter3;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;

@LocalBean
@Stateless
public class TimerSampleBean {

	@Resource
	private SessionContext ctx;

	public void scheduleTimer(long milliseconds) {
		LocalDate date = LocalDate.now().plus(milliseconds, ChronoUnit.MILLIS);
		ctx.getTimerService().createTimer(date.toEpochDay(), "Hello World");
	}

	@Timeout
	public void timeoutHandler(Timer timer) {
		System.out.println("* Received Timer event: " + timer.getInfo());
		timer.cancel();
	}
}
