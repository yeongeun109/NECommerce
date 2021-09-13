package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.application.IEscrowContractService;
import com.ecommerce.blockchain.application.IPurchaseRecordContractService;
import com.ecommerce.blockchain.application.IPurchaseService;
import com.ecommerce.blockchain.domain.Purchase;
import com.ecommerce.blockchain.domain.PurchaseInfo;
import com.ecommerce.blockchain.domain.exception.EmptyListException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO Sub PJT Ⅲ 과제 3
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PurchaseController {

    public static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private IPurchaseService purchaseService;
    private IPurchaseRecordContractService purchaseRecordContractService;
    private IEscrowContractService escrowContractService;

    @Autowired
    public PurchaseController(IPurchaseService purchaseService,
                              IPurchaseRecordContractService purchaseRecordContractService,
                              IEscrowContractService escrowContractService) {
        Assert.notNull(purchaseService, "purchaseService 개체가 반드시 필요!");
        Assert.notNull(purchaseRecordContractService, "purchaseRecordContractService 개체가 반드시 필요!");
        Assert.notNull(escrowContractService, "escrowContractService 개체가 반드시 필요!");
        this.purchaseService = purchaseService;
        this.purchaseRecordContractService = purchaseRecordContractService;
        this.escrowContractService = escrowContractService;
    }

    /**
     * Purchase 등록
     * @param purchase
     * @return
     */
    @ApiOperation(value = "Register purchase info")
    @RequestMapping(value = "/purchases", method = RequestMethod.POST)
    public Purchase registerPurchase(@RequestBody Purchase purchase) {
        return this.purchaseService.create(purchase);
    }

    /**
     * Purchase 상태 업데이트
     * @param pid purchase id
     * @param state purchase state
     * @return Item
     */
    @ApiOperation(value = "update purchase state")
    @RequestMapping(value = "/purchases/{pid}/state/{state}", method = RequestMethod.PUT)
    public Purchase updateState(@PathVariable int pid, @PathVariable String state) {
        return this.purchaseService.updateState(pid, state);
    }

    /**
     * check deposit
     * @param pid purchase id*
     * @return Item
     */
    @ApiOperation(value = "check if the purchase's buyer paid or not")
    @RequestMapping(value = "/purchases/{pid}/deposit", method = RequestMethod.PUT)
    public Purchase checkDeposit(@PathVariable int pid) {
        return this.escrowContractService.checkDeposit(pid);
    }

    /**
     * 내가 판매한 거래 목록
     * @param id 판매자 id
     */
    @ApiOperation(value = "Fetch purchase list by seller's id")
    @RequestMapping(value = "/purchases/seller/{id}", method = RequestMethod.GET)
    public List<PurchaseInfo> getSellerPurchases(@PathVariable int id){
        List<PurchaseInfo> list = this.purchaseService.getBySeller(id);

        if (list == null || list.isEmpty() )
            throw new EmptyListException("사용자가 판매한/판매 중인 거래가 없습니다.");

        return list;
    }

    /**
     * 내가 구매한 거래 목록
     * @param id 구매자의 id
     */
    @ApiOperation(value = "Fetch purchase list by buyer's id")
    @RequestMapping(value = "/purchases/buyer/{id}", method = RequestMethod.GET)
    public List<PurchaseInfo> getBuyerPurchases(@PathVariable int id){
        List<PurchaseInfo> list = this.purchaseService.getByBuyer(id);

        if (list == null || list.isEmpty() )
            throw new EmptyListException("사용자 구매한/구매 중인 거래가 없습니다.");

        return list;
    }

    /**
     * 구매 이력 조회
     * @param pid purchase id
     * @return List<Record> the list of the purchase records
     */
//	@ApiOperation(value = "Fetch record history of purchase_id")
//	@RequestMapping(value = "/purchases/history/{pid}", method = RequestMethod.GET)
//	public List<Record> getHistory(@PathVariable int pid){
//        Purchase purchase = this.purchaseService.getByPurchaseId(pid);
//        List<Record> records = this.purchaseRecordContractService.getHistory(purchase.getContractAddress());
//        if(records == null || records.isEmpty())
//            throw new EmptyListException("조회된 구매 이력이 없습니다.");
//		return records;
//	}

}
