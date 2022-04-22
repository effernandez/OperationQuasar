# OperationQuasar
OperatioFuegoQuasar es una Api que atravez de tres satelites obtiene la posicion y el mesaje de auxilio de un nave perdida en el espacio.
# Arquitectura de Software
Para el desarrollo de esta Api se utilizo el patron de arquitectura de Sotfware Modelo-Vista-Controlador (MVC) el cual separa los datos de una aplicación, la interfaz de usuario, y la lógica de control en tres componentes distintos.

-Modelo <br>
-Vista <br>
-Controlador

![image](https://user-images.githubusercontent.com/70535212/164609846-7e573901-1c0c-479e-9c5f-02b80d039108.png)

# Lenguaje de Programacion
Java version 8
# Framework
Spring boot
# Ejecucion
La aplicacion se encuentra desplegada en https://operationfuegoquasar.herokuapp.com
* servicio topsecret: el cual recibe como datos de entrada una lista con la informacion de los satelites y retorna el mensaje emitido y la posicion de la nave
     POST → https://operationfuegoquasar.herokuapp.com/topsecret/
     
     este servicio se puede consumir desde un cliente postman de la siguiente manera:
     ![image](https://user-images.githubusercontent.com/70535212/164616392-0dea842e-06e5-4aa4-8ef9-dd191751421d.png)
     
* servicio topsecret_spli post: el cual permite realizar el gurdado y actualizacion de la infromacion por satelite <br>
      POST → https://operationfuegoquasar.herokuapp.com/topsecret_split/{satellite_name}
      
    este servicio se puede consumir desde un cliente postman de la siguiente manera:  
    ![image](https://user-images.githubusercontent.com/70535212/164618031-dc5a0031-82ae-49a7-848e-a047d6140a31.png)
    
* servicio topsecret_spli get: el cual indica la posicion y mensaje en caso que sea posible determinarlo y tener la misma estructura del ejemplo del Nivel 2. Caso contrario, deberá responder un mensaje de error indicando que no hay suficiente información.  <br>
   GET → https://operationfuegoquasar.herokuapp.com/topsecret_split/ 
   
     este servicio se puede consumir desde un cliente postman de la siguiente manera:
    ![image](https://user-images.githubusercontent.com/70535212/164618570-5681f92d-1a98-4dea-9506-fa8e5e19d071.png) <br>





