package com.nagarro.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Product {

	@Id
	String prodId;
	
	String prodCode;
	String prodName;
	
	String shortDes;
	String longDesc;
	
	double prodLength;
	double prodBreadth;
	double prodHeight;
	
	double MRP;
	double SSP;
	double YMP;
	
	String category;
	
	
	@OneToMany(mappedBy="product")
	List<Image> images=new ArrayList<>();
	
	String pdfPath;
	
	String prodColor;
	double prodWeight;
	String prodBrand;
	
	String prodStatus;
	
	@OneToMany(mappedBy="product")
	List<Comment> comments=new ArrayList<>();
	
	@ManyToOne
	Seller seller;

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getShortDes() {
		return shortDes;
	}

	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public double getProdLength() {
		return prodLength;
	}

	public void setProdLength(double prodLength) {
		this.prodLength = prodLength;
	}

	public double getProdBreadth() {
		return prodBreadth;
	}

	public void setProdBreadth(double prodBreadth) {
		this.prodBreadth = prodBreadth;
	}

	public double getProdHeight() {
		return prodHeight;
	}

	public void setProdHeight(double prodHeight) {
		this.prodHeight = prodHeight;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public String getProdColor() {
		return prodColor;
	}

	public void setProdColor(String prodColor) {
		this.prodColor = prodColor;
	}


	public double getProdWeight() {
		return prodWeight;
	}

	public void setProdWeight(double prodWeight) {
		this.prodWeight = prodWeight;
	}

	public String getProdBrand() {
		return prodBrand;
	}

	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}

	public String getProdStatus() {
		return prodStatus;
	}

	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}
	

	public double getMRP() {
		return MRP;
	}

	public void setMRP(double mRP) {
		MRP = mRP;
	}

	public double getSSP() {
		return SSP;
	}

	public void setSSP(double sSP) {
		SSP = sSP;
	}

	public double getYMP() {
		return YMP;
	}

	public void setYMP(double yMP) {
		YMP = yMP;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodCode=" + prodCode + ", prodName=" + prodName + ", shortDes="
				+ shortDes + ", longDesc=" + longDesc + ", prodLength=" + prodLength + ", prodBreadth=" + prodBreadth
				+ ", prodHeight=" + prodHeight + ", MRP=" + MRP + ", SSP=" + SSP + ", YMP=" + YMP + ", category="
				+ category + ", images=" + images + ", pdfPath=" + pdfPath + ", prodColor=" + prodColor
				+ ", prodWeight=" + prodWeight + ", prodBrand=" + prodBrand + ", prodStatus=" + prodStatus
				+ ", comments=" + comments + ", seller=" + seller + "]";
	}	
	
	
	
}
