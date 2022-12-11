from rest_framework import viewsets

from api.models import Cliente, TipoCliente
from api.serializers import ClienteSerializer, TipoClienteSerializer

class ClienteViewSet ( viewsets.ModelViewSet ):
    serializer_class = ClienteSerializer
    queryset = Cliente.objects.all()


class TipoClienteViewSet ( viewsets.ModelViewSet ):
    serializer_class = TipoClienteSerializer
    queryset = TipoCliente.objects.all()