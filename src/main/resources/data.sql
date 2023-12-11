insert into registered_users (id,email,username,password,firstname,lastname,city,streetname,streetnumber,role)
values ('76dad340-598d-4b93-8048-96a689a945e2','icex024@gmail.com','prika','$2y$10$5wJzIlBCi6GFOZwGIYTS6ud/jN28.Go.HWxrwKwBjDOXU88yvC4qK','Prika','','','','','ADMIN');
insert into registered_users (id,email,username,password,firstname,lastname,city,streetname,streetnumber,role)
values ('e6dbd222-3ad2-4b62-bcad-347aa6210343','slavko.jevtic.24@gmail.com','manager','$2y$10$xNQaS6TbfIuVLDPLm10xV.Rxv9xydNaPHCJRr5Rj81tjBZM1mPaQq','Prika','','','','','MANAGER');
insert into registered_users (id,email,username,password,firstname,lastname,city,streetname,streetnumber,role)
values ('a4a01b84-969e-11ee-b9d1-0242ac120002','1@gmail.com','customer','$2y$10$ilpzZ9a28y2znNVqyTfXRe6/OXvlCvaT1sjQVZv7eyX35K20rvYL2','customer','','','','','CUSTOMER');
insert into registered_users (id,email,username,password,firstname,lastname,city,streetname,streetnumber,role)
values ('b518556a-d40d-484c-b03b-9aadc3bb42e3','2@gmail.com','driver','$2a$12$KxTetvzx85WbgDoG6sv1auzTJAmcHsm9Ml1uxbHOuWtsVawqjeQ0y','driver','','','','','DELIVERER');

insert into allergens (id,name) values ('304fea88-d90a-4d90-a9db-a273378d7058','Milk');
insert into allergens (id,name) values ('fb3f3ffb-807e-42f0-952d-d59e4f8a7a97','Eggs');
insert into allergens (id,name) values ('c58ca7e3-5806-466e-b548-a8210eb57f29','Fish');
insert into allergens (id,name) values ('d45ce740-ae95-4f26-9fe5-2813979f360c','Crustacean_shellfish');
insert into allergens (id,name) values ('52e82d8d-bb84-4bac-9fd4-f4c1ded18592','Tree_nuts');
insert into allergens (id,name) values ('747f2d1a-befa-4baa-8e79-99cddb46e84f','Peanuts');
insert into allergens (id,name) values ('404a6a23-7c78-468e-921c-f29ac084e891','Wheat');
insert into allergens (id,name) values ('e95de1ca-cc43-4a02-8648-76d06f9fddd8','Soybeans');

