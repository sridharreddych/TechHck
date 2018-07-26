package com.hcl.loan.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1234L;
	
	public User(){}
	
	public User(Integer userId, String emailId, String password, int roleId) {
		super();
		this.userId = userId;
		this.emailId = emailId;
		this.password = password;
		this.roleId = roleId;
	}
	
	private int userId;
	
	@NotNull
	private String bankAccountNo;
	
	@NotNull
	private String bankIfscCode;
	
	@NotNull
	@Size(min=2, max=50)
	private String bankName;
	
	private BigInteger createdBy;
	
	private Date createdDate;
	
	@NotNull
	@Size(min=2, max=50)
	private String currentEmployer;
	
	@NotNull
	private Date dateofbirth;
	
	@NotNull
	@Email
	private String emailId;
	
	@NotNull
	@Size(min=2, max=50)
	private String firstName;
	
	@NotNull
	private String gender;
	
	@NotNull
	private String hniFlag;
	
	@NotNull
	@Size(min=2, max=50)
	private String lastName;
	
	@NotNull
	private String mobileNumber;
	
	private BigInteger modifiedBy;
	
	private Date modifiedDate;
	
	@NotNull
	private String password;
	
	@NotNull
	private String preferredLang;
	
	@NotNull
	private String repaymentMode;
	
	private String status;
	
	@NotNull
	private List<Address> userAddresses;
	
	private String role;
	
	private boolean isValidUser;
	
	private int roleId;
	
	private static final String DEFAULT_ROLE_ID="1";

	public List<Address> getAddresses() {
		return userAddresses;
	}
	public void setUserAddresses(List<Address> userAddresses) {
		this.userAddresses = userAddresses;
	}
	public String getRole() {
		return DEFAULT_ROLE_ID;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getBankIfscCode() {
		return bankIfscCode;
	}
	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public BigInteger getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCurrentEmployer() {
		return currentEmployer;
	}
	public void setCurrentEmployer(String currentEmployer) {
		this.currentEmployer = currentEmployer;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHniFlag() {
		return hniFlag;
	}
	public void setHniFlag(String hniFlag) {
		this.hniFlag = hniFlag;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public BigInteger getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(BigInteger modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPreferredLang() {
		return preferredLang;
	}
	public void setPreferredLang(String preferredLang) {
		this.preferredLang = preferredLang;
	}
	public String getRepaymentMode() {
		return repaymentMode;
	}
	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isValidUser() {
		return isValidUser;
	}

	public void setValidUser(boolean isValidUser) {
		this.isValidUser = isValidUser;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}