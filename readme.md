jwt.postman_collection.json - коллекция Postman

Был реализован только сервис авторизации :(

Есть регистрация, в которой проверка на корректность email, проверка на совпадения username и email с уже имеющимися

Есть авторизация, которая возможна тогда и только тогда, когда передается правильный username и соответвующий ему пароль. Возвращается инфо + токен

Есть получение информаци о пользователях (возможна только для зарегистрированных пользоватлей). Передается токен + username. Если токен валиден и пользователь с username существует, то будет выведена информация о нем (username, emai, roles)

Есть еще дополнительные тестовые запрсы, просто, чтобы показать, что непосрдественно роли имеют значения (у меня нет второго сервиса, поэтому вот @_@ ):
+ метод доступный даже для НЕ зарегестрированных пользователей (просто GET запрос)
+ методы доступные только для конкретных ролей (отдельный для шефа, для админа и для покупателя)

Как запустить (я вроде попробовал создать все заново и так оно и вправду заработало)

1) создать БД "feedbcker" (был использован Postgres) 
2) запустить jwt-auth (ну и донастроить проперти, при необходимости, как минимум пароль)
3) добавить роли в "feedbacker" в схему "roles":

```
INSERT INTO roles(name) VALUES('ROLE_CUSTOMER');
INSERT INTO roles(name) VALUES('ROLE_CHEF');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```
4) отправить запросы с Postman'а

Примеры запросов и ответов

![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/8edea866-61d3-4a6a-9abe-af74711e9ce9)
![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/25b3c1c1-9ba8-473e-9fde-7c6ebc3fb16b)
![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/3b49f3e5-de2e-4f7e-8d19-7c5fcef041d0)
![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/b1665caa-9bd2-494c-bd45-8edec11a8615)

Передаем токен Васи и получаем ответ

![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/653737de-0334-4325-8c0e-d6acfa476a62)
![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/1c3ea821-ce2b-451f-8e60-b104e962134b)

Вася НЕ имеет роли CUSTOMER, поэтому

![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/56b22aee-a0ce-4757-987f-998a480a9b3d)

О Пете нам ничего не известно, у нас только Вася

![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/a32f10c9-6642-43a9-a3d2-4bf06c09a022)

Соответсвенно он и войти не может

![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/bd376ace-8279-4e6a-a990-71a973e707e5)

В ресторане может быть только один vasya

![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/0b4979ec-e818-4b20-9400-342d406c97b1)

Почты тоже не должны повторяться 

![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/f8517938-a1b3-4bed-a638-ead2bf90d76d)

Почта проверяется

![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/f88fa426-3ad5-4947-893b-06fcadfebc3e)
![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/506eef59-06a0-4d6b-a8e7-d769ac7c10d3)

Чтобы войти необходимо ввести пароль, который был введен при регистрации

![image](https://github.com/andrey-2-4/SoftwareDesign/assets/61345502/5688df36-8d0a-4fac-8f91-c07f28c9cbdc)

