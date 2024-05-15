-- item
insert into item (id, code, brand_id, name, item_type_dict, item_sku_id, create_at, create_by, update_at, update_by, version) values (1, '1', 4, '凯唯乐竞赛5号', 'BADMINTON', 1, now(), 1, now(), 1, 1);

-- item_sku
insert into item_sku (id, code, item_id, desc, create_at, create_by, update_at, update_by, version) values (1, '1', 1, NULL, now(), 1, now(), 1, 1);

-- item_price
insert into item_price (id, item_sku_id, price_amt, price_at, create_at, create_by, update_at, update_by, version) values (1, 1, 52.00, '2023-09-04', now(), 1, now(), 1, 1);
insert into item_price (id, item_sku_id, price_amt, price_at, create_at, create_by, update_at, update_by, version) values (2, 1, 70.00, '2024-01-01', now(), 1, now(), 1, 1);
insert into item_price (id, item_sku_id, price_amt, price_at, create_at, create_by, update_at, update_by, version) values (3, 1, 73.00, '2024-02-01', now(), 1, now(), 1, 1);
insert into item_price (id, item_sku_id, price_amt, price_at, create_at, create_by, update_at, update_by, version) values (4, 1, 79.00, '2024-05-15', now(), 1, now(), 1, 1);