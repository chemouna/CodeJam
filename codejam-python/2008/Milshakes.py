

for case in range(1, int(raw_input()) + 1):  # For all test cases
    shakes = int(raw_input()) * [0]  # Get shake size and create unmalted list
    customers = []  # Start empty customer list

    for i in range(int(raw_input())):  # For all customers
        custList = map(int, raw_input().split(" "))  # Get the preferences
        customer = []
        for j in range(custList.pop(0)):  # First value is number of shakes
            flavor = custList.pop(0) - 1  # First pair element is flavor index
            malted = custList.pop(0)  # Second element is malted preference
            customer.append(flavor, malted)  # Add preference to customer

        customers.append(customer)  # When done, add customer to list

    impossible = False
    solved = False
    while not impossible and not solved:  # While not finished
        redo = False
        for customer in customers:  # Examine all customers
            unsatisfied = []
            for flavor, malt in customer:  # Examine all their preferences
                if shakes[flavor] == malt:  # If satisfied, move to next customer
                    unsatisfied = []
                    break
                else:  # If unsatisfied, take note of it
                    unsatisfied.append([flavor, malt])

            for flavor, malt in unsatisfied:  # Check unsatisfied flavors
                if malt == 1 and shakes[flavor] == 0:  # Look for a possible malted preference
                    shakes[flavor] = 1  # Attend the malted preference
                    redo = True  # Restart checking customers
                    break

            if redo:
                break

            if len(unsatisfied) > 0:  # If we've reached here, all insatisfactions are unmalted
                impossible = True  # Then we can't solve it
                break

        if not redo:  # If we don't need to look into customers again
            solved = True  # Problem was solved (might still be impossible)

    result = "IMPOSSIBLE" if impossible else " ".join(map(str, shakes))  # Decide result
    print "Case #%d: %s" % (case, result)  # Print the result
