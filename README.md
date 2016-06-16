# RDA-PRAGMA-Data-Service

RDA-PRAGMA-Data-Service brings persistent IDs and data object registration of data objects generated from scientific analysis carried out using PRAGMA cloud VMs (http://www.pragma-grid.net/). The data service leverage two recent recommendations from the Research Data Alliance (RDA, https://rd-alliance.org/): Persistent Identifier Information Type (PIT) and Data Type Registry (DTR). The objective of the project is to enhance sharing of data objects specifically from analysis of the Int'l Rice Research Institute (http://irri.org/) but is designed in such a way as to be reusable beyond this project for other cases where VMs are used for analysis.  

The RDA-PRAGMA Data Service is composed of a three-layer architecture:

1. Backend storage layer: a persistent MongoDB service serves as a metadata repository and file archive for data objects;
2. Middleware Layer: Middle layer consists of a RESTful web service which responds to queries and ingest requests, and an instance of the PIT service and DTR service for managing PIDs.  
3. Frontend Layer: consists of UI design and JS scripts which send AJAX call to middle layer web services and display response in users' browser.

The below diagrams describe the interactions of the three components through time.  They are read top to bottom:

![alt tag](https://raw.githubusercontent.com/Data-to-Insight-Center/RDA-PRAGMA-Data-Service/master/docs/DOUpload.png)
                              Fig.1. Data object upload sequence

![alt tag](https://raw.githubusercontent.com/Data-to-Insight-Center/RDA-PRAGMA-Data-Service/master/docs/DORetrieval.png) 
                              Fig. 2. Data object retrieval sequence

![alt tag](https://raw.githubusercontent.com/Data-to-Insight-Center/RDA-PRAGMA-Data-Service/master/docs/MiddlewareService.png) 
                              Fig. 3. Middleware service interaction 
                              


#Installation Guide

## Software Dependencies

1. Apache Maven V3.0 or higher
2. JDK V1.6 or higher
3. Python V2.6 or higher 
4. MongoDB Server V3.0 or higher
5. JavaScript V1.8.0 or higher

## Hardware Requirements

1. This software can be deployed on physical resources or VM instance with public network interface.
2. For public access, it requires 3 ports (backend repo, web service APIs, UIs) which iptables rules allow traffic through the firewall.

##Building the Source
Check Out Source Codes:
```
git clone https://github.com/Data-to-Insight-Center/RDA-PRAGMA-Data-Service.git
```
Install handle.net client package to your local Maven Repository
```
mvn install:install-file -Dfile=Data-Service-server/lib/handle-client.jar -DgroupId=Handle.net -DartifactId=handle-client —Dversion=1.0 -Dpackaging=jar
```

Edit the MongoConfig.xml file found under src/main/resources and set your backend mongoDB uri with username/password if exists.
```
vi Data-Service-server/src/main/resources/MongoConfig.xml
```
Edit the SpringConfig.properties file under src/main/resources; set PIT/DTR uri and set middleware web service server address and port
```
vi Data-Service-server/src/main/resources/SpringConfig.properties
```

Build RDA-PRAGMA Data Service 
```
mvn clean install
```
If you want to skip maven test, run the following cmd:
``` 
mvn clean install -Dmaven.test.skip=true
```

##Deploy Data Service server using nohup
```
nohup java -jar ./target/dataIdentity.server-0.2.0.jar &
```

##Deploy Data Service client frontend layer UI
Configure http server port number -- "PORT"
```
vi Data-Service-client/SimpleServer.py

```

Configure frontend and middleware connections
```
vi Data-Service-client/javascript/config.js

```

Run python SimpleHttpServer with POST enabled
```
nohup python SimpleServer.py &
```

##Contributing
This software release is under ISC licence.

##Release History
* 0.2.0 6th release

  Release note:
    
    1/ APIs to store DO initial upload to staging Database;

    2/ APIs to transfer DO to permanent repository when DO get registered with PID;
    
    3/ CRUD APIs for DOs in staging database (user can upload DO, Read DO, Update DO by data and metadata, delete DO);
    
    4/ Update landing page GUI to present domain metadata and pretty-print json format on web browser end 
    
    5/ Junit test coverage for APIs

* 0.1.5 5th release
* 0.1.2 4th release
* 0.1.1 3rd release
* 0.1.0 2nd release
* 0.0.1 Initial release 








