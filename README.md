# stars

 1. Чтобы скачать проект с гитхаба запускаем команду:      git clone https://github.com/bakhityar/stars.git
  
 2. Заходим в папку Stars с помощью команды: 	cd Stars
  
 3. Для запуска сервера БД H2 вводим команду : 	java -cp h2-1.4.190.jar org.h2.tools.Server
 Не трогаем это окно. Сервер H2 работает.
  
 4. Запускаем еще одно окно командной строки из папки Stars: 	Shift+ПКМ -> Открыть окно команд
  
 5. Для запуска приложения запускаем команду: 	gradlew bootRun
  
 6. Ждем когда запустится сервер. После запуска сервера, заходим в браузер по адресу: 	http://localhost:8080
  
  И видим результат. Данные для авторизации: 
  
  Логин - user   			
  
  Пароль - password
