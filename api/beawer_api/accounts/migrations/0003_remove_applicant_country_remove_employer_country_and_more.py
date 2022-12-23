# Generated by Django 4.1.4 on 2022-12-20 19:39

from django.db import migrations, models
import django.utils.timezone


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0002_employer_country'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='applicant',
            name='country',
        ),
        migrations.RemoveField(
            model_name='employer',
            name='country',
        ),
        migrations.RemoveField(
            model_name='employer',
            name='description',
        ),
        migrations.RemoveField(
            model_name='employer',
            name='title',
        ),
        migrations.AddField(
            model_name='applicant',
            name='birth_date',
            field=models.DateTimeField(default=django.utils.timezone.now),
        ),
        migrations.AddField(
            model_name='applicant',
            name='full_name',
            field=models.CharField(blank=True, max_length=100, null=True),
        ),
        migrations.AddField(
            model_name='category',
            name='image',
            field=models.ImageField(blank=True, null=True, upload_to='static/img/'),
        ),
        migrations.AddField(
            model_name='employer',
            name='birth_date',
            field=models.DateTimeField(default=django.utils.timezone.now),
        ),
        migrations.AddField(
            model_name='employer',
            name='full_name',
            field=models.CharField(blank=True, max_length=100, null=True),
        ),
    ]