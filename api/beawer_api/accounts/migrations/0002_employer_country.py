# Generated by Django 4.1.4 on 2022-12-19 07:45

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='employer',
            name='country',
            field=models.CharField(blank=True, max_length=255, null=True),
        ),
    ]
