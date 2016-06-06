package JunitTestCase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Dummy_Table")
public class FakeClass {
	
	@Id
	public int taskID;
	public String taskName;
	
	public FakeClass(int taskID, String taskName){
		this.taskID = taskID;
		this.taskName = taskName;
	}
	
	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	
	

}
