select a.*
from authorities a 
join roles_authorities ra on (a.id = ra.authority_id)
join users_roles ur on (ra.role_id = ur.role_id)
join users u on (ur.user_id = u.id)
where u.id = 1

select exists
(select true as exists from users u 
join users_roles ur on (u.id = ur.user_id)
join roles r on (ur.role_id = r.id) 
join roles_authorities ra on (r.id = ra.role_id) 
join authorities a on (ra.authority_id = a.id) 
where u.username = 'admin' and a.endpoint= substring('PATCH:/api/cliente/1/',0, length(a.endpoint)-1))

select substring('PATCH:/api/cliente/1/',0, length(a.endpoint)-1) from users u 
join users_roles ur on (u.id = ur.user_id)
join roles r on (ur.role_id = r.id) 
join roles_authorities ra on (r.id = ra.role_id) 
join authorities a on (ra.authority_id = a.id) 
where u.username = 'admin' 
 
