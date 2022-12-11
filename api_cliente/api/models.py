from django.db import models

class TipoCliente (models.Model):
    tipo = models.CharField(max_length=80)

class Cliente (models.Model):
    razon_social = models.CharField(max_length=80)
    nro_identificacion = models.IntegerField()
    fecha_nacimiento = models.DateField(auto_now=False, auto_now_add=False)
    monto_primera_compra = models.FloatField()
    tipo = models.ForeignKey(TipoCliente, on_delete=models.PROTECT)
    email = models.CharField(max_length=80)
    phone = models.CharField(max_length=80)
# otros modelos...
