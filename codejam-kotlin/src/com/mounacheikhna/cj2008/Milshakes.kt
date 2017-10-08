package com.mounacheikhna.cj2008

import com.sun.org.apache.xpath.internal.operations.Bool
import java.io.PrintWriter
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.IOException
import java.util.*
import java.util.ArrayList

class Milshakes {

    lateinit var input: BufferedReader
    lateinit var tokenizer: StringTokenizer
    lateinit var out: PrintWriter

    private fun solve() {
        try {
            input = BufferedReader(InputStreamReader(System.`in`))
            tokenizer = StringTokenizer("")
            out = PrintWriter(System.out)

            val cases = nextInt()

            for (c in 0..cases) {
                val n = nextInt() //nb of milkshake flavors
                val m = nextInt() //nb of customers

                val ans = solveCase(m, n)

                if (ans.all { !it }) {
                    out.println("Case #" + (c + 1) + ": IMPOSSIBLE")
                }
                else {
                    printPossibleCaseResults(c, ans)
                }
            }

            out.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
            System.exit(-1)
        }
    }

    private fun printPossibleCaseResults(c: Int, ans: BooleanArray) {
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

    //TODO: rename
    private fun gatherInput(m: Int): List<CustomerInput> {
        val inp = mutableListOf<CustomerInput>()
        for (i in 0 until m) {
            val t = nextInt()
            val likes = mutableListOf<Pair<Int, Int>>()
            for (j in 0 until t) {
                val u = nextInt() - 1
                val v = nextInt()
                likes.add(Pair(u, v))
            }
            inp.add(CustomerInput(t, likes))
        }
        return inp
    }

    data class CustomerInput(val nbLikes: Int, val likes: List<Pair<Int, Int>>)

    private fun solveCase(m: Int, n: Int): BooleanArray {
        val a = array2dOfBoolean(m, n) // ? array of customers  milshakes flavors where a[i][j] is true when customer i is assigned a unmalted milshake j
        val l = IntArray(m)
        val b = IntArray(m) // contains whether a customer has a malted milshake
        Arrays.fill(b, -1) // so if b[i] is != -1 => customer i has a malted milshake of index b[i]
        val st = ArrayList<Int>()
        val ans = BooleanArray(n)

        val customers = gatherInput(m)
        customers.forEachIndexed { i, (_, likes) ->
            likes.forEach { (u, v) ->
                if (v == 1) { // malted
                    b[i] = u
                } else {
                    if (!a[i][u]) { // if customer i is not assigned already the milshake he likes and since he wants unmalted flavor we increase nb of unmalted flavor for customer i
                        l[i]++ //increased nb of unmalted batches
                    }
                    a[i][u] = true
                }
            }
            if (l[i] == 0) {
                st.add(i)
            }
        }

        while (st.size > 0) {
            val i = st.removeAt(st.size - 1)
            if (b[i] == -1) {
                //TODO : make an inner function and make it return false in this case then go to next case if false is returned
                return ans
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
        return ans
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

    private fun array2dOfInt(sizeOuter: Int, sizeInner: Int): Array<IntArray>
            = Array(sizeOuter) { IntArray(sizeInner) }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val milshakes = Milshakes()
            milshakes.solve()
        }
    }

}