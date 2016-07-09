"""samridhdhi URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.9/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin

from students.models import Student
from django.conf.urls import url, include
from django.contrib.auth.models import User
from rest_framework import routers, serializers, viewsets
from samridhdhi import views

# Serializers define the API representation.
class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ('url', 'username', 'email', 'is_staff')

# ViewSets define the view behavior.
class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer

# # Serializers define the API representation.
# class StudentSerializer(serializers.HyperlinkedModelSerializer):
#     class Meta:
#         model = Student
#         fields = ('name', 'gender', 'age')

class StudentSerializer(serializers.Serializer):
	id = serializers.ReadOnlyField()
	name = serializers.CharField()
	gender = serializers.CharField()
	age = serializers.IntegerField()
	language = serializers.CharField()
	location = serializers.CharField()
	occupation = serializers.CharField()
	dob = serializers.CharField()
	contactNumber = serializers.CharField()
	idProof = serializers.CharField()
	parentIncome = serializers.CharField()

	def create(self, validated_data):
		return Student.objects.create(**validated_data)

	def update(self, instance, validated_data):
		instance.gender = validated_data.get('gender', instance.gender)
		instance.save()
		return instance


# ViewSets define the view behavior.
class StudentViewSet(viewsets.ModelViewSet):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer

# Routers provide an easy way of automatically determining the URL conf.
router = routers.DefaultRouter()
router.register(r'users', UserViewSet)
router.register(r'student', StudentViewSet)



urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^updatephonenumber/1/$', views.relocateStepOne, name='relocate'),
    url(r'^updatephonenumber/2/$', views.relocateStepTwo, name='relocate'),
    url(r'^admin/', admin.site.urls),
    url(r'^api/v1/', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework'))
]
