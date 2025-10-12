# eMatShop
An E - commerce example

### Client
    - TODO: describe the client features

### Server
    - TODO: describe the server features

## Getting Started
Guida per clonare e avviare il progetto eMatShop in locale utilizzando Docker.

- Aprire il terminale e clonare il repository del progetto:
`git clone https://www.github.com/marco-battisti-04/eMatShop.git`

- Eseguire il comando `cd eMatShop` per essere sicuro di essere nella directory corretta (deve essere presente il file docker-compose.yml)

- Una volta posizionato nella directory del progetto, eseguire il comando `docker compose build`, questo comando legge il file docker-compose.yml, costruisce le immagini Docker per ogni microservizio e installa le dipendenze, preparando l'ambiente per ogni container

- Per avviare tutti i microservizi in background eseguire il comando:
`docker compose up -d`

I servizi, una volta avviati, sono accessibili tramite questi URL nel browser:
- REGISTRY DEI MICROSERVIZI: porta 8761 per accedere a eureka http://localhost:8761
- CONFIGURAZIONI CENTRALIZZATE: porta 8888 per accedere a configserver http://localhost:8888
- TRACCIAMENTO CHIAMATE TRA SERVIZI: porta 9411 per accedere a zipkin http://localhost:9411
- API GATEWAY PRINCIPALE: porta 9999 per accedere a shopgateway http://localhost:9999
- GESTIONE DEL CATALOGO PRODOTTI: porta 8082 per accedere a catalog http://localhost:8082
- GESTIONE DEI PAGAMENTI: porta 8900 per accedere a payment http://localhost:8900
- GESTIONE ORDINI E ACQUISTI: porta 7500 per accedere a purchase http://localhost:7500
- GESTIONE UTENTI: porta 8998 per accedere a usermanager http://localhost:8998

- C'è la possibilità di eseguire un singolo microservizio anzichè l'intero progetto, usando il comando: `docker compose build <nome_servizio>`

Catalog Service	    --> `docker compose build catalog`

Payment Service	    --> `docker compose build payment`

Purchase Service    --> `docker compose build purchase`

Shop Gateway        --> `docker compose build shopgateway`

Config Server	    --> `docker compose build configserver`

Eureka Server	    --> `docker compose build eurekaserver`

Zipkin              -->	`docker compose build zipkin`

usermanager         --> `docker compose build usermanager`


MICROSERVIZI FOR DUMMIES:

1. L’inizio: la richiesta dal client

Tutto parte da un client (un browser, un’app, o un altro servizio) che fa una richiesta HTTP get.

2. API Gateway

Il gateway è la porta d’ingresso unica per tutte le richieste esterne.
È come un centralino: riceve la chiamata e decide a quale microservizio inoltrarla.

3. Il microservizio Catalog

Si suddivide in diversi passaggi: Controller, Service e Repository.

- Il controller è il punto di ingresso della richiesta all’interno del microservizio.
È la classe che espone le API.
- Il service decide come ottenere i dati (es. ordinarli, filtrarli).
- Repository serve per contattare il DB.

