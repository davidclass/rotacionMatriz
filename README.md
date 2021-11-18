# rotacionMatriz
#Para su ejecutar en un contenedor:
# [1] : Descargar el respositorio
# [2] : Abrir una terminal de comando 
# [3] : Ubicarse en la raiz del proyecto y ejecutar los siguientes comandos:
  > docker build -t backend-apirest:v1 .
  > docker pull openjdk:8
  > docker run -p 8080:8080 --name rotarMatriz  -d backend-apirest:v1
