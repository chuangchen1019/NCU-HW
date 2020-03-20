import java.io.Serializable;
import java.util.Date;

public class PostSerializable implements Serializable {
	private String content = "";
	private boolean isLike = false;
	private String editTime;
	
	public PostSerializable() {}
	
	public PostSerializable(String content, boolean isLike, String editTime){
		this.content = content;
		this.isLike = isLike;
		this.editTime = editTime;
	}
	
	public void setContent(String newContent){
		this.content = newContent;
	}
	public void setIsLike(boolean isLike){
		this.isLike = isLike;
	}
	public void setEditTime(String newEditTime){
		this.editTime = newEditTime;
	}
	
	public String getContent(){
		return this.content;
	}
	public boolean getIsLike(){
		return this.isLike;
	}
	public String getEditTime(){
		return this.editTime;
	}	
	
}
