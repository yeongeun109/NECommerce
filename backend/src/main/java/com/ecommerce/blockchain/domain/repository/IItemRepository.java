package com.ecommerce.blockchain.domain.repository;

import com.ecommerce.blockchain.domain.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IItemRepository
{
	List<Item> list();
	List<Item> getByUser(final long userId);
	Item get(long id);

	@Transactional
	long create(Item item);

	@Transactional
	int update(Item item);

	@Transactional
	int delete(long id);
}
