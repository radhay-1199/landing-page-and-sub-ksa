package com.gl.combo.bean;

public class BillingResponse {
	    private int id;
	    private String callingParty;
	    private String serviceId;
	    private String serviceType;
	    private String requestPlan;
	    private String sequenceNo;
	    private String chargeAmount;
	    private String appliedPlan;
	    private String discountPlan;
	    private String validityDays;
	    private String operationId;
	    private String createdon;
	    private String bearerId;
	    private String errorCode;
	    private String result;
	    private String contentId;
	    private String category;
	    private String optParam1;
	    private String optParam2;
	    private String optParam3;
	    private String optParam4;
	    private String optParam5;
	    private int sameDayChurn;
	    private String billerId;
	    private String action;
	    private String publisher;
	    
	    public BillingResponse() {}
	    
	    public BillingResponse(String callingParty,String createdon,String serviceId,String bearerId,String operationId,String appliedPlan,String chargeAmount,String validityDays,String sequennceNo) {
	    	
	    	this.callingParty=callingParty;
	    	this.createdon=createdon;
	    	this.serviceId=serviceId;
	    	this.bearerId=bearerId;
	    	this.operationId=operationId;
	    	this.appliedPlan=appliedPlan;
	    	this.chargeAmount=chargeAmount;
	    	this.validityDays=validityDays;
	    	this.sequenceNo=sequennceNo;
	    }
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCallingParty() {
			return callingParty;
		}
		public void setCallingParty(String callingParty) {
			this.callingParty = callingParty;
		}
		public String getServiceId() {
			return serviceId;
		}
		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}
		public String getServiceType() {
			return serviceType;
		}
		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}
		public String getRequestPlan() {
			return requestPlan;
		}
		public void setRequestPlan(String requestPlan) {
			this.requestPlan = requestPlan;
		}
		public String getSequenceNo() {
			return sequenceNo;
		}
		public void setSequenceNo(String sequenceNo) {
			this.sequenceNo = sequenceNo;
		}
		public String getChargeAmount() {
			return chargeAmount;
		}
		public void setChargeAmount(String chargeAmount) {
			this.chargeAmount = chargeAmount;
		}
		public String getAppliedPlan() {
			return appliedPlan;
		}
		public void setAppliedPlan(String appliedPlan) {
			this.appliedPlan = appliedPlan;
		}
		public String getDiscountPlan() {
			return discountPlan;
		}
		public void setDiscountPlan(String discountPlan) {
			this.discountPlan = discountPlan;
		}
		public String getValidityDays() {
			return validityDays;
		}
		public void setValidityDays(String validityDays) {
			this.validityDays = validityDays;
		}
		public String getOperationId() {
			return operationId;
		}
		public void setOperationId(String operationId) {
			this.operationId = operationId;
		}
		public String getCreatedon() {
			return createdon;
		}
		public void setCreatedon(String createdon) {
			this.createdon = createdon;
		}
		public String getBearerId() {
			return bearerId;
		}
		public void setBearerId(String bearerId) {
			this.bearerId = bearerId;
		}
		public String getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		public String getContentId() {
			return contentId;
		}
		public void setContentId(String contentId) {
			this.contentId = contentId;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getOptParam1() {
			return optParam1;
		}
		public void setOptParam1(String optParam1) {
			this.optParam1 = optParam1;
		}
		public String getOptParam2() {
			return optParam2;
		}
		public void setOptParam2(String optParam2) {
			this.optParam2 = optParam2;
		}
		public String getOptParam3() {
			return optParam3;
		}
		public void setOptParam3(String optParam3) {
			this.optParam3 = optParam3;
		}
		public String getOptParam4() {
			return optParam4;
		}
		public void setOptParam4(String optParam4) {
			this.optParam4 = optParam4;
		}
		public String getOptParam5() {
			return optParam5;
		}
		public void setOptParam5(String optParam5) {
			this.optParam5 = optParam5;
		}
		public int getSameDayChurn() {
			return sameDayChurn;
		}
		public void setSameDayChurn(int sameDayChurn) {
			this.sameDayChurn = sameDayChurn;
		}
		public String getBillerId() {
			return billerId;
		}
		public void setBillerId(String billerId) {
			this.billerId = billerId;
		}
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public String getPublisher() {
			return publisher;
		}
		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
		
		@Override
		public String toString() {
			return "BillingResponse [id=" + id + ", callingParty=" + callingParty + ", serviceId=" + serviceId
					+ ", serviceType=" + serviceType + ", requestPlan=" + requestPlan + ", sequenceNo=" + sequenceNo
					+ ", chargeAmount=" + chargeAmount + ", appliedPlan=" + appliedPlan + ", discountPlan="
					+ discountPlan + ", validityDays=" + validityDays + ", operationId=" + operationId + ", createdon="
					+ createdon + ", bearerId=" + bearerId + ", errorCode=" + errorCode + ", result=" + result
					+ ", contentId=" + contentId + ", category=" + category + ", optParam1=" + optParam1
					+ ", optParam2=" + optParam2 + ", optParam3=" + optParam3 + ", optParam4=" + optParam4
					+ ", optParam5=" + optParam5 + ", sameDayChurn=" + sameDayChurn + ", billerId=" + billerId
					+ ", action=" + action + ", publisher=" + publisher + "]";
		}
	    
	    
}
