package org.example;


import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

/*
Унаследуемся от файла сгенерированного prototype-ом
 */
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    // В GreetingServiceImplBase - есть метод который необходимо переопределить

    // В GreetingServiceOuterClass - находятся HelloRequest и HelloResponse
    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver){
        // StreamObserver - позволят превратить ответ в поток ответов используя onNext, и используя http версии 2.0
        System.out.println(request);

        //Строим билдер ответа на запрос
        GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.HelloResponse
                .newBuilder()
                //конструируем ответ сервера см. GreetingService.proto->HelloResponse где есть поле greeting
                .setGreeting("Hello from server: "  + request.getName())
                .build();
        responseObserver.onNext(response);

        //после завершения обмена закрываем соединение
        responseObserver.onCompleted();
    }
}
