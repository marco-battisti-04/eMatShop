# 🛍️ eMatShop
Un esempio di progetto **E-Commerce** basato su architettura a microservizi.

### 👥 Autori e Collaboratori

Progetto realizzato da:

[![Federico Potrich](https://img.shields.io/badge/GitHub-Federico--Potrich-yellow?logo=github)](https://github.com/federico-potrich)
[![Marco Battisti](https://img.shields.io/badge/GitHub-Marco--Battisti-blue?logo=github)](https://github.com/marco-battisti-04)
[![Davide Comper](https://img.shields.io/badge/GitHub-Davide--Comper-green?logo=github)](https://github.com/Davide-Comper)
[![Graziana Slaifer](https://img.shields.io/badge/GitHub-Graziana--Slaifer-pink?logo=github)](https://github.com/GrazianaSlaifer)
[![Manuela Nardon](https://img.shields.io/badge/GitHub-Manuela--Nardon-red?logo=github)](https://github.com/manuela-nardon)

---

## 🚀 Getting Started
Guida per clonare e avviare il progetto **eMatShop** in locale utilizzando **Docker**.

### 1️⃣ Clona il repository
Clona il progetto con:

```bash
git clone https://www.github.com/marco-battisti-04/eMatShop.git
```

### 2️⃣ Entra nella directory del progetto
```bash
cd eMatShop
```

### 3️⃣ Entra nella cartella del server
```bash
cd server
```

### 4️⃣ Costruisci le immagini Docker
```bash
docker compose build
```

### 5️⃣ Avvia i microservizi
```bash
docker compose up -d
```

### 🌐 Servizi e Porte

| Servizio                       | Porta | URL                                            | Descrizione                             |
| ------------------------------ | ----- | ---------------------------------------------- | --------------------------------------- |
| **Eureka Server (Registry)**   | 8761  | [http://localhost:8761](http://localhost:8761) | Registro dei microservizi               |
| **Config Server**              | 8888  | [http://localhost:8888](http://localhost:8888) | Gestione configurazioni centralizzate   |
| **Zipkin**                     | 9411  | [http://localhost:9411](http://localhost:9411) | Tracciamento delle chiamate tra servizi |
| **API Gateway (Shop Gateway)** | 9999  | [http://localhost:9999](http://localhost:9999) | Punto d’ingresso principale per le API  |
| **Catalog Service**            | 8082  | [http://localhost:8082](http://localhost:8082) | Gestione del catalogo prodotti          |
| **Payment Service**            | 8900  | [http://localhost:8900](http://localhost:8900) | Gestione dei pagamenti                  |
| **Purchase Service**           | 7500  | [http://localhost:7500](http://localhost:7500) | Gestione ordini e acquisti              |
| **User Manager**               | 8998  | [http://localhost:8998](http://localhost:8998) | Gestione utenti                         |

### ⚙️ Avvio di un singolo microservizio
```bash
docker compose build <nome_servizio>
```

Esempi:
```bash
docker compose build catalog
docker compose build payment
docker compose build purchase
docker compose build shopgateway
docker compose build configserver
docker compose build eurekaserver
docker compose build zipkin
docker compose build usermanager
```

## 🧩 Architettura

``` bash
eMatShop/...
├── client/...
|    └── eShop/...
|    |    ├── public/...
|    |    ├── src/...
|    |    ├── .dockerignore
|    |    ├── .editorconfig
|    |    ├── angular.json
|    |    ├── nginx.conf
|    |    ├── package-lock.json
|    |    ├── package.json
|    |    ├── README.md
|    |    ├── tsconfig.app.json
|    |    ├── tsconfig.json
|    |    └── tsconfig.spec.json
├── server/...
|    ├── Catalog/...
|    ├── ConfigurationClient/...
|    ├── ConfigurationServer/...
|    ├── kubernetes/...
|    ├── Payment/...
|    ├── Purchases/...
|    ├── RestTemplate/...
|    ├── ShopEurekaServer/...
|    ├── ShopGateway/...
|    ├── usermanager/...
|    ├── zipkin/...
|    ├── .README.txt.swp
|    ├── docker-compose.yml
|    ├── run.bat
|    └── run.sh
├── .gitattributes
├── .gitignore
├── LICENCE
└── README.md
```