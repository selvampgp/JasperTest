package com.jasperTest.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
@Entity
@Table(name="ast_account_status")
public class AccountStatus {
	
	@Id
	@Column(name = "ast_status_id",unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountStatusId;
	
	// @Min(18) @Max(100)
	@Column(name = "ast_number")
	private Long accountNumber;
	
	@Column(name="ast_status_code")
	private String statusCode;
	
	@Column(name="ast_status_description")
	private String statusDescription;
	
	/*@Column(name="ast_status_group")
	private String statusGroup;*/
	

	
	
	@Column(name="ast_status_type")
	private String statusType;
	
	@Column(name="ast_active_flag")
	private String activeFlag;
	
	@Column(name="ast_company_id")
	private Integer companyId;
	
	@Column(name="ast_status")
	private String status;
	
	@Column(name="ast_create_usr_user_id")
	private Integer creatorUserID;
	
//	@DateTimeFormat(iso = ISO.DATE)
 //   @NotNull  @Past
	@Column(name="ast_create_date")
	private Date createDate;
	
	@Column(name="ast_creator_remarks")
	private String creatorRemark;
	
//	@Email
	@Column(name="ast_authorized_usr_user_id")
	private String authorizedUserId;
	
	@Column(name="ast_authorized_date")
	private String authorizedDate;
	
	@Column(name="ast_authorizer_remarks")
	private String authorizerRemark;

	public Integer getAccountStatusId() {
		return accountStatusId;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	
	public String getStatusType() {
		return statusType;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public String getStatus() {
		return status;
	}

	public Integer getCreatorUserID() {
		return creatorUserID;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getCreatorRemark() {
		return creatorRemark;
	}

	public String getAuthorizedUserId() {
		return authorizedUserId;
	}

	public String getAuthorizedDate() {
		return authorizedDate;
	}

	public String getAuthorizerRemark() {
		return authorizerRemark;
	}

	public void setAccountStatusId(Integer accountStatusId) {
		this.accountStatusId = accountStatusId;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCreatorUserID(Integer creatorUserID) {
		this.creatorUserID = creatorUserID;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCreatorRemark(String creatorRemark) {
		this.creatorRemark = creatorRemark;
	}

	public void setAuthorizedUserId(String authorizedUserId) {
		this.authorizedUserId = authorizedUserId;
	}

	public void setAuthorizedDate(String authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public void setAuthorizerRemark(String authorizerRemark) {
		this.authorizerRemark = authorizerRemark;
	}

	
	
	
	
	
	

}
