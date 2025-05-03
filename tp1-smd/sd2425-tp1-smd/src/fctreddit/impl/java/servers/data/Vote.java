package fctreddit.impl.java.servers.data;

import java.util.Objects;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vote {
	public static final int UP = 1;
	public static final int NONE = 0;
	public static final int DOWN = -1;

	
	@Id
	String userId;
	@Id
	String postId;
	
	@Immutable
	int sentiment;
		
	public Vote() {
	}
			
	public Vote(String userId, String postId, int sentiment) {
		this.userId = userId;
		this.postId = postId;
		this.sentiment = sentiment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public int getSentiment() {
		return sentiment;
	}

	public void setSentiment(int sentiment) {
		this.sentiment = sentiment;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(postId, sentiment, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		return Objects.equals(postId, other.postId) && sentiment == other.sentiment
				&& Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "Vote [userId=" + userId + ", postId=" + postId + ", sentiment=" + sentiment + "]";
	}
}
