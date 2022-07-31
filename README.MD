# Meteo Station
## _Rest API_



## POST

- /sensor/registration
 ```sh
{
    "name": "sensor"
}
```
- /measurements/add
 ```sh
{
    "value": 25.5,
    "raining": true,
    "sensor":{
    "name": "sensor"
    }
}
```

## GET
- /measurements
- /measurements/rainyDaysCount
