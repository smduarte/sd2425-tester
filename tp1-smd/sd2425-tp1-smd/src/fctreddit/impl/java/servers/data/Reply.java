package fctreddit.impl.java.servers.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Reply {

	@Id 
	String postId;
	
	@Id 
	String parentId;
	
	String topLevelPostIdId;
		
	public Reply() {}
	
	public Reply(String parentId, String postId, long timestamp) {
		this.parentId = parentId;
		this.postId = postId;
	}

	public Reply(String postId, long timestamp) {
		this.postId = postId;
		this.parentId = "";
	}
	
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
	
	
}
