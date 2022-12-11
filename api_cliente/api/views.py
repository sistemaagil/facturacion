from rest_framework import viewsets

from api.models import Cliente, TipoCliente
from api.serializers import ClienteSerializer, TipoClienteSerializer

from rest_framework.decorators import action
from rest_framework.request import Request
from rest_framework.response import Response
import requests

class ClienteViewSet ( viewsets.ModelViewSet ):
    serializer_class = ClienteSerializer
    queryset = Cliente.objects.all()
    
    @action(detail=True, methods=['patch'])
    def completardatos(self, request: Request, *args, **kwargs):
        #Extrae el ID del cliente enviado en la url
        id = kwargs['pk']
        # Consulta de base de datos el ID solicitado
        cliente = Cliente.objects.get(pk=id)
        # Consulta de la API externa datos según el identificador proporcionado 
        response = requests.get("https://jsonplaceholder.typicode.com/users/"+id)
        # En caso de una respuesta satisfactoria de la api externa, se procede a completar los datos
        # en base de datos y se retorna la respuesa al cliente
        if response.status_code == 200:
            data = response.json()
            cliente.email = data['email']
            cliente.phone = data['phone']
            cliente.save()
            # Prepara un serializador para mapear la entidad a JSON
            serializer = ClienteSerializer(cliente)
            # Regresa una respuesta http con la nueva data del cliente
            return Response(serializer.data, content_type="application/json")
        else:
            # si hubo un error en la consulta, devolver un mensaje de error
            return Response("Error en la consulta a la API externa", status=500)

class TipoClienteViewSet ( viewsets.ModelViewSet ):
    serializer_class = TipoClienteSerializer
    queryset = TipoCliente.objects.all()
    