# 数据查重
select *
from auth_rel_user_role
where (user_id, role_id) in
      (select user_id, role_id
       from auth_rel_user_role
       group by user_id, role_id
       having count(user_id) > 1)

# insert select from
