package tictactoe

import java.util.*

val scanner = Scanner(System.`in`)

fun main() {

    Field.printEmptyField()
    print(Field.toMove())
}

object Field {
    val arr2d = Array(3) { IntArray(3) }

    fun toMove(): String {
        print("Enter the coordinates: ")
        var check = true
        var playerX = true
        while (check) {
            try {

                while (playerX) {

                    val x = scanner.next()
                    val y = scanner.next()

                    if (arr2d[x.toInt() - 1][y.toInt() - 1] != 0) {
                        println("This cell is occupied! Choose another one!")
                        println("Enter the coordinates: ")

                    } else {
                        arr2d[x.toInt() - 1][y.toInt() - 1] = 1
                        playerX = false
                        showBoard()
                    }

                }
                val win = checkWin()
                if (win != 0) return if (win == 1) "O wins" else "X wins"
                val arr1d = arr2d.reduce { acc, ints -> acc + ints }
                if (!arr1d.contains(0))
                    check = false
                if (!playerX) {
                    val x = scanner.next()
                    val y = scanner.next()
                    if (arr2d[x.toInt() - 1][y.toInt() - 1] != 0) {
                        println("This cell is occupied! Choose another one!")
                        println("Enter the coordinates: ")

                    } else {
                        arr2d[x.toInt() - 1][y.toInt() - 1] = -1
                        playerX = true
                        showBoard()
                    }
                }
            } catch (e: NumberFormatException) {
                print("You should enter numbers!")
            } catch (e: IndexOutOfBoundsException) {
                print("Coordinates should be from 1 to 3!")
            }

            val win = checkWin()
            if (win != 0) return if (win == 1) "O wins" else "X wins"
            val arr1d = arr2d.reduce { acc, ints -> acc + ints }
            if (!arr1d.contains(0))
                check = false
        }
        return "Draw"
    }

    fun checkWin(): Int {
        for (i in 0..2) {
            if (arr2d[i][0] == arr2d[i][1] && arr2d[i][0] == arr2d[i][2]) return arr2d[i][0]
            if (arr2d[0][i] == arr2d[1][i] && arr2d[0][i] == arr2d[2][i]) return arr2d[0][i]
        }
        if (arr2d[0][0] == arr2d[1][1] && arr2d[0][0] == arr2d[2][2]) return arr2d[0][0]
        if (arr2d[0][2] == arr2d[1][1] && arr2d[0][2] == arr2d[2][0]) return arr2d[0][2]

        return 0
    }

    fun showBoard() {
        val t = "X O"
        println("---------")
        for (i in 0..2) {
            print("| ")
            for (j in 0..2) {
                print("${t[arr2d[i][j] + 1]} ")
            }
            println("|")
        }
        println("---------")
    }

    fun printEmptyField() {
        val field = """
            ---------
            |       |
            |       |
            |       |
            ---------
        """.trimIndent()
        println(field)
    }
}
