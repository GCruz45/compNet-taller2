package service;


public interface LocationService {
	public UserOrder getOrder(int id);

	public UserOrder createOrder(UserOrder order);

	public UserOrder updateOrder(UserOrder order);

	public void deleteOrder(UserOrder order);
}
