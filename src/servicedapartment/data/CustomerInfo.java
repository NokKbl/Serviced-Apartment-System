package servicedapartment.data;

/**
 * CustomerInfo class is a class for create an object which contains the information of a customer.
 * @author Kunyaruk Katebunlu
 */
public class CustomerInfo {
	private String name;
	private String phone;
	private String email;
	
	/**
	 * Initialize the values in a CustomerInfo object.
	 * @param name is a name of a customer.
	 * @param phone is customer's phone number.
	 * @param email is customer's email address.
	 */
	public CustomerInfo(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * Get the customer's name.
	 * @return A customer's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the customer's phone number.
	 * @return A customer's phone number.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Get the customer's email address.
	 * @return A customer's email address.
	 */
	public String getEmail() {
		return email;
	}
	
}
