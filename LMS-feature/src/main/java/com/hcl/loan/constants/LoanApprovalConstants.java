package com.hcl.loan.constants;

public class LoanApprovalConstants {

	private LoanApprovalConstants() {
		super();
	}

	public static final String LOAN_AUTO_APPROVED = "Auto Approved";

	public static final String LOAN_AUTO_REJECTED = "Auto Rejected";

	public static final String LOAN_MANUALLY_APPROVED = "Manually Approved";

	public static final String LOAN_MANUALLY_REJECTED = "Manually Rejected";

	public static final String PENDING_WITH_MANAGER = "Pending with Manager";

	public static final String PENDING_WITH_NONE = "None";

	public static final String MANAGER = "Manager";

	public static final String PENDING_STATUS = "pending";
	
	public static final String DISPATCHED_STATUS = "dispatched";
	public static final String STATUS_MAPPING = "/status";
	public static final String STATUS_TYPE_VARIABLE = "/{statustype}/{userId}";
	public static final String CALCULATE_MAPPING = "/calculate";
	public static final String EMI_MAPPING = "/emi";
	public static final int TENURE_FIVE = 5;
	public static final int TENURE_TEN = 10;
	public static final int TENURE_FIFTEEN = 15;
	public static final double TENURE_BENIFITE_TWENTYFIVE = .25;
	public static final double TENURE_BENIFITE_FIFTY = .50;
	public static final double SENIOR_CITEZEN_BENIFITE = .75;

}
