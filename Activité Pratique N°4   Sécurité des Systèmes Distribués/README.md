## Activité Pratique N°4 :  Sécurité des Systèmes Distribués


![Capture](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/1986ea23-f869-44aa-941c-e9a969691c73)

### Solution
#### Partie 1 : 
 ###### Architecture 
 ![architecture](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/721b22b7-1ac2-4925-a040-69457a04d055)
##### 1. Télécharger Keycloak 19
 Pour obtenir la version la plus récente de Keycloak, il est possible de la télécharger directement depuis le site officiel de Keycloak [Download](https://www.keycloak.org/) 
##### 2. Démarrer Keycloak
Keycloak est lancé en utilisant la commande suivante   kc.bat start-dev

![start_keycloak](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/8fa58de2-444f-4db3-8fb2-45716a72b55c)
##### 3. Créer un compte Admin
À la première utilisation de la console d’administration, il faut créer un utilisateur administrateur de du serveur keycloak
![create_compte_keycloak](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/d413e909-0722-4fa0-9e61-e372efd0d950)
Ensuite,on a  connecter au compte administrateur.
![login to keycloak](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/b55a9c12-a461-4ee4-95d6-d1bee9263765)

##### 4. Créer une Realm
![wallet-realm](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/7a2968e3-fc0c-42a1-9d87-6d2820dc684a)
![wallet-realm-result](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/fb120e36-2886-4399-a3e3-5afedc4a098d)
##### 5. Créer un client à sécuriser
![create_client](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/96d71f4f-a7b7-495b-9ef6-f68aca7f03e4)
![create_client](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/be45658a-d900-4b53-ba31-2c2e044545e0)
![create_client3](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/caf9c074-8805-424c-adf2-cdb4991cd782)
![create_client4](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/98b906f8-b06a-4d24-be55-493b5948b9a3)

##### 6. Créer des utilisateurs 
![create)user](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/c0b61a16-ca19-4efd-bbd7-8dc480ecea5d)
![reset_password](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/6391e913-d22b-4df1-9b2d-7ffd70ce1693)
![create_user3](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/7ae5dd50-cbea-4235-8dda-12107a92f581)
##### 7. Créer des rôles
 ###### ADMIN
 ![Admin_create](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/e300ff02-f4c2-4441-bd3c-c1cbfe224f3c)
 ###### USER
 ![USER_Create](https://github.com/hassanouado/ENSET_S5_Distributed-Systems/assets/95369534/185a3099-27b5-45bd-9cb0-bdca7ba64347)
##### 8. Affecter les rôles aux utilisateurs
##### 9. Avec PostMan :
   ###### - Tester l'authentification avec le mot de passe
   ###### - Analyser les contenus des deux JWT Access Token et Refresh Token
   ###### - Tester l'authentification avec le Refresh Token
   ###### - Tester l'authentification avec Client ID et Client Secret
   ###### - Changer les paramètres des Tokens Access Token et Refresh Token

#### Partie  2 :
  ###### -Sécuriser avec Keycloak les applications Wallet App
