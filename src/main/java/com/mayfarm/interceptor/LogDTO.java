package com.mayfarm.interceptor;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LogDTO {
	
	private String module, uri, ip;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date logDate;
	
	@Override
	public String toString() {
		return "LogDTO [module=" + module + ", uri=" + uri + ", ip=" + ip + ", logDate=" + logDate + "]";
	}
}
