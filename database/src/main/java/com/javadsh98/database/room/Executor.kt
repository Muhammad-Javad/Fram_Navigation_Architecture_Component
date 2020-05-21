package com.javadsh98.database.room

import java.util.concurrent.Executors

val executor = Executors.newSingleThreadExecutor()

fun ioThread(run: ()->Unit){
    executor.execute(run)
}