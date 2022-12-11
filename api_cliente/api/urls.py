from rest_framework.routers import DefaultRouter
from api.views import ClienteViewSet, TipoClienteViewSet

router = DefaultRouter()

router.register('api/cliente', ClienteViewSet)
router.register('api/tipocliente', TipoClienteViewSet)


urlpatterns = []

urlpatterns += router.urls