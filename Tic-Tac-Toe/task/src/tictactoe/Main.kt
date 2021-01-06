package tictactoe

import java.util.*

object Game {
    private var cells = "         ".toCharArray()
    private var countWin = 0
    private var winString = ""
    private var next = 'X'
    private val scanner = Scanner(System.`in`)

    fun init() {
        printTable()
        makeMove()
    }
    private fun makeMove() {
        print("Enter the coordinates:")
        var x = scanner.next()
        var y = scanner.next()
        var move = ("$x $y").split(" ")
                .map {
                    var coord = it.toIntOrNull()
                    when (coord) {
                        !is Int -> {
                            println("You should enter numbers!")
                            makeMove()
                            return
                        }
                        !in 1..3 -> {
                            println("Coordinates should be from 1 to 3!")
                            makeMove()
                            return
                        }
                        else -> coord - 1
                    }
                }.toTypedArray()
        val xCoordinate = move[1]
        val yCoordinate = move[0]

        if (cells[xCoordinate + 3 * yCoordinate] != ' ') {
            println("This cell is occupied! Choose another one!")
            makeMove()
            return
        } else {
            cells[xCoordinate + 3 * yCoordinate] = next
            next = if ( next == 'X') 'O' else 'X'
            if (!checkWinner()) makeMove()
        }

    }

    private fun checkWinner(): Boolean {

        val winCoordinate = arrayOf(
                intArrayOf(0, 1, 2),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8),
                intArrayOf(0, 3, 6),
                intArrayOf(1, 4, 7),
                intArrayOf(2, 5, 8),
                intArrayOf(0, 4, 8),
                intArrayOf(2, 4, 6))

        for (elem in winCoordinate) {
            if (cells[elem[0]] == cells[elem[1]] && cells[elem[0]] == cells[elem[2]] && cells[elem[0]] != ' ') {
                countWin += 1
                winString = cells[elem[0]].toString()
            }
        }

        if (countWin == 1) {
            printTable()
            println("$winString wins")
            return true
        } else if (countWin == 0 && !cells.contains(' ')) {
            printTable()
            println("Draw")
            return true
        }
        printTable()
        return false
    }


    private fun printTable() {
        val field = """
            ---------
            | ${cells[0]} ${cells[1]} ${cells[2]} |
            | ${cells[3]} ${cells[4]} ${cells[5]} |
            | ${cells[6]} ${cells[7]} ${cells[8]} |
            ---------
        """.trimIndent()
        println(field)
    }
}

fun main() {
    Game.init()
}