package cinema

fun calcProfit(rows:Int,cols:Int):Int{
    val seats = rows * cols
    return when{
         seats <= 60 -> seats*10
         else -> {
             if (rows % 2 == 0) return ((seats/2)*10 + (seats/2)*8)
             else {
                 val half = rows/2
                 return ((half*cols)*10 + (seats-(half*cols))*8 )
             }
         }
    }
}

fun ticketPrice(rows:Int,totalRows:Int,totalCols:Int):Int{
    val totSeats = totalCols * totalRows
    return when{
        totSeats <= 60 -> 10
        else -> {
            if (rows <= (totalRows/2)) 10
            else 8
        }
    }
}

fun displayCinema(grid:MutableList<MutableList<Char>>,totalCols:Int,totalRows: Int){
    println()
    println("Cinema:")
    print(" ")
    for(i in 0 until totalCols) {
        print(" ${i+1}")
    }
    println()
    for (i in 0 until  totalRows){
        print(i+1)
        print(" ")
        println(grid[i].joinToString(" "))
    }
}

fun main() {

    println("Enter the number of rows:")
    val totalRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val totalCols = readln().toInt()
    val seatGrid = MutableList(totalRows){ MutableList(totalCols) { ('S') } }
    var ticketsPurchased = 0
    var currentIncome = 0


    while (true){
        println()
        println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit\n")
        val choice = readln().toInt()
        when(choice){
            1 ->  displayCinema(seatGrid,totalCols,totalRows)

            2 -> {
                while(true) {
                    println("Enter a row number:")
                    val rows = readln().toInt()
                    println("Enter a seat number in that row:")
                    val cols = readln().toInt()
                    if(rows > totalRows || cols > totalCols){
                        println("Wrong input!")
                        continue
                    }
                    if (seatGrid[rows - 1][cols - 1] == 'B') {
                        println("That ticket has already been purchased!\n")
                        continue
                    }else{
                        println()
                        println("Ticket price: $${ticketPrice(rows, totalRows, totalCols)}")
                        seatGrid[rows - 1][cols - 1] = 'B'
                        ticketsPurchased++
                        currentIncome += ticketPrice(rows, totalRows, totalCols)
                        break
                    }
                }
            }

            3 -> {
                println("Number of purchased tickets: $ticketsPurchased")
                try{
                    val percentage:Double = (ticketsPurchased.toDouble()/(totalRows*totalCols).toDouble())*100
                    val formatPercentage = "%.2f".format(percentage)
                    println("Percentage: $formatPercentage%")
                }catch (e: Exception){
                    val percentage = 0.00
                    val formatPercentage = "%.2f".format(percentage)
                    println("Percentage: $formatPercentage%")
                }
                println("Current Income: $${currentIncome}")
                println("Total income: \$${calcProfit(totalRows,totalCols)}")
            }

            0 -> break
        }
    }
}
