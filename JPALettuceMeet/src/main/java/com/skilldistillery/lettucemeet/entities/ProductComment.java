package com.skilldistillery.lettucemeet.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "product_comment")
public class ProductComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String comment;
	
	@CreationTimestamp
	@Column(name = "create_time")
	private LocalDateTime created;
	
	@UpdateTimestamp
	@Column(name = "update_time")
	private LocalDateTime updated;
	
	@ManyToOne 
	@JoinColumn(name = "comment_id")
	private ProductComment productComment; 
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; 

//	no arg constructor 
	public ProductComment() {
		super();
	}

	public ProductComment(int id, String comment, LocalDateTime created, LocalDateTime updated,
		ProductComment productComment, Product product, User user) {
		super();
		this.id = id;
		this.comment = comment;
		this.created = created;
		this.updated = updated;
		this.productComment = productComment;
		this.product = product;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public ProductComment getProductComment() {
		return productComment;
	}

	public void setProductComment(ProductComment productComment) {
		this.productComment = productComment;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductComment other = (ProductComment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ProductComment [id=" + id + ", created=" + created + ", updated=" + updated + "]";
	}
	
}
