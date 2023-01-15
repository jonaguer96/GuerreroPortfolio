#import the libraries 
import numpy as np
import pandas as pd 
from datetime import date

#Create Empty Lists
GOODS_OR_SERVICES = []
PRICES = []
DATES = []
EXPENSE_TYPES = []

#Create a function to add expenses to the list and opranize the data
def add_expense(good_or_service, price, date, expense_type):
	GOODS_OR_SERVICES.append(good_or_service)
	PRICES.append(price)
	DATES.append(date)
	EXPENSE_TYPES.append(expense_type)

#Main program
option = -1 #This will be the users input
while(option != 0):
	#Create the option menu
	print('Welcome to the simple expense tracker:')
	print('1. Add Food Expense')
	print('2. Add Household Expense')
	print('3. Add Transportation Expense')
	print('4. Show and Save the Expense Report')
	print('0. Exit')

	option = int(input('Choose an option:\n'))

	#Print a new line
	print()
	#Check for users input
	if option == 0:
		print('Exiting the program')
		break
	elif option == 1:
		print('Adding Food')
		expense_type = 'FOOD'
	elif option == 2:
		print('Adding Household')
		expense_type = 'HOUSEHOLD'
	elif option == 3:
		print('Adding Transportation')
		expense_type = 'TRANSPORTATION'
	elif option == 4:
		#Create a data frame and add expenses
		expense_report = pd.DataFrame()
		expense_report['GOODS_OR_SERVICES'] = GOODS_OR_SERVICES
		expense_report['PRICES'] = PRICES
		expense_report['DATES'] = DATES
		expense_report['EXPENSE_TYPES'] = EXPENSE_TYPES
		#Save the expense report
		expense_report.to_csv('expenses.csv')
		#Show the expense report
		print(expense_report)
	else:
		print('YOu chose an unavailable option. Please choice between 0, 1, 2, 3, or 4')

		#Allow the user to enter the good or service and the price 

	if option == 1 or option == 2 or option == 3:
		good_or_service = input('Enter the good or service for the expense type '+expense_type+':\n')
		price = float(input('Enter the price of the good or service:\n '))
		today = date.today()
		add_expense(good_or_service, price, today, expense_type)

		#Print a new line
		print()
