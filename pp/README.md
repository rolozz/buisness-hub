# О чем проект ?
Personal account of a businessman.
With an online aggregator platform, it will be possible
to issue accounting services
for ordering other services for business

# Как запустить проект ?
1. Сменить версию Java в IDE на 17. Settings --> ProjectStructure --> SDK - 17.
2. Сделайте Build всего проекта. Для этого справа в IDE нажимаем на 
gradle Tasks --> build --> build (если будут ошибки - ничего страшного)
3. Перейдите в модуль [ConfigServer](ConfigServer) и прочитайте README.MD
4. В панели запуска переместите модуль Eureka / EurekaServerApplication в самое начало,
он должен запускаться первым.
5. Запустите [docker-compose-run_db.yaml](docker-compose-run_db.yaml) 
6. Запустите весь проект

# Как запустить один модуль ?
В каждом модуле есть свой docker-compose.yaml - запустите его
