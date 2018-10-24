/**
 * 
 */
package com.opngo.parking.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author miguel
 *
 */
public class SessionEndResponse {

	private String status;
	private BigDecimal total;
	private Date startedAt;
	private Date stoppedAt;
	
	public SessionEndResponse(String status, BigDecimal total, Date startedAt, Date stoppedAt) {
		super();
		this.status = status;
		this.total = total;
		this.startedAt = startedAt;
		this.stoppedAt = stoppedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public Date getStoppedAt() {
		return stoppedAt;
	}

	public void setStoppedAt(Date stoppedAt) {
		this.stoppedAt = stoppedAt;
	}

}
