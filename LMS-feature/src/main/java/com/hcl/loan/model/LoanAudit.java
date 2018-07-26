package com.hcl.loan.model;


/**
 * @author Shashikanth.p
 *
 */
public class LoanAudit {

	private String auditId;
	private String loanId;
	private String loanStatus;
	private String approvalPendingWith;
	private String remarks;

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getApprovalPendingWith() {
		return approvalPendingWith;
	}

	public void setApprovalPendingWith(String approvalPendingWith) {
		this.approvalPendingWith = approvalPendingWith;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
