package com.craftsoftware.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.craftsoftware.soap.service.BillService;
import com.craftsoftware.Bills.CreateBillRequest;
import com.craftsoftware.Bills.CreateBillResponse;
import com.craftsoftware.Bills.DeleteBillRequest;
import com.craftsoftware.Bills.DeleteBillResponse;
import com.craftsoftware.Bills.GetAllBillsRequest;
import com.craftsoftware.Bills.GetAllBillsResponse;
import com.craftsoftware.Bills.GetBillRequest;
import com.craftsoftware.Bills.GetBillResponse;
import com.craftsoftware.Bills.UpdateBillRequest;
import com.craftsoftware.Bills.UpdateBillResponse; 
import com.craftsoftware.models.BillUpdateInfo;
import com.dataaccesslayer.entity.Bill;
@Endpoint
public class BillEndpoint {
	
	BillService service = new BillService();

	@PayloadRoot(namespace = "http://www.craftsoftware.com/Bills", localPart = "GetBillRequest")
	@ResponsePayload
	public GetBillResponse processBillDetailsRequest(@RequestPayload GetBillRequest request) {
		GetBillResponse response = new GetBillResponse();	
		response.setBill(service.findBill(request.getId()));
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Bills", localPart = "DeleteBillRequest")
	@ResponsePayload
	public DeleteBillResponse deleteBillRequest(@RequestPayload DeleteBillRequest request) {
		DeleteBillResponse response = new DeleteBillResponse();
		BillService Bill = new BillService();
		Bill.deleteBill(request.getId());
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Bills", localPart = "GetAllBillsRequest")
	@ResponsePayload
	public GetAllBillsResponse getAllBillsRequest(@RequestPayload GetAllBillsRequest request) {
		List<Bill> Bills = service.getAllBills();
		GetAllBillsResponse response = new GetAllBillsResponse();
		for (Bill Bill : Bills) {
			Bill mapBill = mapBill(Bill);
			response.getBills().add(mapBill);
		}
		return response;
		
	}
	
	private Bill mapBill(Bill Bill) {
		Bill BillDetails = new Bill();
		BillDetails.setId(Bill.getId());
		BillDetails.setFist(Bill.getFist());
		BillDetails.setDepartment(Bill.getDepartment());
		return BillDetails;
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Bills", localPart = "UpdateBillRequest")
	@ResponsePayload
	public UpdateBillResponse updateBillRequest(@RequestPayload UpdateBillRequest request) {
		UpdateBillResponse response = new UpdateBillResponse();
		service.udpateBill(request.getBill());
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Bills", localPart = "CreateBillRequest")
	@ResponsePayload
	public CreateBillResponse updateBillRequest(@RequestPayload CreateBillRequest request) {
		CreateBillResponse response = new CreateBillResponse();
		service.createBill(request.getCreateBill());
		return response;
		
	}
}
