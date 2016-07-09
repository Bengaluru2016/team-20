from django.shortcuts import render, redirect, HttpResponse
from django.contrib.auth.decorators import login_required
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login, logout


def index(request):
	return render(request, "ui/index.html")

def relocateStepOne(request):
	print request
	return HttpResponse(status=200)

def relocateStepTwo(request):
	print request
	return HttpResponse(status=404)	
  	