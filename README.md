### EasyAR STS

EasyAR STS (Security Token Service) provide finer grained and temporary access controlling service

### API URL

URL: https://uac.easyar.com 

### API Reference

1. Get Security Token

    POST /token/v2
    
    Request:
    
    ```
    {
        "apiKey": "1ab2c85790aefd1eeecdacf030211cf252",   
        "expires": 3600,    // seconds
        "timestamp": 1550469858000,  // millisecond
        "acl": [{
            "service": "ecs:spatialmap", // ecs(EasyAR Cloud Service)
            "effect": "Allow",  //  Allow | Deny
            "resource": [
                "8fa9316f19e84ss09f9f5f54a5ecd29aaa"  // resource (spatialmap space) uuid
            ],
            "permission": ["READ"]    // READ | WRITE
        }] 
        "signature": "6e71edb158...b206acf7eb"  
    }
    ```
    
    Response:
    
    ```
    {
        "statusCode": 0,
        "timestamp": 1561537463960,
        "msg": "Success",
        "result":{
            "apiKey": "0d071e81070e2bdcf2dc1b0fb536ea32",
            "expires": 864000,
            "token": "rWHMIM5u+9...MDE4slLeyOJ87jstfQ==",   
            "expiration": "2019-07-06T08:24:23.960+0000"    
        }
    }

    ```
         
    Error response:
    
    ```
    {
        "msg": "api key invalid",
        "result": "",
        "statusCode": 4001011,
        "timestamp": 1561537582902
    }
    ```

