from django.db import models

class Student(models.Model):
	"""
	Description: Model Description
	"""
	name = models.CharField(max_length=30)
	gender = models.CharField(max_length=30)
	age = models.IntegerField()
	language = models.CharField(max_length=30)
	location = models.CharField(max_length=30)
	occupation = models.CharField(max_length=30)
	dob = models.CharField(max_length=30)
	contactNumber = models.CharField(max_length=30)
	idProof = models.CharField(max_length=30)
	parentIncome = models.CharField(max_length=30)
	fatherName = models.CharField(max_length=30)
	motherName = models.CharField(max_length=30)
	nativePlace = models.CharField(max_length=30)
	address = models.CharField(max_length=30)
	alternateContact = models.CharField(max_length=30)

	def __unicode__(self):
		return self.name