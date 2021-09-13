package com.ecommerce.blockchain.application.impl;

import com.ecommerce.blockchain.application.IItemService;
import com.ecommerce.blockchain.domain.Item;
import com.ecommerce.blockchain.domain.exception.ApplicationException;
import com.ecommerce.blockchain.domain.repository.IItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService
{
	public static final Logger logger = LoggerFactory.getLogger(ItemService.class);

	private IItemRepository itemRepository;

//	@Autowired
//	public ItemService(IItemRepository itemRepository) {
//		this.itemRepository = itemRepository;
//	}

	@Override
	public List<Item> list()
	{
		return this.itemRepository.list();
	}

	@Override
	public Item get(final long id)
	{
		return this.itemRepository.get(id);
	}

	@Override
	public List<Item> getByUser(int uid) {
		return this.itemRepository.getByUser(uid);
	}

	/**
	 * TODO Sub PJT Ⅲ 과제 3
	 * 상품 등록 시 상품 정보를 저장한다.
	 * @param item
	 * @return Item
	 */
	@Override
	public Item register(final Item item) {
		return null;
	}

	/**
	 * TODO Sub PJT Ⅲ 과제 3
	 * 상품 판매 취소
	 * @param id 상품 id
	 * @return Item
	 */
	@Override
	public Item delete(final long id)
	{
		return null;
	}

	/**
	 * 상품 정보 업데이트
	 * @param item
	 * @return
	 */
	public Item update(final Item item) {
		Item itemStored = this.itemRepository.get(item.getId());
		if (itemStored == null)
			throw new ApplicationException("해당 상품을 찾을 수 없습니다.");

		if (item.getSeller() == 0 || item.getSeller() != itemStored.getSeller())
			throw new ApplicationException("잘못된 접근입니다.");

		if(item.getName() == null || "".equals(item.getName()))
			item.setName(itemStored.getName());
		if(item.getExplanation() == null || "".equals(item.getExplanation()))
			item.setExplanation(itemStored.getExplanation());
		if(item.getAvailable() == null || "".equals(item.getAvailable()))
			item.setAvailable(itemStored.getAvailable());

		int affected = this.itemRepository.update(item);
		if(affected == 0)
			throw new ApplicationException("상품정보수정 처리가 반영되지 않았습니다.");

		return this.itemRepository.get(item.getId());
	}

}
