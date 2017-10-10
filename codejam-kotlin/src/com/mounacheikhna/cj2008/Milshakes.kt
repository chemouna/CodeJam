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

                val customers = gatherInput(m)
                val ans = solveCase(m, n, customers)

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

    private fun solveCase(m: Int, n: Int, customers: List<CustomerInput>): BooleanArray {
        val a = array2dOfBoolean(m, n) // ? array of customers  milshakes flavors where a[i][j] is true when customer i is assigned a unmalted milshake j
        val countLikesUnmalted = IntArray(m)
        val likesMalted = IntArray(m) // contains whether a customer has a malted milshake
        Arrays.fill(likesMalted, -1) // so if b[i] is != -1 => customer i has a malted milshake of index b[i]
        val st = ArrayList<Int>()
        val ans = BooleanArray(n)

        customers.forEachIndexed { i, (_, likes) ->
            likes.forEach { (u, v) ->
                if (v == 1) { // malted
                    likesMalted[i] = u
                } else {
                    if (!a[i][u]) { // if customer i is not assigned already the milshake he likes and since he wants unmalted flavor we increase nb of unmalted flavor for customer i
                        countLikesUnmalted[i]++ //increased nb of unmalted batches
                    }
                    a[i][u] = true
                }
            }
            if (countLikesUnmalted[i] == 0) {
                st.add(i)
            }
        }

        while (st.size > 0) {
            val i = st.removeAt(st.size - 1)
            if (likesMalted[i] == -1) {
                // they must be unsatisfied because none of their prefered
                // MALTED flavours have been made
                return ans //? not sure of this at all
            }
            ans[likesMalted[i]] = true

            for (j in 0 until m) {
                if (a[j][likesMalted[i]]) {
                    a[j][likesMalted[i]] = false //!Ъ //?
                    countLikesUnmalted[j]--
                    if (countLikesUnmalted[j] == 0) {
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