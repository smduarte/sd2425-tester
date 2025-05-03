package fctreddit.api;
import java.io.File;
import java.net.URI;
import java.util.Objects;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

/**
 * Represents a Post and a Reply in the system
 */
@Entity
public class Post {

	@Id
	@Column(length = 1024)
	private String postId;
	
	@Immutable
	private String authorId;
	@Immutable
	private long creationTimestamp;
	
	@Column(length = 4096)
	private String content;
	
	private String mediaUrl;

	@Immutable
	@Column(length = 1024)
	private String parentUrl; //This should be null when this is a top level post.
	
	@Transient
	private int upVote;
	
	@Transient
	private int downVote;
	
	@JsonIgnore
	@Column(length = 1024)
	private String parentId;
	
	public Post() {
	}
	
	public Post(String authorId, String content) {
		this.postId = null;
		this.authorId = authorId;
		this.content = content;
		this.mediaUrl = null;
		this.parentUrl = null;
		this.upVote = 0;
		this.downVote = 0;
		this.creationTimestamp = System.currentTimeMillis();
	}
	
	public Post(String authorId, String content, String parentUrl) {
		this(authorId, content);
		this.parentUrl = parentUrl;
	}
	
	public Post(String authorId, String content, String parentUrl, String mediaUrl) {
		this(authorId, content, parentUrl);
		this.mediaUrl = mediaUrl;
	}
	
	public Post(String postId, String authorId, long creationTime, String content, String mediaUrl, String parentUrl, int upVote, int downVote) {
		this.postId = postId;
		this.authorId = authorId;
		this.creationTimestamp = creationTime;
		this.content = content;
		this.mediaUrl = mediaUrl;
		this.parentUrl = parentUrl;
		this.upVote = upVote;
		this.downVote = downVote;
		this.parentId = computeParentId();
	}
	
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public long getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public String getParentUrl() {
		return parentUrl;
	}

	public void setParentUrl(String parentUrl) {
		this.parentUrl = parentUrl;
	}

	public int getUpVote() {
		return upVote;
	}

	public void setUpVote(int upVote) {
		this.upVote = upVote;
	}

	public int getDownVote() {
		return downVote;
	}

	public void setDownVote(int downVote) {
		this.downVote = downVote;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(authorId, content, creationTimestamp, downVote, mediaUrl, parentId, parentUrl, postId,
				upVote);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(content, other.content)
				&& creationTimestamp == other.creationTimestamp && downVote == other.downVote
				&& Objects.equals(mediaUrl, other.mediaUrl) && Objects.equals(parentId, other.parentId)
				&& Objects.equals(parentUrl, other.parentUrl) && Objects.equals(postId, other.postId)
				&& upVote == other.upVote;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", authorId=" + authorId + ", creationTimestamp=" + creationTimestamp
				+ ", content=" + content + ", mediaUrl=" + mediaUrl + ", parentUrl=" + parentUrl + ", upVote=" + upVote
				+ ", downVote=" + downVote + ", parentId=" + parentId + "]";
	}

	public boolean notValid() {
		return authorId.isBlank() || content.isBlank();
	}

	public Post updateFrom(Post post) {
		if( post.getMediaUrl() != null ) this.setMediaUrl( post.getMediaUrl() );
		if( post.getContent() != null ) this.setContent( post.getContent() );
		return this;
	}

	public String computeParentId() {
		return parentUrl == null ? null : new File(URI.create(parentUrl).getPath()).getName();
	}

	public Post copyWithVotes(long upVotes, long downVotes) {
		return new Post(
				this.postId,
				this.authorId,
				this.creationTimestamp,
				this.content,
				this.mediaUrl,
				this.parentUrl,
				(int)upVotes,
				(int)downVotes
		);
	}
}
