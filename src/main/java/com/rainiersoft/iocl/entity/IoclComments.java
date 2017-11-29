package com.rainiersoft.iocl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="iocl_comments")
@NamedQueries({
@NamedQuery(name="IoclComments.findAll", query="SELECT i FROM IoclComments i"),
@NamedQuery(name="findCommentsByType", query="SELECT i FROM IoclComments i where commentType=:commentType")
})
public class IoclComments 
{
	private static final long serialVersionUID = 1010000000000L;
	
	public IoclComments()
	{
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CommentId")
	private int commentId;
	
	@Column(name="CommentName")
	private String commentName;

	@Column(name="CommentType")
	private String commentType;
	
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentName() {
		return commentName;
	}

	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
}