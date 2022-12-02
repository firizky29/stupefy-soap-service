# Stupefy REST Service

# Quick Start
1. build 
```
docker-compose build
```
2. up 
```
docker-compose up -d
```
3. up production
```
docker-compose -f docker-compose.yml -f docker-compose.prod.yml up --build
```
4. down 
```
docker-compose down
```
5. migrate 
```
docker exec -it stupefy-soap-service-server-1 ./scripts/migrate.sh
```

## Tugas Besar 2 - IF3110 Pengembangan Perangkat Lunak Berbasis Web<br>
Pengembangan aplikasi berbasis web dengan arsitektur *microservice*

## Author
- Ahmad Romy Zahran (13520009)
- Firizky Ardiansyah (13520095)
- Muhammad Fahmi Irfan (13520152)
  
## 1. Penjelasan Singkat
Stupefy SOAP service merupakan elemen *backend* dari service stupefy yang menyediakan endpoint untuk diambil oleh service lain. Adapun fitur yang diimplementasikan dalam service ini adalah endpoint untuk melakukan *approval/rejection* dari permintaan subscribe, pengecekan status, dan pengelolaan subscription.


## 2. Requirements
1. Docker
2. Java SDK 17
3. Maven
4. WildFly

## 3. Cara Instalasi
1. Clone/Download repository ini.
2. Buatlah file ```.env``` pada root folder, salin field yang perlu diisi dari .env.example (jika sudah terisi, tidak perlu di ubah).
3. Jalankan ```docker compose build```
4. Tunggu hingga semua image berhasil di-*pull*.

## 4. Cara Menjalankan Server 
1. Untuk menjalankan server lakukan dengan perintah
    ```
    docker-compose up -d
    ```
2. Lakukan migrasi dengan menjalankan
```
docker exec -it stupefy-rest-soap-server-1 //bin//bash ./scripts/migrate.sh
```
3. Jika terdapat perubahan pada server, lakukan build package dengan perintah
```
mvn clean package
```

1. Server akan listen pada ```localhost:3101```

## 5. Skema Basis Data
![Screenshot](doc/skema-basisdata.png)

## 6. Pembagian Tugas
| Fitur  | 13520009 | 13520095 | 13520152 |
| --- | --- | --- | --- |
| Logging |  | :white_check_mark: |  |
| API Key |  | :white_check_mark: |  |
| Request Subscribe | :white_check_mark: |  |  |
| Get Requests   | :white_check_mark: |  |  |
| Get Status  |  |  | :white_check_mark: |
| Response Subscribe   | :white_check_mark: |  |  |
| CallBack   | :white_check_mark: |  |  |
| Database   | :white_check_mark: |  |  |

