<h1>API USUARIOS-MONGODB</h1>
<hr>
Nuestro grupo a adaptado una api usuarios para usar con MongoDB para el registro de los alumnos y docentes, donde cada uno tendra su usuario propio para entrar a la intranet y ademas cuando se registre un nuevo usuario le llegue un email de su usuario y contraseña.

<hr>
Para poder crear el target primero debemos saber que version de java tenemos.<br>
java --version<br>
<br>
Si tiene una version destinta debe cambiar tanto el dockerfile la version como el pom.xml con la version que tiene.<br>
<br>
Luego para crear el target es aplicar el comando .<br>
mvn clean install

<h1>Tecnologias Utilizadas </h1>

Programa  | Version
------------- | -------------
Netbeans  | 18
Xampp  | 8.1.17
Java jdk  | 18.0.1
Spring Boot plugin | 3.1 
MongoCompass  | Ultima
Postman  | Ultima 

<h1>Dependencias</h1>

| Dependencias  | 
| ------------- |
|Spring Web 
|Spring for MongoDB 
|Lombok  
|Spring for RabbitMQ
|Spring Boot Devtools
|Spring Boot mail
|Jackson



