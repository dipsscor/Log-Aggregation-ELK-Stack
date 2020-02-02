# Log Aggregation for Spring boot application using ELK Stack and Log4j(Without beats)

Sample Springboot Application demonstrating Log Aggregation using ELK Stack and Log4j. This demonstration does not include log forwarding using Beats.

License : [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)



# Prerequsites:
	JAVA Version = 8 or higher
	Compute : CPU 4
	Memory : 8GB or higher
	Docker Enviornment available
    Docker enviornment with cache clear - network , volumes , images , containers.
    
    
# Preface:

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

        }

        ## Add your filters / logstash plugins configuration here

        output {
            elasticsearch {
                hosts => "elasticsearch:9200"
                user => "elastic"
                password => "changeme"
            }
        }
 
 
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
    
