package  com.craftsoftware.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Component;
import com.dataaccesslayer.entity.LineItem;
import com.dataaccesslayer.entity.Bill;
import com.dataaccesslayer.entity.Product;
import com.dataaccesslayer.repository.LineItemRepository;
import com.dataaccesslayer.repository.BillRepository;
import com.dataaccesslayer.repository.ProductRepository; 
import com.craftsoftware.models.BillUpdateInfo;
import com.craftsoftware.models.ProductInfoForBill; 

@Service
public class BillService {

	 @Autowired
	private BillRepository billRepo;

 	@Autowired
 	private LineItemRepository lineItemRepo;

 
	@Autowired
	private ProductRepository productRepo;

	public enum Status {
		SUCCESS, FAILURE;
	}
	
	private Bill addProductToBill(Long billId, String barCodeId, int quantity) {
	 	Bill o1 = new Bill();
		Product selectedProduct1 = verifyIfProductExists(barCodeId);

		// create line item for a product
		LineItem l1 = new LineItem(selectedProduct1, quantity);
	//	lineItemRepo.save(l1);

		// add lineitem to bill.
		List<LineItem> currentLineItems = o1.getLineItems();
		if (currentLineItems != null) { // There are lineItems in the bill
										// already.
			//logger.debug("There are lineItems in the bill already..Adding to list of items");
			LineItem existingLi = getLineItemWithBarCodeId(barCodeId, currentLineItems);
			if (existingLi == null) {

				o1.getLineItems().add(l1); // there is no line item with
											// existing product
			} else {
				long newQty = existingLi.getQuantity() + quantity;

				existingLi.setQuantity(newQty); // increment the quantity of the
												// product if it already exists
												// in the Bill.
			}

		} else {
			//logger.debug("There are no line items currently in the Bill..Creating new list");
			currentLineItems = new ArrayList<>();
			currentLineItems.add(l1);
			o1.setLineItems(currentLineItems);
		}
		billRepo.save(o1);
		//logger.debug("Product Added Successfully  to Bill : " + l1.getId());
		return o1;
	}

	// Read

	private void computeTotalValues(Bill bill) {

		int noOfItems = 0;
		double totalValue = 0;
		double totalCost = 0;

		if (null != bill.getLineItems()) {
			List<LineItem> lineItems = bill.getLineItems();
			Iterator<LineItem> lineItemsIter = lineItems.iterator();
			while (lineItemsIter.hasNext()) {
				LineItem li = lineItemsIter.next();
				double saleValue = computeValueForItem(li.getQuantity(), li.getProduct().getProductCategory(),
						li.getProduct().getRate());
				//logger.debug("SaleValue &  Line Item  : " + saleValue + "   " + li);
				totalValue += saleValue;
				totalCost += li.getQuantity() * li.getProduct().getRate();
				noOfItems++;
			}
		}
		bill.setNoOfItems(noOfItems);
		bill.setTotalValue(totalValue);
		bill.setTotalCost(totalCost);
		bill.setTotalTax(totalValue - totalCost);
		billRepo.save(bill);
	}

	private double computeValueForItem(long quantity, String productCategory, double rate) {
		//logger.debug("productCategory : " + productCategory + "  quantity = " + quantity + "  rate = " + rate);
		double saleValue = 0;
	 		saleValue = quantity * rate * 1.1; // 10% levy

		 
		return saleValue;
	}

	public Bill createBill(Bill bill) {
		//logger.info("Input recieved to create Bill = " + bill);
		Bill bill1 = billRepo.save(bill);
		//logger.info("Created product with id = " + bill1.getId());
		return bill1;

	}

	public void deleteBill(Long id) {
		verifyBillExists(id);
	//	billRepo.delete(id);
	}

	public Iterable<Bill> getAllBills() {
		Iterable<Bill> bill = billRepo.findAll();
		//logger.info("returning all products");
		return bill;
	}

	public Bill getBillById(Long id) {
		verifyBillExists(id);
		return new Bill();//billRepo.findOne(id);
	}

