# Architecture_06_21

Реализовать работу предприятия по производству металлических конструкций.

Взаимодействие происходит между сущностями:
+ заказчик;
+ менеджер;
+ инженер;
+ логист.

US1 – заказчик создает заказ. Менеджер формирует заявку на реализацию конструкции и отдает на работу инженеру. Инженер выполняет работу согласно заявке менеджера.  
По готовности заявки логист осуществляет доставку конструкции заказчику, завершает заказ.

FR1 – заказчик может в любой момент просматривать статус заказа.  
FR2 – до взятия заявки в работу заказчик может отменить заказ.

NFR1 – заказчик может иметь сколько угодно заказов.  
NFR2 – менеджер может создавать сколько угодно заявок.  
NFR3 – инженер может иметь не больше 1 заявки в работе.  
NFR4 – логист может обрабатывать сколько угодно заявок.

SC1 - приложение должно являться - web-приложением SPA  
SC2 - использовать базу данных Oracle

Web-приложение SPA выбрано для доступа к приложению как с мобильного так и стационарного устройства

Документ API генерится исходя из параметров прописанных в контроллерах  
Пример сформированного API в [swagger.yaml](https://github.com/extlol/Architecture_06_21/blob/main/swagger.yaml) или при запущенном приложении http://localhost:4255/rest-api.html  
Контент из yaml необходимо вставить на [swagger.io](https://editor.swagger.io/)

Страницы интерфейса находятся в /src/main/resources/templates
