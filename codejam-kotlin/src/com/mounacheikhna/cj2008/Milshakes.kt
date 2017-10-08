package com.mounacheikhna.cj2008

import java.io.PrintWriter
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.IOException
import java.util.*
import java.util.ArrayList

class Milshakes {

    lateinit var input: BufferedReader
    lateinit var tokenizer: StringTokenizer

    private fun solve() {
        try {
            input = BufferedReader(InputStreamReader(System.`in`))
            tokenizer = StringTokenizer("")
            val out = PrintWriter(System.out)

            val cases = nextInt()

            for (c in 0..cases) {
                val n = nextInt() //nb of customers
                val m = nextInt() //nb of milshakes
                val a = array2dOfBoolean(n, m)
                val l = IntArray(m)
                val b = IntArray(m)
                Arrays.fill(b, -1)
                val st = ArrayList<Int>()
                val ans = BooleanArray(n)
                for (i in 0 until m) {
                    val t = nextInt() //nb of milshakes a customer likes
                    for (j in 0 until t) { //for each we get what type the customer likes and if it is malted or unmalted
                        val u = nextInt() - 1 //since milshakes array start with 0 we do -1 to get it
                        val v = nextInt() // whether its malted or not
                        if (v == 1) { // malted
                            b[i] = u
                        } else {
                            if (!a[i][u]) {
                                l[i]++ //increased nb of unmalted batches
                            }
                            a[i][u] = true//b //? should it be true ?
                        }
                    }
                    if (l[i] == 0) {
                        st.add(i)
                    }
                }

                while (st.size > 0) {
                    val i = st.removeAt(st.size - 1)
                    if (b[i] == -1) {
                        out.println("Case #" + (c + 1) + ": IMPOSSIBLE");
                        break //TODO : make an inner function and make it return false in this case then go to next case if false is returned
                    }
                    ans[b[i]] = true

                    for (j in 0 until m) {
                        if (a[j][b[i]]) {
                            a[j][b[i]] = false //!Ъ //?
                            l[j]--
                            if (l[j] == 0) {
                                st.add(j)
                            }
                        }
                    }
                }
                out.print("Case #" + (c + 1) + ": ")

                ans.forEach { v ->
                    if (v) {
                        out.println(1.toString() + " ")
                    } else {
                        println(0.toString() + " ")
                    }
                }

                out.println()
            }

            //solveCase()
            out.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
            System.exit(-1)
        }
    }

    @Throws(IOException::class)
    private fun nextInt(): Int {
        return Integer.parseInt(next())
    }

    @Throws(IOException::class) private
    operator fun next(): String? {
        while (!tokenizer.hasMoreTokens()) {
            val line = input.readLine() ?: return null
            tokenizer = StringTokenizer(line)
        }
        return tokenizer.nextToken()
    }

    private fun array2dOfBoolean(sizeOuter: Int, sizeInner: Int): Array<BooleanArray>
            = Array(sizeOuter) { BooleanArray(sizeInner) }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val milshakes = Milshakes()
            milshakes.solve()
        }
    }

}