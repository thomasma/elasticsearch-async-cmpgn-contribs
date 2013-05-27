package com.bigdata.elasticsearch.domain;

import java.io.Serializable;

import org.aver.fft.annotations.Column;
import org.aver.fft.annotations.Transform;

/* 
 * cmte_id	1
 * cand_id	2
 * cand_nm	3
 * contbr_nm	4 
 * contbr_city	5
 * contbr_st	6
 * contbr_zip	7
 * contbr_employer	8
 * contbr_occupation	9
 * contb_receipt_amt	10
 * contb_receipt_dt	    11
 * receipt_desc	        12
 * memo_cd	            13
 * memo_text	        14
 * form_tp	            15
 * file_num             16
 * 
 * C00410118,"P20002978","Bachmann, Michelle","HARVEY, WILLIAM","MOBILE","AL","366010290","RETIRED","RETIRED",250,20-JUN-11,"","","","SA17A",736166
 */

/**
 * Represents a contribution by a donor to a candidate.
 * 
 * @author Mathew Thomas
 * 
 */
@Transform(columnSeparator = ",", skipFirstLine = true)
public class Contribution implements Serializable {
	private static final long serialVersionUID = 9189233814578311344L;
	private String id;
	private String cmteId;
	private String candId;
	private String candNm;
	private String contbrNm;
	private String contbrCity;
	private String contbrSt;
	private String contbrZip;
	private String contbrEmployer;
	private String contbrOccupation;
	private double contbReceiptAmt;
	private String contbReceiptDt;
	private String receiptDesc;
	private String memoCd;
	private String memoText;
	private String formTp;
	private String fileNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(position = 1)
	public String getCmteId() {
		return cmteId;
	}

	public void setCmteId(String cmteId) {
		this.cmteId = cmteId;
	}

	@Column(position = 2)
	public String getCandId() {
		return candId;
	}

	public void setCandId(String candId) {
		this.candId = candId;
	}

	@Column(position = 3)
	public String getCandNm() {
		return candNm;
	}

	public void setCandNm(String candNm) {
		this.candNm = candNm;
	}

	@Column(position = 4)
	public String getContbrNm() {
		return contbrNm;
	}

	public void setContbrNm(String contbrNm) {
		this.contbrNm = contbrNm;
	}

	@Column(position = 5)
	public String getContbrCity() {
		return contbrCity;
	}

	public void setContbrCity(String contbrCity) {
		this.contbrCity = contbrCity;
	}

	@Column(position = 6)
	public String getContbrSt() {
		return contbrSt;
	}

	public void setContbrSt(String contbrSt) {
		this.contbrSt = contbrSt;
	}

	@Column(position = 7)
	public String getContbrZip() {
		return contbrZip;
	}

	public void setContbrZip(String contbrZip) {
		this.contbrZip = contbrZip;
	}

	@Column(position = 8)
	public String getContbrEmployer() {
		return contbrEmployer;
	}

	public void setContbrEmployer(String contbrEmployer) {
		this.contbrEmployer = contbrEmployer;
	}

	@Column(position = 9)
	public String getContbrOccupation() {
		return contbrOccupation;
	}

	public void setContbrOccupation(String contbrOccupation) {
		this.contbrOccupation = contbrOccupation;
	}

	@Column(position = 10)
	public double getContbReceiptAmt() {
		return contbReceiptAmt;
	}

	public void setContbReceiptAmt(double contbReceiptAmt) {
		this.contbReceiptAmt = contbReceiptAmt;
	}

	@Column(position = 11)
	public String getContbReceiptDt() {
		return contbReceiptDt;
	}

	public void setContbReceiptDt(String contbReceiptDt) {
		this.contbReceiptDt = contbReceiptDt;
	}

	@Column(position = 12)
	public String getReceiptDesc() {
		return receiptDesc;
	}

	public void setReceiptDesc(String receiptDesc) {
		this.receiptDesc = receiptDesc;
	}

	@Column(position = 13)
	public String getMemoCd() {
		return memoCd;
	}

	public void setMemoCd(String memoCd) {
		this.memoCd = memoCd;
	}

	@Column(position = 14)
	public String getMemoText() {
		return memoText;
	}

	public void setMemoText(String memoText) {
		this.memoText = memoText;
	}

	@Column(position = 15)
	public String getFormTp() {
		return formTp;
	}

	public void setFormTp(String formTp) {
		this.formTp = formTp;
	}

	@Column(position = 16)
	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	@Override
	public String toString() {
		return "Contribution [id=" + id + ", cmteId=" + cmteId + ", candId="
				+ candId + ", candNm=" + candNm + ", contbrNm=" + contbrNm
				+ ", contbrCity=" + contbrCity + ", contbrSt=" + contbrSt
				+ ", contbrZip=" + contbrZip + ", contbrEmployer="
				+ contbrEmployer + ", contbrOccupation=" + contbrOccupation
				+ ", contbReceiptAmt=" + contbReceiptAmt + ", contbReceiptDt="
				+ contbReceiptDt + ", receiptDesc=" + receiptDesc + ", memoCd="
				+ memoCd + ", memoText=" + memoText + ", formTp=" + formTp
				+ ", fileNum=" + fileNum + ", getId()=" + getId()
				+ ", getCmteId()=" + getCmteId() + ", getCandId()="
				+ getCandId() + ", getCandNm()=" + getCandNm()
				+ ", getContbrNm()=" + getContbrNm() + ", getContbrCity()="
				+ getContbrCity() + ", getContbrSt()=" + getContbrSt()
				+ ", getContbrZip()=" + getContbrZip()
				+ ", getContbrEmployer()=" + getContbrEmployer()
				+ ", getContbrOccupation()=" + getContbrOccupation()
				+ ", getContbReceiptAmt()=" + getContbReceiptAmt()
				+ ", getContbReceiptDt()=" + getContbReceiptDt()
				+ ", getReceiptDesc()=" + getReceiptDesc() + ", getMemoCd()="
				+ getMemoCd() + ", getMemoText()=" + getMemoText()
				+ ", getFormTp()=" + getFormTp() + ", getFileNum()="
				+ getFileNum() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
