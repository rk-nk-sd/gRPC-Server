package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8081)
                .addService(new GreetingServiceImpl())
                //добавляем столько сервисов addService - сколь необходимо
                .build();

        //запуск сервера
        server.start();

        System.out.println("Server started!");

        //что бы программа не завершалась
        server.awaitTermination();
    }
}