	// Iterate through line items to get the LineItem with product.
	private LineItem getLineItemWithBarCodeId(String barCodeId, List<LineItem> currentLineItems) {
		for (int i = 0; i < currentLineItems.size(); i++) {
			LineItem li = currentLineItems.get(i);
			if (barCodeId.equals(li.getProduct().getBarCodeId())) {
				// assumes there will only be one item per product. Save method
				// to ensure that there are no duplicates.
				//logger.info(" Line Items has product: " + barCodeId);
				return li;
			}
		}
		//logger.info(" Current list of Line Items do not have product: " + barCodeId);
		return null;
	}

	private Bill removeProductFromBill(Long billId, String barCodeId) {
		Bill o1 =new Bill();// findByBarCodeId(billId);
		List<LineItem> currentLineItems = o1.getLineItems();
		// check if the product exists in product master
		verifyIfProductExists(barCodeId);
		//logger.info("Bar Code Id to be removed  = " + barCodeId);

		if (currentLineItems != null && !currentLineItems.isEmpty()) {
			LineItem lineItem = getLineItemWithBarCodeId(barCodeId, currentLineItems);
			// check if current list of line items have this product.
		 
			//logger.info("line item to be deleted " + lineItem);
			currentLineItems.remove(lineItem);
			o1.setLineItems(currentLineItems);
			billRepo.save(o1);
			// lineItemRepo.delete(lineItem); //delete if it exists
		} else {
			//logger.info("There are no line items currently in the Bill..Cannot remove productId : " + barCodeId);
		 	}
		billRepo.save(o1);
		return o1;
	}

	public Bill updateBill(BillUpdateInfo billUpdateInfo, Long billId) {

	 
		verifyBillExists(billId);

		//logger.info("Processing products to be added");
		if (null != billUpdateInfo.getProductsToBeAdded()) {
			List<ProductInfoForBill> prodToBeAdded = billUpdateInfo.getProductsToBeAdded();
			Iterator<ProductInfoForBill> prodToBeAddedIter = prodToBeAdded.iterator();
			while (prodToBeAddedIter.hasNext()) {
				ProductInfoForBill pInfo = prodToBeAddedIter.next();
				//logger.debug("Product to be added : " + pInfo);
				addProductToBill(billId, pInfo.getBarCodeId(), pInfo.getQuantity());
			}
		}

		//logger.info("Processing products to be removed");
		if (null != billUpdateInfo.getProductsToBeRemoved()) {
			List<ProductInfoForBill> prodToBeRemoved = billUpdateInfo.getProductsToBeRemoved();
			Iterator<ProductInfoForBill> prodToBeRemovedIter = prodToBeRemoved.iterator();
			while (prodToBeRemovedIter.hasNext()) {
				ProductInfoForBill pInfo = prodToBeRemovedIter.next();
				//logger.info("Product to be removed : " + pInfo);
				removeProductFromBill(billId, pInfo.getBarCodeId());
			}
		}

	 	Bill bill =new Bill();// billRepo.findOne(billId);
	//	bill.setBillStatus(billUpdateInfo.getStatus());
		//logger.info("Computing total for the bill");
	//	computeTotalValues(bill);
		return bill;
	}

	private void verifyBillExists(Long id) {
	//	Bill bill = billRepo.findOne(id);
	//	if (bill == null) {
	//		throw new CustomException("Bill with id " + id + " not found");
	//	}
		//logger.info(" Bill exists with an id = " + id);
	}

	private Product verifyIfProductExists(String barCodeId) {
		List<Product> productsByBarCodeID = productRepo.findByBarCodeId(barCodeId);
		if (null == productsByBarCodeID || productsByBarCodeID.isEmpty()) {
			//logger.info("Problem with input data: BarCode ID  " + barCodeId + " does not exist in Product Master");
	 	} else {
			//logger.debug("selectedProduct1  = " + productsByBarCodeID.get(0).getId());
		}
		return productsByBarCodeID.get(0);
	}

	public void udpateBill(BillUpdateInfo bill, long parseLong) {
		// TODO Auto-generated method stub
		
	}

}
