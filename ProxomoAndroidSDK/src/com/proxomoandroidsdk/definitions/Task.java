package com.proxomoandroidsdk.definitions;

import java.util.Date;

import com.proxomoandroidsdk.enums.Enums.TaskMethod;
import com.proxomoandroidsdk.enums.Enums.TaskResult;
import com.proxomoandroidsdk.enums.Enums.TaskType;
import com.proxomoandroidsdk.enums.Enums.USTimeZone;

public class Task {
	private boolean Enabled;
	private boolean Friday;
	private int Hour;
	private String ID;
	private Date LastRunUTC;
	private TaskResult LastRunResult;
	private String LastRunMessage;
	private TaskMethod Method;
	private String MethodValue;
	private int Minute;
	private boolean Monday;
	private Date NextRunUTC;
	private String PersonID;
	private boolean Saturday;
	private boolean Sunday;
	private TaskType TaskType;
	private boolean Thursday;
	private USTimeZone TimeZone;
	private boolean Tuesday;
	private boolean Wednesday;

	public boolean isEnabled() {
		return Enabled;
	}

	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}

	public boolean isFriday() {
		return Friday;
	}

	public void setFriday(boolean friday) {
		Friday = friday;
	}

	public int getHour() {
		return Hour;
	}

	public void setHour(int hour) {
		Hour = hour;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Date getLastRunUTC() {
		return LastRunUTC;
	}

	public void setLastRunUTC(Date lastRunUTC) {
		LastRunUTC = lastRunUTC;
	}

	public TaskResult getLastRunResult() {
		return LastRunResult;
	}

	public void setLastRunResult(TaskResult lastRunResult) {
		LastRunResult = lastRunResult;
	}

	public String getLastRunMessage() {
		return LastRunMessage;
	}

	public void setLastRunMessage(String lastRunMessage) {
		LastRunMessage = lastRunMessage;
	}

	public TaskMethod getMethod() {
		return Method;
	}

	public void setMethod(TaskMethod method) {
		Method = method;
	}

	public String getMethodValue() {
		return MethodValue;
	}

	public void setMethodValue(String methodValue) {
		MethodValue = methodValue;
	}

	public int getMinute() {
		return Minute;
	}

	public void setMinute(int minute) {
		Minute = minute;
	}

	public boolean isMonday() {
		return Monday;
	}

	public void setMonday(boolean monday) {
		Monday = monday;
	}

	public Date getNextRunUTC() {
		return NextRunUTC;
	}

	public void setNextRunUTC(Date nextRunUTC) {
		NextRunUTC = nextRunUTC;
	}

	public String getPersonID() {
		return PersonID;
	}

	public void setPersonID(String personID) {
		PersonID = personID;
	}

	public boolean isSaturday() {
		return Saturday;
	}

	public void setSaturday(boolean saturday) {
		Saturday = saturday;
	}

	public boolean isSunday() {
		return Sunday;
	}

	public void setSunday(boolean sunday) {
		Sunday = sunday;
	}

	public TaskType getTaskType() {
		return TaskType;
	}

	public void setTaskType(TaskType taskType) {
		TaskType = taskType;
	}

	public boolean isThursday() {
		return Thursday;
	}

	public void setThursday(boolean thursday) {
		Thursday = thursday;
	}

	public USTimeZone getTimeZone() {
		return TimeZone;
	}

	public void setTimeZone(USTimeZone timeZone) {
		TimeZone = timeZone;
	}

	public boolean isTuesday() {
		return Tuesday;
	}

	public void setTuesday(boolean tuesday) {
		Tuesday = tuesday;
	}

	public boolean isWednesday() {
		return Wednesday;
	}

	public void setWednesday(boolean wednesday) {
		Wednesday = wednesday;
	}
}
