# -*- coding: utf-8 -*-
# Generated by Django 1.9.7 on 2016-07-09 11:05
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Student',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=30)),
                ('gender', models.CharField(max_length=30)),
                ('age', models.IntegerField()),
                ('language', models.CharField(max_length=30)),
                ('location', models.CharField(max_length=30)),
                ('occupation', models.CharField(max_length=30)),
                ('dob', models.CharField(max_length=30)),
                ('contactNumber', models.CharField(max_length=30)),
                ('idProof', models.CharField(max_length=30)),
                ('parentIncome', models.CharField(max_length=30)),
            ],
        ),
    ]
