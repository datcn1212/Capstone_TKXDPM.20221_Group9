package entity;

import entity.data.RentBikeInvoiceDAO;

/**
 * @author hdb
 *
 */
public class RentBikeInvoice {
	/**
	 * rental code
	 */
	private String rentalCode;
	/**
	 * bike code
	 */
	private int bikeCode;
	/**
	 * type of bike
	 */
	private String type;
	/**
	 * rent cost
	 */
	private int rentCost;
	/**
	 * who paid the transaction
	 */
	private String owner;
	/**
	 * rent time
	 */
	private String rentTime;
	/**
	 * return time
	 */
	private String returnTime;
	/**
	 * deposit 
	 */
	private int deposit;

	/**
	 * construction
	 * @param rentalCode
	 * @param bikeCode
	 * @param type
	 * @param owner
	 * @param rentTime
	 * @param deposit
	 */
	public RentBikeInvoice(String rentalCode, int bikeCode, String type, String owner, String rentTime, int deposit) {
		this.rentalCode = rentalCode;
		this.bikeCode = bikeCode;
		this.type = type;
		this.rentCost = 0;
		this.owner = owner;
		this.rentTime = rentTime;
		this.returnTime = null;
		this.deposit = deposit;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getBikeCode() {
		return bikeCode;
	}

	public String getRentalCode() {
		return rentalCode;
	}

	public String getType() {
		return type;
	}

	public String getRentTime() {
		return rentTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public int getRentCost() {
		return rentCost;
	}

	public String getOwner() {
		return owner;
	}

	public void setBikeCode(int bikeCode) {
		this.bikeCode = bikeCode;
	}

	public void setRentalCode(String rentalCode) {
		this.rentalCode = rentalCode;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRentTime(String rentTime) {
		this.rentTime = rentTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public void setRentCost(int rentCost) {
		this.rentCost = rentCost;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * save rent bike invoice in database
	 */
	public void saveRentBikeInvoice() {
		RentBikeInvoiceDAO.save(bikeCode, rentalCode, type, rentTime, returnTime, rentCost, owner, deposit);
	}

	/**
	 * update invoice in database after returning
	 * @param returnTime
	 * @param rentCost
	 */
	public void updateAfterReturnBike(String returnTime, int rentCost) {
		this.returnTime = returnTime;
		this.rentCost = rentCost;
		RentBikeInvoiceDAO.updateAfterReturnBike(rentalCode, rentCost, returnTime);
	}

	/**
	 * @return String: detail of the invoice
	 */
	public String getDetail() {
		String s0 = " Detail of rent bike invoice !";
		String s1 = String.format("  %-30s%-30s", "Rental code", rentalCode);
		String s2 = String.format("  %-30s%-30d", "Bike code", bikeCode);
		String s3 = String.format("  %-30s%-30s", "Type", type);
		String s4 = String.format("  %-30s%-30s", "Owner", owner);
		String s5 = String.format("  %-30s%-30s", "Rent time", rentTime);
		String s6 = String.format("  %-30s%-30s", "Return time", returnTime);
		String s7 = String.format("  %-30s%-30d", "Deposit", deposit);
		String s8 = String.format("  %-30s%-30d", "Rent bike cost", rentCost);

		return s0 + '\n' + s1 + '\n' + s2 + '\n' + s3 + '\n' + s4 + '\n' + s5 + '\n' + s6 + '\n' + s7 + '\n' + s8;
	}
}
