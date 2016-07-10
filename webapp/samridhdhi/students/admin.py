from django.contrib import admin

from students.models import Student

class studentAdmin(admin.ModelAdmin):
	# list_display = ['name', 'gender', 'age', 'language', 'location', 'occupation', 'db', 'contactNumber', 'idProof', 'parentIncome']
	class Meta:
		model = Student
admin.site.register(Student, studentAdmin)