insert into ingredients (id,name,meatfree) values ('5af40b2e-67cc-47c3-bded-c904fa7e1be4','Chicken',false);
insert into ingredients (id,name,meatfree) values ('67013b9c-893a-49a1-914c-2da72c210c87','Beef',false);
insert into ingredients (id,name,meatfree) values ('f106574b-3834-4620-94f7-12432ef085b4','Pork',false);
insert into ingredients (id,name,meatfree) values ('78822394-433f-4727-a87d-4da8d5abf0e3','Bacon',false);
insert into ingredients (id,name,meatfree) values ('472dc244-859f-4a00-8f18-35f1de0201b7','Pasta',true);
insert into ingredients (id,name,meatfree) values ('671f2c18-0329-4061-b90f-dc488eed008d','Dough',true);
insert into ingredients (id,name,meatfree) values ('20b9e8af-040b-4dfe-9fb9-4576c0fba7ea','Ketchup',true);
insert into ingredients (id,name,meatfree) values ('fc9b1d42-e374-48f0-8ded-f04bf07c59d1','Mayonnaise',false);
insert into ingredients (id,name,meatfree) values ('b24ea271-2926-4f63-bc60-b9a40a78201d','Pepperoni',false);
insert into ingredients (id,name,meatfree) values ('c8657104-a7ed-47b9-9ae0-159cbc449be3','Cow_cheese',false);
insert into ingredients (id,name,meatfree) values ('c3c3836c-fc95-4c7c-b6c2-db9ab7f007fd','Mozzarella',false);
insert into ingredients (id,name,meatfree) values ('c1f8c0c4-1347-44b4-8542-d8c77bbf39e9','Milk',false);
insert into ingredients (id,name,meatfree) values ('6688dc52-3f79-49d9-9b67-96c21a7c5c39','Parmesan',false);
insert into ingredients (id,name,meatfree) values ('955d37b3-763f-4f0a-b8af-238b1f93a0bd','Pepper',true);
insert into ingredients (id,name,meatfree) values ('1b7f1459-d1d2-40c0-83eb-c217ce5a9aa1','Vanilla',true);
insert into ingredients (id,name,meatfree) values ('968bc701-b100-4aae-8f66-b3085e6789b8','Chocolate',true);
insert into ingredients (id,name,meatfree) values ('54b96433-a9fe-43f9-b0c0-8d1da34803dd','Peanut',true);
insert into ingredients (id,name,meatfree) values ('a9de46c7-c082-40be-aa4c-32052fb5ed64','Nutella',false);
insert into ingredients (id,name,meatfree) values ('21368212-765b-4036-8981-76e5717c1ac8','Strawberries',true);
insert into ingredients (id,name,meatfree) values ('1927b3dc-f977-41be-83e3-9dfd791cb1b3','Walnut',true);
insert into ingredients (id,name,meatfree) values ('70d75cdb-3b02-466b-a7c6-6bfce72ab4fa','Tomato',true);
insert into ingredients (id,name,meatfree) values ('41233dd0-a320-4b44-927d-3a260c6c5978','Onion',true);
insert into ingredients (id,name,meatfree) values ('91bbd515-5ede-4fde-8fe1-5ac066ab2337','Cucumber',true);
insert into ingredients (id,name,meatfree) values ('5fe6af27-0e31-4a16-b3a5-7fc6dd450544','Egg',false);
insert into ingredients (id,name,meatfree) values ('fa468174-d4bf-4ba5-846e-a4fe4fd7e652','Lettuce',true);
insert into ingredients (id,name,meatfree) values ('982997a1-7c22-41e9-abd2-985c158ec838','Cabbage',true);
insert into ingredients (id,name,meatfree) values ('e821173c-bf00-4eab-9cce-92ccb0ede302','Flour',true);
insert into ingredients (id,name,meatfree) values ('a3edece8-13a3-4504-8684-b865f1e4ff95','Potato',true);
insert into ingredients (id,name,meatfree) values ('d409f36a-acf6-4ff8-a820-ae5d845a0455','Fat',true);
insert into ingredients (id,name,meatfree) values ('4c2065c0-7be7-47e5-97c2-79a439cbf0d6','Oil',true);

insert into restaurants (id,name,description,streetname,streetnumber,city,country,worktimestart,worktimeend,numberofslotsformakingfood,freeslots)
values ('0f2fc991-b9fb-4aa7-8951-5965ec0cebb8','Restaurant test','Need to edit','street','street number','city name','country','07:00','22:00',7,7);

insert into menus (id,name,restaurant_id)
values ('5fc2dd31-415c-44c9-8f9b-bfa80c1e746e','Menu test','0f2fc991-b9fb-4aa7-8951-5965ec0cebb8');
-- insert into menus (id,name,restaurant_id)
-- values ('4d0c4407-3d62-41e9-a359-2816b0182ce1','Second menu','0f2fc991-b9fb-4aa7-8951-5965ec0cebb8');

