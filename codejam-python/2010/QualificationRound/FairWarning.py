# Qualification round - Problem B: Fair Warning


def gcd(x, y):
    while x:
        x, y = y % x, x
    return y


testCases = open("warning.in").readlines()
nbCases = int(testCases[0])
for t in range(1, nbCases + 1):
    tc = testCases[t]
    tc = map(lambda x: int(x), tc.split(" ")[1:])
    if len(tc) == 0:
        break

    tc.sort()

    diff = []
    for i in range(len(tc) - 1):
        diff += [(tc[i + 1] - tc[i])]

    T = diff[0]
    for d in diff[1:]:
        T = gcd(T, d)

    print "Case #%d: %d" % (t, (-tc[0] % T))
