--实图：根据角色对账户进行汇总
create view v_account_sum_by_role as
	select acc.coin_id,acc.sub_name, coi.symbol, coi.create_time as publish_time, coi.circulation, pro.id as project_id, pro.project_name as project_name,
                     sum(if(com.role=0,acc.available_amount,0)) as total_of_bank,
                     sum(if(com.role=1,acc.available_amount,0)) as total_of_owner,
                     sum(if(com.role=2,acc.available_amount,0)) as total_of_main,
                     sum(if(com.role=3,acc.available_amount,0)) as total_of_supp
              from account acc
                       join company com on acc.customer_id = com.id
                       join coin coi on coi.id = acc.coin_id
                       join project pro on pro.coin_id = acc.coin_id
              group by acc.coin_id,acc.sub_name, coi.symbol,coi.create_time, coi.circulation,  pro.id, pro.project_name