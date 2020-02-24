package com.craftsoftware.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.craftsoftware.soap.service.BillService;
import com.craftsoftware.Bills.CreateBill;
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
		response.setBill(service.getBillById(Long.parseLong(String.valueOf(request.getId()))));
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Bills", localPart = "DeleteBillRequest")
	@ResponsePayload
	public DeleteBillResponse deleteBillRequest(@RequestPayload DeleteBillRequest request) {
		DeleteBillResponse response = new DeleteBillResponse();
		BillService Bill = new BillService();
		Bill.deleteBill(Long.parseLong(String.valueOf(request.getId())));
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Bills", localPart = "GetAllBillsRequest")
	@ResponsePayload
	public GetAllBillsResponse getAllBillsRequest(@RequestPayload GetAllBillsRequest request) {
		Iterable<Bill> Bills = service.getAllBills();
		GetAllBillsResponse response = new GetAllBillsResponse();
		for (Bill Bill : Bills) {
		 	response.getBills().add(Bill);
		}
		return response;
		
	}
	
	private Bill mapBill(CreateBill Bill) {
		Bill BillDetails = new Bill();
		BillDetails.setId(Bill.getId());
		BillDetails.setTotalValue(Bill.getTotalValue());
		BillDetails.setTotalCost(Bill.getTotalCost());
		return BillDetails;
	}
	private BillUpdateInfo mapBillInfo(Bill Bill) {
		BillUpdateInfo BillDetails = new BillUpdateInfo();
	//	BillDetails.setId(Bill.getId());
	//	BillDetails.setTotalValue(Bill.getTotalValue());
	//	BillDetails.setTotalCost(Bill.getTotalCost());
		return BillDetails;
	}
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Bills", localPart = "UpdateBillRequest")
	@ResponsePayload
	public UpdateBillResponse updateBillRequest(@RequestPayload UpdateBillRequest request) {
		UpdateBillResponse response = new UpdateBillResponse();
		BillUpdateInfo bill = mapBillInfo(request.getBill());
	//	service.udpateBill(bill,Long.parseLong(String.valueOf(request.getBill().getId())));
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/Bills", localPart = "CreateBillRequest")
	@ResponsePayload
	public CreateBillResponse updateBillRequest(@RequestPayload CreateBillRequest request) {
		CreateBillResponse response = new CreateBillResponse();
		service.createBill(mapBill(request.getCreateBill()));
		return response;
		
	}
}
