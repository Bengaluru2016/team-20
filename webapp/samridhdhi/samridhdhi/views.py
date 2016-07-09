from django.shortcuts import render, redirect, HttpResponse
from django.contrib.auth.decorators import login_required
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login, logout


def index(request):
	return render(request, "ui/index.html")

def relocateStepOne(request):
	print request
	response = HttpResponse("Success", status=200)
	response['Content-Type'] = 'text/plain'
	response['Content-Length'] = 4
	response['Body'] = 'Test body'
	return response

def relocateStepTwo(request):
	print request
	response = HttpResponse("Failure", status=404)
	response['Content-Type'] = 'text/plain'
	return response
  	  	


def communityMobilizer(request):
	return render(request, "ui/communitymobilizer.html")

def studentProfile(request):
	return render(request, "ui/profile.html")

def registerStudent(request):
	return render(request, "ui/forms.html")
