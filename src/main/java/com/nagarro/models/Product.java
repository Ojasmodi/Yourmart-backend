package com.nagarro.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // to remove problem of seller->product->seller->product
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
	@LazyCollection(LazyCollectionOption.FALSE)
	Set<Image> images=new HashSet<>();
	
	String pdfPath;
	
	String prodColor;
	double prodWeight;
	String prodBrand;
	
	String prodStatus;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	
	
	@OneToMany(mappedBy="product",cascade = CascadeType.PERSIST)
	@LazyCollection(LazyCollectionOption.FALSE)
	Set<Comment> comments=new HashSet<>();
	
	@ManyToOne
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
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
	
	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodCode=" + prodCode + ", prodName=" + prodName + ", shortDes="
				+ shortDes + ", longDesc=" + longDesc + ", prodLength=" + prodLength + ", prodBreadth=" + prodBreadth
				+ ", prodHeight=" + prodHeight + ", MRP=" + MRP + ", SSP=" + SSP + ", YMP=" + YMP + ", category="
				+ category + ", images=" + images + ", pdfPath=" + pdfPath + ", prodColor=" + prodColor
				+ ", prodWeight=" + prodWeight + ", prodBrand=" + prodBrand + ", prodStatus=" + prodStatus
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", comments=" + comments + ", seller="
				+ seller + "]";
	}

	
	
	
	
}