insert into foods (id,name,description,estimatedtimeforpreparationinminutes,foodtype,meatfree,price,menu_id)
values ('03ad4dfc-c7fe-4282-ada2-23ffe116abd6','Roasted pork','nedd to edit',20,'MEAT',false,890.0,'5fc2dd31-415c-44c9-8f9b-bfa80c1e746e');
insert into foods (id,name,description,estimatedtimeforpreparationinminutes,foodtype,meatfree,price,menu_id)
values ('36918170-91f5-46a5-894d-609aa00b590c','Carbonara','nedd to edit',20,'PASTA',false,890.0,'5fc2dd31-415c-44c9-8f9b-bfa80c1e746e');
insert into foods (id,name,description,estimatedtimeforpreparationinminutes,foodtype,meatfree,price,menu_id)
values ('f99320d6-f638-47d4-8b1d-2e10e2e3698e','Pizza pepperoni','nedd to edit',20,'PIZZA',false,890.0,'5fc2dd31-415c-44c9-8f9b-bfa80c1e746e');
insert into foods (id,name,description,estimatedtimeforpreparationinminutes,foodtype,meatfree,price)
values ('108ef0ec-d4a0-4703-874f-eb9f8f9e651c','Chips with mayonnaise','nedd to edit',20,'APPETIZER',false,890.0);
insert into foods (id,name,description,estimatedtimeforpreparationinminutes,foodtype,meatfree,price,menu_id)
values ('76f57c91-c3ea-4bb5-8830-fddcbe3a7c91','Coca cola 1.5l','nedd to edit',0,'DRINK',false,200.0,'5fc2dd31-415c-44c9-8f9b-bfa80c1e746e');

insert into loyalty_definitions (id,restaurant_id,loyalty_type,freedrink_id,discountinpercentage,threshold,reset)
values ('d213a768-5733-4f6f-b9f5-22b4b93c2e0b','0f2fc991-b9fb-4aa7-8951-5965ec0cebb8','DISCOUNT',null,5,6,true);
insert into loyalty_definitions (id,restaurant_id,loyalty_type,freedrink_id,discountinpercentage,threshold,reset)
values ('713186d8-2b26-49b0-be2a-3308fe33d3d7','0f2fc991-b9fb-4aa7-8951-5965ec0cebb8','FREE_DRINK','76f57c91-c3ea-4bb5-8830-fddcbe3a7c91',0,6,true);

insert into foods_ingredients (foods_id,ingredients_id)
values ('03ad4dfc-c7fe-4282-ada2-23ffe116abd6','f106574b-3834-4620-94f7-12432ef085b4');
insert into foods_ingredients (foods_id,ingredients_id)
values ('03ad4dfc-c7fe-4282-ada2-23ffe116abd6','d409f36a-acf6-4ff8-a820-ae5d845a0455');
insert into foods_ingredients (foods_id,ingredients_id)
values ('03ad4dfc-c7fe-4282-ada2-23ffe116abd6','a3edece8-13a3-4504-8684-b865f1e4ff95');
insert into foods_ingredients (foods_id,ingredients_id)
values ('03ad4dfc-c7fe-4282-ada2-23ffe116abd6','fa468174-d4bf-4ba5-846e-a4fe4fd7e652');
insert into foods_ingredients (foods_id,ingredients_id)
values ('36918170-91f5-46a5-894d-609aa00b590c','78822394-433f-4727-a87d-4da8d5abf0e3');
insert into foods_ingredients (foods_id,ingredients_id)
values ('36918170-91f5-46a5-894d-609aa00b590c','472dc244-859f-4a00-8f18-35f1de0201b7');
insert into foods_ingredients (foods_id,ingredients_id)
values ('36918170-91f5-46a5-894d-609aa00b590c','6688dc52-3f79-49d9-9b67-96c21a7c5c39');
insert into foods_ingredients (foods_id,ingredients_id)
values ('36918170-91f5-46a5-894d-609aa00b590c','5fe6af27-0e31-4a16-b3a5-7fc6dd450544');
insert into foods_ingredients (foods_id,ingredients_id)
values ('f99320d6-f638-47d4-8b1d-2e10e2e3698e','671f2c18-0329-4061-b90f-dc488eed008d');
insert into foods_ingredients (foods_id,ingredients_id)
values ('f99320d6-f638-47d4-8b1d-2e10e2e3698e','b24ea271-2926-4f63-bc60-b9a40a78201d');
insert into foods_ingredients (foods_id,ingredients_id)
values ('f99320d6-f638-47d4-8b1d-2e10e2e3698e','c3c3836c-fc95-4c7c-b6c2-db9ab7f007fd');
insert into foods_ingredients (foods_id,ingredients_id)
values ('f99320d6-f638-47d4-8b1d-2e10e2e3698e','20b9e8af-040b-4dfe-9fb9-4576c0fba7ea');
insert into foods_ingredients (foods_id,ingredients_id)
values ('108ef0ec-d4a0-4703-874f-eb9f8f9e651c','a3edece8-13a3-4504-8684-b865f1e4ff95');
insert into foods_ingredients (foods_id,ingredients_id)
values ('108ef0ec-d4a0-4703-874f-eb9f8f9e651c','4c2065c0-7be7-47e5-97c2-79a439cbf0d6');
insert into foods_ingredients (foods_id,ingredients_id)
values ('108ef0ec-d4a0-4703-874f-eb9f8f9e651c','e821173c-bf00-4eab-9cce-92ccb0ede302');
insert into foods_ingredients (foods_id,ingredients_id)
values ('108ef0ec-d4a0-4703-874f-eb9f8f9e651c','fc9b1d42-e374-48f0-8ded-f04bf07c59d1');

