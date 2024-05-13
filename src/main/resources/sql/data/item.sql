-- item
insert into item (id, code, name, brand_id, item_type_dict, desc, create_at, create_by, update_at, update_by, version) values (1, '1', '凯唯乐竞赛5号', 4, 'BADMINTON', NULL, now(), 1, now(), 1, 1);
insert into item_sku (id, code, item_id, desc, create_at, create_by, update_at, update_by, version) values (1, '1', 1, NULL, now(), 1, now(), 1, 1);
