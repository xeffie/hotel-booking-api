# hotel-booking-api
Vi började hela arbetet med att strukturera upp vilka endpoints vi ville ha i swaggerhub:

https://app.swaggerhub.com/apis/mu25/hotel-bookings-api/1.0.0
#

 **POST**    `/auth/login`               -   Logga in och få JWT-token  
 **POST**    `/bookings`                 -   För att kunna skapa en bokning (detta går att göra som USER)                                      
 **GET**     `/bookings/{bookingNumber}` -   Hämta bokning (USER får bara sin egen, ADMIN får alla)  
 **GET**     `/bookings`                 -   Listar alla bokningar (Endast möjligt som ADMIN)                                  
 **DELETE**  `/bookings/{bookingNumber}` -   Ta bort en bokning (Endast möjligt som ADMIN)


 
 **Vi har två typer av användare som är hårdkodade:**

- guest - guest123 (ROLE_USER)

- admin / admin123 (ROLE_ADMIN)


**Hur inloggningen fungerar:**
  
 Vi använder Spring Security med JWT. Spring kollar användarnamn och lösenord och om autentiseringen lyckas så skapas en JWT där Username och roll sparas i själva tokenen och tokenen skickas tillbaka som svar. Klienten får en token vid lyckad inloggning som sedan kan skickas med i varje request.




