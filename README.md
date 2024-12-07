# franquicias-api
API para al gestión de franquicias.

# Ejecutar la aplicación en entorno local

# por medio de consola 
Ubicado en la raíz del proyecto... 
Abre una ventana de comandos (CMD o PowerShell)
Y escribe el siguiente comando:
    ./mvnw spring-boot:run
Al terminar la ejecución del comanodo 
la aplicación debería estar corriendo en la ruta:
    http://localhost:8080/api

# ejecutar jar file 
Ubicado en la raíz del proyecto... 
Ejecuta el siguiente comando: 
    ./mvnw clean package
El cual se encargará de compilar el proyecto 
y generar un jar file en la carpeta target
y dentro de la misma abre una ventana de comandos
y escribre el siguiente comando: 
    java -jar api-0.0.1-SNAPSHOT.jar
Recuerda tener en cuenta al version del runtime del jdk 
21 para el caso de este proyecto
Al terminar la ejecución del comanodo 
la aplicación debería estar corriendo en la ruta:
    http://localhost:8080/api
