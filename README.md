# Log Aggregation for Spring boot application using ELK Stack , Beats and Log4j

Sample Springboot Application demonstrating Log Aggregation using ELK Stack and Log4j. This demonstration does  include log forwarding using Beats.

License : [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)



# Prerequsites:
	JAVA Version = 8 or higher
	Compute : CPU 4
	Memory : 8GB or higher
	Docker Enviornment available
    Docker enviornment with cache clear - network , volumes , images , containers.
    
    
# Preface (Elastic Stack):
Elastic Stack is a group of open source applications from Elastic designed to take data from any source and in any format and then search, analyze, and visualize that data in real time. It was formerly known as ELK Stack, in which the letters in the name stood for the applications in the group: Elasticsearch, Logstash and Kibana. A fourth application, Beats, was subsequently added to the stack, rendering the potential acronym to be unpronounceable. So ELK Stack became Elastic Stack.


### Elasticsearch
Elasticsearch is a real-time, distributed storage, JSON-based search, and analytics engine designed for horizontal scalability, maximum reliability, and easy management. It can be used for many purposes, but one context where it excels is indexing streams of semi-structured data, such as logs or decoded network packets.

### Kibana
Kibana is an open source analytics and visualization platform designed to work with Elasticsearch. Kibana can be used to search, view, and interact with data stored in Elasticsearch indices, allowing advanced data analysis and visualizing data in a variety of charts, tables, and maps.

### Beats
Beats are open source data shippers that can be installed as agents on servers to send operational data directly to Elasticsearch or via Logstash, where it can be further processed and enhanced. There’s a number of Beats for different purposes:

    Filebeat: Log files
    Metricbeat: Metrics
    Packetbeat: Network data
    Heartbeat: Uptime monitoring
    And more.
    As we intend to ship log files, Filebeat will be our choice.

### Logstash
Logstash is a powerful tool that integrates with a wide variety of deployments. It offers a large selection of plugins to help you parse, enrich, transform, and buffer data from a variety of sources. If the data requires additional processing that is not available in Beats, then Logstash can be added to the deployment.'

![alt text](https://github.com/dipsscor/Log-Aggregation-ELK-Stack/blob/master/screenshots/logstash_pipeline.png)


## Architecture:

![alt text](https://github.com/dipsscor/Log-Aggregation-ELK-Stack/blob/master/screenshots/architecture.png) 

## Running on Docker:

![alt text](https://github.com/dipsscor/Log-Aggregation-ELK-Stack/blob/master/screenshots/architecture_2.png) 


## Springboot Application Configuration
The Springboot application logs data using Log4j and transports the logs using <socket-appender> to transport the logs to the Logstash Server. Logstash server applies filters configured and passes the data to Elastic Search.
Kibana is configured to connect to Elastic Search and queries Elastic Serach for logs.


## Springboot application.
The Springboot application is running on port : 12001.

        URL: http://localhost:12001/swagger-ui.html#
        
   ### Log4j2 config
   Log4j2 config file in resources:
   The host mentioned here is docker container for logstash in docker-compose file.
   
    <Configuration status="warn" strict="true" monitorInterval="30">
        ......

        <Appenders>
     
            </Appender>
                <Socket name="socket" host="logstash" port="5000" reconnectionDelayMillis="5000">
                <Layout type="PatternLayout" pattern="${defaultpattern}" />	
            </Socket>
        </Appenders>
        ........
        
    </Configuration>
    
    
## Elastic Search:
Elastic search is installed using docker-compose and available at:
    
        http://localhost:9200/
        
        
        
## Logstash:
Logstash is installed using docker-compose and available at:
    
        http://localhost:9600/   
        
        TCP Port : 5000
        
  ### Logstash pipeline
  
       input {
            tcp {
                port => 5000
            }

            beats {
                port => 5000
            }

        }

        ## Add your filters / logstash plugins configuration here

        output {
            elasticsearch {
                hosts => "elasticsearch:9200"
                user => "elastic"
                password => "changeme"
            }
        }
 
 
 ## FileBeats configurations
 
 Filebeats config at docker-compose.yml:
 
       filebeat:
        image: docker.elastic.co/beats/filebeat:7.5.0
        volumes:
          - ./FileBeats/filebeat.yml:/usr/share/filebeat/filebeat.yml
        restart: on-failure
        networks:
          - elk
        depends_on:
          - logstash
 
 
 filebeat.yml file under ./FileBeats folder
 
     filebeat:
      inputs:
        -
          paths:
            - /logs/*.log
          input_type: log
    output:
      logstash:
        hosts: ["logstash:5000"]

 ## Kibana:
 Kibana is installed using docker-compose and available at:
 
    http://localhost:5601/
    
    
Username: elastic
password: changeme
    
    
   ### Kibana elastic index configure:
    1. Create an index pattern like "logstash-2020.02.02-000001" in kibana index creation.
    2. View logs in discover.
    
 
 
 # References
 
    https://www.elastic.co/guide/en/kibana/current/settings.html
    https://howtodoinjava.com/microservices/elk-stack-tutorial-example/
    https://www.javainuse.com/spring/springboot-microservice-elk
    https://github.com/deviantony/docker-elk
    