insert into ingredients_allergens (ingredients_id,allergens_id)
values ('472dc244-859f-4a00-8f18-35f1de0201b7','404a6a23-7c78-468e-921c-f29ac084e891');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('671f2c18-0329-4061-b90f-dc488eed008d','404a6a23-7c78-468e-921c-f29ac084e891');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('fc9b1d42-e374-48f0-8ded-f04bf07c59d1','fb3f3ffb-807e-42f0-952d-d59e4f8a7a97');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('c8657104-a7ed-47b9-9ae0-159cbc449be3','304fea88-d90a-4d90-a9db-a273378d7058');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('c3c3836c-fc95-4c7c-b6c2-db9ab7f007fd','304fea88-d90a-4d90-a9db-a273378d7058');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('c1f8c0c4-1347-44b4-8542-d8c77bbf39e9','304fea88-d90a-4d90-a9db-a273378d7058');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('6688dc52-3f79-49d9-9b67-96c21a7c5c39','304fea88-d90a-4d90-a9db-a273378d7058');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('54b96433-a9fe-43f9-b0c0-8d1da34803dd','747f2d1a-befa-4baa-8e79-99cddb46e84f');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('a9de46c7-c082-40be-aa4c-32052fb5ed64','304fea88-d90a-4d90-a9db-a273378d7058');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('a9de46c7-c082-40be-aa4c-32052fb5ed64','fb3f3ffb-807e-42f0-952d-d59e4f8a7a97');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('1927b3dc-f977-41be-83e3-9dfd791cb1b3','52e82d8d-bb84-4bac-9fd4-f4c1ded18592');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('5fe6af27-0e31-4a16-b3a5-7fc6dd450544','fb3f3ffb-807e-42f0-952d-d59e4f8a7a97');
insert into ingredients_allergens (ingredients_id,allergens_id)
values ('e821173c-bf00-4eab-9cce-92ccb0ede302','404a6a23-7c78-468e-921c-f29ac084e891');

insert into loyalties(id,loyalty_definition_id,restaurant_id,user_id,number_of_orders)
values ('043fa163-0efc-4e39-b1ae-37b8398cca50','d213a768-5733-4f6f-b9f5-22b4b93c2e0b','0f2fc991-b9fb-4aa7-8951-5965ec0cebb8','a4a01b84-969e-11ee-b9d1-0242ac120002',6);
insert into loyalties(id,loyalty_definition_id,restaurant_id,user_id,number_of_orders)
values ('e7055fdf-6910-401f-b7ae-7cbb15d6097d','713186d8-2b26-49b0-be2a-3308fe33d3d7','0f2fc991-b9fb-4aa7-8951-5965ec0cebb8','a4a01b84-969e-11ee-b9d1-0242ac120002',6);
