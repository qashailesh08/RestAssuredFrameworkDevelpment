package api.payloads;

public class StoreModule {
	int id;
	int petId;
	int quantity;
	String shipedate;
	String status;
	boolean compelete;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getShipedate() {
		return shipedate;
	}

	public void setShipedate(String shipedate) {
		this.shipedate = shipedate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isCompelete() {
		return compelete;
	}

	public void setCompelete(boolean compelete) {
		this.compelete = compelete;
	}
}
