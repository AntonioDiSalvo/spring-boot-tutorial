versione 0.0.1 :
* progetto Web 
* logging di base
* definizione di un controller REST
  * definire alcuni endpoint:
    * un endpoint GET
    * un endpoint POST
    * un endpoint POST che mappi soltanto alcuni specifici campi
* utilizzo di un aspect per il logging

versione 0.0.2 :
* integrazione persistenza
  * utilizzando MongoDB
* ritornare dei documenti escludendo campi selezionati

versione 0.0.3 :
* rimappare entity in DTO selezionando solo alcuni campi
* gestire delle props da file per ambienti distinti (profili)
  * gestire diversi databases Mongo per profili diversi
* gestire delle props da config-server (secondo convenzione : http://localhost:8888/bootiful/default)

versione 0.0.4 :
* completare il CRUD con delle entita` piu complesse
* autenticazione e autorizzazione con Spring Security
  * autenticazione con sessione per app MVC
  * autenticazione con flusso OAuth
  * centralizzare (annotare) filtro JWT per controller REST

* divisione in branches per i meccanismi di sicurezza analizzati

versione 0.0.5 :
* attivare gli actuator di Spring Boot
* definire un check di Health personalizzato e aggiungerlo al gruppo readiness

versione 0.0.6 :
* pacchettizzazione come Jar eseguibile
* pacchettizazione come immagine Docker
* riportare su Kubernetes

versione 0.0.7 :
* definizione di tests (unitari e di integrazione)